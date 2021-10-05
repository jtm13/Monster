import java.awt.*;
import javax.swing.ImageIcon;
/**
 * The stage for the game (320 x 320).
 * Also has bars, so more height than actually indicated (420)
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage
{
    // instance variables - replace the example below with your own
    private Image i; // main stage
    private Image m; // bars
    /*
     * Default Constructor for objects of class Stages
     * @pre: files must be where indicated
     */
    public Stage()
    {
        // initialise instance variables
        i = new ImageIcon("MonsterSprites\\Stage.png").getImage();
        WallHitbox.walls(i);
        WallHitbox.wall(39, 40, 61, 59);
        WallHitbox.wall(217, 40, 61, 59);
        WallHitbox.wall(217, 206, 61, 59);
        WallHitbox.wall(43, 206, 61, 59);
        m = new ImageIcon("MonsterSprites\\Bars.png").getImage();
    }

    /*
     * paints the stage
     * @pre: must be big enough to support 320 * 420
     * @param: Graphics g for it to actually work
     */
    public void paint(Graphics g)
    {
        g.drawImage(i,0,0, null);
        g.drawImage(m,0,320,null); // draws both images
        g.setColor(Color.RED); // sets the color to red
        int pal = (int)Math.round(197 * Game.getPlayerHealthPercentage()); // gets the amount of pixels asociated with width and Player 1's hitPoints
        int en = (int)Math.round(197 * Game.getEnemyHealthPercentage()); // gets the amount of pixels asociated with width and Player 2's hitPoints
        g.fillRect(102, 339, pal, 14);
        g.fillRect(102, 386, en, 14); //paints the inside of the bars red to an extent
    }
}
