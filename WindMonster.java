
/**
 * Write a description of class WindMonster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WindMonster extends Monster {
    
    /**
     * Passes the necessary variables to Monster.
     * @pre: files are in right places
     */
    public WindMonster(String name, int x, int y) {
        super(name, new Sprite(x, y, 3, "MonsterSprites\\WindMonster Idle 1.png",
        "MonsterSprites\\WindMonster Idle 2.png", "MonsterSprites\\WindMonster Moving 1.png",
        "MonsterSprites\\WindMonster Moving 2.png"));
    }

    /**
     * Returns the element of the Monster.
     * @return: String element of the Monster
     */
    public String attackMessage() {
        return "Wind";
    }

    /**
     * Checks if the Monster has a type advantage against the other Monster.
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type advantage, false otherwise
     */
    public boolean attackAdvantage(Monster m) {
        return (m instanceof WaterMonster);
    }

    /**
     * Checks if the Monster has a type disadvantage against the other Monster.
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type disadvantage, false otherwise
     */
    public boolean attackDisadvantage(Monster m) {
        return (m instanceof EarthMonster);
    }
}
