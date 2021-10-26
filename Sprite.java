import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;

/**
 * Has arrays of BufferedImages and handles the movement of them.
 *
 */
public class Sprite {
    // instance variables - replace the example below with your own
    private int x;
    private int y; // for the point
    private int speed; // for the movement speed
    private BufferedImage[] icon; // for the images
    private int[] position; // for the rotation/image chosen
    private Rectangle hurtbox; // for the damage checks
    private Rectangle previous;
    private boolean clear;
    
    /**
     * Default constructor.<p> Sets up position(rotation), speed, and x/y.
     * @param: int x and y for the point, int speed for the movement speed
     */
    public Sprite(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        position = new int[2]; // position initialized
        position[0] = 0; // image
        position[1] = 0; // rotation
    }

    /**
     * Main Constructor.<p> Sets up position, rotation, speed, and images.
     * @pre: pictures must be same dimension
     * @param: int x and y for the point, int speed for the movement speed, String var args for the undefined amount of files
     */
    public Sprite(int x, int y, int speed, String ... args) {
        this(x,y, speed); // other constructor
        int t = 0;
        for (String c:args) { //counts them
            t++;
        }
        icon = new BufferedImage[t]; // makes an array with t length
        t = 0; // recycling

        for (String c:args) {
            try {
                icon[t] = ImageIO.read(new File(c)); // filling the array
            }
            catch (Exception e) {
                System.out.println(e);
            }
            t++;
        }
        hurtbox = new Rectangle(x,y,this.icon[0].getWidth(),this.icon[0].getHeight());
        previous = new Rectangle(x,y,this.icon[0].getWidth(),this.icon[0].getHeight());
        clear = false;
        // this assumes that the dimensions of all images are the same
    }

    /**
     * Gets rectangle of Sprite.
     * @return: Rectangle hurtbox
     */
    Rectangle getHurtbox() {
        return hurtbox;
    }

    /**
     * Rotates image. Got it off the internet. Please don't sue me bro.
     * @param: int r for rotation degrees and int t for image being rotated
     */
    private BufferedImage rotate(int r, int t) {
        BufferedImage image = icon[t].getSubimage(0,0,icon[t].getWidth(),icon[t].getHeight());
        final double rads = Math.toRadians(r);
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
    } // I can tell you absolutely nothing about how this code works, it just does

    /**
     * Moves the sprite up based on speed.
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveYUp() {
        if (WallHitbox.check(hurtbox, "up", speed)) {
            y-= speed;
            hurtbox.setLocation(x,y);
            position[1] = 0; // sets rotation
            try {
                Thread.sleep(50); // same as Game delay
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return true;
        } // if not colliding with a wall
        return false;
    }

    /**
     * Moves the sprite down based on speed.
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveYDown() {
        if (WallHitbox.check(hurtbox, "down", speed)) {
            y+= speed;
            hurtbox.setLocation(x,y);
            position[1] = 2;// sets rotation
            try {
                Thread.sleep(50); // same as Game delay
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return true;
        } // if not colliding with a wall
        return false;
    }

    /**
     * Moves the sprite left based on speed.
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveXLeft() {
        if (WallHitbox.check(hurtbox, "left", speed)) {
            x-= speed;
            hurtbox.setLocation(x,y);
            position[1] = 3;// sets rotation
            try {
                Thread.sleep(50); // same as Game delay
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return true;
        } // if not colliding with a wall
        return false;
    }

    /**
     * Moves the sprite right based on speed.
     * @return: boolean true if the sprite moves, false otherwise
     */
    public boolean moveXRight() {
        if (WallHitbox.check(hurtbox, "right", speed)) {
            x+= speed;
            hurtbox.setLocation(x,y);
            position[1] = 1;// sets rotation
            try {
                Thread.sleep(50); // same as Game delay
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return true;
        } // if not colliding with a wall
        return false;
    }

    /**
     * Sets rotation (default on purpose).
     * @param: int position to set to
     */
    void setPosition(int position) {
        this.position[1] = position;
    }

    /**
     * sets image (default on purpose).
     * @param: int position to set to
     */
    void setImage(int position) {
        if (position >= 0 && position < icon.length) {
            this.position[0] = position;
        }
    }

    /**
     * Gets position array.
     * @return: Array position
     */
    int[] getArray() {
        return position;
    }

    /**
     * Gets rotation of the sprite. 0 is up, 1 is left, 2 is down
     * 3 is right.
     * @return: int rotation
     */
    int getPosition() {
        return this.position[1];
    }

    /**
     * Gets X-value (X point of the image).
     * @return: int x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets Y-value (Y point of the image).
     * @return: int y
     */
    public int getY() {
        return y;
    }
    
    void setClear(boolean clear) {
        this.clear = clear;
    } // setClear

    boolean getClear() {
        return clear;
    } // setClear
    
    /**
     * Paints the {@code Sprite} object.
     * @param: Graphics g to paint the sprite
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)previous.getX(), (int)previous.getY(), (int)previous.getWidth(), (int)previous.getHeight()); 
        previous.setLocation(x,y);
        if (clear) {
            return;
        } // if
        g.drawImage(rotate(position[1] * 90, position[0]), x, y, null); // paints the rotated image at the point
    }
}
