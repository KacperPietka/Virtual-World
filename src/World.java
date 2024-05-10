import java.util.*;

public class World {
    public static Random rand = new Random();
    public static Vector<Organisms> organismsVector = new Vector<>();
    public static int width = 30;
    public static int height = 30;

    public World(int width, int height) {
        World.width = width;
        World.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void addOrganism(Organisms organism) {
        organismsVector.add(organism);
    }

    public static void SpawnOrganisms() {
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        new Wolf(x, y);
        int x2 = rand.nextInt(width);
        int y2 = rand.nextInt(height);
        new Antelope(x2, y2);
        int x3 = rand.nextInt(width);
        int y3 = rand.nextInt(height);
        new Sheep(x3, y3);
        int x4 = rand.nextInt(width);
        int y4 = rand.nextInt(height);
        new Fox(x4, y4);
        int x6 = rand.nextInt(width);
        int y6 = rand.nextInt(height);
        new Belladonna(x6, y6);
        int x7 = rand.nextInt(width);
        int y7 = rand.nextInt(height);
        new Turtle(x7, y7);
        int x8 = rand.nextInt(width);
        int y8 = rand.nextInt(height);
        new Sosnowsky(x8, y8);
        int x9 = rand.nextInt(width);
        int y9 = rand.nextInt(height);
        new Grass(x9, y9);
        int x10 = rand.nextInt(width);
        int y10 = rand.nextInt(height);
        new Sow_Thistle(x10, y10);
        int x11 = rand.nextInt(width);
        int y11 = rand.nextInt(height);
        new Fox(x11, y11);
        int x12 = rand.nextInt(width);
        int y12 = rand.nextInt(height);
        new Guarana(x12, y12);
        int x13 = rand.nextInt(width);
        int y13 = rand.nextInt(height);
        new Human(x13, y13);
    }

    public static Human getHuman() {
        for (Organisms o : organismsVector) {
            if (o.getSign() == 'H') {
                return (Human) o;
            }
        }
        return null;
    }

    public static void bubbleSortOrganisms() {
        int n = organismsVector.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Organisms.compare_initiative(organismsVector.get(j), organismsVector.get(j + 1)) == 0) {
                    Collections.swap(organismsVector, j, j + 1);
                }
            }
        }
    }

    public static Organisms getOrganism(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return null;
        for (Organisms o : organismsVector) {
            if (o.getX() == x && o.getY() == y) {
                return o;
            }
        }
        return null;
    }

    public static void makeTurn() {
        bubbleSortOrganisms();
        List<Organisms> copyOfOrganisms = new ArrayList<>(organismsVector);
        for (Organisms organism : copyOfOrganisms) {
            if (organism.getStrength() > -1) {
                organism.action();
            }
        }

    }
}