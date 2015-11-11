package game;

import display.Display;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ACER on 11/9/2015.
 */
public class EnemiesList {
    LinkedList<Enemy> enemies = new LinkedList<>();

    Enemy tempEnemy;


    public EnemiesList() {

        addEnemy(new Enemy(100, 0, 52, 56,"/images/pyggi3.png"));
        addEnemy(new Enemy(100, 0, 58, 58,"/images/pingvin.png"));


    }


    public void tick() {

        for (int i = 0; i < enemies.size(); i++) {
            tempEnemy = enemies.get(i);
            tempEnemy.tick();
            if (tempEnemy.y >= 500) {
                removeEnemy(tempEnemy);
            }

            if (Enemy.getBoundingBox().intersects(Player.getBoundingBox())){
                removeEnemy(tempEnemy);

            }

//            if (tempEnemy.x == Player.x || tempEnemy.y == Player.y) {
//            }

//        addEnemy(new Enemy(100, 0, 110, 110));
        }
    }
    public void render(Graphics g){
        for (int i = 0; i < enemies.size(); i ++){
            tempEnemy = enemies.get(i);

            tempEnemy.render(g);
        }
    }

    public void addEnemy(Enemy enemy){

        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }
}
