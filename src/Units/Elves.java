package Units;

import java.awt.*;

/**
 * Created by william on 2016-12-21.
 */
public class Elves extends NonMonster {

    private double raceMultiplier = 1.2;

    public Elves(int x, int y) {
        this.x = x;
        this.y = y;
        RandomizeClassAndUpdate();
    }

    // Allow character to select class if its Player.
    // Otherwise auto random select class.
    // Determine all other attribute accordingly
    private void RandomizeClassAndUpdate() {
        // stub
    }




    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void useMp() {

    }

    @Override
    public void changeStatus(String s) {

    }
}
