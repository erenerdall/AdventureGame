import java.util.ArrayList;

public class Inventory {
    private Weapons weapon;
    private Armors armor;
    private ArrayList<String> prizelist;

    public ArrayList<String> getPrizelist() {
        return prizelist;
    }

    public void setPrizelist(String prizelist) {
        this.prizelist.add(prizelist);
    }

    public Inventory() {
        this.weapon = new Weapons(0,"Yumruk",0,0);
        this.armor=new Armors("Zırh yok",0,0,0);
        this.prizelist=new ArrayList<String>();
    }

    public Armors getArmor() {
        return armor;
    }

    public void setArmor(Armors armor) {
        this.armor = armor;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }


}