package Graphics;

import Launcher.Game;
import Units.Player;
import Worlds.Tile;

/**
 * Created by william on 2016-12-24.
 */
public class GameCamera {

    private float xOffset, yOffset;
    private Game game;

    public GameCamera(Game game, float xOffset, float yOffset) {
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void center(Player p) {
        xOffset = (float)p.getX() - game.getWidth() / 2;
        yOffset = (float)p.getY() - game.getHeight() / 2;
        checkBoundary();

    }

    private void checkBoundary(){
        if(xOffset < 0)
            xOffset = 0;
        else if(xOffset > game.getGameState().getWorld().getWidth() * Tile.getTILEWIDTH() - game.getWidth())
            xOffset = game.getGameState().getWorld().getWidth() * Tile.getTILEWIDTH()- game.getWidth();
        if(yOffset < 0)
            yOffset = 0;
        else if(yOffset > game.getGameState().getWorld().getHeight() * Tile.getTILEHEIGHT() - game.getHeight() + 150) {
            yOffset = game.getGameState().getWorld().getHeight() * Tile.getTILEHEIGHT()  - game.getHeight() + 150;
        }
    }

    public void move(float x, float y) {
        xOffset += x;
        yOffset += y;
        checkBoundary();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
