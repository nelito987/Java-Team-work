package game;

import display.Display;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private String title;
    private Display display;

    private BufferStrategy bs;
    private Graphics g;

    private Thread thread;
    private boolean isRunning;


    //MAIN LOOP - where the magic happens
    public void run() {
        this.init(); //initialize display

        while (isRunning){

            //make the game slower
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.thick();
            this.render();
        }//end while

        this.stop();
    }

    //thread start function
    public synchronized void start(){
        //automatically calls run() when creating new thread
        //because Game implements Runnable
        this.thread = new Thread(this); // initialize new thread
        isRunning = true;
        this.thread.start();
    }

    //thread stop function
    public synchronized void stop(){
        try {
            isRunning = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //CONSTRUCTOR
    public Game(String name) {
        this.title = name;
    }

    //initialize-display-function
    private void init(){
        this.display = new Display(this.title);

    }

    //update function
    private void thick(){

    }

    //draw function
    private void render(){
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null){
            //if bs doesn't exist - create a general double-buffering strategy
            display.getCanvas().createBufferStrategy(2);
            return;
        }

        //creates a drawing context for the drawing buffer
        this.g = this.bs.getDrawGraphics();

        g.clearRect(0,0,Display.WIDTH, Display.HEIGHT);
        //START drawing

        //END drawing

        //dispose of the graphic context and release any system resource
        // that is using. For efficiency used when finished using a graphic object.
        this.g.dispose();
        //makes the next available buffer visible by either
        //coping the memory or changing the display pointer.
        //display the buffer.
        this.bs.show();
    }

}
