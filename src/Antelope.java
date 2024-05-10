import java.util.Random;
import java.util.Vector;

public class Antelope extends Animals{
    public Antelope(int x, int y){
        super('@', 4, 4, x, y);
    }
    public void action(){
        Random rand = new Random();
        int move1 = rand.nextInt(4);
        int[] firstMove = Organisms.possible_moves[move1];
        int x = firstMove[0];
        int y = firstMove[1];
        int newX = getX() + x*2;
        int newY = getY() + y*2;
        this.setPosition(newX, newY);
    }

    @Override
    public void collision(Organisms other){
        super.collision(other);
    }

    public boolean escape(){
        Random random = new Random();
        int escaped =  random.nextInt(2);
        if(escaped == 0){
            this.undoMove();
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Organisms baby(){
        return new Antelope(this.getX(), this.getY());
    }


}
