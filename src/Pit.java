import java.util.Random;

public class Pit extends BattleLoc{
    Random r=new Random();
    public Pit(Player player){
        super(player,"Maden",new Snake(),"Rastgele",0,null);
        this.setMaxMonster(countSnake());
    }
    public int countSnake(){
        this.setMaxMonster(r.nextInt(6)+1);
        return getMaxMonster();
    }


}
