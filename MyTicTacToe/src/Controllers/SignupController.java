/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import mytictactoe.*;
import DataBase.DataBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 *
 * @author MrMr
 */
public class SignupController implements Initializable {
    @FXML
    TextField uname;
    @FXML
    PasswordField password,confirm;
    @FXML
    Label error;
    DataBase db=new DataBase();
    public void SignUp(ActionEvent event)
    {
         
         if(password.getText().equals(confirm.getText()))
         db.checkUserData(uname.getText(), password.getText());
         else
             error.setText("Password No Match");
    }
      public void SignIn(ActionEvent event)
    {
        navigateTo("/mytictactoe/Login.fxml");

    }
public void navigateTo(String screen)
{
          try {
            Stage s = (Stage) password.getScene().getWindow();
            s.close();
            Parent parent = FXMLLoader.load(getClass().getResource(screen));

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println("not load");
        }
}
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
