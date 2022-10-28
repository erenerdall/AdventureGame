public class Cave extends BattleLoc{
    private String food;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Cave(Player player) {
        super(player,"Mağara",new Zombie(),"Food",3,"food");
    }

}
