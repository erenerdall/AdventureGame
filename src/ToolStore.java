import com.sun.security.jgss.GSSUtil;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya hoşgeldiniz.");
        System.out.println("Ne satın almak istersiniz?");
        System.out.println("1-Silah");
        System.out.println("2-Zırh");
        System.out.println("3-Çıkış yap");
        int selectTool = Main.input.nextInt();
        switch (selectTool) {
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Çıkış yapıldı.");
                break;
            default:
                System.out.println("Geçersiz bir değer girdiniz.");
                break;
        }
        return true;
    }

    public void printArmor() {
        System.out.println("---Zırhlar---");
        for (Armors w : Armors.armors()) {
            System.out.println("<" + " " + "ID-" + w.getID() + "\t" + w.getName() + "\t\t" + "Engelleme:\t+" + w.getBlock() + "\tFiyat\t" + w.getMoney() + " " + ">");
        }
    }

    public void buyArmor() {
        System.out.println("Almak istediğiniz zırhı seçiniz.");
        int selectArmor = Main.input.nextInt();
        Armors armor = null;
        System.out.println("=========================================");
        switch (selectArmor) {
            case 1:
                armor = Armors.armors()[0];
                if (this.getPlayer().getMoney() >= armor.getMoney()) {
                    if (this.getPlayer().getInventory().getArmor().getName().equals(armor.getName())){
                        System.out.println("Aynı zırhı ("+this.getPlayer().getInventory().getArmor().getName()+") satın alamazsınız.");
                    }
                    else {
                    System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - armor.getMoney());
                    System.out.println(armor.getName() + " Zırhı başarıyla satın aldınız. ");
                    System.out.println(armor.getBlock() + " Engelleme kazandınız.");
                    System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(Armors.armors()[0]);
                    System.out.println("Yeni zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    }
                } else if (this.getPlayer().getMoney() < armor.getMoney()) {
                    System.out.println("Zırhı satın alamadınız paranız yetersiz!");
                    System.out.println("Paranız: " + this.getPlayer().getMoney());
                }
                break;
            case 2:
                armor = Armors.armors()[1];
                if (this.getPlayer().getMoney() >= armor.getMoney()) {
                    if (this.getPlayer().getInventory().getArmor().getName().equals(armor.getName())){
                        System.out.println("Aynı zırhı ("+this.getPlayer().getInventory().getArmor().getName()+") satın alamazsınız.");
                    }
                    else {
                    System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - armor.getMoney());
                    System.out.println(armor.getName() + " Zırhı başarıyla satın aldınız. ");
                    System.out.println(armor.getBlock() + " Engelleme kazandınız.");
                    System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(Armors.armors()[1]);
                    System.out.println("Yeni zırhınız: " + this.getPlayer().getInventory().getArmor().getName());

                    }

                } else if (this.getPlayer().getMoney() < armor.getMoney()) {
                    System.out.println("Zırhı satın alamadınız paranız yetersiz!");
                    System.out.println("Paranız: " + this.getPlayer().getMoney());
                }
                break;
            case 3:
                armor = Armors.armors()[2];
                if (this.getPlayer().getMoney() >= armor.getMoney()) {
                    if (this.getPlayer().getInventory().getArmor().getName().equals(armor.getName())){
                        System.out.println("Aynı zırhı ("+this.getPlayer().getInventory().getArmor().getName()+") satın alamazsınız.");
                    }
                    else {
                    System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - armor.getMoney());
                    System.out.println(armor.getName() + " Zırhı başarıyla satın aldınız. ");
                    System.out.println(armor.getBlock() + " Engelleme kazandınız.");
                    System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(Armors.armors()[2]);
                    System.out.println("Yeni zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    }

                } else if (this.getPlayer().getMoney() < armor.getMoney()) {
                    System.out.println("Zırhı satın alamadınız paranız yetersiz!");
                    System.out.println("Paranız: " + this.getPlayer().getMoney());
                }
                break;
            default:
                System.out.println("Geçersiz sayı girdiniz.");
                break;
        }
    }

    public void printWeapon() {
        System.out.println("---Silahlar---");
        for (Weapons w : Weapons.weapon()) {
            System.out.println("<" + " " + "ID-" + w.getID() + "\t" + w.getName() + "\t\t" + "Hasar:\t+" + w.getDamage() + "\tFiyat\t" + w.getMoney() + " " + ">");
        }

    }

    public void buyWeapon() {
        System.out.println("Almak istediğiniz silahı seçiniz.");
        int selectWeapon = Main.input.nextInt();
        Weapons weapon = null;
        System.out.println("=========================================");
        switch (selectWeapon) {
            case 1:
                weapon = Weapons.weapon()[0];
                if (this.getPlayer().getMoney() >= weapon.getMoney()) {
                    if (this.getPlayer().getInventory().getWeapon().getName().equals(weapon.getName())) {
                        System.out.println("Aynı silahı (" + this.getPlayer().getInventory().getWeapon().getName() + ") tekrar alamazsınız.");
                    } else {
                        System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().setMoney(getPlayer().getMoney() - weapon.getMoney());
                        System.out.println("Tabancayı başarıyla satın aldınız.");
                        System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                        this.getPlayer().getInventory().setWeapon(Weapons.weapon()[0]);
                        System.out.println("Yeni hasarınız: " + this.getPlayer().getTotalDamage());
                        System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    }
                } else if (this.getPlayer().getMoney() < weapon.getMoney()) {
                    System.out.println("Paranız yetersiz");
                    System.out.println("Paranız: " + getPlayer().getMoney());
                }
                break;
            case 2:
                weapon = Weapons.weapon()[1];
                if (this.getPlayer().getMoney() >= weapon.getMoney()) {
                    if (this.getPlayer().getInventory().getWeapon() == Weapons.weapon()[1]) {
                        System.out.println("Aynı silahı (" + this.getPlayer().getInventory().getWeapon().getName() + ") tekrar alamazsınız.");
                    } else {
                        System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().setMoney(getPlayer().getMoney() - weapon.getMoney());
                        System.out.println("Kılıcı başarıyla satın aldınız.");
                        System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                        this.getPlayer().getInventory().setWeapon(Weapons.weapon()[1]);
                        System.out.println("Yeni hasarınız: " + this.getPlayer().getTotalDamage());
                        System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    }
                } else if (this.getPlayer().getMoney() < 35) {
                    System.out.println("Paranız yetersiz");
                    System.out.println("Paranız :" + getPlayer().getMoney());
                }
                break;
            case 3:
                weapon = Weapons.weapon()[2];
                if (this.getPlayer().getMoney() >= weapon.getMoney()) {
                    if (this.getPlayer().getInventory().getWeapon() == Weapons.weapon()[2]) {
                        System.out.println("Aynı silahı (" + this.getPlayer().getInventory().getWeapon().getName() + ") tekrar alamazsınız.");
                    } else {
                        System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().setMoney(getPlayer().getMoney() - weapon.getMoney());
                        System.out.println("Tüfeği başarıyla satın aldınız.");
                        System.out.println("Yeni paranız: " + this.getPlayer().getMoney());
                        this.getPlayer().getInventory().setWeapon(Weapons.weapon()[2]);
                        System.out.println("Yeni hasarınız: " + this.getPlayer().getTotalDamage());
                        System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    }

                } else if (this.getPlayer().getMoney() < 45) {
                    System.out.println("Paranız yetersiz");
                    System.out.println("Paranız: " + getPlayer().getMoney());
                }
                break;
            default:
                System.out.println("Geçersiz sayı girdiniz.");
        }
    }
}
