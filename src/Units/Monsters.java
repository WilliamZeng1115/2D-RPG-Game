package Units;

import Worlds.Tile;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by william on 2016-12-21.
 */
public abstract class Monsters extends AI {


    protected enum Rank {Normal, Rare, Mythical, Legendary, God};

    protected float x, y;
    protected String name;
    protected Rank rank;
    protected int level;
    protected int gold;
    protected int exp;
    protected int hp;
    protected int mp;
    protected String description;
    protected BufferedImage b;
    protected double hpRatio, mpRatio;
    protected int damage;


    public abstract void Berserk();

    public abstract void friendly();

    public abstract void neutral();

    public abstract void aggro(Player player) throws Exception;

    public abstract void fear();

    @Override
    public void DropItem() {
        // stub
    }

    @Override
    public void DropGold() {
        // stub
    }

    // Some monsters don't fight so no basic attacks (do it inside the monsters)

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public String getDescription() {
        return description;
    }


    public double getHpRatio() {
        return hpRatio;
    }

    public double getMpRatio() {
        return mpRatio;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public BufferedImage getB() {

        return b;
    }
}
