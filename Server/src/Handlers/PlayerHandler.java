/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataBase.DataBase;
import Models.Player;
import static Shared.DataBaseHandler.setIsDBChanged;
import Shared.JSONHandeling;
import Shared.Requests;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author MrMr
 */
public class PlayerHandler extends Thread {

    public Socket playerSocket;
    private Player player;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private String errorMsg;
    private volatile JSONObject forwardedRequest;
    private JSONObject jsonObj;

    private volatile boolean isStayAlive = true;
    private static Vector<PlayerHandler> onlinePlayerHandlers = new Vector<>();
 

 

  


    public PlayerHandler(Socket socket, Player playerInfo) {
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

    public static Vector<PlayerHandler> getOnlinePlayerHandlers() {
        return onlinePlayerHandlers;
    }



    public static void setOnlinePlayerHandlers(Vector<PlayerHandler> onlinePlayerHandlers) {
        PlayerHandler.onlinePlayerHandlers = onlinePlayerHandlers;
    }

    //close the current thread and logout the current player
    public void close() {
        try {

            //Signout this player
       //TODO MARK OFFLINE ON DATABAS

            //remove this current player from online vector
            onlinePlayerHandlers.remove(this);
         

            //Close the connection
            playerSocket.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        //set flag to false to break the loop
        isStayAlive = false;

        //close this thread
        this.stop();

    }

    @Override
    public void run() {
        //Listen to the requests of the players
        while (isStayAlive) {
            try {

                if (jsonObj != null) {
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
       public static boolean updateUsrStatus(String _username, String _status) {
        try {
            DataBase db = new DataBase();
            Connection con = null;
            Statement stmt = null;
            con = db.getCon();
            stmt = db.getStmt();
            setIsDBChanged(true);
            stmt=con.createStatement();
            int checkUpdate = stmt.executeUpdate("UPDATE playerdata SET Status='" + _status + "' WHERE Name = '" + _username + "'");
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public static Vector<Player> getAllOnlinePlayers() {

        Vector<Player> curr = new Vector<>();

        DataBase db = new DataBase();
        Connection con = null;
        Statement stmt = null;
        try {
            con = db.getCon();

            stmt = db.getStmt();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from playerdata where Status = 'Online'");
            while (rs.next()) {

                System.out.println(rs.getString(2));
                System.out.println("ok*******xxx" + rs.getString(2) + rs.getString(5));
                Player currPlayer = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), "Online", rs.getInt(4));
                curr.add(currPlayer);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;

        }
        return curr;
    }
    //Players Requests handeling

}
