package Units;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Graphics.Assets;
import Launcher.Game;
import Worlds.Tile;
import Worlds.World;

/**
 * Created by william on 2016-12-21.
 */
public class Slime extends Monsters {

    private float xSpeed, ySpeed;
    private Game game;
    private int aheadX, aheadY, aheadY2, aheadX2;
    private int ahead;
    private World world;

    // x and y is the position
    public Slime(Game game, float x, float y, World world) {
        this.game = game;
        this.world = world;
        this.x = x;
        spawnX = x;
        this.y = y;
        spawnY = y;
        name = "Slime";
        description = "Most basic monster, even" + "\r\n" + "a child can defeat";
        hp = 10;
        mp = 10;
        rank = Rank.Normal;
        level = (int) (Math.random() * 3);
        gold = (int) (Math.random() * 50);
        exp = 5;
        xSpeed = 0;
        ySpeed = 0;
        radius = 500;
        ahead = 5;
        path = new LinkedList<Tile>();
        b = Assets.slime;
        hpRatio = (double)hp / (double)100;
        mpRatio = (double)mp / (double)100;
        damage = 5;
    }

    public int Attack() {
        // Can attack both range and melee (a random chance)
        return 0;

    }

    @Override
    public void useMp() {

    }

    @Override
    public void changeStatus(String s) {
        currState = AI.convertToStatus(s);
    }

    @Override
    public void Berserk() {
        // stub later
    }

    @Override
    public void friendly() {
        // stub later
    }

    @Override
    public void neutral() {
        targetY = spawnY;
        targetX = spawnX;
        // stub add auto pathing later for 6- 7 default path
    }

    private long now;
    private long lastTime = System.nanoTime();
    @Override
    public void aggro(Player player) throws Exception{
        this.targetX = (int)player.getX();
        this.targetY = (int)player.getY();
        if(Math.abs(targetX - spawnX) > radius || Math.abs(targetY - spawnY) > radius)
            throw new Exception("Out of aggro boundary");

        now = System.nanoTime();

        if((now - lastTime) / 1000000000 >= 1) {
            attack(player);
            lastTime = now;
        }
    }

    private void attack(Player player) {
        if(player.getHp() <= 0)
            return;
        if(Math.abs((int)(targetX / Tile.getTILEWIDTH() - (int)(x / Tile.getTILEWIDTH()))) <= 1 && (int)Math.abs((int)(targetY / Tile.getTILEHEIGHT() - (int)(y / Tile.getTILEHEIGHT()))) <= 1) {
            player.takeDamage(damage);
        }
    }


    @Override
    public void fear() {
        // stub later
    }

    private void followPlayer() {
        if(x - 16> targetX + 16)
            xSpeed = -0.6f;
        else if(x + 16 < targetX - 16)
            xSpeed = 0.6f;
        if(y - 16 > targetY + 16)
            ySpeed = -0.6f;
        else if(y + 16 < targetY - 16)
            ySpeed = 0.6f;
    }

    private void calculateAhead() {
        aheadX = (int)x + (int)(xSpeed * ahead);
        aheadY = (int)(y + ySpeed * ahead);
        aheadX2 = (int)(aheadX * 0.5);
        aheadY2 = (int)(aheadY * 0.5);
    }

    private double normalize(double a, double b) {
        double v = Math.sqrt((a * a) + (b * b));
        return a / v;
    }


    private void checkForWalls() {
        int width = Tile.getTILEWIDTH();
        int height = Tile.getTILEHEIGHT();
        calculateAhead();
        if(world.getTile((int)aheadX / width, (int)aheadY / height) != null) {
            if (world.getTile((int) aheadX2 / width, (int) aheadY2 / height).isSolid()) {
                int centerX = (((int)(aheadX / width) * width) + width / 2);
                int centerY = (((int)(aheadY / height) * height) + height / 2);

                double obstacleX = aheadX2 - centerX;
                double obstacleY = aheadY2 - centerY;

                double temp = obstacleX;
                obstacleX = normalize(obstacleX, obstacleY) * 0.5f;
                obstacleY = normalize(obstacleY, temp) * 0.5f;
                temp = obstacleX;

                if(xSpeed == 0) {
                    obstacleX = 2;
                }
                if(ySpeed == 0) {
                    obstacleY = 2;
                }
                xSpeed += obstacleX;
                ySpeed += obstacleY;
            }
            else if (world.getTile((int) aheadX / width, (int) aheadY / height).isSolid()) {
                int centerX = (((int)(aheadX / width) * width) + width / 2);
                int centerY = (((int)(aheadY / height) * height) + height / 2);

                double obstacleX = aheadX - centerX;
                double obstacleY = aheadY - centerY;

                double temp = obstacleX;
                obstacleX = normalize(obstacleX, obstacleY) * 0.5f;
                obstacleY = normalize(obstacleY, temp) * 0.5f;
                if(xSpeed == 0) {
                    obstacleX = 2;
                }
                if(ySpeed == 0) {
                    obstacleY = 2;
                    }

                xSpeed += obstacleX;
                ySpeed += obstacleY;
            }
        }


    }

//    private void setSpeed() throws Exception {
//        if(path.isEmpty()) {
//            throw new Exception("No path to target");
//        }
//        Tile temp = path.get(0);
//
//        int nextX = temp.getX();
//        int nextY = temp.getY();
//        if(x < nextX* Tile.getTILEWIDTH() + Tile.getTILEWIDTH() /2 && x < spawnX + radius)
//            xSpeed = 0.5f;
//        else if(x > nextX * Tile.getTILEWIDTH()+ Tile.getTILEWIDTH() / 2 && x > spawnX - radius)
//            xSpeed = -0.5f;
//        if(y > nextY * Tile.getTILEHEIGHT()+ Tile.getTILEHEIGHT() / 2 && y > spawnY - radius)
//            ySpeed = -0.5f;
//        else if(y < nextY * Tile.getTILEHEIGHT() + Tile.getTILEHEIGHT() / 2 && y < spawnY + radius)
//            ySpeed = 0.5f;
//        if(x == nextX * Tile.getTILEWIDTH() + Tile.getTILEWIDTH() / 2 && y == nextY * Tile.getTILEHEIGHT() + Tile.getTILEHEIGHT() / 2) {
//            xSpeed = 0;
//            ySpeed = 0;
//            path.remove(0);
//        }


//    }

    private void move() {

        followPlayer();
        checkForWalls();
//        try {
//            setSpeed();
//        } catch (Exception e) {
//            xSpeed = 0;
//            ySpeed = 0;
//        }
        x += xSpeed;
        y += ySpeed;
        xSpeed = 0;
        ySpeed = 0;
    }


    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(b, (int)(x - Tile.getTILEHEIGHT() /4 - game.getGameCamera().getxOffset()), (int)(y - Tile.getTILEHEIGHT() /4 - game.getGameCamera().getyOffset()) , null);
    }


}
