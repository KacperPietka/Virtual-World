import java.util.Random;

public class Sow_Thistle extends Plants {
    public Sow_Thistle(int x, int y) {
        super('%', 0, 0, x, y);
    }

    @Override
    public void action()
    {
        for(int i = 0; i < 3; i++)
        {
            Random rand = new Random();
            int spreading = rand.nextInt(100);
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
                    break;
                }
            }
        }


    }

    @Override
    public void collision(Organisms other) {
        super.collision(other);
    }

    @Override
    public Organisms baby() {
        return new Sow_Thistle(this.getX(), this.getY());
    }
}
