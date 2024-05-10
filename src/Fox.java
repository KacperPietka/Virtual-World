import java.util.Random;

public class Fox extends Animals {
    public Fox(int x, int y){
        super('^', 3, 7, x, y);
    }
    @Override
    public void action(){
        Random rand = new Random();
        int move1 = rand.nextInt(4);
        int[] firstMove = Organisms.possible_moves[move1];
        int x = firstMove[0];
        int y = firstMove[1];
        int newX = getX() + x;
        int newY = getY() + y;
        if(World.getOrganism(newX,newY) == null || World.getOrganism(newX, newY).getStrength() <= this.getStrength()){
            this.setPosition(newX, newY);
        }
    }

    @Override
    public void collision(Organisms other){
        super.collision(other);
    }

    @Override
    public Organisms baby(){
        return new Fox(this.getX(), this.getY());
    }
}
