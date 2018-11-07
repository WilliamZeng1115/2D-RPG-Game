package Launcher;

import StatePackage.MenuState;
import StatePackage.StateManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by William1115 on 2016-05-03.
 */
public class Menu extends MouseAdapter {

    private Game game;
    private MenuState menuState;
    private int mx, my;

    public Menu(Game game) {
        this.game = game;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        if(mouseOver(game.getWidth() / 2 - 100, game.getHeight() / 2, 200, 64) && StateManager.getCurrentState().equals(menuState)) {
            StateManager.setCurrentState(game.getGameState());
        }

        if(mouseOver(game.getWidth() / 2 - 100, game.getHeight() / 2 + 208, 200, 64) && StateManager.getCurrentState().equals(menuState)) {
            System.exit(0);
        }

    }

    public void mouseReleased(MouseEvent e) {

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

        Font f = new Font("arial", 1, 100);
        Font f1 = new Font("arial", 1, 50);

        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.setFont(f);
        g.setColor(Color.green);
        g.drawString("RPG" , game.getWidth() / 2 - 100, game.getHeight() / 2 - 100);

        g.setFont(f1);
        g.setColor(Color.white);
        g.drawRect(game.getWidth() / 2 - 100, game.getHeight() / 2, 200, 64);
        g.drawString("Play", game.getWidth() / 2 - 80, game.getHeight() / 2 + 48);


        g.setColor(Color.white);
        g.drawRect(game.getWidth() / 2 - 100, game.getHeight() / 2 + 104, 200, 64);
        g.drawString("Help", game.getWidth() / 2 - 80, game.getHeight() / 2 + 148);


        g.setColor(Color.white);
        g.drawRect(game.getWidth() / 2 - 100, game.getHeight() / 2 + 208, 200, 64);
        g.drawString("Quit", game.getWidth() / 2 - 80, game.getHeight() / 2 + 258);

    }
}
