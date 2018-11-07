package StatePackage;

import Launcher.Game;
import Launcher.Stats;

import java.awt.*;

/**
 * Created by william on 2016-12-28.
 */
public class StatState extends State {

    private Stats stats;


    public StatState(Game game, Stats stats) {
        super(game);
        this.stats = stats;
    }

    @Override
    public void tick() {
        stats.tick();

    }

    @Override
    public void render(Graphics g) {
        stats.render(g);

    }
}
