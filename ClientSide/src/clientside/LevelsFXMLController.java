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
 * FXML Controller class
 *
 * @author computec
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
