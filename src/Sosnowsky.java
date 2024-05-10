public class Sosnowsky extends Plants{

    public Sosnowsky(int x, int y){
        super ('S', 10, 0, x, y);
    }

    @Override
    public void action()
    {
        Organisms organism_neighbour = World.getOrganism( getX() - 1, getY());
        if (organism_neighbour != null) {
            organism_neighbour.kill();
            return;
        }
        Organisms organism_neighbour2 = World.getOrganism( getX() + 1, getY());
        if (organism_neighbour2 != null) {
            organism_neighbour2.kill();
            return;
        }
        Organisms organism_neighbour3 = World.getOrganism( getX(), getY() - 1);
        if (organism_neighbour3 != null) {
            organism_neighbour3.kill();
            return;
        }
        Organisms organism_neighbour4 = World.getOrganism( getX() , getY() + 1);
        if (organism_neighbour4 != null) {
            organism_neighbour4.kill();
            return;
        }
    }
    @Override
    public void collision(Organisms other)
    {
        other.kill();
        World.organismsVector.remove(other);
        this.kill();
    }
}
