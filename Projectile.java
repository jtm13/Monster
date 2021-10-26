import java.util.ArrayList;

/**
 * For projectiles.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Projectile {
    // instance variables - replace the example below with your own
    private Sprite t; // Projectile HAS-A Sprite
    private int damage; // for damage
    private int hashCode; // for object recognition

    /**
     * Constructor for objects of class Projectile.
     * @pre: the file must be in the right place
     * @param: int x and int y for point, int damage for damage, int direction for the direction the object is going, int hashCode for object recognition
     */
    public Projectile(int x, int y, int damage, int direction, int hashCode) {
        // initialise instance variables
        t = new Sprite(x, y, 12, "MonsterSprites\\Shot.png");
        t.setPosition(direction);
        this.damage = damage;
        this.hashCode = hashCode; //for checking if the monster who made it is hit
        move();
    }

    /**
     * Moves all projectiles in ArrayList.
     * @param: ArrayList<Projectile> spriteHolder to move them
     */
    public void move() {
        // put your code here
        final Projectile p = this;
        Thread j = new Thread(new Runnable() {
                    /**
                     * Runs the seperate thread (can be infinite).
                     */
                    public void run() {
                        Sprite sprite = t; // I know it is confusing, but I don't care as long as it works
                        boolean b = true; // to give it the benefit of the doubt
                        while (b && !sprite.getClear()) {
                            switch (sprite.getPosition()) { // decides the movement of the projectile
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
                            try
                            {
                                Thread.sleep(50);
                            } catch(Exception e) { //refreshes every 15th of a second (15fps)
                                System.err.println(e);
                            }
                            ArrayList<Monster> mon = Game.getMonsters();
                            if (mon.stream().filter(a -> !a.check(p)).count() != 0) {
                                b = false;
                            } // if
                            if (!b) { // which is why they all return booleans
                                sprite.setClear(true);
                            }
                        }
                    }
                });
        j.start();
    }

    /**
     * Paints the projectiles in the ArrayList (default on purpose).
     * @param: Graphics g for it to paint, ArrayList spriteHolder for painting
     */
    static void paint(java.awt.Graphics g, ArrayList<Projectile> spriteHolder) {
        int size = spriteHolder.size();
        for (int t = 0; t < size; t++) {
            Sprite s = spriteHolder.get(t).t;
            s.paint(g);
        }
        spriteHolder.removeIf(i -> i.t.getClear());
    }

    /**
     * Gets the sprite from Projectile (default on purpose).
     * @return: Sprite t
     */
    Sprite getSprite() {
        return t; 
    }

    /**
     * Gets the damage from Projectile (default on purpose).
     * @return: int damage
     */
    int getDamage() {
        return damage;
    }

    /**
     * Gets the hashCode from Projectile (default on purpose).
     * @return: int hashCode
     */
    int getHashCode() {
        return hashCode;
    }
}
