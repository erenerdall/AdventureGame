import java.util.*;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    private String name;
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private int choose;
    private Inventory inventory;
    BattleLoc locControl;

    public Player() {
        this.inventory = new Inventory();
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void selectChar() {
        System.out.println("Karakterler");
        System.out.println("====================================================================================");
        System.out.println("1-Samuray\tHasar: 5\tSağlık: 21\tPara: 15");
        System.out.println("2-Okçu\tHasar: 7\tSağlık: 18\tPara: 20");
        System.out.println("3-Şovalye\tHasar: 8\tSağlık: 24\tPara: 5");
        System.out.println("====================================================================================");
        choose = input.nextInt();
        switch (choose) {
            case 1:
                initPlayer(new Samurai());
                System.out.println(this.name + " Seçtiniz");
                break;
            case 2:
                initPlayer(new Archer());
                this.name = "Okçu";
                this.damage = 7;
                this.health = 18;
                this.money = 20;
                System.out.println(this.name + " Seçtiniz");
                break;
            case 3:
                initPlayer(new Knight());
                this.name = "Şovalye";
                this.damage = 8;
                this.health = 24;
                this.money = 5;
                System.out.println(this.name + " Seçtiniz");
                break;
            default:
                System.out.println("Geçersiz seçim yaptınız.");
                break;
        }
    }

    public void selectLoc() {
        Location location = null;
        while (true) {
            playerInfo();
            System.out.println("=========================================");
            System.out.println("Lütfen Gitmek istediğiniz bölgeyi seçiniz.");
            System.out.println("1-Güvenli Ev");
            System.out.println("2-Eşya Dükkanı");
            System.out.println("3-Mağara <Ödül-->Yemek> (Dikkat zombi çıkabilir)");
            System.out.println("4-Orman <Ödül-->Odun> (Dikkat vampir çıkabilir)");
            System.out.println("5-Nehir <Ödül-->Su> (Dikkat ayı çıkabilir)");
            System.out.println("6-Maden <Ödül-->Rastgele> (Dikkat yılan var)");
            System.out.println("0-Oyunu sonlandır");
            System.out.println("=========================================");
            int selectLocation = input.nextInt();
            switch (selectLocation) {
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(this);
                    break;
                case 2:
                    location = new ToolStore(this);
                    break;
                case 3:
                    if (this.getInventory().getPrizelist().contains("food")){
                        System.out.println("Buradaki ödülü kazandınız tekrar giremezsiniz.");
                        System.out.println();
                        System.out.println("Güvenli Eve dönüyorsunuz");
                        System.out.println("===================");
                        location=new SafeHouse(this);
                    }
                    else {
                    location=new Cave(this);
                    }
                    break;
                case 4:
                    if (this.getInventory().getPrizelist().contains("firewood")) {
                        System.out.println("Buradaki ödülü kazandınız tekrar giremezsiniz.");
                        System.out.println();
                        System.out.println("Güvenli Eve dönüyorsunuz");
                        System.out.println("===================");
                        location = new SafeHouse(this);
                    }else {
                    location=new Forest(this);
                    }
                    break;
                case 5:
                    if (this.getInventory().getPrizelist().contains("water")) {
                        System.out.println("Buradaki ödülü kazandınız tekrar giremezsiniz.");
                        System.out.println();
                        System.out.println("Güvenli Eve dönüyorsunuz");
                        System.out.println("===================");
                        location = new SafeHouse(this);
                    }else {
                    location=new River(this);
                    }
                    break;
                case 6:
                    location=new Pit(this);
                    break;
                default:
                    System.out.println("Yanlış seçim yaptınız güvenli evdesiniz.");
                    location = new SafeHouse(this);
            }
            if (location==null){
                System.out.println("Oyundan çekildiniz!");
                break;
            }
            if (!location.onLocation()) {
            if (this.getInventory().getPrizelist().contains("food")&&this.getInventory().getPrizelist().contains("firewood")&&this.getInventory().getPrizelist().contains("water")){
                System.out.println("OYUNU KAZANDINIZ !!!");
                System.out.println("TEBRİKLER");
                break;
            }
                System.out.println("GAME OVER !");
                break;
            }

        }

    }

    public void playerInfo() {
        System.out.println("Silahınız: " + this.getInventory().getWeapon().getName() +
                " Hasarınız: " + this.getTotalDamage() +
                " Zırhınız: " + this.getInventory().getArmor().getName() +
                " Engelleme: " + this.getInventory().getArmor().getBlock() +
                " Canınız: " + this.getHealth()  +
                " Paranız: " + this.getMoney()
        );
    }

    public void initPlayer(Heroes hero) {
        this.setName(hero.getName());
        this.setDamage(hero.getDamage());
        this.setDefaultHealth(hero.getHealth());
        this.setHealth(hero.getHealth());
        this.setMoney(hero.getMoney());
    }
}
