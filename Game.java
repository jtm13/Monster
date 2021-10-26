import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Game brings it all together.
 * Has ArrayLists of Monster and Projectiles to track them and keep them painted/alive in the heap
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {
    private static JFrame frame; // for this to exist
    private static Foreground com; // for painting
    private static java.util.ArrayList<Monster> monsters = new java.util.ArrayList<Monster>();
    private static java.util.ArrayList<Projectile> projectiles = new java.util.ArrayList<Projectile>(); // both for storage
    private static Stage stage; // for the stage
    private static Player play; // for the monsters
    private static Boolean win = null; // Gaming the system hard (boolean does not support null)

    /**
     * Constructor for objects of class Game.
     * This is where the magic happens and ends.
     * Therefore, this commenting is different than the rest.
     */
    public Game() {
        stage = new Stage();
        frame = new JFrame();
        monsters.add(new FireMonster("", -33,-33)); 
        monsters.add(new FireMonster("", -33,-33));
        play = new Player(); // initializes instance variables
        com = new Foreground();
        frame.getContentPane().add(BorderLayout.CENTER,com); // adds Foreground to center of JFrame
        frame.getContentPane().add(BorderLayout.EAST, play.getPanel()); // adds the Label and buttons
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets default to exit when user closes(goes off of it)
        frame.setSize(700, 700); // sets JFrame size
        frame.setVisible(true); // sets JFrame visible
        // adds monster objects to ArrayList
        Thread t = new Thread(new Run()); // creates new Thread to not disrupt the main Thread
        t.start(); // starts loop
    }

    static java.util.ArrayList<Monster> getMonsters() {
        return new java.util.ArrayList<Monster>(monsters);
    }

    /**
     * Checks all monsters in Monsters ArrayList.
     */
    public static void check() {
        for (int a = 0; a < monsters.size(); a++) {
            if (monsters.get(a).getDeath()) {
                monsters.remove(a); 
                a--; // have to do this to account for removing monsters
                if (a + 1 == 1) {
                    win = true;
                } else { // certainty
                    win = false;
                }
            }
        }
    }

    /**
     * Paints every monster and projectile in ArrayLists.
     * @param: Graphics g for the painting to work
     */
    public static void animate(Graphics g)
    {
        for (Monster sprites:monsters) {
            sprites.getSprite().paint(g); // sprites have their own paint method
        } // reworte this, so monsters are now officially sprites
        Projectile.paint(g, projectiles); // projectiles paint all the projectiles in a given ArrayList
    }

    /**
     * Gets the player 1 heaith percentage.
     * @return: double percentage from 0 to 1
     */
    public static double getPlayerHealthPercentage() {
        return Game.monsters.get(0).getHealthPercentage();
    }

    /**
     * Gets the player 2 heaith percentage.
     * @return: double percentage from 0 to 1
     */
    public static double getEnemyHealthPercentage() {
        if (Game.monsters.size() >= 2) {
            return Game.monsters.get(1).getHealthPercentage();
        }
        return 0;
    }

    /**
     * Adds a projectile to Projectiles.
     * @param: Projectile p to add it to Projectiles
     */
    public static void add(Projectile p) {
        projectiles.add(p);
    }

    /**
     * Updates the monsters ArrayList.
     * @param: ArrayList<monster> m to replace monsters
     */
    static void update(java.util.ArrayList<Monster> m) {
        Game.monsters.clear();
        Game.monsters = m;
        stage.setFirst(true);
        WallHitbox.setFirst(true);
    }

    /**
     * Initiates the game.
     */
    public static void main(String[] args) {
        new Game();
    }

    /**
     * For the Game Thread.
     */
    class Run implements Runnable {

        /**
         * Runs the seperate thread (can be infinite).
         */
        public void run() {
            do {
                Game.check();
                com.repaint();
            }while(win == null); // win code will display before it gets to while loop check
        }
    }

    /**
     * For painting with JPanel.
     */
    class Foreground extends JPanel {

        /**
         * Paints the panel.
         * @param: Graphics g to paint panel (not made by me)
         */
        public void paintComponent(Graphics g) {
            if (win == null) {
                //for Stage
                stage.paint(g);
                //for Sprites
                animate(g);
                WallHitbox.paint(g);
            } else if(win == true) { // normal
                stage.paint(g); // to make bar 0
                g.drawImage(new ImageIcon("MonsterSprites\\Player 1 Wins.png").getImage(), 0, 0,null);
            } else { // win screens
                stage.paint(g); // to make bar 0
                g.drawImage(new ImageIcon("MonsterSprites\\Player 2 Wins.png").getImage(), 0, 0,null);
            }
            try
            {
                Thread.sleep(50);
            } catch(Exception e) { //refreshes every 15th of a second (15fps)
                System.err.println(e);
            }
        }
    }
}
