/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside;

import clientHandler.ClientHandler;
import clientHandler.Game;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MrMr
 */
public class MainBoaredController implements Initializable {

    @FXML
    private Button saveBtn;
    @FXML
    private Text player1Label;
    @FXML
    private Text player2Label;
    @FXML
    private ImageView cell1;
    @FXML
    private ImageView cell2;
    @FXML
    private ImageView cell3;
    @FXML
    private ImageView cell4;
    @FXML
    private ImageView cell5;
    @FXML
    private ImageView cell6;
    @FXML
    private ImageView cell7;
    @FXML
    private ImageView cell8;
    @FXML
    private ImageView cell9;
    Game game;
    char player1Value;
    char player2Value;
    boolean play;
    MouseEvent event;
    int mode;//0 easy, 1 medium, 2 hard
    boolean finish;
    boolean isDraw;
    @FXML
    private AnchorPane resultAnchor;
    @FXML
    private SubScene resultSubscene;
    @FXML
    private Label winnerLabel;
    @FXML
    private Button quitBtn;

    @FXML
    private AnchorPane savingSubscene;
    @FXML
    private Label savingLbl;
    @FXML
    private Button homeBtn;
    @FXML
    private AnchorPane waitingSubscene;
    @FXML
    private Label waitingLbl;
    @FXML
    private Button okBtn;

    public void setPlayerSympol(char sympol,ImageView imageView)
    {

        if(sympol=='X')
          imageView.setImage(new Image(getClass().getResource("/Images/x.png").toExternalForm()));
        else if(sympol=='O')
           imageView.setImage(new Image(getClass().getResource("/Images/o.png").toExternalForm())); 
        else
              imageView.setImage(null); 
    }
       private void player1Handle(){
        toggleNextMove();
        togglePlay();
    }
        private void togglePlay(){
        if(play == true){
            play = false;
        }
        else{
            play = true;
        }

    }
    private void player2Handle(){
       toggleNextMove();
       togglePlay();
    }
    
