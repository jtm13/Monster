import java.awt.event.*;
/**
 * KeyListener to JFrame
 * Tried, didn't want to mess with key bindings to get rid of focus issues, so this is going on a button
 */
public class Keys extends KeyAdapter
{
    boolean[][] pressed; // for key recognition
    Monster pal;
    Monster enemy;
    /*
     * Constructor for Keys
     */
    public Keys(Monster pal, Monster enemy)
    {
        pressed = new boolean[2][6]; // for both objects
        for(int t = 0; t < 6; t++)
        {
            pressed[0][t] = false;
            pressed[1][t] = false;
        }
        this.pal = pal;
        this.enemy = enemy;
    }

    /*
     * controls objects when keys are pressed
     * @param: KeyEvent e for knowing KeyEvents
     */
    public void keyPressed(KeyEvent e)
    {
        Thread f; // to not mess up main thread
        switch(e.getKeyCode()) // gets the key pressed
        {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN: // for options(keyboard or keypad)
            if(!pressed[0][1]) // for no janky physics
            {
                f = new Thread(new Runnable(){  // it works right
                        public void run(){
                            pressed[0][1] = true;
                            pal.getSprite().setImage(2);
                            while(pressed[0][1])
                            {
                                if(!pal.getSprite().moveYDown()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            pal.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP: // for options(keyboard or keypad)
            if(!pressed[0][0]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[0][0] = true;
                            pal.getSprite().setImage(2);
                            while(pressed[0][0])
                            {
                                if(!pal.getSprite().moveYUp()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            pal.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT: // for options(keyboard or keypad)
            if(!pressed[0][2]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[0][2] = true;
                            pal.getSprite().setImage(2);
                            while(pressed[0][2])
                            {
                                if(!pal.getSprite().moveXLeft()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            pal.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT: // for options(keyboard or keypad)
            if(!pressed[0][3]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[0][3] = true;
                            pal.getSprite().setImage(2);
                            while(pressed[0][3])
                            {
                                if(!pal.getSprite().moveXRight()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            pal.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_SPACE: 
            if(!pressed[0][4]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[0][4] = true;
                            pal.attackOther(enemy);
                            pressed[0][4] = false;
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_S:
            if(!pressed[1][1]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[1][1] = true;
                            enemy.getSprite().setImage(2);
                            while(pressed[1][1])
                            {
                                if(!enemy.getSprite().moveYDown()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            enemy.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_W:
            if(!pressed[1][0]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[1][0] = true;
                            enemy.getSprite().setImage(2);
                            while(pressed[1][0])
                            {
                                if(!enemy.getSprite().moveYUp()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            enemy.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_A:
            if(!pressed[1][2]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[1][2] = true;
                            enemy.getSprite().setImage(2);
                            while(pressed[1][2])
                            {
                                if(!enemy.getSprite().moveXLeft()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            enemy.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_D:
            if(!pressed[1][3]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[1][3] = true;
                            enemy.getSprite().setImage(2);
                            while(pressed[1][3])
                            {
                                if(!enemy.getSprite().moveXRight()) // if colliding with a wall, break
                                {
                                    break;
                                }
                            }
                            enemy.getSprite().setImage(0);
                        }
                    });
                f.start();
            }
            break;
            case KeyEvent.VK_Q: // add
            if(!pressed[1][4]) // for no janky physics
            {
                f = new Thread(new Runnable(){ 
                        public void run(){
                            pressed[1][4] = true;
                            enemy.attackOther(pal);
                            pressed[1][4] = false;
                        }
                    });
                f.start();
            }
            break;

        }
    }

    /*
     * makes the pressed keys false
     * @param: KeyEvent e to know what key was released
     */
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode()) // key released
        {
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
        }
    }
}