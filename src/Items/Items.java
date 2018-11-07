package Items;

import Units.NonMonster;
import Units.Player;

/**
 * Created by william on 2016-12-21.
 */
public abstract class Items {
    public enum Rarity {Common, Rare, Unique, Gold, Legendary, God};

    protected String uniqueName;
    protected int damage;
    protected double costAndSell;
    protected Rarity rarity;
    protected String classRestricted;
    protected int statRatio;

    Items(int costAndSell, Rarity rarity, String classRestricted, String uniqueName, int damage, int statRatio) {
        this.uniqueName = uniqueName;
        this.damage = damage;
        this.costAndSell = costAndSell;
        this.rarity = rarity;
        this.classRestricted = classRestricted;
        this.statRatio = statRatio;
    }

    public void wearItem(Player player) {
        // outPut not wearable
    }

    public String getUniqueName() {
        return uniqueName;
    }

    // change to stats later
    public int getDamage() {
        return damage;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public double getCostAndSell() {
        return costAndSell;
    }

    public String getClassRestricted() {
        return classRestricted;
    }

}
