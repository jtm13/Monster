import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.GregorianCalendar;
/**
 * Game brings it all together.
 * Has ArrayLists of Monster and Projectiles to track them and keep them painted/alive in the heap
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    private static JFrame frame; // for this to exist
    private static Foreground com; // for painting
    private static java.util.ArrayList<Monster> monsters = new java.util.ArrayList<Monster>();
    private static java.util.ArrayList<Projectile> projectiles = new java.util.ArrayList<Projectile>(); // both for storage
    private static Stage stage; // for the stage
    private static Player play; // for the monsters
    private static Boolean win = null; // Gaming the system hard (boolean does not support null)
    private static GregorianCalendar timestamp = new GregorianCalendar();
    /**
     * Constructor for objects of class Game
     * this is where the magic happens and ends
     * therefore, this commenting is different than the rest
     */
    public Game()
    {
        stage = new Stage();
        frame = new JFrame();
        monsters.add(new FireMonster("", -33,-33)); 
        monsters.add(new FireMonster("", -33,-33));
        play = new Player(); // initializes instance variables
        com = new Foreground();
        frame.getContentPane().add(BorderLayout.CENTER,com); // adds Foreground to center of JFrame
        frame.getContentPane().add(BorderLayout.EAST, play.getPanel()); // adds the Label and buttons
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets default to exit when user closes(goes off of it)
        frame.setSize(700, 700); // sets JFrame size
        frame.setVisible(true); // sets JFrame visible
        // adds monster objects to ArrayList
        Thread t = new Thread(new Run()); // creates new Thread to not disrupt the main Thread
        t.start(); // starts loop
    }

    /*
     * checks all monsters in Monsters ArrayList
     */
    public static void check()
    {
        for(int a = 0; a < monsters.size(); a++) 
        {
            for(int t = 0; t < projectiles.size(); t++)
            {
                if(!monsters.get(a).check(projectiles.get(t))) // if monster hitbox intersects with projectile hurtbox
                {
                    projectiles.remove(t);
                    t--; // have to do this to account for removing projectiles
                    if(monsters.get(a).getDeath()) // if monster is dead
                    {
                        monsters.remove(a); 
                        a--; // have to do this to account for removing monsters
                        if(a + 1 == 1) // certainty
                        {
                            win = true;
                        }
                        else
                        {
                            win = false;
                        }
                    }
                }
            }
        }
    }

    /*
     * paints every monster and projectile in ArrayLists
     * @param: Graphics g for the painting to work
     */
    public static void animate(Graphics g)
    {
        for(Monster sprites:monsters) // reworte this, so monsters are now officially sprites
        {
            sprites.getSprite().paint(g); // sprites have their own paint method
        }
        Projectile.paint(g, projectiles); // projectiles paint all the projectiles in a given ArrayList
    }

    /*
     * gets the player 1 heaith percentage
     * @return: double percentage from 0 to 1
     */
    public static double getPlayerHealthPercentage()
    {
        return Game.monsters.get(0).getHealthPercentage();
    }

    /*
     * gets the player 2 heaith percentage
     * @return: double percentage from 0 to 1
     */
    public static double getEnemyHealthPercentage()
    {
        if(Game.monsters.size() >= 2)
        {
            return Game.monsters.get(1).getHealthPercentage();
        }
        return 0;
    }

    /*
     * adds a projectile to Projectiles
     * @param: Projectile p to add it to Projectiles
     */
    public static void add(Projectile p)
    {
        projectiles.add(p);
    }

    /*
     * updates the monsters ArrayList
     * @param: ArrayList<monster> m to replace monsters
     */
    static void update(java.util.ArrayList<Monster> m)
    {
        Game.monsters.clear();
        Game.monsters = m;
    }
    
    /*
     * initiates the game
     */
    public static void main(String[] args)
    {
        new Game();
    }
    
    /*
     * gives the timestamp
     */
    protected static long getTime()
    {
        return timestamp.getTimeInMillis();
    }
    /**
     * For the Game Thread
     */
    class Run implements Runnable
    {
        /*
         * runs the seperate thread (can be infinite)
         */
        public void run()
        {
            do // win code will display before it gets to while loop check
            {
                Game.check();
                Projectile.move(projectiles);
                com.repaint();
            }while(win == null);
        }
    }
    /**
     * for painting with JPanel
     */
    class Foreground extends JPanel
    {
        /*
         * paints the panel
         * @param: Graphics g to paint panel (not made by me)
         */
        public void paintComponent(Graphics g)
        {
            if(win == null) // normal
            {
                //for Stage
                stage.paint(g);
                //for Sprites
                Projectile.paint(g, projectiles);
                animate(g);
                WallHitbox.paint(g);
            }
            else if(win == true) // win screens
            {
                g.drawImage(new ImageIcon("MonsterSprites\\Player 1 Wins.png").getImage(), 0, 0,null);
            }
            else
            {
                g.drawImage(new ImageIcon("MonsterSprites\\Player 2 Wins.png").getImage(), 0, 0,null);
            }
            try //refreshes every 15th of a second (15fps)
            {
                Thread.sleep(1000/15);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}
