package Worlds;

import Launcher.Game;

import java.awt.*;

/**
 * Created by William1115 on 2016-05-03.
 */
public class World {

    private int width, height;
    private int[][] tiles;
    private Tile[][] tileTemp;

    private int spawnX, spawnY;
    private Game game;

    public World(Game game, String path) {
        loadWorld(path);
        this.game = game;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        int xStart = (int)Math.max(0, game.getGameCamera().getxOffset() / Tile.getTILEWIDTH());
        int xEnd = (int)Math.min(width, (game.getGameCamera().getxOffset() + game.getWidth()) / Tile.getTILEWIDTH() + 1);
        int yStart = (int)Math.max(0, game.getGameCamera().getyOffset() / Tile.getTILEHEIGHT());
        int yEnd = (int)Math.min(height, (game.getGameCamera().getyOffset() + game.getHeight()) / Tile.getTILEHEIGHT() + 1);

        for(int y = yStart; y < yEnd; y++)  {
            for(int x = xStart; x < xEnd; x++) {

                getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH -  game.getGameCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()));

            }
        }

    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        return tileTemp[x][y];
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);

        // split at all spaces
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        // now world building
        tiles = new int[width][height];
        tileTemp = new Tile[width][height];
       // cost = new int[width][height];
       // parentTile =  new Tile[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                if(tiles[x][y] == 0) {
                    tileTemp[x][y] = new GroundTile();
                }
                else  if(tiles[x][y] == 1) {
                    tileTemp[x][y] = new WallTile();
                }
                tileTemp[x][y].setPos(x, y);
            //    cost[x][y] = 99999;

            }
        }
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
