
/**
 * Write a description of class EarthMonster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EarthMonster extends Monster {
    
    /**
     * Passes the necessary variables to Monster.
     * @pre: files are in right places
     */
    public EarthMonster(String name, int x, int y) {
        super(name, new Sprite(x, y, 3, "MonsterSprites\\EarthMonster Idle 1.png",
        "MonsterSprites\\EarthMonster Idle 2.png", "MonsterSprites\\EarthMonster Moving 1.png",
        "MonsterSprites\\EarthMonster Moving 2.png"));
    }

    /**
     * Returns the element of the Monster.
     * @return: String element of the Monster
     */
    public String attackMessage() {
        return "Earth";
    }

    /**
     * Checks if the Monster has a type advantage against the other Monster.
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type advantage, false otherwise
     */
    public boolean attackAdvantage(Monster m) {
        return (m instanceof WindMonster);
    }

    /**
     * Checks if the Monster has a type disadvantage against the other Monster.
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type disadvantage, false otherwise
     */
    public boolean attackDisadvantage(Monster m) {
        return (m instanceof FireMonster);
    }
}
