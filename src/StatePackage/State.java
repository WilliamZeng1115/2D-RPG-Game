package StatePackage;

import Launcher.Game;

import java.awt.*;

/**
 * Created by William on 2016-04-27.
 */
public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }


    public abstract void tick();

    public abstract  void render(Graphics g);

}
