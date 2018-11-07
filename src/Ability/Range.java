package Ability;

import Units.Monsters;
import Units.Player;
import Worlds.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by william on 2016-12-27.
 */
public class Range extends Ability {

    public Range(BufferedImage bufferedImage , Player player) {
        super(bufferedImage, player);
        id = 2;
        damage = 2;
        coolDown = 5;
        name = "Stone Throw";
        description = "A simple stone" + "\n" + "can be deadly" + "\n" + "if used right";
        mpCost = 10;
        hpCost = 0;
        range = 4;
    }

    public void shoot(int x, int y, int targetX, int targetY, Monsters m) {
        int tempX = Tile.getTILEWIDTH();
        int tempY = Tile.getTILEHEIGHT();
        if(Math.abs(x/ tempX - targetX / tempX) <= range && Math.abs(y / tempY - targetY / tempY) <= range && player.getMp() - mpCost >= 0) {
            player.useMp(mpCost);
            m.takeDamage(damage + + player.getAdditionalDamage());
        }
        else System.out.println("out of range or not enough mana");
    }

}
