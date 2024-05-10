import java.util.Random;
public class Plants extends Organisms{

    public Plants(char sign, int strength, int initiative, int x, int y)
    {
        super(sign, strength, initiative, x , y);
    }


    @Override
    public void action() {
        Random rand = new Random();
        int spreading = rand.nextInt(50);
        if (spreading == 1)
        {
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

    }

    @Override
    public void collision(Organisms other)
    {
        if (this == other)
            return;
        if (this.getStrength() > other.getStrength())
        {
            other.kill();
        }
        else if (this.getStrength() < other.getStrength())
        {
            this.kill();
        }
        else
        {
            this.kill();
            other.kill();

        }
    }
}
