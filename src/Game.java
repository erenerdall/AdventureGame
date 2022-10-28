import java.util.Scanner;
public class Game {
    Scanner input=new Scanner(System.in);
    private String nickName;
    Player p1=new Player();
    public void start(){
        System.out.println("İsminizi giriniz: ");
        nickName= input.next();
        System.out.println("Merhaba "+nickName+" macera oyununa hoşgeldin! ");
        System.out.println("Karakterini seçebilirsinz.");
        System.out.println("====================================================================================");
        p1.selectChar();
        p1.selectLoc();
    }


}
