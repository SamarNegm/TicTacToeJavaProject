/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside;

import clientHandler.ClientHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author Marwa
 */
public class WelcomeFXMLController implements Initializable {
    @FXML
    private ImageView WelcomeImg;
    @FXML
    private Label username;
    @FXML
    private Button startPlaying;

    // StartPlaying button handler switch the scene to StartPane
    @FXML
    private void startPlayingHandler(MouseEvent event) {
        ClientHandler.changeScene("Start");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(ClientHandler.getPlayer().getUsername());
    }    
}
