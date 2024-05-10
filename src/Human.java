public class Human  extends  Animals{
    private int ability = 0;
    private int human_x;
    private int human_y;
    public Human(int x, int y){
        super('H', 5, 4, x, y);
    }

    public void action(){
        this.setPosition(this.human_x, this.human_y);
    }

    @Override
    public void collision(Organisms other){
        super.collision(other);
    }

    public void move(int x1, int y1){
        if ((this.getX() + x1) < 0 || (this.getY() + y1) < 0 || (this.getX() + x1) >= World.getWidth() || (this.getY() + y1) >= World.getHeight()){
            return;
        }
        if (ability == 10)
        {
            this.Strengthen(5);
        }
        if (ability <= 10 && ability > 5)
        {
            this.Strengthen(-1);
            System.out.println("Human's Strength: " + this.getStrength());
            ability--;
        }
        else if (ability > 0)
        {
            ability--;
        }
        this.human_x = this.getX() + x1;
        this.human_y = this.getY() + y1;

    }

    public void activate_ability()
    {
        if (ability == 0)
        {
            System.out.println("Ability has been activated!");
            ability = 10;
        }
    }

    public void spreading(){
        return;
    }


}
