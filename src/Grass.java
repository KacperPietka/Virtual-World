public class Grass extends Plants{
    public Grass(int x, int y){
        super ('#', 0, 0, x, y);
    }


    @Override
    public void collision(Organisms other) {
        super.collision(other);
    }

    @Override
    public Organisms baby(){
        return new Grass(this.getX(), this.getY());
    }
}
