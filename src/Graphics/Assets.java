package Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by William on 2016-04-27.
 */
public class Assets {

    public static final int WIDTH = 32, HEIGHT = 32;

    public static BufferedImage ground, wall, player, slime, punch;

    // Singleton
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Untitled.png"));

        ground = sheet.crop(64, 64, WIDTH, HEIGHT);
        wall = sheet.crop(33, 0, WIDTH - 2, HEIGHT - 2);
        player = sheet.crop(0, 0, WIDTH, HEIGHT);
        slime = sheet.crop(64, 32, WIDTH, HEIGHT);
        punch = sheet.crop(64, 0, WIDTH, HEIGHT);


    }
}
