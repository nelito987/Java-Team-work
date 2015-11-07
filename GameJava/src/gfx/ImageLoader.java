package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    //return image function
    public static BufferedImage loadImage(String path){
        try {
            //reading a file and visualise it as picture
            return ImageIO.read(ImageLoader.class.getResource(path)); //getResource returns URL
        } catch (IOException e) {
            e.printStackTrace();
            //exit application
            System.exit(1);
        }
        return null;
    }
}
