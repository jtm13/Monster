
/**
 * You gotta catch 'em all, right
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trainer {
    // instance variables - replace the example below with your own
    private Monster[] mon;
    private Sprite sprite;
    private String name;

    /**
     * Main Constructor for objects of class Trainer.
     * @param: String name for name, var arg Monster m for the intant use of Trainer objects
     */
    public Trainer(String name, Monster ... m) {
        this.name = name;
        int t = 0;
        for (Monster monster:m) {
            t++;
        }
        mon = new Monster[t];
        t = 0;
        for (Monster monster:m) {
            mon[t] = monster;
            t++;
        }
    }

    /**
     * Int Constructor of Trainer class.
     * @param: String name for name, int t for the length of the Monster array
     * didn't make because I need to, but because I want to
     */
    public Trainer(String name, int t) {
        this.name = name;
        mon = new Monster[t];
    }

    /**
     * Null Constructor of Trainer class (creates Monster Array of length 3)
     */
    public Trainer() {
        this("Ash", 3);
    }

    /**
     * Puts a monster in the array at the given location.
     * @param  Monster m to add and int index for the location
     */
    public void addMonster(Monster m, int index) {
        if (index < mon.length && index >= 0) {
            mon[index] = m;
        }
    }

    /**
     * Returns a Monster of the array if it is alive.
     * @param: int index for the location
     * @return: Monster if no ArrayIndexOutOfBounds or NullPointer Exceptions and is alive, null otherwise
     */
    public Monster getMonster(int index) {
        if (index < mon.length && index >= 0 && mon[index] != null && !mon[index].getDeath()) {
            return mon[index];
        }
        return null;
    }

    /**
     * Gets the info about the team.
     * @return: team(every Monster) toString()
     */
    public String getTeam() {
        String a = "";
        for (Monster m:mon) {
            a += m.toString() + "\n";
        }
        return a;
    }

    /**
     * Returns the hit points and death state of the given monster.
     * @return: state of Monster providing no ArrayIndexOutOfBounds and NullPointer Execptions
     */
    public String checkStatus(int index) {
        if (index < mon.length && index >= 0 && mon[index] != null) {
            return "HP =" + mon[index].getHitPoints() + "; death =" + mon[index].getDeath();
        }
        return null;
    }

    /**
     * checks if the team lost
     * @return: boolean, true if trainer lost, false otherwise
     */
    public boolean lost() {
        for (Monster m:mon) {
            if (!m.getDeath()) {
                return false;
            }
        }
        return true;
    }
}
