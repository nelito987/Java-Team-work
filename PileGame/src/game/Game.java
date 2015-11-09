package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

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
        this.player = new Player(300, 450, 110, 100);
        enemies = new EnemiesList();

    }

    //UPDATE
    private void tick(){

        this.player.tick();
        enemies.tick();

    }

    //DRAW
    private void render(){
        this.bs = this.display.getCanvas().getBufferStrategy();

        if(this.bs == null){
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, this.width, this.height);
        //START DRAW
        this.g.drawImage(ImageLoader.load("/images/sky.jpg"),0,0,null);

        this.player.render(g); //draw player
        enemies.render(g); // draw enemies
        this.g.drawRect(this.player.getBoundingBox().x, //make boundingbox visible, it is NOT necessary for the game
            this.player.getBoundingBox().y,
            this.player.getBoundingBox().width,
            this.player.getBoundingBox().height);
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
