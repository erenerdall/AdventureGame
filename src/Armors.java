public class Armors {
    private String name;
    private int block;
    private int ID;
    private int money;

    public Armors(String name, int block, int ID, int money) {
        this.name = name;
        this.block = block;
        this.ID = ID;
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public static Armors[] armors(){
        Armors armorList[]=new Armors[3];
        armorList[0]=new Armors("Hafif",1,1,15);
        armorList[1]=new Armors("Orta",3,2,25);
        armorList[2]=new Armors("Ağır",5,3,40);
        return armorList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
