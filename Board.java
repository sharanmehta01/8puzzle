import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Integer;
import java.lang.String;
/**
 * The game board for the 8-puzzle
 * @instructor Dr. Lewicki
 * @course CSDS 391 
 * @author Sharan Mehta 
 */
public class Board
{
    // instance variables - each Board object has a 3 by 3 platofrm
    private char [][] boardState = new char [3][3];
    public int maxnodes = Integer.MAX_VALUE;    // initially maxNodes is 2,147,483,647
    public char [] moves = new char [3];
    //public char [][] prevState = new char [3][3];
    private Board prevBoard = new Board();
    private int heuristic;
    public char [][] goalState = new char [3][3];
    private char backTrack;
    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        // initialise instance variables
        char n = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                boardState[i][j] = n;
                goalState[i][j] = n;
                n++;
            } // end of for j
        } // end of for i
        boardState[0][0] = 'b';    //replacing the 0 digit with the blank tile to create a solved statrting state
        goalState[0][0] = 'b';
        moves[0] = 'u';
        moves[1] = 'd';
        moves[2] = 'l';
        moves[3] = 'r';
        prevBoard = null;
        int heuristic = 0;
    } // end of constructor
    public void setPrevBoard(Board prevBoard) {
        this.prevBoard = prevBoard;
    } // end of setter method
    public Board getPrevBoard() {
        return this.prevBoard;
    } // end of getter method
    public void setHeuristic(int x) {
        this.heuristic = x;
    } // end of setter method
    public int getHeuristic() {
        return this.heuristic;
    } // end of getter method
    public char[][] getBoardState() {
        return this.boardState;
    } // end of getter method
    public void setBackTrack(char bt) {
        this.backTrack = bt;
    } // end of setter method
    public char getBackTrack() {
        return this.backTrack;
    } // end of getter method
    /**
     * The disp method moves the blank space when possible
     * disp is named after displacement
     * @param y is a character that symbolizes which way we want to move in
     * @returns void. Method completes computation internally
     */
    public void disp(char y) {
        if (y == 'u') { //indicates up
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && i>0) {
                                    this.boardState[i][j] = this.boardState[i-1][j];
                                    this.boardState[i-1][j] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of up move 
                    if (y == 'd') { //indicates down
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && i<2) {
                                    this.boardState[i][j] = this.boardState[i+1][j];
                                    this.boardState[i+1][j] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of down move 
                    if (y == 'l') { //indicates left
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && j>0) {
                                    this.boardState[i][j] = this.boardState[i][j-1];
                                    this.boardState[i][j-1] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
                    } else // end of left move
                    if (y == 'r') { //indicates right
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && j<2) {
                                    this.boardState[i][j] = this.boardState[i][j+1];
                                    this.boardState[i][j+1] = 'b';
                                } // end of movement
                            } // end of for j
                        } // end of for i
        } // end of right move 
        this.printState();
    } // end of disp method
    /**
     * The canMove method checks if the blank space can move
     * @param y is a character that symbolizes which way we want to move in
     * @returns booelan depending of if we can move
     */
    public boolean canMove(char y) {
        boolean can = false;
        if (y == 'u') { //indicates up
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && i>0) {
                                    can = true;
                                } // end of check
                            } // end of for j
                        } // end of for i
                    } else // end of up move check
                    if (y == 'd') { //indicates down
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && i<2) {
                                    can = true;
                                } // end of check
                            } // end of for j
                        } // end of for i
                    } else // end of down move check
                    if (y == 'l') { //indicates left
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && j>0) {
                                   can = true;
                                } // end of check
                            } // end of for j
                        } // end of for i
                    } else // end of left move check
                    if (y == 'r') { //indicates right
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) { 
                                if (this.boardState[i][j] == 'b' && j<2) {
                                    can = true;
                                } // end of check
                            } // end of for j
                        } // end of for i
        } // end of right move check
        return can;
    } // end of canMove
    /**
     * The setState method arragnes the tiles on the board
     * @param command is a string that is read from the instructions file
     * @returns void. Method completes computation internally
     */
    public void setState(String command) {
        int n = 10;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) { 
                this.boardState[i][j] = command.charAt(n);
                n++;
            } // end of for j
            n++;  // we have to skip the blank spaces
        } // end of for i
    } // end of setState method
    /**
     * The printState method prints the current arrangement of tiles
     * @returns void. The method prints an output to the system dirctly
     */
    public void printState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sb.append(this.boardState[i][j]);
            } // end of for j
            sb.append(" "); //adding a space
        } // end of for i
        System.out.println(sb);
    } // end of printState 
    /**
     * The randomize method makes random moves to the board state
     * @param n is an integer that symbolizes how many random moves we want
     * @returns void. Method completes computation internally and prints the new state directly 
     */
    public void randomize(int n) { 
        Random rand = new Random(1234);
        for (int i = 0; i < n; i++) {
            char r = moves[rand.nextInt(4)];
            if (canMove(r)) {
                this.disp(r);
            } else {
                i--;
            } // end of if else
        } // end of for i
        System.out.println("After randominzing the board state is");
        this.printState();
    } // end of randomize
    /**
     * The setMaxNodes method changes the number of max nodes we are allowed to visit
     * @param n is an integer that symbolizes the new max node limit
     * @returns void. Method completes computation internally
     */
    public void setMaxNodes(int n) {
        this.maxnodes = n;
    } // end of setMaxNodes
    /**
     * The firstHeursitc method calculates the heurisitc associated with displaced tiles
     * @param char[][] takes in the board state for which the heuristic is being calculated
     * @returns int is the heurstic value associated with that board state
     */
    public int firstHeuristic (char [][] boardState) {
        int h1 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if ((boardState[i][j] == 1) && (i != 0) && (j != 0)) {
                    h1++;
                }
                if ((boardState[i][j] == 2) && (i != 0) && (j != 1)) {
                    h1++;
                }
                if ((boardState[i][j] == 3) && (i != 0) && (j != 2)) {
                    h1++;
                }
                if ((boardState[i][j] == 4) && (i != 1) && (j != 0)) {
                    h1++;
                }
                if ((boardState[i][j] == 5) && (i != 1) && (j != 1)) {
                    h1++;
                }
                if ((boardState[i][j] == 6) && (i != 1) && (j != 2)) {
                    h1++;
                }
                if ((boardState[i][j] == 7) && (i != 2) && (j != 0)) {
                    h1++;
                }
                if ((boardState[i][j] == 8) && (i != 2) && (j != 1)) {
                    h1++;
                }
            }// end of for j
        } // end of for i
        return h1;
    } // end of firstHeuristic
    /**
     * The secondHeursitc method calculates the heurisitc associated with manhattan distance
     * @param char[][] takes in the board state for which the heuristic is being calculated
     * @returns int is the heurstic value associated with that board state
     */
    public int secondHeuristic (char [][] boardState) {
        int h2 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if ((boardState[i][j] == 1) && (i != 0) && (j != 0)) {
                    h2 = h2 + i + j;
                }
                if ((boardState[i][j] == 2) && (i != 0) && (j != 1)) {
                    h2 = h2 + i + 1;
                }
                if ((boardState[i][j] == 3) && (i != 0) && (j != 2)) {
                    h2 = h2 + i + (2-j);
                }
                if ((boardState[i][j] == 4) && (i != 1) && (j != 0)) {
                    h2 = h2 + j + 1;
                }
                if ((boardState[i][j] == 5) && (i != 1) && (j != 1)) {
                    h2 = h2 + 1 + 1;
                }
                if ((boardState[i][j] == 6) && (i != 1) && (j != 2)) {
                    h2 = h2 + 1 + (2-j);
                }
                if ((boardState[i][j] == 7) && (i != 2) && (j != 0)) {
                    h2 = h2 + (2-i) + j;
                }
                if ((boardState[i][j] == 8) && (i != 2) && (j != 1)) {
                    h2 = h2 + (2-i) + 1;
                }
            }// end of for j
        } // end of for i
        return h2;
    } // end of secondHeuristic
    /**
     * The thirdHeursitc method calculates the heurisitc associated with displaced tiles and manhattan distance
     * Made this function for local beam search
     * @param char[][] takes in the board state for which the heuristic is being calculated
     * @returns int is the heurstic value associated with that board state
     */
    public int thirdHeuristic (char [][] boardState) {
        int h1 = 0;
        int h2 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if ((boardState[i][j] == 1) && (i != 0) && (j != 0)) {
                    h1++;
                    h2 = h2 + i + j;
                }
                if ((boardState[i][j] == 2) && (i != 0) && (j != 1)) {
                    h1++;
                    h2 = h2 + i + 1;
                }
                if ((boardState[i][j] == 3) && (i != 0) && (j != 2)) {
                    h1++;
                    h2 = h2 + i + (2-j);
                }
                if ((boardState[i][j] == 4) && (i != 1) && (j != 0)) {
                    h1++;
                    h2 = h2 + j + 1;
                }
                if ((boardState[i][j] == 5) && (i != 1) && (j != 1)) {
                    h1++;
                    h2 = h2 + 1 + 1;
                }
                if ((boardState[i][j] == 6) && (i != 1) && (j != 2)) {
                    h1++;
                    h2 = h2 + 1 + (2-j);
                }
                if ((boardState[i][j] == 7) && (i != 2) && (j != 0)) {
                    h1++;
                    h2 = h2 + (2-i) + j;
                }
                if ((boardState[i][j] == 8) && (i != 2) && (j != 1)) {
                    h1++;
                    h2 = h2 + (2-i) + 1;
                }
            }// end of for j
        } // end of for i
        return h1+h2;
    } // end of thirdHeuristic
    
    /*
    /**
     * The sort method re-organizes the queue based on heuristic
     * @returns void
     */
    /*
    public void sort() {
        int s = this.size();
        Board current = new Board();
        for (int i = 0; i < s; i++) {
            current = this.get(i);
            for (int j = i; j < s; j++) {
                if (current.getHeuristic() > this.get(j).getHeuristic()) {
                    this.add(i,this.get(j)); 
                    this.add(j,current);
                } // end of if
            } // end of for j
        } // end of for i
    } // end of sort
    */
    /**
     * The aStar method solves the board using A* search algorithms
     * @param h is an integer that symbolizes the heuristic function we want (h1 or h2) 
     * @returns boolean depending on weather the board is solved or not
     */
    public boolean aStar(int h) {
       //char [][] currentState = new char[3][3];
       Board currentBoard = new Board();
       int g = 0;
       boolean solved = false;
       for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                currentBoard.boardState[i][j] = this.boardState[i][j];
            } // end of for j
       } // end of for i
       if (h == 1) {
           ArrayList <Board> visited = new ArrayList <Board> (0);
           ArrayList <Board> queue = new ArrayList <Board> ();
           while ((visited.size() <= maxnodes) && (solved == false)) {
               if (currentBoard.getBoardState().equals(goalState)) {
                   solved = true;
               } else {
                   currentBoard.setHeuristic(g+firstHeuristic(currentBoard.getBoardState()));
                   g++;
                   if (currentBoard.canMove('r')) {
                       Board right = new Board();
                       right.prevBoard = currentBoard;
                       currentBoard.disp('r');
                       right.setBackTrack('l');
                       right = currentBoard;
                       right.setHeuristic(g+firstHeuristic(right.getBoardState()));
                       queue.add(right);
                   } //end of right node
                   if (currentBoard.canMove('l')) {
                       Board left = new Board();
                       left.prevBoard = currentBoard;
                       currentBoard.disp('l');
                       left.setBackTrack('r');
                       left = currentBoard;
                       left.setHeuristic(g+firstHeuristic(left.getBoardState()));
                       queue.add(left);
                   } //end of left node
                   if (currentBoard.canMove('u')) {
                       Board up = new Board();
                       up.prevBoard = currentBoard;
                       currentBoard.disp('u');
                       up.setBackTrack('d');
                       up = currentBoard;
                       up.setHeuristic(g+firstHeuristic(up.getBoardState()));
                       queue.add(up);
                   } //end of up node
                   if (currentBoard.canMove('d')) {
                       Board down = new Board();
                       down.prevBoard = currentBoard;
                       currentBoard.disp('d');
                       down.setBackTrack('u');
                       down = currentBoard;
                       down.setHeuristic(g+firstHeuristic(down.getBoardState()));
                       queue.add(down);
                   } //end of down node
                   //queue.sort();
                   int s = queue.size();
                   Board current = new Board();
                   for (int i = 0; i < s; i++) {
                       current = queue.get(i);
                       for (int j = i; j < s; j++) {
                           if (current.getHeuristic() > queue.get(j).getHeuristic()) {
                               queue.add(i,queue.get(j));
                               queue.add(j,current);
                           } // end of if
                       } // end of for j
                   } // end of for i
                   // end of sorting
                   if (visited.contains(queue.get(0))) {  //if we've already been to the node before then we are risk of getting into a loop
                       solved = false;
                   } else {
                       currentBoard = queue.get(0);
                       queue.remove(0);
                       visited.add(currentBoard);
                       //currentBoard.printState();
                   } // end of if else
               } // end of if else
           } //end of while
           if (solved) {
               System.out.println("the path is");
               for (int i = 0; i < visited.size(); i++) {
                    visited.get(i).printState();
               } // end of for i
           } // end of if
       } else //end of h1
       if (h == 2) {
           ArrayList <Board> visited = new ArrayList <Board> (0);
           ArrayList <Board> queue = new ArrayList <Board> ();
           while ((visited.size() <= maxnodes) && (solved == false)) {
               if (currentBoard.getBoardState().equals(goalState)) {
                   solved = true;
               } else {
                   currentBoard.setHeuristic(g+secondHeuristic(currentBoard.getBoardState()));
                   g++;
                   if (currentBoard.canMove('r')) {
                       Board right = new Board();
                       right.prevBoard = currentBoard;
                       currentBoard.disp('r');
                       right.setBackTrack('l');
                       right = currentBoard;
                       right.setHeuristic(g+secondHeuristic(right.getBoardState()));
                       queue.add(right);
                   } //end of right node
                   if (currentBoard.canMove('l')) {
                       Board left = new Board();
                       left.prevBoard = currentBoard;
                       currentBoard.disp('l');
                       left.setBackTrack('r');
                       left = currentBoard;
                       left.setHeuristic(g+secondHeuristic(left.getBoardState()));
                       queue.add(left);
                   } //end of left node
                   if (currentBoard.canMove('u')) {
                       Board up = new Board();
                       up.prevBoard = currentBoard;
                       currentBoard.disp('u');
                       up.setBackTrack('d');
                       up = currentBoard;
                       up.setHeuristic(g+secondHeuristic(up.getBoardState()));
                       queue.add(up);
                   } //end of up node
                   if (currentBoard.canMove('d')) {
                       Board down = new Board();
                       down.prevBoard = currentBoard;
                       currentBoard.disp('d');
                       down.setBackTrack('u');
                       down = currentBoard;
                       down.setHeuristic(g+secondHeuristic(down.getBoardState()));
                       queue.add(down);
                   } //end of down node
                   //queue.sort();
                   int s = queue.size();
                   Board current = new Board();
                   for (int i = 0; i < s; i++) {
                       current = queue.get(i);
                       for (int j = i; j < s; j++) {
                           if (current.getHeuristic() > queue.get(j).getHeuristic()) {
                               queue.add(i,queue.get(j));
                               queue.add(j,current);
                           } // end of if
                       } // end of for j
                   } // end of for i
                   // end of sorting
                   if (visited.contains(queue.get(0))) {  //if we've already been to the node before then we are risk of getting into a loop
                       solved = false;
                   } else {
                       currentBoard = queue.get(0);
                       visited.add(currentBoard);
                       queue.remove(0);
                       //currentBoard.printState();
                   } // end of if else
               } // end of if else
           } //end of while
           if (solved) {
               System.out.println("the path is");
               for (int i = 0; i < visited.size(); i++) {
                    visited.get(i).printState();
               } // end of for i
           } // end of if
       } // end of h2
       return solved;
    } // end of aStar
     /**
     * The beam method solves the board using local beam search algorithms
     * @param k is an integer that symbolizes the beam width
     * @returns boolean depending on weather the board is solved or not
     */
    public boolean beam(int k) {
       boolean solved = false;
       Board rootBoard = new Board();
       int f = 0;
       for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rootBoard.boardState[i][j] = this.boardState[i][j];
            } // end of for j
       } // end of for i
       ArrayList <Board> visited = new ArrayList <Board> (0);
       ArrayList <Board> queue = new ArrayList <Board> (1);
       queue.add(rootBoard);
       while ((visited.size() <= maxnodes) && (solved == false)) {
           for (int i = 0; i < queue.size(); i++) {
               queue.get(i).setHeuristic(thirdHeuristic(queue.get(i).getBoardState()));
               if (queue.get(i).canMove('r')) {
                   Board right = new Board();
                   right.prevBoard = queue.get(i);
                   queue.get(i).disp('r');
                   right.setBackTrack('l');
                   right = queue.get(i);
                   queue.get(i).disp('l');
                   right.setHeuristic(thirdHeuristic(right.getBoardState()));
                   queue.add(right);
               } //end of right node
               if (queue.get(i).canMove('l')) {
                   Board left = new Board();
                   left.prevBoard = queue.get(i);
                   queue.get(i).disp('l');
                   left.setBackTrack('r');
                   left = queue.get(i);
                   queue.get(i).disp('r');
                   left.setHeuristic(thirdHeuristic(left.getBoardState()));
                   queue.add(left);
               } //end of right node
               if (queue.get(i).canMove('u')) {
                   Board up = new Board();
                   up.prevBoard = queue.get(i);
                   queue.get(i).disp('u');
                   up.setBackTrack('d');
                   up = queue.get(i);
                   queue.get(i).disp('d');
                   up.setHeuristic(thirdHeuristic(up.getBoardState()));
                   queue.add(up);
               } //end of right node
               if (queue.get(i).canMove('d')) {
                   Board down = new Board();
                   down.prevBoard = queue.get(i);
                   queue.get(i).disp('d');
                   down.setBackTrack('u');
                   down = queue.get(i);
                   queue.get(i).disp('u');
                   down.setHeuristic(thirdHeuristic(down.getBoardState()));
                   queue.add(down);
               } //end of right node
           } // end of for i
           int s = queue.size();
           for (int i = 0; i < s; i++) {
               if (visited.contains(queue.get(i))) { // if we've already been to the node before then we are risk of getting into a loop
                   queue.remove(i);
               } // end of if 
           } // end of for i
           s = queue.size();
           Board current = new Board();
           for (int i = 0; i < s; i++) {
               current = queue.get(i);
               for (int j = i; j < s; j++) {
                   if (current.getHeuristic() > queue.get(j).getHeuristic()) {
                       queue.add(i,queue.get(j));
                       queue.add(j,current);
                   } // end of if
               } // end of for j
           } // end of for i
           // end of sorting
           int les;
           if (queue.size() < k) {
               les = queue.size();
           } else {
               les = k;
           }
           for (int i = 0; i < les; i++) {
               if (queue.get(i).getBoardState().equals(goalState)) {
                   solved = true;
               } else {
                   visited.add(queue.get(i));
                   queue.remove(i);
                   //currentBoard.printState();
               } // end of if else 
           } // end of for i
       } // end of while loop
       return solved;
    } // end of beam
} // end of class Board
