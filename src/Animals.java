import java.util.Vector;
import java.util.Random;

public class Animals extends Organisms{
    public Animals(char sign, int strength, int initiative, int x, int y) {
        super(sign, strength, initiative, x , y);
    }

    @Override
    public void action(){
        Random random = new Random();
        int move1 = random.nextInt(4);
        int[] firstMove = Organisms.possible_moves[move1];
        int x = firstMove[0];
        int y = firstMove[1];
        int newX = getX() + x;
        int newY = getY() + y;
        this.setPosition(newX, newY);
    }

    @Override
    public void collision(Organisms other){
        if (this == other)
        {
            return;
        }
        if (this.sign == other.sign) {
            Organisms baby = this.baby();
            Random rand2 = new Random();
            int move = rand2.nextInt(4);
            int[] firstMove = Organisms.possible_moves[move];
            int x = firstMove[0];
            int y = firstMove[1];
            int newX = getX() + x;
            int newY = getY() + y;
            if (World.getOrganism(newX, newY) == null)
            {
                baby.setPosition(newX, newY);
                System.out.println("New Organism spawned: " + baby.getSign());
            }
        }
        else if (this.escape() || other.escape())
        {
            return;
        }
        else if (this.getStrength() > other.getStrength())
        {

            other.kill();
        }
        else if (this.getStrength() < other.getStrength())
        {
            this.kill();
        }
        else
        {
            other.kill();
        }
    }
    public void spreading(){

    }
}
