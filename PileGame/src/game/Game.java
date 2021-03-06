package game;

import com.sun.prism.*;
import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageProducer;
import java.util.LinkedList;
import java.util.Random;

public class Game implements Runnable {
    private String title;
    private int width, height;

    private Thread thread;
    private  boolean isRunning;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private SpriteSheet sh;

    private InputHandler ih;

    //OBJECTS
    private Player player;

    private EnemiesList enemies;

    private BonusList bonuses;

    //Drop enemies
    private Random rnd = new Random();
    private int timeCounter = 0;
    private int dropsCounter = 0;
    // animation
    //private final int w = 110;
    //private final int h = 100;
    int x = 0;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
    }

    private void init() {
        this.display = new Display(this.title, this.width, this.height);
        this.ih = new InputHandler(this.display);
        this.sh = new SpriteSheet(ImageLoader.load("/images/spriteBird.png"));
        Assets.init();
        this.player = new Player(300, 450, 110, 110,100);
        enemies = new EnemiesList();
        bonuses = new BonusList();

    }

    //UPDATE
    private void tick(){
        if (Player.health <=0){

            isRunning = false;



            // addEnding screen
        }
        this.player.tick();
        enemies.tick();
        bonuses.tick();

        //this drops the enemies
        timeCounter++;
        if(timeCounter == 5){
            int randomX = rnd.nextInt(700);
            int typeOfEnemy = rnd.nextInt(3);
            // I use this switch to randomly chose what kind of enemy it will drop.
            switch (typeOfEnemy) {
                case 0:
                    enemies.addEnemy(new Enemy(randomX, 0, 52, 56, "/images/pyggi3.png"));
                    break;
                case 1:
                    enemies.addEnemy(new Enemy(randomX, 0, 59, 50, "/images/slon.png"));

                    break;
                case 2:
                    enemies.addEnemy(new Enemy(randomX, 0, 58, 58, "/images/pingvin.png"));

                    break;

            }
            timeCounter = 0;
            dropsCounter+=2;


        }
        //if(timeCounter == 15){
        //    int randomX = rnd.nextInt(700);
//
        //    int  typeOfBonus = rnd.nextInt(2);
        //    switch (typeOfBonus){
        //        case  0 :
        //            bonuses.addBonus(new Bonus(randomX, 0 ,55, 56,"/images/heart2.png"));
        //        case  1 :
        //            bonuses.addBonus(new Bonus(randomX, 0 ,51, 51,"/images/diamand.png"));
        //    }
        //    timeCounter = 0;
        //    dropsCounter++;
        //}


        //This drops the hearts
      if(dropsCounter == 20){
          int randomX = rnd.nextInt(700);
         //enemies.addEnemy(new Enemy(randomX, 0, 55, 56, "/images/heart2.png"));
         bonuses.addBonus(new Bonus(randomX, 0 ,55, 56,"/images/heart2.png"));

          dropsCounter = 0;
      }


    }

    //DRAW
    public  void render(){
        this.bs = this.display.getCanvas().getBufferStrategy();

        if(this.bs == null){
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, this.width, this.height);
        //START DRAW
        this.g.drawImage(ImageLoader.load("/images/sky.jpg"),0,0,null);
        //g.drawImage(ImageLoader.load("/images/gameover.png"),0,0,null);
        this.player.render(g); //draw player
        enemies.render(g);
        bonuses.render(g);

        g.setColor(Color.GRAY);
        g.fillRect(5,5,100,10);
        g.setColor(Color.GREEN);
        g.fillRect(5,5,Player.health,10);

        //END DRAW

        this.bs.show();
        this.g.dispose();
    }

    @Override
    public void run(){
            this.init();

            int fps = 30;
            double ticksPerFrame = 1_000_000_000/fps;
            double delta = 0;
            long now;
            long lastTimeTicked = System.nanoTime();


            while(isRunning){
                now = System.nanoTime();
                delta += (now - lastTimeTicked)/ticksPerFrame;
        //        timer+= now - lastTimeTicked;
                lastTimeTicked = now;

                if(delta >= 1){
                    tick();
                    render();
        //            ticks++;
                    delta--;
                }
            }

        this.stop();
    }

    //start game function
    public synchronized void start() {
        if(!this.isRunning){
            this.isRunning = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    //end game function
    public synchronized void stop(){
        if(this.isRunning){
            try{
                this.isRunning  =false;
                this.thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
