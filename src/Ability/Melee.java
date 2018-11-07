package Ability;

import Units.Monsters;
import Units.Player;
import Worlds.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by william on 2016-12-27.
 */
public class Melee extends Ability {

    public Melee(BufferedImage bufferedImage, Player player) {
        super(bufferedImage, player);
        id = 1;
        damage = 3;
        coolDown = 1;
        name = "Punch";
        description = "A fist can solve" + "\n" + "any problem!!";
        mpCost = 5;
        hpCost = 0;
        range = 1;
    }


    public void punch(int x, int y, int targetX, int targetY, Monsters m) {
        int tempX = Tile.getTILEWIDTH();
        int tempY = Tile.getTILEHEIGHT();
        if(Math.abs(x/ tempX - targetX / tempX) <= range && Math.abs(y / tempY - targetY / tempY) <= range) {
            m.takeDamage(damage + player.getAdditionalDamage());
        }
        else System.out.println("out of range");
    }

}
