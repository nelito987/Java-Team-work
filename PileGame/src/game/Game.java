package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private String title;
    private int width, height;

    private Thread thread;
    private  boolean isRunning;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private SpriteSheet sh;


    private Player player;
    private InputHandler ih;

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

    }

    private void tick(){

        this.player.tick();

        // animation
        //x++;
        //if(x >= 4){
        //    x = 0;
        //}
    }

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

        this.player.render(g);
        //this.g.drawImage(this.sh.crop(0+ x*110,0,110,100), 100, 200, null);

        //END DRAW
        this.bs.show();
        this.g.dispose();
    }

    @Override
    public void run(){
            this.init();

        //while (isRunning){
        //    tick();
        //    render();
        //}

            int fps = 30;
            double ticksPerFrame = 1_000_000_000/fps;
            double delta = 0;
            long now;
            long lastTimeTicked = System.nanoTime();
        //    long timer = 0;
        //    int ticks = 0;

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

    public synchronized void start() {
        if(!this.isRunning){
            this.isRunning = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

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
