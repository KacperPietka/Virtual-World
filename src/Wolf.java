public class Wolf extends Animals{
    public Wolf(int x, int y) {
        super('!', 9, 5, x, y);
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
        return new Wolf(this.getX(), this.getY());
    }
}
