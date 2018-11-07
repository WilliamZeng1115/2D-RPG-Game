package Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by William on 2016-04-27.
 */
public class SpriteSheet {
    private BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;

    }

    public BufferedImage crop(int x, int y, int width, int height) {
        // get the image of size x, y, width, height
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
