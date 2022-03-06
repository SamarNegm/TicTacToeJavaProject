/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataBase.DataBase;
import Models.Player;
import Shared.JSONHandeling;
import Shared.Requests;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author MrMr
 */
public class AuthenHandler extends Thread {

    private Socket socket;
    private String errorMsg;
    private JSONObject jsonObj;
    private Player curPlayer;
    static Connection con = null;
    static Statement stmt = null;
    static PreparedStatement preparedStmt = null;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    DataBase db = new DataBase();
    private volatile boolean isStayAlive = true;
    public static Vector<AuthenHandler> connectedClientsList = new Vector();

    public AuthenHandler(Socket socket) {
        this.socket = socket;
        try {
            //Create the input and output channels
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            //Add to the vector of handlers
            connectedClientsList.add(this);

            //Start the thread
            this.start();

        } catch (IOException ex) {
            //Client socket has dropped (close this thread)
            // ServerUtils.appendLog("[AuthenHandler class]: Client dropped connection");
            this.close();
        }

        con = db.getCon();
        stmt = db.getStmt();

    }

    @Override
    public void run() {
        while (isStayAlive) {
            try {
                //handle user requests
                userRequestHandler(inputStream.readUTF());
                
                //send response to the user  
                outputStream.writeUTF(jsonObj.toString());
            } catch (IOException ex) {

                close();

            } catch (Exception ex) {
                //invalid data received (skip this iteration)
                System.out.println(ex);
            }
        }
    }

    private void userRequestHandler(String jsonStr) throws ParseException, IOException {
        System.out.println("vvvv");
        jsonObj = JSONHandeling.parseStringToJSON(jsonStr);
        JSONObject responseJsonObj = new JSONObject();
        //findout which request
        String requestType = (String) jsonObj.get("type");
        System.out.println("type " + requestType);
        switch (requestType) {
            //sign in or sign up request
            case (Requests.SIGN_IN):
            case (Requests.SIGN_UP):

                if (requestType.equals(Requests.SIGN_IN)) {
                    IsAuthenticated((String) jsonObj.get("username"), (String) jsonObj.get("password"));
                } else {
                    signUp((String) jsonObj.get("username"), (String) jsonObj.get("password"));
                }

                break;

            //close request
            case ("close"):

                this.close();
                break;

        }

    }

    public boolean IsAuthenticated(String uname, String Pass) {
        JSONObject responseJsonObj = new JSONObject();
        try {

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from playerdata");
            while (rs.next()) {
                System.out.println(rs.getString(2));
                if (rs.getString(2).equals(uname) && rs.getString(3).equals(Pass)) {
                    System.out.println("ok*******");
                    Player currPlayer = new Player(rs.getInt(1), uname, Pass, rs.getString(5), rs.getInt(4));

                    ///send to client acceptance  
                    responseJsonObj = JSONHandeling.playerToJSON(currPlayer);

                    //add the json response construction
                    responseJsonObj = JSONHandeling.constructJSONResponse(responseJsonObj, Requests.SIGN_IN);

                    System.out.println("back with respone.. " + responseJsonObj.toJSONString());
                    //send response to the user   
                    System.out.println("Soket: "+socket.getLocalPort());
                    outputStream.writeUTF(responseJsonObj.toString());
                    outputStream.writeChars("hiiii");
                    System.out.println("sent"+responseJsonObj.toString());
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("not ok||");
        responseJsonObj = JSONHandeling.errorToJSON("error", errorMsg);
        return false;
    }

    public void checkUserData(String uname, String password) {

        if (exist(uname)) {
            System.out.println("UserName is exist");
        } else {
            signUp(uname, password);
        }

    }

    public void signUp(String uname, String password) {
        try {
            String queryIsert = " insert into playerdata (  Name, Password, Score, Status)"
                    + " values (?, ?, ?,?)";
            preparedStmt = con.prepareStatement(queryIsert);
            preparedStmt.setString(1, uname);
            preparedStmt.setString(2, password);
            preparedStmt.setInt(3, 0);
            preparedStmt.setString(4, "Online");
            System.out.println("iserted....");
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean exist(String uname) {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from playerdata");
            while (rs.next()) {
                System.out.println(rs.getString(2));
                if (rs.getString(2).equals(uname)) {
                    System.out.println("ok*******");
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("not ok||");
        return false;
    }

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
