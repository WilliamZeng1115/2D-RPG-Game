package Worlds;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by William1115 on 2016-05-02.
 */
public class Tile {

    protected BufferedImage texture;
    protected int x, y;
    protected boolean solid = false;
    protected int cost;
    protected Tile t;

    public static final int TILEWIDTH = 65, TILEHEIGHT = 65;

    public Tile(BufferedImage texture) {
        this.texture = texture;
        cost = 99999;
        t = null;
    }

    public boolean isSolid() {
        return solid;
    }


    public void setSolid(Boolean solid) {
        this.solid = solid;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setParentTile(Tile t) {
        this.t = t;
    }

    public Tile getParentTile() {
        return t;
    }


    public static int getTILEWIDTH() {
        return TILEWIDTH;
    }

    public static int getTILEHEIGHT() {
        return TILEHEIGHT;
    }

}
