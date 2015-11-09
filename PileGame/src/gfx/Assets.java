package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public  static BufferedImage playerBird, enemyBird;


    public static void init(){
        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("/images/spriteBird.png"));

        playerBird = spriteSheet.crop(0, 0, 110, 100);
        //enemy = ...
    }
}
