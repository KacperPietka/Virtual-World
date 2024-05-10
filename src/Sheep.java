public class Sheep extends Animals{
    public Sheep(int x, int y) {
        super('*', 4, 4, x , y);
    }

    @Override
    public void action(){
        super.action();
    }
    @Override
    public void collision(Organisms other){
        super.collision(other);
    }
    @Override
    public Organisms baby(){
        return new Sheep(this.getX(), this.getY());
    }
}
