import java.util.Random;

public class Turtle extends Animals{
    public Turtle(int x, int y) {
        super('?', 2, 1, x , y);
    }

    @Override
    public void action(){
        Random rand = new Random();
        int stay = rand.nextInt(4);
        if (stay == 0){
            super.action();
        }
    }

    @Override
    public void collision(Organisms other){
        if(other.getStrength() < 5){
            other.undoMove();
        }
        else {super.collision(other);}
    }

    @Override
    public Organisms baby(){
        return new Turtle(this.getX(), this.getY());
    }
}
