import java.util.Vector;

public class Organisms {
    public  char sign;
    private int strength;
    public int initiative;
    public  int x;
    public  int y;
    public  int previous_x;
    public  int previous_y;
    protected static int[][] possible_moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public Organisms(char sign1, int strength1, int initiative1, int x1, int y1) {
        this.sign = sign1;
        this.strength = strength1;
        this.initiative = initiative1;
        this.x = x1;
        this.y = y1;
        World.addOrganism(this);
    }
    public int getY() {return y;}
    public int getX() {return x;}
    public int getStrength(){
        return strength;
    }
    public void Strengthen(int strength1){
        this.strength += strength1;
    }
    public int getInitiative(){return initiative;}

    public void kill(){
        System.out.println("Killed Organism: " + this.getSign());
        this.x = -1;
        this.strength = -1;
        this.initiative = -1;

    }

    public char getSign() {return sign;}

    public void setPosition(int x1, int y1){
        if (x1 < 0 || x1 >= World.getWidth() || y1 < 0 || y1 >= World.getHeight())
            return;
        this.previous_x = this.x;
        this.previous_y = this.y;
        Organisms other = World.getOrganism(x1, y1);
        this.x = x1;
        this.y = y1;
        if (other != null)
        {
            other.collision(this);
        }
    }

    public void draw()
    {

    }
    public boolean escape(){
        return false;
    }
    public void undoMove(){
        setPosition(previous_x, previous_y);
    }

    public static int compare_initiative(Organisms a, Organisms b)
    {
        if(a.getInitiative() >= b.getInitiative()){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void action() {}

    public void collision(Organisms other) {}

    public Organisms baby() {return null;}
}
