package Ability;

import Units.Player;

import java.awt.image.BufferedImage;

/**
 * Created by william on 2016-12-21.
 */
public abstract class Ability {

    protected int damage;
    protected int coolDown;
    protected String name;
    protected String description;
    protected int mpCost;
    protected int hpCost;
    protected int range;
    protected BufferedImage bufferedImage;
    protected int id;
    protected Player player;

    public Ability(BufferedImage bufferedImage, Player player) {
        this.bufferedImage = bufferedImage;
        this.player = player;
    }

    public int getDamage() {
        return damage;
    }

    public int getId() {
        return id;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMpCost() {
        return mpCost;
    }

    public int getHpCost() {
        return hpCost;
    }

    public int getRange() {
        return range;
    }
}
