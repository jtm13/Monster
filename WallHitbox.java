import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Image;

/**
 * Stores and checks Rectangle objects with borders.
 * fully static (private constructor)
 * add walls where objects cannot pass through and a border where objects cannot escape through
 * border = new Rectangle(-1, -1, 1000, 1000) is the default border
 */
public class WallHitbox {
    // instance variables - replace the example below with your own
    static ArrayList<Rectangle> walls = new ArrayList<Rectangle>(); // for storage
    static Rectangle border = new Rectangle(-1, -1, 1000, 1000); // default
    private static boolean first = true;
    /**
     * Private constructor so objects cannot be created.
     * (why would somebody make an object of this,
     *  this is just like Math class, it does only static
     *   stuff).
     */
    private WallHitbox() {
    }

    /**
     * Adds invisible rectangles (walls) to the stage.
     * @param: int x for xPoint, int y for yPoint, int length for width, int width for height (confusing for people who want to try to hack my code)
     */
    public static void wall(int x, int y, int length, int width) { // I forgot about Rectangle parameter names
        walls.add(new Rectangle(x, y, length, width));
    }

    /**
     * Adds a border to the stage.
     * @pre: image must encompass entire stage
     * @param: Image i to get all necessary information
     */
    public static void walls(Image i) {
        border = new Rectangle(0,0, i.getWidth(null), i.getHeight(null));
    }

    /**
     * Modifies the rectangle for checking.
     * @pre: parameter direction must be up, down, left, or right
     * @param: Rectangle r for checking and modifying, String direction for modifying, int speed for modifying and checking
     * @return: boolean true if it is in a movable zone, false otherwise
     */
    public static boolean check(Rectangle r, String direction, int speed) {
        switch(direction.trim().toLowerCase()) {
            case "up":
            r.setLocation((int)r.getX(), (int)r.getY() - 2 * speed); // don't know why, but it looks better this way
            break;
            case "down":
            r.setLocation((int)r.getX(), (int)r.getY() + speed); // basic
            break;
            case "left":
            r.setLocation((int)r.getX() - speed, (int)r.getY()); // basic
            break;
            case "right":
            r.setLocation((int)r.getX() + speed, (int)r.getY()); // basic
            break;
        }
        return check(r); // checks the modified rectangle
    }

    /**
     * Checks the rectangle for intersections.
     * @param: Rectangle r for checking
     * @return: boolean true if it is in a movable zone, false otherwise
     */
    public static boolean check(Rectangle r) {
        for (Rectangle rect:walls) {
            if (r.intersects(rect)) { // if r intersects any of the rectangles, false
                return false;
            }
        }
        if (!r.intersection(border).equals(r)) { // if r does not fully intersect border, false
            return false;
        }
        return true;
    }

    static void setFirst(boolean first) {
        WallHitbox.first = first;
    } // setFirst

    /**
     * Paints the border outline in black.
     * @param: Graphics g for it to actually work
     * @post: the border is painted black on the thing (JPanel, JComponent, any J thing the has paint)
     */
    public static void paint(java.awt.Graphics g) { // did not want to import it (too lazy)
        if (!first) {
            return;
        } // if not first
        g.setColor(java.awt.Color.BLACK); // have to set color every time
        g.drawRect((int)border.getX(), (int)border.getY(),(int)border.getWidth(),(int)border.getHeight()); // draws only the outline
        first = false;
    }
}
