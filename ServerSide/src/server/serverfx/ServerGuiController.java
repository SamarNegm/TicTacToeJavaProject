/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.serverfx;

import com.sun.javaws.exceptions.CouldNotLoadArgumentException;
import database.DBMethods;
import database.playerinfo.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Server;
import static server.serverfx.ServerSide.DBStatusFlag;
import static server.serverfx.ServerSide.refreshRate;
import static server.serverfx.ServerSide.rootOrigin;
import server.utils.ServerUtils;

/**
 * FXML Controller class
 *
 * @author MrMr
 */
public class ServerGuiController implements Initializable {

    @FXML
    private TableView tableView;
    @FXML
    private ToggleButton sart;
    @FXML
    private TableColumn tableColumn, tableColumn0, tableColumn1;
    private Boolean controlFlag;
    private Boolean visableFlag;
    private static Stage window;
    ObservableList<Player> _tableView;
    Server serverInGUI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.controlFlag = false;
        this.visableFlag = false;
        this.serverInGUI = new Server();
         _tableView = FXCollections.observableArrayList();
              Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
            if( DBStatusFlag ){

            tableViewHandler();
            }


        }), new KeyFrame(Duration.seconds(refreshRate)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void buttonStyle(String text, double font) {
        sart.setDisable(false);
        sart.setStyle("-fx-background-color: #6ac08f;; -fx-background-radius: 30; -fx-text-fill: #ffff; -fx-font-size: 30;");
        sart.setText(text);
        sart.setTextFill(javafx.scene.paint.Color.WHITE);
        sart.setFont(new Font("Arial Bold", font));
    }

    @FXML
    private void sartServer(ActionEvent event) {

        if (DBStatusFlag) {
            if ((!controlFlag)) {
                controlFlag = true;
                ServerUtils.clearLog(controlFlag);
                serverInGUI.start();
                buttonStyle("Running", 30.0);
                sart.setStyle("-fx-background-color: #6ac08f;; -fx-background-radius: 30; -fx-text-fill: #ffff; -fx-font-size: 30;");

            } else {

                controlFlag = false;
                ServerUtils.clearLog(controlFlag);
                serverInGUI.stop();

                //create new object
                serverInGUI = new Server();
                buttonStyle("Stopped", 30.0);

            }

        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            if (DBMethods.checkDBConnection()) {
                buttonStyle("Stopped", 40.0);
                DBStatusFlag = true;
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Connection to Database Server Failed ...");
                a.show();
                buttonStyle("Check Database", 23.0);
                DBStatusFlag = false;
            }
        }
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.show();

    }

    private void refreshLoadIndicator() {

        double all = 0;
        double online = 0;
        double offline = 0;
        double none = .001;
        int busy = 0;

        if (DBMethods.getAllRecords(Player.statusType.online.toString()) != null) {
            online = DBMethods.getAllRecords(Player.statusType.online.toString()).size();
        }

        if (DBMethods.getAllRecords(Player.statusType.offline.toString()) != null) {
            offline = DBMethods.getAllRecords(Player.statusType.offline.toString()).size();
        }

        if (DBMethods.getAllRecords(Player.statusType.busy.toString()) != null) {
            busy = DBMethods.getAllRecords(Player.statusType.busy.toString()).size();
        }

        if (offline == 0 && online + busy > 0) {
            all = 1;
        } else {
            all = (double) (online + busy) / (none + online + offline + busy);
        }

//        loadIndicator.setProgress(all);
    }
    @FXML
    protected void clientPageCloseButton() {
        serverInGUI.stop();
        Stage stage;
        stage = (Stage) sart.getScene().getWindow();
        stage.close();
    }
    public void tableViewHandler() {

      _tableView.clear();
        if (DBMethods.getAllOrderedDesc("score") != null) {
            _tableView.addAll(DBMethods.getAllOrderedDesc("score"));
        } else {
            _tableView.clear();
        }

        tableView.setItems(_tableView);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("status"));
       tableColumn1.setCellValueFactory(new PropertyValueFactory<>("score"));
    }
    public Server getServerInGUI() {
        return serverInGUI;
    }
}
