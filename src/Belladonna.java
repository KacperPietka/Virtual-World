public class Belladonna extends Plants{

    public Belladonna(int x, int y){
        super ('B', 99, 0, x, y);
    }

    @Override
    public void collision(Organisms other)
    {
        other.kill();
        World.organismsVector.remove(other);
        this.kill();
    }

    @Override
    public Organisms baby()
    {
        return new Belladonna(this.x, this.y);
    }

}
