import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;
/**
 * Has arrays of BufferedImages and handles the movement of them.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sprite
{
    // instance variables - replace the example below with your own
    private int x;
    private int y; // for the point
    private int speed; // for the movement speed
    private BufferedImage[] icon; // for the images
    private int[] position; // for the rotation/image chosen
    private Rectangle hurtbox; // for the damage checks
    /*
     * default constructor
     * @param: int x and y for the point, int speed for the movement speed
     */
    public Sprite(int x, int y, int speed)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        position = new int[2]; // position initialized
        position[0] = 0; // image
        position[1] = 0; // rotation
    }

    /*
     * Main Constructor
     * @pre: pictures must be same dimension
     * @param: int x and y for the point, int speed for the movement speed, String var args for the undefined amount of files
     */
    public Sprite(int x, int y, int speed, String ... args)
    {
        this(x,y, speed); // other constructor
        int t = 0;
        for(String c:args) //counts them
        {
            t++;
        }
        icon = new BufferedImage[t]; // makes an array with t length
        t = 0; // recycling

        for(String c:args)
        {
            try
            {
                icon[t] = ImageIO.read(new File(c)); // filling the array
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            t++;
        }
        hurtbox = new Rectangle(x,y,this.icon[0].getWidth(),this.icon[0].getHeight()); // this assumes that the dimensions of all images are the same
    }

    /*
     * gets rectangle
     * @return: Rectangle hurtbox
     */
    Rectangle getHurtbox()
    {
        return hurtbox;
    }

    /*
     * rotates image
     * @param: int r for rotation degrees and int t for image being rotated
     */
    private BufferedImage rotate(int r, int t)
    {
        BufferedImage image = icon[t].getSubimage(0,0,icon[t].getWidth(),icon[t].getHeight());
        final double rads = Math.toRadians(r); // I can tell you absolutely nothing about how this code works, it just does
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        return rotatedImage;
    }

    /*
     * moves the sprite up based on speed
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveYUp()
    {
        if(WallHitbox.check(hurtbox, "up", speed)) // if not colliding with a wall
        {
            y-= speed;
            hurtbox.setLocation(x,y);
            position[1] = 0; // sets rotation
            try
            {
                Thread.sleep(50); // same as Game delay
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    /*
     * moves the sprite down based on speed
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveYDown()
    {
        if(WallHitbox.check(hurtbox, "down", speed)) // if not colliding with a wall
        {
            y+= speed;
            hurtbox.setLocation(x,y);
            position[1] = 2;// sets rotation
            try
            {
                Thread.sleep(50); // same as Game delay
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    /*
     * moves the sprite left based on speed
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveXLeft()
    {
        if(WallHitbox.check(hurtbox, "left", speed)) // if not colliding with a wall
        {
            x-= speed;
            hurtbox.setLocation(x,y);
            position[1] = 3;// sets rotation
            try
            {
                Thread.sleep(50); // same as Game delay
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    /*
     * moves the sprite right based on speed
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveXRight()
    {
        if(WallHitbox.check(hurtbox, "right", speed)) // if not colliding with a wall
        {
            x+= speed;
            hurtbox.setLocation(x,y);
            position[1] = 1;// sets rotation
            try
            {
                Thread.sleep(50); // same as Game delay
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    /*
     * sets rotation (default on purpose)
     * @param: int position to set to
     */
    void setPosition(int position)
    {
        this.position[1] = position;
    }

    /*
     * sets image (default on purpose)
     * @param: int position to set to
     */
    void setImage(int position)
    {
        if(position >= 0 && position < icon.length)
        {
            this.position[0] = position;
        }
    }

    /*
     * gets position
     * @return: Array position
     */
    int[] getArray()
    {
        return position;
    }

    /*
     * gets rotation
     * @return: int rotation
     */
    int getPosition()
    {
        return this.position[1];
    }

    /*
     * gets X-value
     * @return: int x
     */
    public int getX()
    {
        return x;
    }

    /*
     * gets Y-value
     * @return: int y
     */
    public int getY()
    {
        return y;
    }

    /*
     * paints the sprite
     * @param: Graphics g to paint the sprite
     */
    public void paint(Graphics g)
    {
        g.drawImage(rotate(position[1] * 90, position[0]), x, y, null); // paints the rotated image at the point
    }
}
