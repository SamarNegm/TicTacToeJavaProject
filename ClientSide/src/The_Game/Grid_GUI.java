/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package The_Game;

import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Grid_GUI {

    Stage primaryStage;
    VBox root;
    Scene scene;

    public Grid_GUI() {
        primaryStage = new Stage();
        root = new VBox();
        root.setId("root");
        HBox top = new HBox();
        top.setId("top");
        HBox winner_result = new HBox();
        Button newGame = new Button("New Game");
        //newGame.setFont(new Font(20));
        newGame.setMinWidth(200);
        newGame.setId("newGame");

        Button quit = new Button("Quit");
        //quit.setFont(new Font(20));
        quit.setMinWidth(200);
        quit.setId("quit");
        GridPane grid = new GridPane();
        grid.setId("grid");
        Button btn[][] = new Button[3][3];
        Label winner = new Label();
        Label result = new Label();

        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                int x = i, y = j;
                btn[i][j] = new Button("");
                grid.add(btn[i][j], j, i);
                btn[i][j].setPrefWidth(200);
                btn[i][j].setPrefHeight(200);
                btn[i][j].setFont(new Font(70));
                btn[i][j].setOnAction(e
                        -> {
                    int xCount = 0, oCount = 0;
                    String win = "";
                    //result mean no one played or during game
                    if (result.getText().equals("")) {

                        xCount = 0;
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 3; b++) {
                                if (btn[a][b].getText().equals("X")) {
                                    xCount++;
                                }
                            }
                        }
                        if (btn[x][y].getText().equals("")) {
                            btn[x][y].setText("X");
                            btn[x][y].setId("X");
                            xCount++;
                            win = game_functions.check(btn);
                            if (!win.equals("")) {
                                winner.setId("winner");
                                winner.setText("Winner");
                                result.setId(win);
                                result.setText(win);
//                                            try {
////                                                Model.add_point();
//                                                //////FUNCTION to add one point
//                                            } catch (SQLException ex) {
//                                                Logger.getLogger(Grid_GUI.class.getName()).log(Level.SEVERE, null, ex);
//                                            } catch (ClassNotFoundException ex) {
//                                                Logger.getLogger(Grid_GUI.class.getName()).log(Level.SEVERE, null, ex);
//                                            }
                            } else if (xCount == 5) {
                                winner.setId("winner");
                                winner.setText("DRAW");
                            }
                            if (winner.getText().equals("")) {
                                game_functions.fillO(btn);
                            }
                            win = game_functions.check(btn);
                            if (!win.equals("")) {
                                winner.setId("winner");
                                winner.setText("Winner");
                                result.setId(win);
                                result.setText(win);

                            }
                        }

                    }
                });
            }
        }
        newGame.setOnAction(e
                -> {
            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    btn[a][b].setText("");
                    btn[a][b].setId("empty");
                }
            }

            winner.setId("");
            winner.setText("");
            result.setId("");
            result.setText("");
        });

        quit.setOnAction(e -> System.exit(0));

        newGame.setPrefWidth(100);
        HBox.setMargin(newGame, new Insets(10, 0, 10, 15));
        quit.setPrefWidth(100);
        HBox.setMargin(quit, new Insets(10, 0, 10, 20));
        VBox.setMargin(grid, new Insets(20, 20, 20, 20));
        top.getChildren().addAll(newGame, quit);
        top.setAlignment(Pos.CENTER);
        winner.setAlignment(Pos.CENTER);
        winner.setPrefWidth(160);
        winner.setPrefHeight(70);
        winner.setFont(new Font(40));
        VBox.setMargin(winner, new Insets(0, 0, 0, 240));
        result.setPrefWidth(70);
        result.setPrefHeight(70);
        result.setAlignment(Pos.CENTER);
        VBox.setMargin(result, new Insets(0, 0, 0, 280));
        ///////////////////////////////
        winner_result.getChildren().addAll(winner, result);
        winner_result.setAlignment(Pos.CENTER);
        //HBox.setMargin(winner_result,new Insets(0, 0, 0, 240));
        root.getChildren().addAll(top, grid, winner_result);
        //primaryStage.setTitle("Tic Tac Toe");
        scene = new Scene(root, 640, 820);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        // primaryStage.setScene(scene);
        // primaryStage.show();

    }

    public Scene get_scene() {

        return scene;

    }
}
