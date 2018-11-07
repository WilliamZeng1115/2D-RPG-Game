package Launcher;

import Ability.Ability;
import StatePackage.GameState;
import StatePackage.StateManager;
import Units.Monsters;
import Units.Player;
import Worlds.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by William1115 on 2016-07-01.
 */
public class InGame extends MouseAdapter {

    private Game game;
    private GameState gameState;
    private Player player;
    private int mx, my;
    private Boolean mouseRelease;
    private int gameHeight, gameWidth;


    public InGame(Game game) {
        this.game = game;
        mouseRelease = true;
        gameHeight = game.getHeight();
        gameWidth = game.getWidth();

    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

//    long now;
//    // Current Time in nanoSecnod
//    long lastTime = System.nanoTime();
//
//    //begin the game loop to tick the things and render it constantly if running is true, set from start
//    while(running) {
//        now = System.nanoTime();
//        // if the amount of time passed from last time till now is larger than timePerTick then its >= 1 thus tick/render
//        // if the amount of time passed is lower than timePerTick then its < 1 thus no tick/render
//        delta += (now - lastTime) / timePerTick;
//        lastTime = now;
//
//        if(delta >= 1) {
//            tick();
//            render();
//        }

    long lastTime = System.nanoTime();
    long now;

    public void mousePressed(MouseEvent e) {
        float xOffset = game.getGameCamera().getxOffset();
        float yOffset = game.getGameCamera().getyOffset();
        mx = (int)(e.getX() + xOffset);
        my = (int)(e.getY() + yOffset);

        now = System.nanoTime();
        if(SwingUtilities.isLeftMouseButton(e)) {

            if (mouseOver((int) ((gameWidth - 250 + xOffset)), (int) (gameHeight - 100 + yOffset), 100, 50) && StateManager.getCurrentState().equals(gameState)) {
                StateManager.setCurrentState(game.getMenuState());
            }

            if (mouseOver((int) (gameWidth - 150 + xOffset), (int) (gameHeight - 100 + yOffset), 100, 50) && StateManager.getCurrentState().equals(gameState)) {
                System.exit(0);
            }
            if (mouseOver(0, 0, (int) (gameWidth + xOffset), (int) (gameHeight + yOffset) - 150) && StateManager.getCurrentState().equals(gameState)) {

                    gameState.getMonsterInfo(mx, my);

            }
            if(mouseOver((int)(455 + xOffset), (int)(gameHeight - 115 + yOffset), 125 , gameHeight - 35) && StateManager.getCurrentState().equals(gameState)) {
                StateManager.setCurrentState(game.getShopState());
                mouseRelease = false;

            }

            if(mouseOver((int)(25 + xOffset), (int)(gameHeight - 100 + yOffset), 150, 50) && StateManager.getCurrentState().equals(game.getGameState())) {
                StateManager.setCurrentState(game.getBagState());
            }

            if(mouseOver((int)(184 + xOffset), (int)(630 + yOffset), 40, 20) && StateManager.getCurrentState().equals(game.getGameState())) {
                StateManager.setCurrentState(game.getStatState());
            }

        }

        if(SwingUtilities.isRightMouseButton(e)) {

            if (mouseOver(0, 0, (int) (gameWidth + xOffset), (int) (gameHeight + yOffset) - 150) && StateManager.getCurrentState().equals(gameState) && (now - lastTime) / 1000000000 >= 1) {
                lastTime = now;
                player.clicked(mx, my);

            } else System.out.println("too much clicking");
        }

    }
    public Boolean getMouseRelease() {
        return mouseRelease;
    }

    public void setMouseRelease(Boolean b) {
        this.mouseRelease = b;
    }

    public void mouseReleased(MouseEvent e) {
        mouseRelease = true;
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
        float xOffset = game.getGameCamera().getxOffset();
        float yOffset = game.getGameCamera().getyOffset();
        Font f = new Font("arial", 1, 50);
        Font f1 = new Font("arial", 1, 35);
        Font f2 = new Font("arial", 1, 15);

        g.drawRect(0, 0, gameWidth, gameHeight - 150);

        g.setColor(Color.lightGray);
        g.fillRect(0, gameHeight - 150, gameWidth, 150);

        g.setColor(Color.white);
        g.setFont(f1);
        g.fillRect(gameWidth - 150, gameHeight - 100, 100, 50);
        g.setColor(Color.black);
        g.drawRect(gameWidth - 150, gameHeight - 100, 100, 50);
        g.drawString("Quit", gameWidth - 135, gameHeight - 60);

        g.setColor(Color.white);
        g.setFont(f1);
        g.fillRect(gameWidth - 250, gameHeight - 100, 100, 50);
        g.setColor(Color.black);
        g.drawRect(gameWidth - 250, gameHeight - 100, 100, 50);
        g.drawString("Menu", gameWidth - 245, gameHeight - 60);


        g.drawRect(205, gameHeight - 115, 505, 80);
        g.drawLine(330, gameHeight - 115, 330, gameHeight - 35);
        g.drawLine(455, gameHeight - 115, 455, gameHeight - 35);

        // make this a bit further to make it a map instead of ability
        g.drawLine(580, gameHeight - 115, 580, gameHeight - 35);

        g.setColor(Color.white);
        g.fillRect(10, 590, 250, 55);
        g.drawImage(player.getBufferedImage(), Math.max(15, (int)player.getX() - (gameWidth + (int)xOffset)), (Math.max(605, (int)player.getY() - (gameHeight + (int)yOffset))), null);
        g.setColor(Color.black);
        g.setFont(f2);
        g.drawString("DeathKnight", 55, 607);

        g.setColor(Color.red);
        g.fillRect(55, 612, 100, 10);
        g.setColor(Color.cyan);
        g.fillRect(55, 625, 100, 10);

        g.setColor(Color.green);
        g.fillRect(55, 612, (int)(player.getHp() / player.getHpRatio()), 10);
        g.setColor(Color.blue);
        g.fillRect(55, 625, (int)(player.getMp() / player.getMpRatio()), 10);

        g.setColor(Color.BLACK);
        g.drawString("Developer", 169, 607);
        g.drawString("Lvl: " +  player.getLevel(), 185, 622);

        g.setColor(Color.blue);
        g.drawString("Stats", 184, 640);


        g.setFont(f2);
        Ability a = player.getAbilityOne();
        if(player.getAbilityOne() != null) {

            // can set these to variable to alter what to display in the skill boxes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11111
            // Using mouse over
            g.setColor(Color.black);
            g.drawString(a.getName(), 240, gameHeight - 100);
            g.setColor(Color.BLUE);
            g.drawString("Click M", 240, gameHeight - 40);
            if (mouseOver((int) (205 + xOffset), (int) (gameHeight - 115 + yOffset), 125, 80)) {
                g.setColor(Color.black);
                g.fillRect(205, gameHeight - 200, 125, 85);
            }
        }

        a = player.getAbilityTwo();
        if(player.getAbilityTwo() != null) {
            g.setColor(Color.black);
            g.drawString(a.getName(), 345, gameHeight - 100);
            g.setColor(Color.BLUE);
            g.drawString("Click R", 360, gameHeight - 40);
            if (mouseOver((int) (330 + xOffset), (int) (gameHeight - 115 + yOffset), 125, 80)) {
                g.setColor(Color.black);
                g.fillRect(330, gameHeight - 200, 125, 85);
            }
        }




        g.setColor(Color.yellow);
        g.drawString("Buy more ability", 462, gameHeight - 70);


        g.setColor(Color.ORANGE);
        g.setFont(f);
        g.fillRect(25, gameHeight - 100, 150, 50);
        g.setColor(Color.black);
        g.drawRect(25, gameHeight - 100, 150, 50);
        g.drawString("Bag", 50, gameHeight - 60);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
