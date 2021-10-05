
/**
 * Write a description of class WaterMonster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WaterMonster extends Monster
{
    /*
     * passes the necessary variables to Monster
     * @pre: files are in right places
     */
    public WaterMonster(String name, int x, int y)
    {
        super(name, new Sprite(x, y, 3, "MonsterSprites\\WaterMonster Idle 1.png", "MonsterSprites\\WaterMonster Idle 2.png", "MonsterSprites\\WaterMonster Moving 1.png", "MonsterSprites\\WaterMonster Moving 2.png"));
    }

    /*
     * returns the element of the Monster
     * @return: String element of the Monster
     */
    public String attackMessage()
    {
        return "Water";
    }

    /*
     * checks if the Monster has a type advantage against the other Monster
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type advantage, false otherwise
     */
    public boolean attackAdvantage(Monster m)
    {
        return (m instanceof FireMonster);
    }

    /*
     * checks if the Monster has a type disadvantage against the other Monster
     * @param: Monster m to compare
     * @return: boolean, true if te Monster has a type disadvantage, false otherwise
     */
    public boolean attackDisadvantage(Monster m)
    {
        return (m instanceof WindMonster);
    }
}
