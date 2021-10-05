
/*
 * The abstract superclass Monster defines the expected common behaviors,
 * via abstract methods.
 */
public abstract class Monster {
    private String name;  // name of monster
    private int hitPoints; // how many points the monster has out of health
    private int health; // how much max health the monster has
    private boolean death; // wheter the monster id alive
    private Sprite sprite; // the monster HAS-A Sprite
    private long time = 0;
    /*
     * backgroud constructor
     * @param: String name for the name, int health for the health and hitPoints
     */
    private Monster(String name, int health) {  // constructor
        this.name = name;
        hitPoints = health; // set to max health
        this.health = health; // health is set
        death = false; // all monsters must be born alive
    }

    /*
     * main constructor
     * @param: String name for the name, Sprite sprite for the sprite of monster
     */
    public Monster(String name, Sprite sprite) {  // main constructor
        this(name, 100); // if I want more constructors
        this.sprite = sprite;
    }

    /*
     * gets sprite (default on purpose)
     * @return: monster sprite object
     */
    Sprite getSprite()
    {
        return sprite;
    }

    /*
     * gets name
     * @return: String name
     */
    public String getName()
    {
        return name;
    }

    /*
     * gets hitPoints
     * @return: int hitPoints
     */
    public int getHitPoints()
    {
        return hitPoints;
    }

    /*
     * gets percentage of hitPoints to health (default on purpose)
     * @return: double percentage from 0 to 1
     */
    double getHealthPercentage()
    {
        return (double)hitPoints/health;
    }

    /*
     * gets death state
     * @return: boolean death
     */
    public boolean getDeath()
    {
        return death;
    }

    /*
     * checks the sprite hitbox to see if monster is hit
     * @param: Projectile p to get the hurtbox and damage
     * @return: boolean true if not hit, false if hit
     */
    public boolean check(Projectile p)
    {
        if(sprite.getHurtbox().intersects(p.getSprite().getHurtbox()) && p.getHashCode() != hashCode()) // so projectiles will not attack their creators
        {
            damage(p.getDamage());
            return false;
        }
        return true;
    }
    // Returns a message saying what element the Monster is using to attack with.
    public abstract String attackMessage();
    //Returns true if the Monster that is not a parameter has the type advantage. 
    //Returns false if there is no type advantage.
    public abstract boolean attackAdvantage(Monster m);
    //Returns true if the Monster that is not a parameter has the type disadvantage. 
    //Returns false if there is no type disadvantage.
    public abstract boolean attackDisadvantage(Monster m);

    /*
     * creates a Projectile object with damage associated with the type of the enemy Monster
     * @pre: Monster objects do not exceed two
     * @param: Monster m to compare type advantages
     */
    public void attackOther(Monster m)
    {
        if(Game.getTime() - time <= 1000)
        {
            return;
        }
        int damage = 10;
        if(attackAdvantage(m)) // monster has an attack advantage
        {
            damage *= 2;
        }
        else if(attackDisadvantage(m)) // monster has an attack disadvantage
        {
            damage /= 2;
        }
        int x = sprite.getX(); // for Projectile purposes
        int y = sprite.getY();
        switch(sprite.getPosition()) // get the rotation of the object
        {
            case 0: // up
            y -= 9;
            x += 12;
            break;
            case 1: // right
            y += 12;
            x += 33;
            break;
            case 2: // down
            y += 33;
            x += 12;
            break;
            case 3: // left
            y += 12;
            x -= 9;
            break;
        }
        Game.add(new Projectile(x, y, damage, sprite.getPosition(), hashCode())); //Game handles the storage of Projectiles
        time = Game.getTime();
    }

    /*
     * damages the monster
     * @pre: must not be negative
     * @param: int d for the amount of damage done
     * @post: Monster has less hitPoints left
     */
    private void damage(int d)
    {
        hitPoints -= d;
        if(hitPoints <= 0) // death is immenent
        {
            hitPoints = 0;
            die(); // don't know why I created it
        }
    }

    /*
     * makes death equal true
     * @post: Monster dies
     */
    private void die()
    {
        death = true;
    }

    /*
     * returns the instance variables for the Monster
     * @return: String
     */
    public String toString()
    {
        return "Monster class: name is " + name + ", hit Points is " + hitPoints + ", health is " + health + ", death is " + death + ", and has a sprite.";
    }

    /*
     * sees if two Monster objects are the same
     * @pre: the parameter IS-A Monster
     * @param: Object monster to check 
     * @return: boolean, true if they are the same, false otherwise
     */
    public boolean equals(Object monster)
    {
        Monster m = (Monster)monster;
        if(name.equals(m.getName()) && hitPoints == m.getHitPoints() && death == m.getDeath() && attackMessage().equals(m.attackMessage()))
        {
            return false;
        }
        return true;
    }
}