package Launcher;

import StatePackage.LoseState;
import StatePackage.StateManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by william on 2016-12-22.
 */
public class LoseScreen extends MouseAdapter {

    private Game game;
    private int mx, my;
    private int gameWidth, gameHeight;

    public LoseScreen(Game game) {
        this.game = game;
        gameWidth = game.getWidth();
        gameHeight = game.getHeight();
    }

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        if(mouseOver(gameWidth / 2 - 100, gameHeight / 2 + 104, 200, 64) && StateManager.getCurrentState().equals(game.getLoseState())) {
            StateManager.setCurrentState(game.getMenuState());
        }

        if(mouseOver(gameWidth/ 2 - 100, gameHeight / 2 + 208, 200, 64) && StateManager.getCurrentState().equals(game.getLoseState())) {
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

        g.setFont(f);

        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);

        g.setColor(Color.white);
        g.drawString("You Died!!", (gameWidth / 2 - 250), gameHeight / 2 - 100);

        g.setFont(f1);
        g.setColor(Color.white);
        g.drawRect(gameWidth / 2 - 100, gameHeight / 2 + 104, 200, 64);
        g.drawString("Menu", gameWidth / 2 - 80, gameHeight / 2 + 148);


        g.setColor(Color.white);
        g.drawRect(gameWidth / 2 - 100, gameHeight / 2 + 208, 200, 64);
        g.drawString("Quit", gameWidth / 2 - 80, gameHeight / 2 + 258);

    }
}
