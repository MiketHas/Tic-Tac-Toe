import java.util.Scanner;

public class Main {

    static int emptySpaces;
    static int whichRow;
    static int whichCol;
    static int xo;

    static char[][] board;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    public static void start() {
        emptySpaces = 9;
        whichRow = 0;
        whichCol = 0;
        xo = 0;

        boardArray();

        // Initiating a game board
        printBoard(board);

        // Initiating playersMove Array that will take coordinates
        String[] playersMove = new String[2];

        // Starting the game
        gameON(board, playersMove);

        // Checking if the game is over
        System.out.println(getState(board));
        System.out.println("Would you like to play again? Type 1 for YES, 2 for NO");
        String again = input.next();
        if (again.equals("1")) {
            start();
        }
    }

    public static void boardArray() {
        // Declaring a 2D Array and filling it with spaces
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // method to print out the game board
    public static void printBoard(char[][] board) {
        System.out.println("---------");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("---------");
    }

    // Possibilities of X/O win
    private static boolean isTheWinner(char[][] boardField, char symbol) {
        return (boardField[0][0] == symbol && boardField[0][1] == symbol && boardField[0][2] == symbol) ||
                (boardField[1][0] == symbol && boardField[1][1] == symbol && boardField[1][2] == symbol) ||
                (boardField[2][0] == symbol && boardField[2][1] == symbol && boardField[2][2] == symbol) ||

                (boardField[0][0] == symbol && boardField[1][0] == symbol && boardField[2][0] == symbol) ||
                (boardField[0][1] == symbol && boardField[1][1] == symbol && boardField[2][1] == symbol) ||
                (boardField[0][2] == symbol && boardField[1][2] == symbol && boardField[2][2] == symbol) ||

                (boardField[0][0] == symbol && boardField[1][1] == symbol && boardField[2][2] == symbol) ||
                (boardField[0][2] == symbol && boardField[1][1] == symbol && boardField[2][0] == symbol);
    }

    // Determining if the game is over
    public static boolean isGameOver() {

        return emptySpaces == 0;
    }

    // Method starting the game
    public static void gameON(char[][] boardField, String[] playersMove) {
        System.out.print("Enter the coordinates: ");
        playersMove[0] = input.next();
        playersMove[1] = input.next();
        if (areNumbers(playersMove)) {
            goodMove(boardField, playersMove);
        } else {
            gameON(boardField, playersMove);
        }

    }

    // Checking if players typed the numbers at all
    public static boolean areNumbers(String[] playersMove) {

        if ((!playersMove[0].matches("[0 123456789]+")) || (!playersMove[1].matches("[0 123456789]+"))) {
            System.out.println("You should enter numbers!");
            return false;
        } else {
            return true;
        }
    }

    // If playersMove is a correct number, change it to a String
    public static void goodMove(char[][] boardField, String[] playersMove) {
        whichRow = Integer.parseInt(playersMove[0]);
        whichCol = Integer.parseInt(playersMove[1]);

        validMove(boardField, playersMove);
    }

    // Making a valid move on the board
    public static void validMove(char[][] boardField, String[] playersMove) {

        if (!areCoorOk()) {
            System.out.println("Coordinates should be from 1 to 3!");
            gameON(boardField, playersMove);
        } else if (isFieldTaken(boardField)) {
            System.out.println("This cell is occupied! Choose another one!");
            gameON(boardField, playersMove);
        } else {
            switch (xo) {
                case 0:
                    boardField[whichRow - 1][whichCol - 1] = 'X';
                    emptySpaces--;
                    xo = 1;
                    printBoard(boardField);
                    if ((isGameOver()) || isTheWinner(boardField, 'X')) {
                        getState(boardField);
                    } else {
                        gameON(boardField, playersMove);
                    } break;
                case 1:
                    boardField[whichRow - 1][whichCol - 1] = 'O';
                    emptySpaces--;
                    xo = 0;
                    printBoard(boardField);
                    if ((isGameOver()) || isTheWinner(boardField, 'O')) {
                        getState(boardField);
                    } else {
                        gameON(boardField, playersMove);
                    } break;
            }
        }
    }
    // Checking if coordinates are between 1-3
    public static boolean areCoorOk() {
        return (whichRow >= 1) && (whichRow <= 3) && (whichCol >= 1) && (whichCol <= 3);
    }

    // Checking if the chosen field is already taken
    public static boolean isFieldTaken(char[][] boardField) {
        if (boardField[whichRow-1][whichCol-1] == ' ') {
            return false;
        }
        return true;
    }

    // Checking if the game is over
    public static String getState(char[][] boardField) {
        boolean xWins = isTheWinner(boardField, 'X');
        boolean oWins = isTheWinner(boardField, 'O');

        if (xWins) { //True/False
            return "X wins";
        } else if (oWins) { //True/False
            return "O wins";
        } else {
            return "Draw";
        }
    }
}