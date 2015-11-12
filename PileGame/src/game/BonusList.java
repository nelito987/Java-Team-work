package game;


import java.awt.*;
import java.util.LinkedList;

public class BonusList {
    LinkedList<Bonus> bonusUp = new LinkedList<>();

    Bonus tempBonus;

    public BonusList(){

        addBonus(new Bonus(100, 0, 55, 56,"/images/heart2.png"));
    }
    public  void tick(){

        for (int i = 0; i < bonusUp.size(); i++) {
            tempBonus = bonusUp.get(i);
            tempBonus.tick();

            //Enemies going in the void
            if (tempBonus.y >= 550) {
                removeBonus(tempBonus);
            }


            //COLLISION
            if (Bonus.getBoundingBox().intersects(Player.getBoundingBox())){
                removeBonus(tempBonus);
                Player.health +=10;
            }
        }
    }
    public  void  render(Graphics g){
        for (int i = 0; i < bonusUp.size(); i++) {
            tempBonus = bonusUp.get(i);
            tempBonus.render(g);
        }
    }
    public  void  addBonus(Bonus bonus){

        bonusUp.add(bonus);
    }
    public void removeBonus(Bonus bonus){
        bonusUp.remove(bonus);
    }
}
