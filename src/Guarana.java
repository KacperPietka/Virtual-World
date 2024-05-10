public class Guarana extends Plants{
    public Guarana(int x, int y){
        super ('G', 0, 0, x, y);
    }


    @Override
    public void collision(Organisms other) {
        other.Strengthen(3);
        this.kill();
    }

    @Override
    public Organisms baby(){
        return new Guarana(this.getX(), this.getY());
    }
}
