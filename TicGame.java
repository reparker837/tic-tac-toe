import java.util.Scanner;
/**
 * Write a description of class TicGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicGame
{
    protected char BLANK; //represents an empty spot on the game board
    protected char[][] board; //represents the game board as matrix of player symbols
    protected int boardSize; //represents board size, which will be a boardSize x boardSize matrix
    protected char gameResult; //game result, '?' means game not over yet
    //'T' means tie, and any other character 'C' means the player represented 
    //by said character 'C' won the game
    int gamesPlayed = 0;
    int humanWins;
    int cpuWins;
    int ties;
    public TicGame(int boardSize) //Constructor for objects of class TicGame
    {
        this.boardSize = boardSize;
    }

    protected boolean executeMove(TicMove move, char symbol){
        //Executes the move passed as an argument
        board[move.row][move.col] = symbol;
        return true;
    }

    public static int getBoardSize(){ //Returns the game board size - not using?
        return 0;
    }

    public char getGameResult(){ //Returns the game result as a character
        return 'x';
    }

    public boolean isGameOver(){ //A method that returns true if the game is over
        boolean allOver = false;

        for(int i = 0; i<boardSize; i++){ //checking for horizontal matches
            char testSquare = board[i][0];

            int j= 0;
            do{
                if (testSquare == BLANK){
                    break;
                }
                if(testSquare == board[i][j]){
                    allOver = true;
                    j++;
                }
                else{
                    allOver = false;
                    j++;
                }
            }while(allOver == true && j<boardSize);
            if(allOver == true){
                gameResult = 'C';
                System.out.println("Game Over!");
                return allOver;
            }
        }

        for(int j = 0; j<boardSize; j++){ //checking for vertical matches
            char testSquare = board[0][j];

            int i = 0;
            do{
                if (testSquare == BLANK){
                    break;
                }
                if(testSquare == board[i][j]){
                    allOver = true;
                    i++;
                }
                else{
                    allOver = false;
                    i++;
                }
            }while(allOver == true && i<boardSize);
            if(allOver == true){
                gameResult = 'C';
                System.out.println("Game Over!");
                return allOver;
            }
        }

        int i = 0;
        int j = 0;
        while(i < boardSize && j < boardSize){ //checking for diagonal matches
            char testSquare = board[0][0];
            if(testSquare == BLANK){
                break;
            }
            else if(testSquare == board[i][j]){
                allOver = true;
                i++;
                j++;
            }
            else{
                allOver = false;
                break;
            }

        }
        if(allOver == true){
            gameResult = 'C';
            System.out.println("Game Over!");
            return allOver;
        }

        i = 0;
        j = boardSize - 1;
        while(i >= 0 && j >= 0){ //checking for other diagonal matches
            char testSquare = board[0][boardSize - 1];
            if(testSquare == BLANK){
                break;
            }
            else if(testSquare == board[i][j]){
                allOver = true;
                i++;
                j--;
            }
            else{
                allOver = false;
                break;
            }

        }
        if(allOver == true){
            gameResult = 'C';
            System.out.println("Game Over!");
            return allOver;
        }
        if(board[0][0] != BLANK){
            for(int l = 0; l<boardSize; l++){
                for(int k = 0; k<boardSize; k++){
                    if(board[l][k] != BLANK){
                        allOver = true;

                    }
                    else{
                        return false;
                    }
                }
            }
            if(allOver == true){
                gameResult = 'T';
                System.out.println("Game Over!");
                return allOver;
            }

        }
        return false; 
    }

    public boolean isValidMove(TicMove move){ //Validates a potential move
        if(move.row < boardSize && move.col < boardSize && move.col >= 0){
            if(board[move.row][move.col] == BLANK){
                return true;
            }
            return false;
        }
        return false;
    }

    protected void resetGame(TicGame game){
        for(int j = 0; j<boardSize; j++){
            for(int k = 0; k<boardSize; k++){
                board[j][k] = BLANK;
            }
        }
        
        System.out.println("New game:");
        System.out.println(game);
        gameResult = '?';
    }

    public String toString(){ 
        String sboard = "";
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        for(int r = 0; r<boardSize; r++){
            for(int c = 0; c<boardSize; c++){
                if(r==0 && c==0){
                    for(int i = 0; i<boardSize; i++)
                    {
                        sboard += "  " + (i+1) +  " ";
                    }
                    sboard += "\n";
                }
                if(boardSize - 1 == 0){
                    sboard += alphabet[r] + " " + board[r][c];
                }
                else if(c==0){
                    sboard += alphabet[r] + " " + board[r][c] + " |";
                }

                else if(c == boardSize - 1){
                    sboard += " " + board[r][c] + "\n";
                }
                else{
                    sboard += " " + board[r][c] + " |";
                }

            }
            if(r == boardSize-1){
                sboard += "";
            }
            else{
                sboard += " ";            
                for(int i = 0; i<boardSize; i++){
                    if(i == boardSize-1){
                        sboard += "---";
                    }
                    else{
                        sboard += "---|";
                    }
                }
                sboard += "\n";
            }
        }
        return sboard;
    } 

    public static void main(String[] args){
        //Runs a single-player, text-based game of Tic-tac-toe
        System.out.println("CMPU 102 HW #2 by Rose Parker");

        int i = 3;
        try{
            i = Integer.parseInt(args[0]); 
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Board size not valid.");

        }
        catch(NumberFormatException r){
            System.out.println("Board size not valid.");
        }
        if (!(i >= 1 &&  i <= 9)){
            System.out.println("Board size not valid.");
            i = 3;            
        }
        int boardSize = i;

        TicGame game = new TicGame(boardSize); //creates game
        
        game.board = new char[boardSize][boardSize];
        game.BLANK = ' ';
        for(int j = 0; j<boardSize; j++){
            for(int k = 0; k<boardSize; k++){
                game.board[j][k] = game.BLANK;
            }
        }

        game.gameResult = '?';
        System.out.println(game);
        boolean over = false;
        do{
            int m = (int) (Math.random()*2); 
            do{
                HumanTicPlayer human = new HumanTicPlayer();
                CpuTicPlayer cpu = new CpuTicPlayer();
                if (m == 0){
                    //player starts
                    TicMove move;
                    char symbol = human.getSymbol();
                    boolean valid;
                    do{
                        move = human.pickMove(game);
                        valid = game.isValidMove(move);
                        if (valid == false){
                            System.out.println("Move invalid. Try again!");
                        }
                    } while (valid == false);
                    game.executeMove(move, symbol);
                    System.out.println("Your move:");
                    System.out.println(game);
                    over = game.isGameOver();
                    if (over == true){
                        if(game.gameResult == 'C'){
                            System.out.println("You won!!");
                            game.humanWins++;
                        }
                        if(game.gameResult == 'T'){
                            System.out.println("Tie!!");
                            game.ties++;
                        }

                        char x = game.getGameResult();
                        game.resetGame(game);
                    }
                    else{
                        move = cpu.pickMove(game);
                        char sym = cpu.getSymbol();
                        game.executeMove(move, sym);
                        System.out.println("Opponent's move:");
                        System.out.println(game);
                        over = game.isGameOver();
                        if (over == true){
                            if(game.gameResult == 'C'){
                                System.out.println("You lost!! :(");
                                game.cpuWins++;
                            }
                            if(game.gameResult == 'T'){
                                System.out.println("Tie!!");
                                game.ties++;
                            }

                            char x = game.getGameResult();
                            game.resetGame(game);
                        }
                    }
                }
                else{
                    //cmpu starts
                    TicMove move = cpu.pickMove(game);
                    char sym = cpu.getSymbol();
                    game.executeMove(move, sym);
                    System.out.println("Opponent's move:");
                    System.out.println(game);
                    over = game.isGameOver();
                    if (over == true){
                        if(game.gameResult == 'C'){
                            System.out.println("You lost!! :(");
                            game.cpuWins++;
                        }
                        if(game.gameResult == 'T'){
                            System.out.println("Tie!!");
                            game.ties++;
                        }
                        char x = game.getGameResult();
                        game.resetGame(game);
                    }
                    m = 0; 
                }
            } while (over == false);
            game.gamesPlayed++;
        } while (over == true);
    }
}