    private void checkWinOrDraw(){

        int win = game.checkWin();

        if(win == 1){
            finish = true;
            
            if(player1Value == 'X'){
                
                if(player1Label.getText().equals(ClientHandler.getPlayer().getUsername()))
                {
                    try {
                        Thread.sleep(300);
                           
                        ClientHandler.gameEndedRequest(player1Label.getText(), isDraw, "");
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }

                winnerLabel.setText(player1Label.getText() + " won!");
                setSceneVisibility(true);
            
            }
            else{
                
                if(player2Label.getText().equals(ClientHandler.getPlayer().getUsername()))
                {
                    try {
                        Thread.sleep(300);
                           
                        ClientHandler.gameEndedRequest(player2Label.getText(), isDraw, "");
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                        }
                }
                winnerLabel.setText(player2Label.getText() + " won!");
                setSceneVisibility(true);
            }
            
        }
        else if(win == 2){
            
            if(player1Value == 'X'){
                
                if(player1Label.getText().equals(ClientHandler.getPlayer().getUsername())){
                    try {
                        isDraw = true;
                        Thread.sleep(300);   
                        ClientHandler.gameEndedRequest(player1Label.getText(), isDraw, "");
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
             }

            finish = true;

            winnerLabel.setText("It's a draw!");
            setSceneVisibility(true);

        }
        //game.printBoard();
    }
    
    @FXML
    private void cell1Handler(MouseEvent event){
        if(play && (cell1.getImage()==null)){
            setPlayerSympol(player1Value, cell1);
            game.setCell1(player1Value);
            ClientHandler.sendMoveRequest(0, 0);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell2Handler(MouseEvent event){
        if(play && (cell2.getImage()==null)){
            setPlayerSympol(player1Value, cell2);
            game.setCell2(player1Value);
            ClientHandler.sendMoveRequest(0, 1);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell3Handler(MouseEvent event){
          if(play && (cell3.getImage()==null)){
            setPlayerSympol(player1Value, cell3);
            game.setCell3(player1Value);
            ClientHandler.sendMoveRequest(0, 2);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell4Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok4");
        if(play && (cell4.getImage()==null)){
            setPlayerSympol(player1Value, cell4);
            game.setCell4(player1Value);
            ClientHandler.sendMoveRequest(1, 0);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell5Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok5");
        if(play && (cell5.getImage()==null)){
            setPlayerSympol(player1Value, cell5);
            game.setCell5(player1Value);
            ClientHandler.sendMoveRequest(1, 1);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell6Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok6");
        if(play && (cell6.getImage()==null)){
            setPlayerSympol(player1Value, cell6);
            game.setCell6(player1Value);
            ClientHandler.sendMoveRequest(1, 2);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell7Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok7");
        if(play && (cell7.getImage()==null)){
            setPlayerSympol(player1Value, cell7);
            game.setCell7(player1Value);
            ClientHandler.sendMoveRequest(2, 0);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    @FXML
    private void cell8Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok8");
        if(play && (cell8.getImage()==null)){
            setPlayerSympol(player1Value, cell8);
            game.setCell8(player1Value);
            ClientHandler.sendMoveRequest(2, 1);
            checkWinOrDraw();
            player1Handle();
        }

    }
       @FXML 
    private void cell9Handler(MouseEvent event){
          System.out.println("hiiiiiiiiiiiii11111111 ok9");
        if(play && (cell9.getImage()==null)){
            setPlayerSympol(player1Value, cell9);
            game.setCell9(player1Value);
            ClientHandler.sendMoveRequest(2, 2);
            checkWinOrDraw();
            player1Handle();
        }
    }
    
    private void setSceneVisibility(Boolean visible){
        if(visible){
            resultAnchor.setVisible(true);
        }
        else{
            resultAnchor.setVisible(false);
        }
    }

    private void exitBtnHandler(ActionEvent event){        
        Platform.exit();
    }
    
    private void backBtnHandler(ActionEvent event){
        setSceneVisibility(false);
    }
      private void toggleNextMove(){
        if(game.getNextMove() == 0){
            game.setNextMove(1);
        }
        else{
            game.setNextMove(0);
        }
    }
    
    
    public void secondPlayerMove(){
        if(!finish){
            
            //get cell move of the second player
            Game.CellPosition resultCell = Game.getMoveOfNextPlayer();
            
            if(resultCell.row == 0){
                
                switch (resultCell.col) {
                    case 0:
                           setPlayerSympol(player2Value, cell1);
                 
                        game.setCell1(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                        
                    case 1:
                           setPlayerSympol(player2Value, cell2);
                        game.setCell2(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                        
                    case 2:
                            setPlayerSympol(player2Value, cell3);
                        game.setCell3(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                        
                    default:
                        break;
                }
            }
            else if(resultCell.row == 1){
                switch (resultCell.col) {
                    case 0:
                        setPlayerSympol(player2Value, cell4);
                        game.setCell4(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    case 1:
                            setPlayerSympol(player2Value, cell5);
                        game.setCell5(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    case 2:
                            setPlayerSympol(player2Value, cell6);
                        game.setCell6(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    default:
                        break;
                }
            }
            else if(resultCell.row == 2){
                switch (resultCell.col) {
                    case 0:
                          setPlayerSympol(player2Value, cell7);
                        game.setCell7(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    case 1:
                           setPlayerSympol(player2Value, cell8);
                        game.setCell8(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    case 2:
                           setPlayerSympol(player2Value, cell9);
                        game.setCell9(player2Value);
                        player2Handle();
                        checkWinOrDraw();
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private void setPlayerData(){
        if(ClientHandler.getPlayer().getInvited()){
            play = false;
            player1Value = 'O';
            player2Value = 'X';
            player1Label.setText(ClientHandler.getPlayer().getUsername());
            player2Label.setText(ClientHandler.getPlayer().getOpponent());
        }
        else{
            play = true;
            player1Value = 'X';
            player2Value = 'O';
            player1Label.setText(ClientHandler.getPlayer().getUsername());
            player2Label.setText(ClientHandler.getPlayer().getOpponent());
        }
    }
    
    private void setGameLoaded(){
        if(ClientHandler.getNextPlayer().equals(ClientHandler.getPlayer().getUsername())){
            if(ClientHandler.getNextMove() == 'X'){
                player1Value = 'X';                
                player2Value = 'O';  
                game.setNextMove(0);
            }
            else{
                player1Value = 'O';
                player2Value = 'X';
                game.setNextMove(1);
            }
            player1Label.setText(ClientHandler.getPlayer().getUsername());
            player2Label.setText(ClientHandler.getPlayer().getOpponent());
            play = true;
        }
        else{
            if(ClientHandler.getNextMove() == 'X'){
                player1Value = 'O';                
                player2Value = 'X';  
                game.setNextMove(0);
            }
            else{
                player1Value = 'X';
                player2Value = 'O';
                game.setNextMove(1);
            }
            player1Label.setText(ClientHandler.getPlayer().getUsername());
            player2Label.setText(ClientHandler.getPlayer().getOpponent());
            play = false;
        }

        game.setBoard(ClientHandler.getBoard());
        //game.printBoard();
    }
    
    private void setLoadedBoard(){
        int numOfMoves = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(game.getBoard()[i][j] == 'X' || game.getBoard()[i][j] == 'O'){
                    numOfMoves++;
                }
                
                if(i == 0){
                    switch (j) {
                        case 0:
                            setPlayerSympol(game.getBoard()[i][j], cell1);
                                                
                            break;

                        case 1:
                            setPlayerSympol(game.getBoard()[i][j], cell2);                       
                            break;

                        case 2:
                              setPlayerSympol(game.getBoard()[i][j], cell3);
                            break;

                        default:
                            break;
                    }
                }
                else if(i == 1){
                    switch (j) {
                        case 0:
                            setPlayerSympol(game.getBoard()[i][j], cell4);
                            break;
                        case 1:
                            setPlayerSympol(game.getBoard()[i][j], cell5);
                            break;
                        case 2:
                               setPlayerSympol(game.getBoard()[i][j], cell6);
                            break;
                        default:
                            break;
                    }
                }
                else if(i == 2){
                    switch (j) {
                        case 0:
                              setPlayerSympol(game.getBoard()[i][j], cell7);
                            break;
                        case 1:
                              setPlayerSympol(game.getBoard()[i][j], cell8);
                            break;
                        case 2:
                              setPlayerSympol(game.getBoard()[i][j], cell9);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        game.setMovesCount(numOfMoves);
    }
    /**
     * Initializes the controller class.
     */
      @FXML 
    private void saveBtnHandler(ActionEvent event){
        String nextMove = " ";
        if(game.getNextMove() == 0){
            nextMove = "X";
        }
        else if(game.getNextMove() == 1){
            nextMove = "O";
        }
        ClientHandler.saveGameRequest(nextMove);
       // ClientHandler.changeScene("start");
    }
      @FXML 
        private void exitHandler(ActionEvent event) {
//        ClientHandler.getPlayer().updateStatus("online");
        ClientHandler.setReplay(false);
        ClientHandler.changeScene("Start");
    }
      @FXML 
    private void homeBtnHandler(ActionEvent event){
        ClientHandler.changeScene("Start");
    }
      @FXML 
    private void okBtnHandler(ActionEvent event){
        
        clearCells();
        game.clearBoard();
        
        if(ClientHandler.getGameAccepted()){
            
            
            game.setNextMove(0);
            game.setMovesCount(0);
            setPlayerData();
            finish = false;
            isDraw = false;
            setSceneVisibility(false);
            waitingSubscene.setVisible(false);
            okBtn.setDisable(true);
            ClientHandler.setReplay(false);
            ClientHandler.setIsLoaded(false);
        }
        else{
            ClientHandler.changeScene("Start");
        }
    }
    private void clearCells(){
        cell1.setImage(null);
        cell2.setImage(null);
        cell3.setImage(null);
        cell4.setImage(null);
        cell5.setImage(null);
        cell6.setImage(null);
        cell7.setImage(null);
        cell8.setImage(null);
        cell9.setImage(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               clearCells();
        setSceneVisibility(false);
        ClientHandler.setMultigameCtrl(this);
        savingSubscene.setVisible(false);
        homeBtn.setDisable(true);
        waitingSubscene.setVisible(false);
        okBtn.setDisable(true);
        // chatBox.appendText(String.format("\n"));
        
        game = new Game();
        
        if(ClientHandler.getIsLoaded()){
            
            setGameLoaded();
            setLoadedBoard();
        }
        else{
            game.setNextMove(0);
            setPlayerData();
        }
        
        finish = false;
        isDraw = false;
    }

    
    public Label getWaitingLbl(){
        
        return this.waitingLbl;
    }
    
    public Label getSavingLbl(){
        
        return this.savingLbl;
    }
    
    public Button getOkBtn(){
        
        return this.okBtn;
    }
    
    public Button getHomtBtn(){
        
        return this.homeBtn;
    }
    
    public AnchorPane getSavingSubscene(){
        
        return this.savingSubscene;
    }
    
    public AnchorPane getWaitingSubscene(){
        
        return this.waitingSubscene;
    }
}
