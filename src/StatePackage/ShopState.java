package StatePackage;

import Launcher.Game;
import Launcher.Shop;

import java.awt.*;

/**
 * Created by William on 2016-04-27.
 */
public class ShopState extends State {
    private Shop shop;


    public ShopState(Game game, Shop shop) {
        super(game);
        this.shop = shop;
    }

    @Override
    public void tick() {
        shop.tick();

    }

    @Override
    public void render(Graphics g) {
        shop.render(g);

    }
}
