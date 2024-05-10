import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SwingApp extends JFrame {
    private static final int GRID_SIZE = 30;
    private JPanel gridPanel;
    private Human human;

    public SwingApp() {
        setTitle("Kacper Pietka, 197587");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        initializeGrid();

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(gridPanel, BorderLayout.CENTER);
        getContentPane().add(contentPanel);

        pack();

        setLocationRelativeTo(null);
    }


    private Color getOrganismColor(char sign) {
        switch (sign) {
            case '^':
                return Color.ORANGE; // Fox
            case '*':
                return Color.WHITE; // Sheep
            case '#':
                return Color.GREEN; // Grass
            case '!':
                return Color.GRAY; // Wolf
            case 'S':
                return Color.CYAN; // Sosnowsky
            case '%':
                return Color.YELLOW; // Sow_Thistle
            case '?':
                return Color.BLUE; // Turtle
            case 'B':
                return Color.RED; // Belladonna
            case '@':
                return Color.PINK; // Antelope
            case 'H':
                return Color.MAGENTA; // Human
            case 'G':
                return Color.DARK_GRAY; // Guarana
            default:
                return Color.GRAY; // Default color
        }
    }


    private void initializeGrid() {
        gridPanel.removeAll();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JPanel cellPanel = new JPanel(new BorderLayout());
                boolean organismFound = false;
                for (Organisms organism : World.organismsVector) {
                    if (organism.getX() == i && organism.getY() == j && organism.getStrength() > -1 && organism.getInitiative() > -1) {
                        JButton button = new JButton(String.valueOf(organism.getSign()));
                        button.setBackground(getOrganismColor(organism.getSign()));
                        cellPanel.add(button, BorderLayout.SOUTH);
                        organismFound = true;
                        break;
                    }
                }
                if (!organismFound) {
                    JButton button = new JButton(String.valueOf(' '));
                    cellPanel.add(button, BorderLayout.SOUTH);
                }

                gridPanel.add(cellPanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void saveWorldToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Organisms organism : World.organismsVector) {
                writer.write(organism.getX() + "," + organism.getY() + "," +
                        organism.getStrength() + "," + organism.getInitiative() + "," +
                        organism.getSign());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "World state saved to file successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadMapFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            World.organismsVector.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                char sign = parts[4].charAt(0);
                Organisms organism;
                switch (sign) {
                    case '^':
                        organism = new Fox(x, y);
                        break;
                    case '*':
                        organism = new Sheep(x, y);
                        break;
                    case '#':
                        organism = new Grass(x, y);
                        break;
                    case '!':
                        organism = new Wolf(x, y);
                        break;
                    case 'S':
                        organism = new Sosnowsky(x, y);
                        break;
                    case '%':
                        organism = new Sow_Thistle(x, y);
                        break;
                    case '?':
                        organism = new Turtle(x, y);
                        break;
                    case 'B':
                        organism = new Belladonna(x, y);
                        break;
                    case '@':
                        organism = new Antelope(x, y);
                        break;
                    case 'H':
                        organism = new Human(x, y);
                        break;
                    case 'G':
                        organism = new Guarana(x, y);
                        break;
                    default:
                        organism = null;
                        break;
                }
            }

            JOptionPane.showMessageDialog(null, "Map loaded successfully!");
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateGrid() {
        initializeGrid();
    }



    public static void main(String[] args) {
        World.SpawnOrganisms();
        SwingUtilities.invokeLater(() -> {
            SwingApp app = new SwingApp();
            app.setVisible(true);
            app.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {
                    Human human = World.getHuman();
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            human.move(-1, 0);
                            break;
                        case KeyEvent.VK_DOWN:
                            human.move(1, 0);
                            break;
                        case KeyEvent.VK_LEFT:
                            human.move(0, -1);
                            break;
                        case KeyEvent.VK_RIGHT:
                            human.move(0, 1);
                            break;
                        case KeyEvent.VK_I:
                            human.activate_ability();
                            break;
                        case KeyEvent.VK_Q:
                            System.exit(0);
                            break;
                        case KeyEvent.VK_S:
                            app.saveWorldToFile("map.txt");
                            break;
                        case KeyEvent.VK_L:
                            app.loadMapFromFile("map.txt");
                            app.updateGrid();
                            break;
                    }
                    World.makeTurn();
                    app.updateGrid();
                    if(human.getStrength() == -1) {System.out.println("HUMAN IS DEAD! GAME OVER"); System.exit(0);}

                }
                @Override
                public void keyReleased(KeyEvent e) {}
            });
            app.requestFocusInWindow();
        });

    }
}
