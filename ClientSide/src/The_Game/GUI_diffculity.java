/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package The_Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed Hamdy
 */
public class GUI_diffculity {
   public Button easy;
    public Button hard;
    public Scene scene ; 
    public Label label ; 
    public GUI_diffculity () {
     //Stage primaryStage = new Stage();
        label = new Label("Choose your Difficulty level");
        //label.setFont(new Font(30));
        label.setId("label");
        easy = new Button("Easy");
        hard = new Button ("Hard");
        easy.setId("easy");
        hard.setId("hard");
        VBox container = new VBox() ; 
        container.setId("Cont");
        container.setSpacing(80);
        HBox easy_hard = new HBox();
        container.setAlignment(Pos.CENTER);  
        easy_hard.setAlignment(Pos.CENTER); 
        easy_hard.setSpacing(10);
 
//VBox.setMargin(easy_hard, );
        easy_hard.setId("easy_hard");
        easy_hard.getChildren().addAll(easy,hard);
        container.getChildren().addAll(label,easy_hard);
        scene = new Scene(container,640, 820);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        //primaryStage.setScene(scene);
        //primaryStage.show();
}
    public Scene get_scene () {
        return scene ; 
    }
    
}
