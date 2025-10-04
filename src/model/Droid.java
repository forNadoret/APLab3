package model;

public abstract class Droid {
    private int maxHealth;
    private String name;
    private int health;
    private int damage;
    private int armour;

    public Droid(int health, int damage, int armour) {
        this.health = health;
        this.damage = damage;
        this.armour = armour;
        this.maxHealth = health;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmour() {
        return armour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void restoreHealth()
    {
        this.health = maxHealth;
    }

    public String toString() {
        return name +
                " [health=" + health +
                ", damage=" + damage +
                ", armour=" + armour +
                "]";
    }
}