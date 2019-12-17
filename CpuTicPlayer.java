
public class CpuTicPlayer extends ATicPlayer
{

    public char getSymbol(){ //Returns the symbol that represents this player
        return 'O';
    }

    public TicMove pickMove(TicGame game){ //Picks a move uniformly at random
        TicMove cpumove;

        do{
            int a = (int) (Math.random()*game.boardSize);
            int b = (int) (Math.random()*game.boardSize);

            cpumove = new TicMove(a,b);
        } while (game.isValidMove(cpumove) == false);
        return cpumove;
    }
}
