package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class Enemy {
    public int y;
    private int x, width, height, velocity;
    private SpriteSheet sh;



    private int cropWidth;

    //Enemies Constructor
    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int rand = ThreadLocalRandom.current().nextInt(5,10);
        this.velocity = rand;
        this.sh = new SpriteSheet(ImageLoader.load("/images/spriteBird.png"));
        this.cropWidth = 0;
    }

    //Update Function
    public void tick(){

        this.y += this.velocity;


    }

    //Draw Function
    public void render(Graphics g){
        g.drawImage(this.sh.crop(0 + this.cropWidth*this.width,0, this.width, this.height),this.x, this.y, null);

    }
}
