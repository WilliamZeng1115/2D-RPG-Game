package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by William on 2016-04-27.
 */
public class ImageLoader {


    // loading any of the image
    public static BufferedImage loadImage(String path) {
        // in case path is not valid
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            // if path does't work
            System.exit(1);
        }
        return null;
    }


}
