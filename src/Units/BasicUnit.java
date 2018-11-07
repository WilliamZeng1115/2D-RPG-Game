package Units;

import java.awt.*;

/**
 * Created by william on 2016-12-21.
 */
public abstract class BasicUnit {

    private int beginningVal = 5;
    protected int str = (int) (Math.random() * beginningVal);
    protected int end = (int) (Math.random() * beginningVal);
    protected int agility = (int) (Math.random() * beginningVal);
    protected int perception = (int) (Math.random() * beginningVal);
    protected int charisma = (int) (Math.random() * beginningVal);
    protected  int intelligence = (int) (Math.random() * beginningVal);

    public abstract void DropItem();

    public abstract void DropGold();

    public abstract void tick();

    public abstract void render(Graphics g);

}
