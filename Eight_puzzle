import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.lang.Integer;
import java.lang.String; 
/**
 * class eight_puzzle for CSDS 391 P1
 * @instructor Dr. Lewicki
 * @author Sharan Mehta 
 */
public class Eight_puzzle 
{
    // instance variables - each 8puzzle object has a 3 by 3 game board
    private static Board gameBoard = new Board();
    /*
    public char [][] boardState = new char [3][3];
    public int maxnodes = Integer.MAX_VALUE;    // initially maxNodes is 2,147,483,647
    public char [] moves = new char [3];
    */
    /**
     * Constructor for objects of class eight_puzzle
     */
    public Eight_puzzle()
    {
        // initialise instance variables
        /*
        char n = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                boardState[i][j] = n;
                n++;
            } // end of for j
        } // end of for i
        boardState[0][0] = 'b';    //replacing the 0 digit with the blank tile
        moves[0] = 'u';
        moves[1] = 'd';
        moves[2] = 'l';
        moves[3] = 'r';
        */
    } // end of constructor
    /*
    public void disp(char y) {
        if (y == 'u') { //idicates up
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (boardState[i][j] == 'b' && i>0) {
                                    boardState[i][j] = boardState[i-1][j];
                                    boardState[i-1][j] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of up move 
                    if (y == 'd') { //idicates down
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (boardState[i][j] == 'b' && i<2) {
                                    boardState[i][j] = boardState[i+1][j];
                                    boardState[i+1][j] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of down move 
                    if (y == 'l') { //idicates left
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (boardState[i][j] == 'b' && j>0) {
                                    boardState[i][j] = boardState[i][j-1];
                                    boardState[i][j-1] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of left move
                    if (y == 'r') { //idicates right
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (boardState[i][j] == 'b' && j<2) {
                                    boardState[i][j] = boardState[i][j+1];
                                    boardState[i][j+1] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
        } // end of right move 
    } // end of disp method
    public void setState(String command) {
        int n = 10;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) { 
                boardState[i][j] = command.charAt(n);
                n++;
            } // end of for j
            n++;  // we have to skip the blank spaces
        } // end of for i
    } // end of setState method
    public void printState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sb.append(boardState[i][j]);
            } // end of for j
            sb.append(" "); //adding a space
        } // end of for i
        System.out.println(sb);
    } // end of printState 
    public void randomize(int n) { 
        Random rand = new Random(1234);
        for (int i = 0; i < n; i++) {
            this.disp(moves[rand.nextInt(4)]);
        } // end of for i
    } // end of randomize
    public void setMaxNodes(int n) {
        this.maxnodes = n;
    } // end of setMaxNodes
    */
    /*
     * main method
     * takes an input file filled with commands
     */
    public static void main(String [] args) {
        try {
            File input = new File("inputFile.txt");
            Scanner sc = new Scanner (input);
            while (sc.hasNextLine()) {
                String command = sc.next();
                char c = command.charAt(0);
                int length = command.length() - 1;
                if (c == 's') {    //this indicates setState <state> with state starting at index 10
                    gameBoard.setState(command);
                } else // end of setState
                // please note in write up that move is called disp (as in displacement)
                if (c == 'd') {    //this indicates disp <direction> with direction starting at index 6
                    gameBoard.disp(command.charAt(6));
                } else // end of disp
                if (c == 'p') {
                    gameBoard.printState();
                } else 
                if (c == 'r') { //this indicates randomizeState <n> with n at index 16
                    int n = command.charAt(16);
                    gameBoard.randomize(n);
                    gameBoard.printState();
                } else  // end of randomizeState 
                if (c == 'm') { //this indicates maxNodes <n> with n at index 10
                    int n = Integer.parseInt(command.substring(10,length));
                    gameBoard.setMaxNodes(n);
                } else  //end of maxNodes
                if (c == 'A' || c == 'a') { //this indicates A* <1> or A* <2> with heurstic # at index 4
                    System.out.println("The current board state");
                    gameBoard.printState();
                    int h = command.charAt(4) - '0';
                    gameBoard.aStar(h);
                } else //end of A-star solver
                if (c == 'b') { //this indicates beam <k> with k at index 6
                    System.out.println("The current board state");
                    gameBoard.printState();
                    int k = Integer.parseInt(command.substring(6,length));
                    gameBoard.beam(k);
                } //end of beam solver 
            } // end of while loop
            sc.close();
        } catch (FileNotFoundException e) { 
            System.out.println ("File not found error");
            e.printStackTrace();
        } // end of try-catch
    } // end of public void main 
} // end of eight_puzzle
