import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Monsters monster;
    private String award;
    private int maxMonster;
    Random r = new Random();
    Weapons weapon = null;
    Armors armor = null;
    private int[] coins = {1, 5, 10};
    private int i = 1;
    private String prize;

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public Armors getArmor() {
        return armor;
    }

    public void setArmor(Armors armor) {
        this.armor = armor;
    }

    public int[] getCoins() {
        return coins;
    }

    public void setCoins(int[] coins) {
        this.coins = coins;
    }

    public BattleLoc(Player player, String locationName, Monsters monsters, String award, int maxMonster,String prize) {
        super(player, locationName);
        this.monster = monsters;
        this.award = award;
        this.maxMonster = maxMonster;
        this.prize=prize;
    }


    @Override
    public boolean onLocation() {
        maxMonster = this.randomMonster();
        System.out.println("Şuanda buradasınız --> " + this.getLocationName());
        System.out.println("Dikkat burada " + this.maxMonster + " adet " + this.monster.getName() + " yaşıyor.");
        System.out.println("Ödül : " + this.getAward() + " + Para");
        System.out.println("<S>avaş veya <K>aç");
        String selectBattle = Main.input.next();
        selectBattle = selectBattle.toUpperCase();
        if (selectBattle.equals("S")) {
            System.out.println("Savaş başlıyor !!");
            if (combat(maxMonster)) {
                System.out.println(this.getLocationName() + "--> buradaki tüm düşmanları yendiniz.");
                System.out.println("Büyük Ödülü Kazandınız");
                System.out.println("Ödülünüz: "+this.getPrize());
                System.out.println(this.getLocationName() + " -> Buraya tekrar gelemezsiniz!");
                this.getPlayer().getInventory().setPrizelist(this.getPrize());
                return true;
            }
            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Öldünüz !! ");
                return false;
            }
        }

        return true;
    }

    public boolean combat(int maxMonster) {
        int defaultMonsterHealth = this.getMonster().getHealth();
        int whoAttacked = r.nextInt(2) + 1;
        for (this.getI(); this.getI() <= maxMonster; i++) {
            this.getMonster().setHealth(defaultMonsterHealth);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                playerStats();
                monsterStats(this.getI());
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = Main.input.next().toUpperCase();
                if (selectCombat.equals("V")) {
                    if (whoAttacked==1){
                    System.out.println("İlk Siz vurdunuz!");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    System.out.println();
                    System.out.println("------------------");
                    if (this.getMonster().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vurdu!");
                        if (this.getPlayer().getInventory().getArmor().getBlock() > this.getMonster().getDamage()) {
                            this.getMonster().setDamage(0);
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - (this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock()));
                        System.out.println("Kalan canınız: " + this.getPlayer().getHealth());
                    }
                    if (this.getMonster().getHealth() <= 0) {
                        System.out.println(i + "." + this.getMonster().getName() + " öldürüldü");
                        if (getLocationName().equals("Maden")) {
                            this.getChance();
                        }
                        if (this.getMonster().getAwardCoin() > 0) {
                            System.out.println("Para kazandınız.");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAwardCoin());
                            System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                        }

                    }
                    if (this.getPlayer().getHealth() <= 0) {
                        return false;
                    }
                    }
                    if (whoAttacked==2){
                        System.out.println("İlk Canavar size vurdu!");
                        if (this.getPlayer().getInventory().getArmor().getBlock()>this.getMonster().getDamage()){
                            this.getMonster().setDamage(0);
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-this.getMonster().getDamage());
                        System.out.println("Kalan canınız: " + this.getPlayer().getHealth());
                        System.out.println("------------------");
                        System.out.println();
                        if (this.getPlayer().getHealth() <= 0) {
                            return false;
                        }
                        if (this.getMonster().getHealth()>0){
                            System.out.println("Siz vurdunuz!");
                            this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                            System.out.println();
                            System.out.println("------------------");
                        }
                        if (this.getMonster().getHealth()<=0){
                            System.out.println(i + "." + this.getMonster().getName() + " öldürüldü");
                            if (getLocationName().equals("Maden")) {
                                this.getChance();
                            }
                            if (this.getMonster().getAwardCoin() > 0) {
                                System.out.println("Para kazandınız.");
                                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAwardCoin());
                                System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                            }
                        }

                    }
                } else if (selectCombat.equals("K")) {
                    System.out.println("Kaçtınız.");
                    return false;
                }
            }
        }

        return true;

    }

    public void getChance() {
        int chance = r.nextInt(101) + 1;
        if (chance < 0 && chance >= 15) {
            //silah kazanılacak
            System.out.println("Rastgele bir silah kazandınız!!!");
            System.out.print("-Ödülünüz: ");
            int weaponChance = r.nextInt(101) + 1;
            if (weaponChance > 0 && weaponChance <= 50) {
                //tabanca kazanılacak
                this.getPlayer().getInventory().setWeapon(Weapons.weapon()[0]);
                System.out.println("Tabanca");
            } else if (weaponChance > 50 && weaponChance <= 80) {
                //kılıç kazanılacak
                this.getPlayer().getInventory().setWeapon(Weapons.weapon()[1]);
                System.out.println("Kılıç");
            } else {
                //tüfek kazanıalcak
                System.out.println("Tüfek");
                this.getPlayer().getInventory().setWeapon(Weapons.weapon()[2]);
            }
        } else if (chance > 15 && chance <= 30) {
            // armor kazanılacak
            System.out.println("Rastgele bir zırh kazandınız!!!");
            System.out.print("-Ödülünüz: ");
            int armorChance = r.nextInt(101) + 1;
            if (armorChance > 0 && armorChance <= 50) {
                //hafif armor kazanılacak
                System.out.println("Hafif zırh");
                this.getPlayer().getInventory().setArmor(Armors.armors()[0]);
            } else if (armorChance > 50 && armorChance <= 80) {
                //orta armor kazanılacak
                System.out.println("Orta zırh");
                this.getPlayer().getInventory().setArmor(Armors.armors()[1]);
            } else {
                //ağır armor kazanılacak
                System.out.println("Ağır zırh");
                this.getPlayer().getInventory().setArmor(Armors.armors()[2]);
            }
        } else if (chance > 30 && chance <= 55) {
            //para kazanılacak
            System.out.println("Rastgele para kazandınız!!!");
            System.out.print("-Ödülünüz: ");
            int moneyChance = r.nextInt(101) + 1;
            if (moneyChance > 0 && moneyChance <= 50) {
                // 1 coin kazanılacak
                System.out.println("1");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getCoins()[0]);
            } else if (moneyChance > 50 && moneyChance <= 80) {
                //5 para kazanılacak
                System.out.println("5");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getCoins()[1]);

            } else {
                System.out.println("10");
                //10 para kazanılacak
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getCoins()[2]);

            }
        } else {
            System.out.println("Ödül kazanamadınız !");
            // kazanılmayacak
        }
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        if (this.getMonster().getHealth() < 0) {
            this.getMonster().setHealth(0);
        }
        System.out.println("Canavarın kalan canı: " + this.getMonster().getHealth());
    }

    public void playerStats() {
        System.out.println("-------Oyuncu Değerleri-------");
        System.out.println("Can: " + this.getPlayer().getHealth());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Para: " + this.getPlayer().getMoney());
        if (this.getPlayer().getInventory().getWeapon().getDamage() > 0) {
            System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        }
        if (this.getPlayer().getInventory().getArmor().getBlock() > 0) {
            System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        }
        System.out.println();
    }

    public void monsterStats(int i) {
        System.out.println("-------" + i + "." + this.getMonster().getName() + " Değerleri-------");
        System.out.println("Can: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        if (this.getMonster().getAwardCoin() > 0) {
            System.out.println("Kazanılacak Para: " + this.getMonster().getAwardCoin());
        }
        System.out.println();
    }

    public int randomMonster() {
        return r.nextInt(maxMonster) + 1;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    public Monsters getMonster() {
        return monster;
    }

    public void setMonster(Monsters monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
