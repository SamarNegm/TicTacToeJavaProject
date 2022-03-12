package clientHandler;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private char[][] board;
    private int nextMove;
    private int movesCount;
    private final char playerX = 'X';
    private final char playerO = 'O';
    private int winner;
    private static int mode;
    private static CellPosition moveOfNextPlayer;

    public static class CellPosition {
        // Cell variables
        public int row = -1;
        public int col = -1;

        // Default
        public CellPosition() {
        }

        // Intilized cell
        public CellPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Default constructor for the game
    public Game() {
        this.board = new char[][] { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        movesCount = 0;
    }

    // Getters and Setters for game class
    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] loadedBoard) {
        this.board = loadedBoard;
    }

    public static int getMode() {
        return mode;
    }

    public static void setMoveOfNextPlayer(CellPosition move) {
        moveOfNextPlayer = move;
    }

    public static void setMode(int mode) {
        Game.mode = mode;
    }

    public void setMovesCount(int moves) {
        movesCount = moves;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }

    public static CellPosition getMoveOfNextPlayer() {
        return Game.moveOfNextPlayer;
    }

    public int getNextMove() {
        return nextMove;
    }

    // Setters for cells
    public void setCell1(char value) {
        board[0][0] = value;
        // Increasing the move count untill it hits all 9 cells in each set cell
        movesCount++;
    }

    public void setCell2(char value) {
        board[0][1] = value;
        movesCount++;
    }

    public void setCell3(char value) {
        board[0][2] = value;
        movesCount++;
    }

    public void setCell4(char value) {
        board[1][0] = value;
        movesCount++;
    }

    public void setCell5(char value) {
        board[1][1] = value;
        movesCount++;
    }

    public void setCell6(char value) {
        board[1][2] = value;
        movesCount++;
    }

    public void setCell7(char value) {
        board[2][0] = value;
        movesCount++;
    }

    public void setCell8(char value) {
        board[2][1] = value;
        movesCount++;
    }

    public void setCell9(char value) {
        board[2][2] = value;
        movesCount++;
    }

    // Check for row matching in all grid level
    public boolean checkRows() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ') {
                if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                    result = true;
                    if (board[i][0] == playerX) {
                        winner = 0; // Player X is winner 0
                    } else {
                        winner = 1; // Player O is winner 1
                    }
                }
            }
        }
        return result;
    }

    // Check for column matching in all grid level
    public boolean checkCols() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != ' ') {
                if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                    result = true;
                    if (board[0][i] == playerX) {
                        winner = 0;
                    } else {
                        winner = 1;
                    }
                }
            }
        }
        return result;
    }

    // Check for diagonals matching in all grid level
    public boolean checkDiagonals() {
        boolean result = false;
        // Diagonal from left to bottom right checker
        if (board[0][0] != ' ') {
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                result = true;
                if (board[0][0] == playerX) {
                    winner = 0;
                } else {
                    winner = 1;
                }
            }
        }
        // Diagonal from right to bottom left checker
        if (board[0][2] != ' ') {
            if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
                result = true;
                if (board[0][2] == playerX) {
                    winner = 0;
                } else {
                    winner = 1;
                }
            }
        }
        return result;
    }

    // checker for moves if reached 9 move, the grid is now full
    public boolean checkNumberOfMoves() {
        boolean result = false;
        if (movesCount == 9) {
            result = true;
        }

        return result;
    }

    // Game status checker
    public int checkWin() {
        boolean resultRows;
        boolean resultCols;
        boolean resultDiagonals;
        boolean resultMoves = false;
        int win = 0; // 0 game didn't ended yet
        resultRows = checkRows();
        resultCols = checkCols();
        resultDiagonals = checkDiagonals();

        if (resultCols || resultRows || resultDiagonals) {
            win = 1; // 1 player already won
        } else {
            resultMoves = checkNumberOfMoves();
        }

        if (resultMoves == true) {
            win = 2; // draw, no one won the grid already filled
        }
        return win;
    }

    // Cleaning the board
    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Get number of empty cells
    public int getNumberOfMoves() {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    count++;
                }
            }
        }
        return count;
    }

    // Get boolean of empty cell
    public boolean checkMovesOnBoard() {
        boolean move = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    move = true;
                    break;
                }
            }
        }
        return move;
    }

    // Load empty cell to list with it's postion
    public ArrayList getAvailableMoves() {
        ArrayList<CellPosition> availableMoves = new ArrayList();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    CellPosition c = new CellPosition(i, j);
                    availableMoves.add(c);
                }
            }
        }
        return availableMoves;
    }

    // Check game status and return score
    public int evaluateMove(char[][] board) {
        boolean resultRows;
        boolean resultCols;
        boolean resultDiagonals;

        int score = 0;
        resultRows = checkRows();

        // Check for matching row
        if (resultRows) {
            if (winner == 1) { // If O won
                score = 10;
            } else {
                score = -10;// If X won
            }
            return score;
        }

        // Check for matching column
        resultCols = checkCols();
        if (resultCols) {
            if (winner == 1) {
                score = 10;
            } else {
                score = -10;
            }
            return score;
        }

        // Check for matching diagonal
        resultDiagonals = checkDiagonals();
        if (resultDiagonals) {
            if (winner == 1) {
                score = 10;
            } else {
                score = -10;
            }
            return score;
        }
        return score;
    }

    public int minimax(char[][] board, int depth, boolean isMaximizerPlayer) {
        int score = evaluateMove(board);

        if (score == 10) {
            return (score * (getNumberOfMoves() + 1)) - depth;
        } else if (score == -10) {
            return (score * (getNumberOfMoves() + 1)) + depth;
        }

        boolean checkLeftMoves = checkMovesOnBoard();
        if (!checkLeftMoves) {
            return 0;
        }
        int bestValue;
        if (isMaximizerPlayer) {
            bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = playerO;
                        bestValue = Math.max(bestValue, minimax(board, depth + 1, !isMaximizerPlayer));
                        board[i][j] = ' ';
                    }
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = playerX;
                        bestValue = Math.min(bestValue, minimax(board, depth + 1, !isMaximizerPlayer));
                        board[i][j] = ' ';
                    }
                }
            }
        }
        return bestValue;
    }

    public CellPosition getBestMove() {
        int bestValue = Integer.MIN_VALUE;
        CellPosition bestCell = new CellPosition();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = playerO;
                    int newValue = minimax(board, 0, false);
                    board[i][j] = ' ';

                    if (newValue > bestValue) {
                        bestValue = newValue;
                        bestCell.row = i;
                        bestCell.col = j;
                    }
                }
            }
        }
        return bestCell;
    }

    // Randomizing the cell for the array list
    public CellPosition getRandomCell() {
        ArrayList<CellPosition> availableMoves = getAvailableMoves();
        int length = availableMoves.size();
        Random random = new Random();

        int rand = random.nextInt(length);

        return availableMoves.get(rand);
    }

    // Converting to one dimensional array method
    public static char[] convertToOneDimension(char[][] arr) {

        char[] oneDimensionArr = new char[9];
        int index = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                oneDimensionArr[index] = arr[i][j];
                index++;
            }
        }
        return oneDimensionArr;
    }

    // Converting to two dimensional array method
    public static char[][] convertToTwoDimension(char[] arr) {

        char[][] twoDimensionArr = new char[3][3];
        int index = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                twoDimensionArr[i][j] = arr[index];
                index++;
            }
        }
        return twoDimensionArr;

    }
}