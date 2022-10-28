import java.util.Random;

public class Snake extends Monsters{
    Random r=new Random();
    private int itemChance;

    public Snake(){
        super(4,0,12,"Yılan",0);
        this.setDamage(snakeDamage());
    }
    public int snakeDamage() {
        this.setDamage(r.nextInt(7) + 3);
        return getDamage();
    }


}
