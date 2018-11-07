package StatePackage;

import Items.Bag;
import Launcher.Game;

import java.awt.*;

/**
 * Created by william on 2016-12-28.
 */
public class BagState extends State {

    private Bag bag;

    public BagState(Game game, Bag bag) {
        super(game);
        this.bag = bag;
    }

    @Override
    public void tick() {
        bag.tick();
    }

    @Override
    public void render(Graphics g) {
        bag.render(g);
    }
}
