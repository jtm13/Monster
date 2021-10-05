import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/**
 * Player deals with selection of monsters by players
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int x;
    Monster pal;
    Monster enemy;
    private JButton[] buttons;
    private JLabel label;
    private JPanel panel;
    private java.util.ArrayList<Monster> mon;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        x = 0;
        mon = new java.util.ArrayList<Monster>();
        buttons = new JButton[4];
        buttons[0] = new JButton("Earth");
        buttons[1] = new JButton("Fire");
        buttons[2] = new JButton("Water");
        buttons[3] = new JButton("Wind"); // instansiation
        buttons[0].addActionListener(new AL1());
        buttons[1].addActionListener(new AL2());
        buttons[2].addActionListener(new AL3());
        buttons[3].addActionListener(new AL4()); // adding listener inputs
        label = new JLabel("Player " + (x + 1) + ": choose your monster");
        panel = new JPanel(); // more instansiation
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // changing layout format of JPanel(default FlowManager)
        panel.add(label);
        panel.add(buttons[0]);
        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]); // adds Jcomponents to JPanel
        panel.setOpaque(true);
        panel.setBackground(java.awt.Color.WHITE);
    }

    /*
     * gets the JPanel
     * @return: JPanel panel
     */
    JPanel getPanel()
    {
        return panel;
    }

    /*
     * changes the layout of the JPanel
     * @post: JPanel buttons removed and changes/sets labels and a button
     */
    private void decide()
    {
        panel.remove(buttons[0]);
        panel.remove(buttons[1]);
        panel.remove(buttons[2]);
        panel.remove(buttons[3]); // removal
        label.setText("Player 1 controls are arrows and Spacebars to shoot.");
        panel.add(new JLabel("Player 2 controls are WASD and Q to shoot.")); // can't do this in one JLabel
        JButton b = new JButton("Start");
        b.addKeyListener(new Keys(mon.get(0), mon.get(1))); // for focus issues
        panel.add(b); // adds button
        buttons = null; // into the garbage it goes
        Game.update(mon); // for managing and painting
    }
    class AL1 implements ActionListener
    {
        /*
         * creates specified object for clicking on button
         * @param: ActionEvent b to provide input info (not used, just for implementation)
         */
        public void actionPerformed(ActionEvent b)
        {
            int y = 0; // dictates Y-Coordinates
            switch(x)
            {
                case 0:
                y = 270;
                break;
                case 1:
                y = 1;
                break;
            }
            mon.add(new EarthMonster("Grounded", 143, y)); // each different
            x++;
            if(x == 2) // two is the specified limit for this game
            {
                decide();
            }
        }
    }
    class AL2 implements ActionListener
    {
        /*
         * creates specified object for clicking on button
         * @param: ActionEvent b to provide input info (not used, just for implementation)
         */
        public void actionPerformed(ActionEvent b)
        {
            int y = 0; // dictates Y-Coordinates
            switch(x)
            {
                case 0:
                y = 270;
                break;
                case 1:
                y = 1;
                break;
            }
            mon.add(new FireMonster("Charaturtle", 143, y)); // each different
            x++;
            if(x == 2) // two is the specified limit for this game
            {
                decide();
            }
        }
    }
    class AL3 implements ActionListener
    {
        /*
         * creates specified object for clicking on button
         * @param: ActionEvent b to provide input info (not used, just for implementation)
         */
        public void actionPerformed(ActionEvent b)
        {
            int y = 0; // dictates Y-Coordinates
            switch(x)
            {
                case 0:
                y = 270;
                break;
                case 1:
                y = 1;
                break;
            }
            mon.add(new WaterMonster("Waturtle", 143, y)); // each different
            x++;
            if(x == 2) // two is the specified limit for this game
            {
                decide();
            }
        }
    }
    class AL4 implements ActionListener
    {
        /*
         * creates specified object for clicking on button
         * @param: ActionEvent b to provide input info (not used, just for implementation)
         */
        public void actionPerformed(ActionEvent b)
        {
            int y = 0; // dictates Y-Coordinates
            switch(x)
            {
                case 0:
                y = 270;
                break;
                case 1:
                y = 1;
                break;
            }
            mon.add(new WindMonster("Windmill", 143, y)); // each different
            x++;
            if(x == 2) // two is the specified limit for this game
            {
                decide();
            }
        }
    }
}
