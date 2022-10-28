import java.util.Iterator;

public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player){
        super(player,"Güvenli Ev");
    }
    @Override
    public boolean onLocation(){
        if (this.getPlayer().getInventory().getPrizelist().contains("food")&&this.getPlayer().getInventory().getPrizelist().contains("firewood")&&this.getPlayer().getInventory().getPrizelist().contains("water")){
            return false;
        }
        System.out.println("Güvenli Evdesiniz !");
        System.out.println("Canınız yenilendi !");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        System.out.println("-Kazandığınız ödüller-");
        this.getPlayer().getInventory().getPrizelist().forEach(i-> System.out.println(i));
        return true;
    }
}
