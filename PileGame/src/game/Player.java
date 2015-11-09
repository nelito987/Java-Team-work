package game;

import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;


public class Player {
    private int x, y, width, height, lives, velocity;
    private SpriteSheet sh;
    private String name; //for example if we want to keep score;

    private int cropWidth;

    public static boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Player(int x, int y, int width, int height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;

        this.velocity = 15;
        this.lives = 3;
        this.sh = new SpriteSheet(ImageLoader.load("/images/spriteBird.png"));
        this.cropWidth = 0;
    }

    public Player(int x, int y, int width, int height) {
        this(x,y,width,height,"");
    }
    public void tick(){
        if (isMovingDown){
            this.y += this.velocity;
        }else if(isMovingUp){
            this.y -= this.velocity;
        }
        if(isMovingLeft){
            this.x -= this.velocity;
        }else if (isMovingRight){
            this.x += this.velocity;
        }

        this.cropWidth++;
        if (this.cropWidth >=4){
            this.cropWidth = 0;
        }
        if (x<=0)
            x=0;
        if (x >= 800-110)
        x=800-110;
        if (y<=0)
            y=0;
        if (y >= 600 -100)
            y=600-100;


    }

    public void render(Graphics g){
        g.drawImage(this.sh.crop(0 + this.cropWidth*this.width,0, this.width, this.height),this.x, this.y, null);

    }
}
