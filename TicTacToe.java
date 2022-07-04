// NAME: SHYAM PRAJAPATI
// ENTRY NUMBER: 2020CSB1110
// Tictactoe game(player vs computer) designed by SHAYM PRAJAPATI

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        player p1 = new player();
        player p2 = new player();
        //enter 1 for pvp mode and 2 for pvc mode
        System.out.print("Enter the mode you want to play: 1 for player vs player, 2 for player vs computer ");
        int mode;
        mode = in.nextInt();
        in.nextLine();
        if (mode == 1) //pvp mode
        {
            //taking name inputs of players
            System.out.print("Enter player 1's name ");
            p1.name = in.nextLine();
            System.out.print("Enter player 2's name ");
            p2.name = in.nextLine();
            // initialising 3x3 char array tictactoe board
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    board[i][j] = '-';
            }
            // initially board looks like:
            // - - -
            // - - -
            // - - -
            boolean isplayer1 = true;// to track which player's turn
            boolean gameended = false;// to track if game is still on

            while (!gameended) {
                drawboard(board);
                if (isplayer1)
                    System.out.println(p1.getName() + "'s turn ");
                else
                    System.out.println(p2.getName() + "'s turn");
                char symbol;
                if (isplayer1) {
                    symbol = 'X';
                } else
                    symbol = 'O';
                int row;
                int col;
                // players have to enter inputs as row number and column
                while (true) {
                    System.out.println("enter a row: 0,1 or 2 ");
                    row = in.nextInt();
                    System.out.println("enter a column: 0,1 or 2 ");
                    col = in.nextInt();
                    // if player enters wrong inputs continue
                    if (row < 0 || col < 0 || row > 2 || col > 2) {
                        System.out.println("your rows and columns are out of bounds");
                    } else if (board[row][col] != '-')
                        System.out.println("the cell is already used ");
                    else
                        break;
                }
                board[row][col] = symbol;
                // check if any of the two player has won or the match is tied
                if (haswon(board) == 'X') {
                    System.out.println(p1.getName() + " has won");
                    gameended = true;// end the game
                } else if (haswon(board) == 'O') {
                    System.out.println(p2.getName() + " has won");
                    gameended = true;// end the game
                } else {
                    if (hastied(board)) {
                        System.out.println("match tied");// match tied end the game
                        break;
                    } else {
                        isplayer1 = !isplayer1;// other player's turn
                    }
                }
            }
            drawboard(board);

            in.close();
        } else if (mode == 2) //pvc mode
        {   // player name input
            System.out.print("Enter player 1's name ");
            p1.name = in.nextLine();
            p2.name = "computer";
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    board[i][j] = '-';
            }
            drawboard(board);

            boolean isplayer1 = true;//keeps track on player's turn
            boolean gameended = false;//keep track on game progress

            while (!gameended) {
                if (isplayer1) {
                    System.out.println(p1.getName() + "'s turn ");
                    playerturn(board, in);
                    drawboard(board);
                } else {
                    System.out.println(p2.getName() + "'s turn");
                    computerturn(board);
                    drawboard(board);
                }

                if (haswon(board) == 'X') {
                    System.out.println(p1.getName() + " has won");
                    gameended = true;
                } else if (haswon(board) == 'O') {
                    System.out.println(p2.getName() + " has won");
                    gameended = true;
                } else {
                    if (hastied(board)) {
                        System.out.println("match tied");
                        break;
                    } else {
                        isplayer1 = !isplayer1;
                    }
                }
            }
            drawboard(board);
            in.close();
        }

    }
// method used when its player's turn
    private static void playerturn(char[][] board, Scanner in) {
        //taking cell inputs as row and column 
        int row, col;
        while (true) {
            System.out.println("enter a row: 0,1 or 2 ");
            row = in.nextInt();
            System.out.println("enter a column: 0,1 or 2 ");
            col = in.nextInt();
            if (row < 0 || col < 0 || row > 2 || col > 2) {
                System.out.println("your rows and columns are out of bounds ");
            } else if (board[row][col] != '-')
                System.out.println("the cell is already used ");
            else
                break;
        }
        board[row][col] = 'X';
    }
// method used when its computer's turn
    private static void computerturn(char[][] board) {

        Random rand = new Random();//rand will store random integer between 1-9

        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            // checking if the cell corresponding to rand is empty
            if (isValidMove(board, computerMove)) {
                break;
            }
        }
        System.out.println("Computer chose " + computerMove);
        placeMove(board, computerMove, 'O');//placing 'O' corresponding to rand
    }
// method to check if computer can place 'O' on cell corresponding to rand 
    private static boolean isValidMove(char[][] board, int position) {
        switch (position) {
            case 1:
                return (board[0][0] == '-');
            case 2:
                return (board[0][1] == '-');
            case 3:
                return (board[0][2] == '-');
            case 4:
                return (board[1][0] == '-');
            case 5:
                return (board[1][1] == '-');
            case 6:
                return (board[1][2] == '-');
            case 7:
                return (board[2][0] == '-');
            case 8:
                return (board[2][1] == '-');
            case 9:
                return (board[2][2] == '-');
            default:
                return false;
        }
    }
   //method used by computer to place 'O' at rand 
    private static void placeMove(char[][] board, int position, char symbol) {
        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][1] = symbol;
                break;
            case 3:
                board[0][2] = symbol;
                break;
            case 4:
                board[1][0] = symbol;
                break;
            case 5:
                board[1][1] = symbol;
                break;
            case 6:
                board[1][2] = symbol;
                break;
            case 7:
                board[2][0] = symbol;
                break;
            case 8:
                board[2][1] = symbol;
                break;
            case 9:
                board[2][2] = symbol;
                break;
            default:
                System.out.println("not a valid move ");
        }
    }
   //method to print the tictactoe board
    public static void drawboard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
// method to check if a particular player has won
    public static char haswon(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-')
                return board[i][0];
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-')
                return board[0][i];
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
            return board[0][0];
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-')
            return board[0][2];
        return '-';
    }
// method to check if the match has tied
    public static boolean hastied(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

}

