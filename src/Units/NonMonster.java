package Units;

import Ability.Ability;
import Items.Bag;
import java.util.List;

/**
 * Created by william on 2016-12-21.
 */
public abstract class NonMonster extends AI {


    protected enum Classes {Warrior, Hunter, Berserk, Sniper, Archer, Mage};

    protected float x, y;
    protected Classes c;
    protected int classLevel;
    protected String name;
    protected String description;
    protected String job;
    protected Bag bag;
    protected List<Ability> abilities;
    protected int level = 1;
    protected int gold;
    protected int exp;
    protected int hp;
    protected int mp;

    @Override
    public void DropItem() {
        // stub
    }

    @Override
    public void DropGold() {
        // stub
    }

    public Classes StringToClass(String s) {
        // stub
        return Classes.Archer;
    }

    // Able to try to speak to either monsters or Non-Monsters
    public void TalkTo(BasicUnit b) {
        // stub
    }

    public void BasicAttack(BasicUnit b) {
        // stub
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
