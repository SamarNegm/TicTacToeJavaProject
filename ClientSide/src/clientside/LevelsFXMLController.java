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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Marwa
 */
public class LevelsFXMLController implements Initializable {
    @FXML
    private Button newbtn;
    @FXML
    private Button newbtn1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientHandler.setLevelsCtrl(this);
    }   
    @FXML
    private void easyHandler(MouseEvent event) {
        ClientHandler.changeScene("mainBoardWithComputerFXML",1);
    }
    @FXML
    private void hardHandler(MouseEvent event) {
        ClientHandler.changeScene("mainBoardWithComputerFXML",2);
    }

    
}
