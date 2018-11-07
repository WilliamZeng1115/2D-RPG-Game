package Launcher;

import StatePackage.ShopState;
import StatePackage.StateManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by William1115 on 2016-07-01.
 */
public class Shop extends MouseAdapter {
    private Game game;
    private int mx, my;

    public Shop(Game game) {
        this.game = game;
    }



    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        if(mouseOver(475, 675, 242, 100) && StateManager.getCurrentState().equals(game.getShopState()) && game.getInGame().getMouseRelease()) {
            StateManager.setCurrentState(game.getGameState());
        }

        if(mouseOver(717, 675, 242, 100) && StateManager.getCurrentState().equals(game.getShopState())) {
            System.exit(0);
        }

    }

    public void mouseReleased(MouseEvent e) {
        game.getInGame().setMouseRelease(true);
    }

    private boolean mouseOver(int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height)
                return true;

        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.setColor(Color.white);
        g.drawRect(35, 25, game.getWidth() - 70, 125);

        g.drawRect(35, 175, 400, game.getHeight() - 200);

        g.drawRect(475, 175, 485, game.getHeight() - 325);

        g.drawRect(475, 675, 485, 100);

        Font f1 = new Font("arial", 1, 40);
        g.setColor(Color.ORANGE);
        g.setFont(f1);
        g.drawString("Gold: ", 50, 75);

        g.setColor(Color.white);
        g.drawLine(470, 25, 470, 150);

        g.setColor(Color.green);
        g.drawString("Health: ", 500, 75);
        g.setColor(Color.cyan);
        g.drawString("Other Stat: ", 500, 125);

        g.setColor(Color.ORANGE);
        g.drawString("Ability Upgrades ", 50, 225);

        g.drawString("Items", 510, 225);

        g.setColor(Color.white);
        g.drawLine(717, 675, 717, 775);

        g.setColor(Color.red);
        g.drawString("QUIT", 747, 740);

        g.setColor(Color.green);
        g.drawString("Resume", 515, 740);

    }

}
