import java.util.ArrayList;
/**
 * For projectiles
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Projectile
{
    // instance variables - replace the example below with your own
    private Sprite t; // Projectile HAS-A Sprite
    private int damage; // for damage
    private int hashCode; // for object recognition
    /**
     * Constructor for objects of class Projectile
     * @pre: the file must be in the right place
     * @param: int x and int y for point, int damage for damage, int direction for the direction the object is going, int hashCode for object recognition
     */
    public Projectile(int x, int y, int damage, int direction, int hashCode)
    {
        // initialise instance variables
        t = new Sprite(x, y, 6, "MonsterSprites\\Shot.png");
        t.setPosition(direction);
        this.damage = damage;
        this.hashCode = hashCode; //for checking if the monster who made it is hit
    }

    /*
     * Moves all projectiles in ArrayList
     * @param: ArrayList<Projectile> spriteHolder to move them
     */
    public static void move(ArrayList<Projectile> spriteHolder)
    {
        // put your code here
        for(int t = 0; t < spriteHolder.size(); t++)
        {
            Sprite sprite = spriteHolder.get(t).t; // I know it is confusing, but I don't care as long as it works
            boolean b = true; // to give it the benefit of the doubt
            switch(sprite.getPosition()) // decides the movement of the projectile
            {
                case 0:
                b = sprite.moveYUp();
                break;
                case 2:
                b = sprite.moveYDown();
                break;
                case 3:
                b = sprite.moveXLeft();
                break;
                case 1:
                b = sprite.moveXRight();
                break;
            }
            if(!b) // which is why they all return booleans
            {
                spriteHolder.remove(t);
                t--; // for not skipping projectiles
            }
        }
    }

    /*
     * paints the projectiles in the ArrayList (default on purpose)
     * @param: Graphics g for it to paint, ArrayList spriteHolder for painting
     */
    static void paint(java.awt.Graphics g, ArrayList<Projectile> spriteHolder)
    {
        for(Projectile p:spriteHolder)
        {
            Sprite s = p.t;
            s.paint(g);
        }
    }

    /*
     * gets the sprite from Projectile (default on purpose)
     * @return: Sprite t
     */
    Sprite getSprite()
    {
        return t; 
    }

    /*
     * gets the damage from Projectile (default on purpose)
     * @return: int damage
     */
    int getDamage()
    {
        return damage;
    }

    /*
     * gets the hashCode from Projectile (default on purpose)
     * @return: int hashCode
     */
    int getHashCode()
    {
        return hashCode;
    }
}
