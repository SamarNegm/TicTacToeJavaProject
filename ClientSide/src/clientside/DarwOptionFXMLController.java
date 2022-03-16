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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MrMr
 */
public class DarwOptionFXMLController implements Initializable {

    @FXML
    private Button tryagain;
    @FXML
    private Button tryagain1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void tryagainHandler(MouseEvent event) {
        ClientHandler.changeScene("mainBoardWithComputerFXML");
        
    }
    @FXML
    private void tryagain1Handler(MouseEvent event) {
        ClientHandler.changeScene("Newgame");
    }
}
