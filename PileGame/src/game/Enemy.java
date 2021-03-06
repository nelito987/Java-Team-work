package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class Enemy {
    public int x, y;
    private int width, height, velocity;
    private SpriteSheet sh;

    //Area for collision
    private static Rectangle boundingBox;
    public static Rectangle getBoundingBox() {return boundingBox;}

    private int cropWidth;

    //Enemies Constructor
    public Enemy(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int rand = ThreadLocalRandom.current().nextInt(5,10);
        this.velocity = rand;
        this.sh = new SpriteSheet(ImageLoader.load(path));
        this.cropWidth = 0;

        //creating the collision rectangle
        boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
    }

    //Update Function
    public void tick(){

        this.y += this.velocity;

        //updating the collision rectangle
        boundingBox.setBounds(this.x, this.y, this.width, this.height);

    }

    //Draw Function
    public void render(Graphics g){
        g.drawImage(this.sh.crop(0 + this.cropWidth*this.width,0, this.width, this.height),this.x, this.y, null);

    }
}
