package StatePackage;

import Launcher.Game;
import Launcher.Menu;

import java.awt.*;

/**
 * Created by William on 2016-04-27.
 */
public class MenuState extends State {
    private Menu menu;

    public MenuState(Game game, Menu menu) {
        super(game);
        this.menu = menu;

    }

    @Override
    public void tick() {
        menu.tick();
    }

    @Override
    public void render(Graphics g) {
        menu.render(g);

    }
}
