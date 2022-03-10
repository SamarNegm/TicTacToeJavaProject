/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBase.DataBase;
import static DataBase.DataBase.Connet;
import Handlers.ClientHandler;
import Handlers.Encryption;
import Models.Player;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author MrMr
 */
public class LoginController implements Initializable {

    @FXML
    TextField uname;
    @FXML
    PasswordField pass;
    @FXML
    private Label errorLable;
    Thread readerThread;

    public LoginController() {

    }

    public Label getErrorLable() {
        return errorLable;
    }
    Player player = null;

    public void SignUp(ActionEvent event) {
        ClientHandler.navigateTo("/mytictactoe/SignUp.fxml");
    }

    public void SignIn(ActionEvent event) {
        if (!uname.getText().isEmpty() && !pass.getText().isEmpty()) {
            player = new Player();
            String salt = Encryption.getSalt(15);
            String encryptedPassword = Encryption.generateSecurePassword(pass.getText(), salt);

            ClientHandler.setLoginCtrl(this);
            ClientHandler.setPlayer(player);
       System.out.println("enc "+encryptedPassword);
            //Generate a new login request to the server.
            JSONObject loginReq = new JSONObject();
            loginReq.put("type", "signin");
            loginReq.put("username", uname.getText());
            loginReq.put("password", encryptedPassword);
            ClientHandler.sendRequest(loginReq);
            readerThread = new Thread(new ClientHandler.recieveRespone());
        }

    }



    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
