/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import static Handlers.AuthenHandler.stmt;
import Models.Player;
import Shared.DataBaseHandler;
import Shared.JSONHandeling;
import Shared.Requests;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author MrMr
 */
public class ActiveUsersHandler extends Thread{
    private Vector <Player> activPlayers;
    private Vector <PlayerHandler> handlersVect;

    public Vector<Player> getActivPlayers() {
        return activPlayers;
    }

    public void setActivPlayers(Vector<Player> activPlayers) {
        this.activPlayers = activPlayers;
    }
    public ActiveUsersHandler()
    {
        this.start();
    }
         @Override
    public void run()
    {
        while (true)
        {
            //Check if the db has changed from the last iteration

              
                //update players list
            
                
             if(DataBaseHandler.isDBChanged())
            {
                System.out.println("new change********");
                //update players list
                activPlayers = PlayerHandler.getAllOnlinePlayers();
               
                if (activPlayers == null)
                {
                    activPlayers = new Vector();
                }
                 for(int i=0;i<activPlayers.size();i++)
                     System.out.println("active users... "+activPlayers.elementAt(i).getUsername());
                //update handlers
                handlersVect = PlayerHandler.getOnlinePlayerHandlers();

                //Notify all players
                notifyNewPlayerList(activPlayers);
            }
            
            
           // if not skip this iteration
            try {
                sleep(300);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    public Vector <Player> getCurrentPlayerList()
    {
        return activPlayers;
    }
    
    private void notifyNewPlayerList(Vector <Player> playerList)
    {
        System.out.println("notifing,,,");
        //construct json array
        JSONArray jsonArray = JSONHandeling.playerListToJSONArray(playerList);
        JSONObject json = new JSONObject();

        json.put("type", Requests.UPDATE_ACTIVE_USERS);
        json.put("list", jsonArray);
        

        //Broadcast the json array
        for(PlayerHandler handler: handlersVect)
        {
            try {
                handler.getOutputStream().writeUTF(json.toString());
                System.out.println(json.toJSONString());
            } catch (IOException ex) {
                
                //Client has dropped remove this client
                handler.close();
            }
        }
    }
    
    public void close()
    {
        this.stop();
    }


}
