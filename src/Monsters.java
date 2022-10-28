public class Monsters {
    private int ID;
    private int damage;
    private int health;
    private String name;
    private int awardCoin;

    public Monsters(int ID, int damage, int health, String name, int awardCoin) {
        this.ID = ID;
        this.damage = damage;
        this.health = health;
        this.name = name;
        this.awardCoin = awardCoin;
    }

    public int getAwardCoin() {
        return awardCoin;
    }

    public void setAwardCoin(int awardCoin) {
        this.awardCoin = awardCoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
