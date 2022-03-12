
package clientside;

import clientHandler.ClientHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class AboutFXMLController implements Initializable {

    @FXML
    private Pane pageOne;
    @FXML
    private Pane pageTwo;
    @FXML
    private Pane pageThree;

    
    
    int page=1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pageTwo.setVisible(false);
        pageThree.setVisible(false);
    }    

    @FXML
    private void homeBtn(MouseEvent event) {
        ClientHandler.changeScene("Start");
    }

    @FXML
    private void switchRigth(MouseEvent event) {

        page++;
        if(page>3)
            page=1;
        pageOne.setVisible(false);
        pageTwo.setVisible(false);
        pageThree.setVisible(false);
        switch(page){
            case 1: pageOne.setVisible(true);break;
            case 2: pageTwo.setVisible(true);break;
            case 3: pageThree.setVisible(true);break;   
        }
    }

    @FXML
    private void switchLeft(MouseEvent event) {
        page--;
        if(page<1)
            page=3;
        pageOne.setVisible(false);
        pageTwo.setVisible(false);
        pageThree.setVisible(false);
        switch(page){
            case 1: pageOne.setVisible(true);break;
            case 2: pageTwo.setVisible(true);break;
            case 3: pageThree.setVisible(true);break;   
        }
    }
    
}
