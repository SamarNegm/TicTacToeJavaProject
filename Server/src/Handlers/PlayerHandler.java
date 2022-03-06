/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Models.Player;
import Shared.JSONHandeling;
import Shared.Requests;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author MrMr
 */
public class PlayerHandler extends Thread{
 
    public Socket playerSocket;
    private Player player;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    
    private String errorMsg;
    private volatile JSONObject forwardedRequest;
    private JSONObject jsonObj;
    
    private volatile boolean isStayAlive = true;
     private static Vector <PlayerHandler> onlinePlayerHandlers = new Vector <>();
     
         public PlayerHandler(Socket socket, Player playerInfo)
    {
        this.playerSocket = socket;
        this.player = playerInfo;
                
        jsonObj = new JSONObject();

        
        try {
            
            //Create the input and output channels
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            //Add to the vector of handlers
            onlinePlayerHandlers.add(this);    
            
            //Start the thread to accept requests
            this.start();
            
        } catch (IOException ex) {
            
        
            close();
        }
    }

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public void run()
    {
        //Listen to the requests of the players
        while (isStayAlive)
        {
            try {


                if (jsonObj != null)
                {
                    //send response to the user               
                    outputStream.writeUTF(jsonObj.toString());
                }  
                //Connection Drop
            } catch (IOException ex) { 
             
                close();
                
            } catch (Exception ex) {
                System.out.println(ex);
            } 
        }
    }
        //Players Requests handeling
    
}