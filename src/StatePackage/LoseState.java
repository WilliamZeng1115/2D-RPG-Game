package StatePackage;

import Launcher.Game;
import Launcher.LoseScreen;

import java.awt.*;

/**
 * Created by william on 2016-12-22.
 */
public class LoseState extends State {

    private LoseScreen loseScreen;

    public LoseState(Game game, LoseScreen loseScreen) {
        super(game);
        this.loseScreen = loseScreen;
    }

    @Override
    public void tick() {
        loseScreen.tick();
    }

    @Override
    public void render(Graphics g) {
        loseScreen.render(g);
    }
}
