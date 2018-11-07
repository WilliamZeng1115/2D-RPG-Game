package Launcher;

import StatePackage.StateManager;
import Units.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by william on 2016-12-28.
 */
public class Stats extends MouseAdapter{
    private Game game;
    private Player player;
    private int mx, my;
    private int gameHeight, gameWidth;
    private int tempStr = 0, tempEnd = 0, tempInt = 0, tempAg = 0, tempChar = 0, tempPercep = 0;
    private int tempPoints;


    public Stats(Game game) {
        this.game = game;
        gameHeight = game.getHeight();
        gameWidth = game.getWidth();
        player = game.getPlayer();
        tempPoints = player.getBeginningValue();

    }

    public void updatePointsOnLevel() {
        tempPoints += 5;
        player.addBeginningValue(5);
    }

    private void resetAllTemp() {
        tempStr = 0;
        tempEnd = 0;
        tempInt = 0;
        tempAg = 0;
        tempChar = 0;
        tempPercep = 0;
    }

    public void mousePressed(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e) && StateManager.getCurrentState().equals(game.getStatState())) {
            mx = e.getX();
            my = e.getY();

            if(mouseOver(750, 650, 120, 50)) {
                resetAllTemp();
                tempPoints = player.getBeginningValue();
                StateManager.setCurrentState(game.getGameState());
            }

            else if(mouseOver(160, 520, 110, 40)) {
                player.subBeginningValue(tempStr + tempChar + tempAg + tempEnd + tempInt + tempPercep);
                player.addStats(tempStr, tempChar, tempAg, tempEnd, tempInt, tempPercep);
                resetAllTemp();

            }

            else if(mouseOver(660, 520, 110, 40)) {
                resetAllTemp();
                tempPoints = player.getBeginningValue();
            }

            else if(mouseOver(115, 102, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempStr++;
                tempPoints--;
            }
            else if(mouseOver(70, 102, 25, 25)) {
                if(tempStr > 0) {
                    tempStr--;
                    tempPoints++;
                }
            }

            else if(mouseOver(115, 255, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempInt++;
                tempPoints--;
            }

            else if(mouseOver(70, 255, 25, 25)) {
                if(tempInt > 0) {
                    tempInt--;
                    tempPoints++;
                }
            }

            else if(mouseOver(115, 405, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempChar++;
                tempPoints--;
            }

            else if(mouseOver(70, 405, 25, 25)) {
                if(tempChar > 0) {
                    tempChar--;
                    tempPoints++;
                }
            }

            else if(mouseOver(790, 102, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempEnd++;
                tempPoints--;
            }

            else if(mouseOver(830, 102, 25, 25)) {
                if(tempEnd > 0) {
                    tempEnd--;
                    tempPoints++;
                }
            }

            else if(mouseOver(790, 255, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempAg++;
                tempPoints--;
            }

            else if(mouseOver(830, 255, 25, 25)) {
                if(tempAg > 0) {
                    tempAg--;
                    tempPoints++;
                }
            }
            else if(mouseOver(790, 405, 25, 25)) {
                if(tempPoints <= 0)
                    return;
                tempPercep++;
                tempPoints--;
            }

            else if(mouseOver(830, 405, 25, 25)) {
                if(tempPercep > 0) {
                    tempPercep--;
                    tempPoints++;
                }
            }
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

    private void renderButtons(Graphics g) {
        g.setColor(Color.white);

        // Str
        g.drawRect(115, 102, 25, 25);
        g.drawLine(115, 114, 140, 114);
        g.drawLine(127, 102, 127, 127);

        g.drawRect(70, 102, 25, 25);
        g.drawLine(70, 114, 95, 114);


        // Int
        g.drawRect(115, 255, 25, 25);
        g.drawLine(115, 267, 140, 267);
        g.drawLine(127, 255, 127, 280);

        g.drawRect(70, 255, 25, 25);
        g.drawLine(70, 267, 95, 267);

        // Char
        g.drawRect(115, 405, 25, 25);
        g.drawLine(115, 417, 140, 417);
        g.drawLine(127, 405, 127, 430);

        g.drawRect(70, 405, 25, 25);
        g.drawLine(70, 417, 95, 417);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11

        // End
        g.drawRect(790, 102, 25, 25);
        g.drawLine(790, 114, 815, 114);
        g.drawLine(802, 102, 802, 127);

        g.drawRect(830, 102, 25, 25);
        g.drawLine(830, 114, 855, 114);


        // Ag
        g.drawRect(790, 255, 25, 25);
        g.drawLine(790, 267, 815, 267);
        g.drawLine(802, 255, 802, 280);

        g.drawRect(830, 255, 25, 25);
        g.drawLine(830, 267, 855, 267);

        // Perception
        g.drawRect(790, 405, 25, 25);
        g.drawLine(790, 417, 815, 417);
        g.drawLine(802, 405, 802, 430);

        g.drawRect(830, 405, 25, 25);
        g.drawLine(830, 417, 855, 417);
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);

        g.setColor(Color.white);
        Font f = new Font("arial", 1, 25);

        g.setFont(f);
        g.drawString("Deathknight Lvl " + player.getLevel(), 380, 50);


        g.drawString("Strength:         " + (player.getStr() + tempStr), 175, 125);
        g.drawString("Endurance:    " + (player.getEnd() + tempEnd), 575, 125);
        g.drawString("Intelligence:    " + (player.getIntelligence() + tempInt), 175, 275);
        g.drawString("Agility:            " + (player.getAgility() + tempAg), 575, 275);
        g.drawString("Charisma:       " + (player.getCharisma() + tempChar), 175, 425);
        g.drawString("Perception:     " + (player.getPercep() + tempPercep), 575, 425);

        g.drawString("Points: " + tempPoints, 420, 500);

        g.drawRect(160, 520, 110, 40);
        g.drawRect(660, 520, 110, 40);
        g.drawString("Accept", 175, 550);
        g.drawString("Reset", 675, 550);

        g.drawRect(750, 650, 120, 50);
        g.drawString("Back", 780, 683);

        renderButtons(g);

    }
}
