import java.util.Scanner;
/**
 * Write a description of class HumanTicPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HumanTicPlayer extends ATicPlayer
{

    public char getSymbol(){ //Returns the symbol that represents this player
        return 'X';
    }

    public TicMove pickMove(TicGame game){ //This method asks the user to pick a tic-tac-toe move
        //give an option to quit here
        Scanner scanner = new Scanner(System.in);
        int length;
        int r = 0;
        int c = 0;
        do{
            System.out.println("Choose your move (or 'quit'):");
            String s = scanner.next();
            length = s.length();
            s = s.toUpperCase();
            System.out.println(s);
            
            if(s.equals("QUIT")){ //fix this
                System.out.println("You played " + game.gamesPlayed + " game(s).");
                System.out.println("Games won: " + game.humanWins);
                System.out.println("Games lost: " + game.cpuWins);
                System.out.println("Games tied: " + game.ties);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
                System.exit(0);
            }

            else if(length == 2){
                r = s.charAt(0);
                c = s.charAt(1) - 49;
                if((int)r >= 'A' && (int)r <= 'I'){
                    r -= 65;
                    return new TicMove(r, c);
                }
            }
            else{
                System.out.println("Error: invalid input!");
            }
        }while(length != 2 && !((int)r >= 'A' && (int)r <= 'I'));
        return new TicMove(-1, -1);
    }
}
