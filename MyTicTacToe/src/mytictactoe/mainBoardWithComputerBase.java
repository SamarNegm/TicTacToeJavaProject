package mytictactoe;

import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.jfoenix.controls.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainBoardWithComputerBase extends AnchorPane {

    protected final ImageView borderView;
    protected final ImageView playerImage1;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final JFXButton button1;
    protected final ImageView button1Image;
    protected final JFXButton button2;
    protected final ImageView button2Image;
    protected final JFXButton button5;
    protected final ImageView button5Image;
    protected final JFXButton button3;
    protected final ImageView button3Image;
    protected final JFXButton button9;
    protected final ImageView button9Image;
    protected final JFXButton button6;
    protected final ImageView button6Image;
    protected final JFXButton button4;
    protected final ImageView button4Image;
    protected final JFXButton button7;
    protected final ImageView button7Image;
    protected final JFXButton button8;
    protected final ImageView button8Image;
    protected final ImageView xInTitle;
    protected final ImageView oInTitle;
    protected final ImageView robotImage;
    protected final JFXButton saveState;
    Random rand =new Random();
    int state;
    boolean wins=false;
    String[] arr=new String[9];
    int[][] myNumbers = { {0, 1, 2}, {3, 4, 5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };
    private boolean playerTurn = true;
    int turnCounter=0;
    int hardTurn=-1;
    ArrayList<Button> buttons;
    ArrayList<ImageView> imageView;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
    }
    private void setupButton(Button button, int index) {
        button.setOnMouseClicked(mouseEvent -> {
            if("".equals(arr[index])){
            System.out.println("we are in button "+index);
            setPlayerSymbol(button,index);
            //button.setDisable(true);
            checkIfGameIsOver();
            }
        });
    }
    private void checkIfGameIsOver() {
        
        for (int a = 0; a <= 7; a++) {
            //X winner
            if ("X".equals(arr[myNumbers[a][0]]) && "X".equals(arr[myNumbers[a][2]]) && "X".equals(arr[myNumbers[a][1]])) {
                text.setText("winner");
                text0.setText("is");
                text1.setText("Samar");
                wins=true;
                buttons.forEach(button ->{
                    
                    button.setDisable(true);
                  });
            }

            //O winner
            else if ("O".equals(arr[myNumbers[a][0]]) && "O".equals(arr[myNumbers[a][2]]) && "O".equals(arr[myNumbers[a][1]])) {
                text.setText("winner");
                text0.setText("is");
                text1.setText("Ommar");
                wins=true;
                buttons.forEach(button ->{
                    
                    button.setDisable(true);
                  });
            }
            
            else if(turnCounter > 9){
                text.setText("game");
                text0.setText("is");
                text1.setText("draw");
                wins=true;
                buttons.forEach(button ->{
                    
                    button.setDisable(true);
                  });
                    }
            System.out.println("mytictactoe.mainBoardWithComputerBase.checkIfGameIsOver() "+turnCounter);
        }
    
    }
    
    public void setPlayerSymbol(Button button,int index){
        if(playerTurn ){
            arr[index]="X";
            imageView.get(index).setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
            playerTurn = false;
            turnCounter++;
            
            checkIfGameIsOver();
            if( wins !=true){
                    computerTurn("O");
                    turnCounter++;
                    
            }
            checkIfGameIsOver();
            
            
            
        } 
        else{
            arr[index]="O";
            imageView.get(index).setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
            playerTurn = true;
            turnCounter++;
            
            checkIfGameIsOver();
            System.out.println("mytictactoe.mainBoardWithComputerBase.setPlayerSymbol()"+wins);
            if( wins !=true){
                    computerTurn("X");
                    turnCounter++;
            }
            checkIfGameIsOver();
            
        }
        
    }
    private void computerTurn(String turn){
        
        
        buttons.forEach(button ->{
                //setupButton(button,buttons.indexOf(button));
                button.setDisable(true);
            
        });
        //if it is easy game it use random
        if (state==1){
            try {
                        Thread.sleep(300);
                } catch (InterruptedException ex) {
                        Logger.getLogger(mainBoardWithComputerBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            while(turnCounter < 9){
              int genrateRandom= rand.nextInt(9);
              if("".equals(arr[genrateRandom])){
                if ("X".equals(turn)){  
                    
                    
                    imageView.get(genrateRandom).setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
                    playerTurn = false;
                    arr[genrateRandom]="X";
                    //checkIfGameIsOver();
                
                }
                else if("O".equals(turn)){
                    
                    imageView.get(genrateRandom).setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
                    playerTurn = true;
                    arr[genrateRandom]="O";
                    
                }
                  break;
              }
              
            }
            
        }
        //if it is hard
        if (state ==2){
             hardTurn=-1;
                try {
                        Thread.sleep(300);
                } catch (InterruptedException ex) {
                        Logger.getLogger(mainBoardWithComputerBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                  if("O".equals(turn)){
                    hardTurn = hard("O");
                      System.out.println("main.WithCombuter.computerTurn() "+ hardTurn);
                    if (hardTurn !=-1){
                        arr[hardTurn]="O";
                        imageView.get(hardTurn).setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
                        playerTurn=true;
                    }
                  }
                  else{
                      hardTurn = hard("X");
                    if (hardTurn !=-1){
                        arr[hardTurn]="X";
                        imageView.get(hardTurn).setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
                    }
                    playerTurn=false;
                    
                  }
        
        }
        buttons.forEach(button ->{
            //setupButton(button,buttons.indexOf(button));
            button.setDisable(false);
        });
        
    }
    private int hard(String turn) {
        hardTurn=-1;
        //important for the win when the computer is X
         if("O".equals(turn)){
             for (int i=0;i<8;i++){
             if("X".equals(arr[myNumbers[i][0]]) && "X".equals(arr[myNumbers[i][1]]) && "".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][2];
             }
             if("X".equals(arr[myNumbers[i][0]]) &&"".equals(arr[myNumbers[i][1]]) &&"X".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][1];
             }
             if("".equals(arr[myNumbers[i][0]]) &&"X".equals(arr[myNumbers[i][1]]) &&"X".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][0];
             }
             
         }
         }
         //important for the win when the computer is O
         else if("X".equals(turn)){
             for (int i=0;i<8;i++){
             if("O".equals(arr[myNumbers[i][0]]) &&"O".equals(arr[myNumbers[i][1]]) &&"".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][2];
             }
             if("O".equals(arr[myNumbers[i][0]]) &&"".equals(arr[myNumbers[i][1]]) &&"O".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][1];
             }
             if("".equals(arr[myNumbers[i][0]]) &&"O".equals(arr[myNumbers[i][1]]) &&"O".equals(arr[myNumbers[i][2]] )){
                 hardTurn=myNumbers[i][0];
             }
             }
         }
        //to avoid lose
        for (int i=0;i<8;i++){
             if( "X".equals(arr[myNumbers[i][0]]) &&"X".equals(arr[myNumbers[i][1]]) &&"".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][2];
             }
             if( "X".equals(arr[myNumbers[i][0]]) &&"".equals(arr[myNumbers[i][1]]) &&"X".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][1];
             }
             if("".equals(arr[myNumbers[i][0]]) &&"X".equals(arr[myNumbers[i][1]]) &&"X".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][0];
             }
             if("".equals(arr[myNumbers[i][2]]) &&"O".equals(arr[myNumbers[i][1]]) &&"O".equals(arr[myNumbers[i][0]])){
                 hardTurn=myNumbers[i][2];
             }
             if("".equals(arr[myNumbers[i][1]]) &&"O".equals(arr[myNumbers[i][2]]) &&"O".equals(arr[myNumbers[i][0]])){
                 hardTurn=myNumbers[i][1];
             }
             if("".equals(arr[myNumbers[i][0]]) &&"O".equals(arr[myNumbers[i][1]]) &&"O".equals(arr[myNumbers[i][2]])){
                 hardTurn=myNumbers[i][0];
             }
         }   
        
         
         //generate random if it is still un changed
         if(hardTurn ==-1 ){
                        while(turnCounter <9){
                            int genrateRandom= rand.nextInt(9);
                            if("".equals(arr[genrateRandom])){
                                hardTurn=genrateRandom;
                                break;
                            }
                        }
            }
         
                        System.out.println("hiiiiii " +hardTurn);
        return hardTurn;
    }

    public mainBoardWithComputerBase(int state1) {
        state=state1;
        borderView = new ImageView();
        playerImage1 = new ImageView();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        button1 = new JFXButton();
        button1Image = new ImageView();
        button2 = new JFXButton();
        button2Image = new ImageView();
        button5 = new JFXButton();
        button5Image = new ImageView();
        button3 = new JFXButton();
        button3Image = new ImageView();
        button9 = new JFXButton();
        button9Image = new ImageView();
        button6 = new JFXButton();
        button6Image = new ImageView();
        button4 = new JFXButton();
        button4Image = new ImageView();
        button7 = new JFXButton();
        button7Image = new ImageView();
        button8 = new JFXButton();
        button8Image = new ImageView();
        xInTitle = new ImageView();
        oInTitle = new ImageView();
        robotImage = new ImageView();
        saveState = new JFXButton();
        
        // int playerTurn = 0;
        //arraylist of all buttons  and imageview in the game
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        imageView = new ArrayList<>(Arrays.asList(button1Image,button2Image,button3Image,button4Image,button5Image,button6Image,button7Image,button8Image,button9Image));
        //My Initializable 
        buttons.forEach(button ->{
            setupButton(button,buttons.indexOf(button));
            button.setFocusTraversable(false);
        });
        for(int i=0;i<9;i++){
            arr[i]="";
        }
        
        
        setId("AnchorPane");
        setPrefHeight(509.0);
        setPrefWidth(746.0);
        setStyle("-fx-background-color: ffff;");
        getStyleClass().add("mainFxmlClass");
        getStylesheets().add("/mytictactoe/mainboardwithcomputer.css");

        borderView.setFitHeight(247.0);
        borderView.setFitWidth(363.0);
        borderView.setLayoutX(148.0);
        borderView.setLayoutY(114.0);
        borderView.setPickOnBounds(true);
        borderView.setPreserveRatio(true);
        borderView.setImage(new Image(getClass().getResource("Images/borders.png").toExternalForm()));

        playerImage1.setFitHeight(114.0);
        playerImage1.setFitWidth(149.0);
        playerImage1.setLayoutX(14.0);
        playerImage1.setLayoutY(337.0);
        playerImage1.setPickOnBounds(true);
        playerImage1.setPreserveRatio(true);
        playerImage1.setImage(new Image(getClass().getResource("Images/plyer2.png").toExternalForm()));

        text.setLayoutX(212.0);
        text.setLayoutY(57.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setStyle("-fx-fill: #6ac08f;; -fx-font-size: 25;");
        text.setText("Samr");
        text.setWrappingWidth(94.98828125);

        text0.setLayoutX(307.0);
        text0.setLayoutY(60.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setStyle("-fx-font-size: 40; -fx-fill: #1db2ca; -fx-font-weight: bold;");
        text0.setText("VS");
        text0.setWrappingWidth(57.09375);

        text1.setLayoutX(383.0);
        text1.setLayoutY(57.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setStyle("-fx-fill: #6ac08f;; -fx-font-size: 25;");
        text1.setText("Me");
        text1.setWrappingWidth(94.98828125);

        button1.setLayoutX(152.0);
        button1.setLayoutY(114.0);
        button1.setStyle("-fx-background-color: ffff;");

        button1Image.setFitHeight(68.0);
        button1Image.setFitWidth(78.0);
        button1Image.setPickOnBounds(true);
        button1Image.setPreserveRatio(true);
        //button1Image.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
        button1.setGraphic(button1Image);

        button2.setLayoutX(258.0);
        button2.setLayoutY(114.0);
        button2.setStyle("-fx-background-color: ffff;");

        button2Image.setFitHeight(69.0);
        button2Image.setFitWidth(83.0);
        button2Image.setPickOnBounds(true);
        button2Image.setPreserveRatio(true);
        //button2Image.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
        button2.setGraphic(button2Image);

        button5.setLayoutX(258.0);
        button5.setLayoutY(199.0);
        button5.setStyle("-fx-background-color: ffff;");

        button5Image.setFitHeight(65.0);
        button5Image.setFitWidth(83.0);
        button5Image.setPickOnBounds(true);
        button5Image.setPreserveRatio(true);
        //button5Image.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
        button5.setGraphic(button5Image);

        button3.setLayoutX(378.0);
        button3.setLayoutY(114.0);
        button3.setStyle("-fx-background-color: ffff;");

        button3Image.setFitHeight(64.0);
        button3Image.setFitWidth(86.0);
        button3Image.setPickOnBounds(true);
        button3Image.setPreserveRatio(true);
        //button3Image.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
        button3.setGraphic(button3Image);

        button9.setLayoutX(378.0);
        button9.setLayoutY(290.0);
        button9.setStyle("-fx-background-color: ffff;");

        button9Image.setFitHeight(64.0);
        button9Image.setFitWidth(79.0);
        button9Image.setPickOnBounds(true);
        button9Image.setPreserveRatio(true);
        //button9Image.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
        button9.setGraphic(button9Image);

        button6.setLayoutX(378.0);
        button6.setLayoutY(199.0);
        button6.setStyle("-fx-background-color: ffff;");

        button6Image.setFitHeight(65.0);
        button6Image.setFitWidth(78.0);
        button6Image.setPickOnBounds(true);
        button6Image.setPreserveRatio(true);
        //button6Image.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
        button6.setGraphic(button6Image);

        button4.setLayoutX(152.0);
        button4.setLayoutY(199.0);
        button4.setStyle("-fx-background-color: ffff;");

        button4Image.setFitHeight(67.0);
        button4Image.setFitWidth(81.0);
        button4Image.setPickOnBounds(true);
        button4Image.setPreserveRatio(true);
        //button4Image.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
        button4.setGraphic(button4Image);

        button7.setLayoutX(152.0);
        button7.setLayoutY(290.0);
        button7.setStyle("-fx-background-color: ffff;");

        button7Image.setFitHeight(65.0);
        button7Image.setFitWidth(78.0);
        button7Image.setPickOnBounds(true);
        button7Image.setPreserveRatio(true);
        //button7Image.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
        button7.setGraphic(button7Image);

        button8.setLayoutX(258.0);
        button8.setLayoutY(290.0);
        button8.setStyle("-fx-background-color: ffff;");

        button8Image.setFitHeight(63.0);
        button8Image.setFitWidth(88.0);
        button8Image.setPickOnBounds(true);
        button8Image.setPreserveRatio(true);
        //button8Image.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));
        button8.setGraphic(button8Image);

        xInTitle.setFitHeight(37.0);
        xInTitle.setFitWidth(57.0);
        xInTitle.setLayoutX(152.0);
        xInTitle.setLayoutY(28.0);
        xInTitle.setPickOnBounds(true);
        xInTitle.setPreserveRatio(true);
        xInTitle.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));

        oInTitle.setFitHeight(62.0);
        oInTitle.setFitWidth(46.0);
        oInTitle.setLayoutX(485.0);
        oInTitle.setLayoutY(28.0);
        oInTitle.setPickOnBounds(true);
        oInTitle.setPreserveRatio(true);
        oInTitle.setImage(new Image(getClass().getResource("Images/o.png").toExternalForm()));

        robotImage.setFitHeight(125.0);
        robotImage.setFitWidth(107.0);
        robotImage.setLayoutX(531.0);
        robotImage.setLayoutY(74.0);
        robotImage.setPickOnBounds(true);
        robotImage.setPreserveRatio(true);
        robotImage.setImage(new Image(getClass().getResource("Images/robot.png").toExternalForm()));

        saveState.setLayoutX(576.0);
        saveState.setLayoutY(440.0);
        saveState.setStyle("-fx-background-color: #6ac08f; -fx-text-fill: ffff; -fx-background-radius: 30; -fx-font-size: 16;");
        saveState.setText("save state");
        
        
        getChildren().add(borderView);
        getChildren().add(playerImage1);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button5);
        getChildren().add(button3);
        getChildren().add(button9);
        getChildren().add(button6);
        getChildren().add(button4);
        getChildren().add(button7);
        getChildren().add(button8);
        getChildren().add(xInTitle);
        getChildren().add(oInTitle);
        getChildren().add(robotImage);
        getChildren().add(saveState);

    }
}
