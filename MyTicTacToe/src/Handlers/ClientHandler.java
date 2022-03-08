/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Models.Player;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controllers.LoginController;
import Controllers.SignupController;
import mytictactoe.mainBoardWithComputerBase;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytictactoe.MyTicTacToe;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author MrMr
 */
public class ClientHandler {

    private static Socket clientSocket;
    private static DataInputStream ds;
    private static Stage window;
    private static Player player;
    private static String currentScene;
    private static DataOutputStream ps;
    private static LoginController loginctrl;
     private static SignupController signUpCtrl;

    public static SignupController getSignUpCtrl() {
        return signUpCtrl;
    }

    public static void setSignUpCtrl(SignupController signUpCtrl) {
        ClientHandler.signUpCtrl = signUpCtrl;
    }
    private ClientHandler(){  
    }
    
    public static Socket getClientSocket() {
        return clientSocket;
    }

    public static void setClientSocket(Socket clientSocket) {
        ClientHandler.clientSocket = clientSocket;
    }

    public static DataInputStream getDs() {
        return ds;
    }

    public static void setDs(DataInputStream ds) {
        ClientHandler.ds = ds;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        ClientHandler.window = window;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        ClientHandler.player = player;
    }

    public static String getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(String currentScene) {
        ClientHandler.currentScene = currentScene;
    }

    public static DataOutputStream getPs() {
        return ps;
    }

    public static void setPs(DataOutputStream ps) {
        ClientHandler.ps = ps;
    }

    public static LoginController getLoginctrl() {
        return loginctrl;
    }

    public static void setLoginctrl(LoginController loginctrl) {
        ClientHandler.loginctrl = loginctrl;
    }

    public static boolean connectToServer() {
        boolean res = true;
        try {
            clientSocket = new Socket("127.0.0.1", 5055);
          
            ps = new DataOutputStream(clientSocket.getOutputStream());
            ds = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            res = false;
        }
        return res;
    }

    public static void closeCon() {
        try {
            ps.close();
            ds.close();
            clientSocket.close();
        } catch (IOException ex) {
        }
    }

    public static void sendRequest(JSONObject jsonMsg) {
        if(connectToServer()){
        try {
            System.out.println("sending requst..." + jsonMsg.toString());
            ps.writeUTF(jsonMsg.toJSONString());
           
        } catch (IOException ex) {
        }
    }
    }

      /* Receive requests from the server and send them to the handler */
    public static class recieveRespone extends Thread {
        boolean running = true;
        String response;
        public recieveRespone ()  {
          
            this.start();
        }
        @Override
        public void run(){
            while (running) {
                try {
              
                    
                    response = ds.readUTF();
                    if (response != null) {
                        handleResponse(response);
                    }
                } catch (IOException ex) {
                    running=false;
                    navigateTo("mainXOboard.fxml");
                }
            }
        }
    }
    private static void handleResponse(String response)
    {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonMsg = (JSONObject) parser.parse(response);
            switch (jsonMsg.get("type").toString()) {
                case "signin":
                    Login(jsonMsg);
                    break;
                case "signup":
                    Login(jsonMsg);
                    break;
        
                
            }
        } catch (ParseException ex) {
        }
    }
    public static void navigateTo(String screen) {
        setCurrentScene(screen);
        Platform.runLater(() -> {
            //     Parent root = FXMLLoader.load(MyTicTacToe.class.getResource(screen));
            
            mainBoardWithComputerBase root =new mainBoardWithComputerBase(2,window);
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        });
    }
    
    private static void Login(JSONObject response)
    {
        System.out.println("yaaaaaa login..."+response.toString());
        String request = response.get("type").toString();
        String resStatus = response.get("responseStatus").toString();
        if(resStatus.equals("true")){
            player.setScore(Integer.parseInt(response.get("score").toString()));
            player.setUsername(response.get("username").toString());
            player.setStatus(response.get("status").toString());
            navigateTo("mainXOboard.fxml");
        }
        else{
            String error = response.get("errorMsg").toString();
             System.out.println(error.toString()+"............");
            String warning;
            
            if ( request.equals("signin") && error.equals("signedin"))
                warning="You are already signed in.";
            else if ( request.equals("signin") && error.equals("fail"))
                warning="Wrong user name or password";
            else if ( request.equals("signup") && error.equals("fail"))
                warning="Username already exists.";
            else
                warning="unexpected";
            Platform.runLater(() -> {
            
                System.out.println(warning);
                if (request.equals("signin"))
                loginctrl.getErrorLable().setText(warning);
                else
                 signUpCtrl.getErrorLable().setText(warning);
            
            });
        }
    }
    public static void setLoginCtrl(LoginController ctrl) {
        loginctrl = ctrl;
    }
}
