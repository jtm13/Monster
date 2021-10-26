
import java.awt.event.*;

/**
 * KeyListener to JFrame.
 * Tried, didn't want to mess with key bindings to get rid of focus issues, so this is going on a button
 */
public class Keys extends KeyAdapter {
    boolean[][] pressed; // for key recognition
    Monster pal;
    Monster enemy;
    private KeyPress kp;
    /**
     * Constructor for Keys.
     */
    public Keys(Monster pal, Monster enemy) {
        pressed = new boolean[2][6]; // for both objects
        for (int t = 0; t < 6; t++) {
            pressed[0][t] = false;
            pressed[1][t] = false;
        }
        this.pal = pal;
        this.enemy = enemy;
    }

    /**
     * Controls objects when keys are pressed.
     * @param: KeyEvent e for knowing KeyEvents
     */
    public void keyPressed(KeyEvent e) {
        kp = new KeyPress();
        Thread f = new Thread(kp); // to not mess up main thread
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN: // for options(keyboard or keypad)
            helper(0,1,f);
            break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP: // for options(keyboard or keypad)
            helper(0,0,f);
            break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT: // for options(keyboard or keypad)
            helper(0,2,f);
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT: // for options(keyboard or keypad)
            helper(0,3,f);
            break;
            case KeyEvent.VK_SPACE: 
            f = new Thread(new ShootsEnemy());
            f.start();
            break;
            case KeyEvent.VK_S:
            helper(1,1,f);
            break;
            case KeyEvent.VK_W:
            helper(1,0,f);
            break;
            case KeyEvent.VK_A:
            helper(1,2,f);
            break;
            case KeyEvent.VK_D:
            helper(1,3,f);
            break;
            case KeyEvent.VK_Q: // add
            f = new Thread(new ShootsPlayer());
            f.start();
            break;
        } // gets the key pressed
    }

    /**
     * Makes the pressed keys false.
     * @param: KeyEvent e to know what key was released
     */
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) { 
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
            pressed[0][1] = false;
            break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
            pressed[0][0] = false;
            break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
            pressed[0][2] = false;
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
            pressed[0][3] = false;
            break;
            case KeyEvent.VK_S:
            pressed[1][1] = false;
            break;
            case KeyEvent.VK_W:
            pressed[1][0] = false;
            break;
            case KeyEvent.VK_A:
            pressed[1][2] = false;
            break;
            case KeyEvent.VK_D:
            pressed[1][3] = false;
            break;
        } // key released
    }

    /**
     * 
     */
    private void helper(int x, int y, Thread f) {
        if (!pressed[x][y]) {
            kp.run(x,y);
            f.start();
        } // for no janky physics
    }
    
    /**
     * Returns the number of pressed keys we care about being pressed.
     */
    private byte arrayChecker() {
        byte x = 0;
        for (boolean[] press:pressed) {
            for (boolean p:press) {
                if (p) {
                    x++;
                } // if one of them is false
            } // for p
        } // for press
        return x;
    } // arrayChecker
    
    /**
     * Implements Runnable to cut down on code.
     */
    class KeyPress implements Runnable {
        int[] arPos = {-1,-1};

        /**
         * Overloaded method that setups the arPos array.
         */
        public void run(int arPosLeft, int arPosRight) {
            arPos[0] = arPosLeft;
            arPos[1] = arPosRight;
        } // run

        /**
         * Method implemented. Runs when the thread calls it.
         */
        public void run() {
            pressed[arPos[0]][arPos[1]] = true;
            Monster mon = null;
            if (arPos[0] == 0) {
                mon = pal;
            } else {
                mon = enemy;
            } // which player
            mon.getSprite().setImage(arPos[1]);
            while(pressed[arPos[0]][arPos[1]]) {
                if (!move(mon)) {
                    break;
                } // if colliding with a wall, break
            }
            mon.getSprite().setImage(0);
        } // run
        
        /**
         * Gets the monster to move.
         */
        private boolean move(Monster m) {
            switch (arPos[1]) {
                case 0:
                    return m.getSprite().moveYUp();
                case 1:
                    return m.getSprite().moveYDown();
                case 2:
                    return m.getSprite().moveXLeft();
                case 3:
                    return m.getSprite().moveXRight();
            } // switch
            return false;
        } // move
    } // class

    /**
     * Implements {@link Runnable} to run when player shoots the enemy.
     */
    class ShootsEnemy implements Runnable {

        /**
         * Method implemented. Runs when the thread calls it.
         */
        public void run() {
            if (!pressed[0][4]) {        
                pressed[0][4] = true;
                pal.attackOther(enemy);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                pressed[0][4] = false;
            } // for no janky physics
        } // run
    } // class

    /**
     * Implements {@link Runnable} to run when enemy shoots the player.
     */
    class ShootsPlayer implements Runnable {

        /**
         * Method implemented. Runs when the thread calls it.
         */
        public void run() {
            if (!pressed[1][4]) {
                pressed[1][4] = true;
                enemy.attackOther(pal);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                pressed[1][4] = false;
            } // for no janky physics
        } // run
    } // class
}