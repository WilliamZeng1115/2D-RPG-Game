package Items;

import Ability.Ability;
import Launcher.Game;
import StatePackage.StateManager;
import Units.Player;
import Items.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created by william on 2016-12-21.
 */
public class Bag extends MouseAdapter {

    private int maxSize;
    private int mx, my;
    private Game game;
    private int gameWidth, gameHeight;
    private Player player;
    private static int PER_PAGE = 3;
    private int weaponPage = 0, armorPage = 0, itemPage = 0, skillPage = 0;
    private LinkedList<Weapon> weapons;
    private LinkedList<Armor> armors;
    private LinkedList<Item> items;
    private LinkedList<Ability> abilities;
    private Weapon currWeapon;
    private Armor currArmor;
    private Item currrItem;
    private Ability currAbilityOne, currAbilityTwo;
    private Items selected;
    private Ability selectedAbility;

    public Bag(int size, Game game) {
        this.game = game;
        maxSize = size;
        gameWidth = game.getWidth();
        gameHeight = game.getHeight();
        this.player = game.getPlayer();
        weapons = player.getWeapons();
        armors = player.getArmors();
        items = player.getItems();
        abilities = player.getAbilities();
        currWeapon = player.getWeapon();
        currArmor = player.getArmor();
        currrItem = player.getItem();
        currAbilityOne = player.getAbilityOne();
        currAbilityTwo = player.getAbilityTwo();

    }

    public void storeItem(Items i) {
        // stub
    }

    public Items getItemDetails(String name) {
        // stub
        return null;
    }

    public void removeItem(String name) {
        // stub
    }

    public void sortBagByDamage() {
        // stub
    }

    public void sortBagByClass() {
        // stub
    }

    public void clearBag() {
        // stub
    }

    public void mousePressed(MouseEvent e) {
        if(StateManager.getCurrentState().equals(game.getBagState())) {
            mx = e.getX();
            my = e.getY();

            if (mouseOver(790, 715, 100, 50)) {
                StateManager.setCurrentState(game.getGameState());

            } else if (mouseOver(10, 220, 20, 20)) {
                if (weaponPage > 0)
                    weaponPage--;
            } else if (mouseOver(210, 220, 20, 20)) {
                int maxPage = weapons.size() / PER_PAGE;
                if (weaponPage < maxPage)
                    weaponPage++;

            } else if (mouseOver(255, 220, 20, 20)) {
                if (armorPage > 0)
                    armorPage--;
            } else if (mouseOver(455, 220, 20, 20)) {
                int maxPage = armors.size() / PER_PAGE;
                if (armorPage < maxPage)
                    armorPage++;

            } else if (mouseOver(510, 220, 20, 20)) {
                if (itemPage > 0)
                    itemPage--;
            } else if (mouseOver(710, 220, 20, 20)) {
                int maxPage = items.size() / PER_PAGE;
                if (itemPage < maxPage)
                    itemPage++;

            } else if (mouseOver(760, 220, 20, 20)) {
                if (skillPage > 0)
                    skillPage--;
            } else if (mouseOver(960, 220, 20, 20)) {
                int maxPage = abilities.size() / PER_PAGE;
                if (skillPage < maxPage)
                    skillPage++;
            }

            else if(mouseOver(45, 85, 150, 98)) {
                selectedAbility = null;
                if(weapons.size() > weaponPage * PER_PAGE)
                    selected = weapons.get(weaponPage * PER_PAGE);
                else selected = null;
            }else if(mouseOver(45, 183, 150, 108)) {
                selectedAbility = null;
                if(weapons.size() > weaponPage * PER_PAGE + 1)
                    selected = weapons.get(weaponPage * PER_PAGE + 1);
                else selected = null;
            }else if(mouseOver(45, 291, 150, 108)) {
                selectedAbility = null;
                if(weapons.size() > weaponPage * PER_PAGE + 2)
                    selected = weapons.get(weaponPage * PER_PAGE + 2);
                else selected = null;
            }

            else if(mouseOver(290, 85, 150, 98)) {
                selectedAbility = null;
                if(armors.size() > armorPage * PER_PAGE)
                    selected = armors.get(armorPage * PER_PAGE);
                else selected = null;
            }else if(mouseOver(290, 183, 150, 108)) {
                selectedAbility = null;
                if(armors.size() > armorPage * PER_PAGE + 1)
                    selected = armors.get(armorPage * PER_PAGE + 1);
                else selected = null;
            }else if(mouseOver(290, 291, 150, 108)) {
                selectedAbility = null;
                if(armors.size() > armorPage * PER_PAGE + 2)
                    selected = armors.get(armorPage * PER_PAGE + 2);
                else selected = null;
            }

            else if(mouseOver(545, 85, 150, 98)) {
                selectedAbility = null;
                if(items.size() > itemPage * PER_PAGE)
                    selected = items.get(itemPage * PER_PAGE);
                else selected = null;
            }else if(mouseOver(545, 183, 150, 108)) {
                selectedAbility = null;
                if(items.size() > itemPage * PER_PAGE + 1)
                    selected = items.get(itemPage * PER_PAGE + 1);
                else selected = null;
            }else if(mouseOver(545, 291, 150, 108)) {
                selectedAbility = null;
                if(items.size() > itemPage * PER_PAGE + 2)
                    selected = items.get(itemPage * PER_PAGE + 2);
                else selected = null;
            }

            else if(mouseOver(795, 85, 150, 98)) {
                if(abilities.size() > skillPage * PER_PAGE) {
                    selectedAbility = abilities.get(skillPage * PER_PAGE);
                    selected = null;
                }else {
                    selected = null;
                    selectedAbility = null;
                }
            }else if(mouseOver(795, 183, 150, 108)) {
                if(abilities.size() > skillPage * PER_PAGE + 1) {
                    selectedAbility = abilities.get(skillPage * PER_PAGE + 1);
                    selected = null;
                } else {
                    selected = null;
                    selectedAbility = null;
                }
            }else if(mouseOver(795, 291, 150, 108)) {
                if(abilities.size() > skillPage * PER_PAGE + 2) {
                    selectedAbility = abilities.get(skillPage * PER_PAGE + 2);
                    selected = null;
                } else {
                    selected = null;
                    selectedAbility = null;
                }
            }
            else if(selected != null && mouseOver(300, 650, 100, 50)) {
                if(Weapon.class.isAssignableFrom(selected.getClass())) {
                    currWeapon = (Weapon) selected;
                    player.setWeapon(currWeapon);
                }
                else if(Armor.class.isAssignableFrom(selected.getClass())) {
                    currArmor = (Armor) selected;
                    player.setArmor(currArmor);
                }
                else if(Item.class.isAssignableFrom(selected.getClass())) {
                    currrItem = (Item) selected;
                    player.setItem(currrItem);
                }
            }
            else if(selectedAbility != null && mouseOver(300, 650, 100, 50)) {
                if(Ability.class.isAssignableFrom(selectedAbility.getClass())) {
                    if(currAbilityOne == null) {
                        currAbilityOne = selectedAbility;
                        player.setAbilityOne(currAbilityOne);
                    }
                    else  {
                        currAbilityTwo = selectedAbility;
                        player.setAbilityTwo(currAbilityTwo);
                    }

                }
            }

            else if(selected != null && mouseOver(550, 650, 100, 50)) {
                if(Weapon.class.isAssignableFrom(selected.getClass())) {
                    if(currWeapon == selected)
                        currWeapon = null;
                    player.removeWeapon((Weapon)selected);
                    selected = null;
                }
                else if(Armor.class.isAssignableFrom(selected.getClass())) {
                    if(currArmor == selected)
                        currArmor = null;
                    player.removeArmor((Armor)selected);
                    selected = null;
                }
                else if(Item.class.isAssignableFrom(selected.getClass())) {
                    if(currrItem == selected)
                        currrItem = null;
                    player.removeItem((Item)selected);
                    selected =null;
                }
            }
            else if(selectedAbility != null && mouseOver(550, 650, 100, 50)) {
                if(Ability.class.isAssignableFrom(selectedAbility.getClass())) {
                    if(currAbilityOne == selectedAbility) {
                        if(currAbilityTwo == selectedAbility) {
                            currAbilityTwo = null;
                            player.setAbilityOne(null);
                        }
                        currAbilityOne = null;
                        player.setAbilityOne(null);
                    }
                    else  if(currAbilityTwo == selectedAbility){
                                if(currAbilityOne == selectedAbility) {
                                     currAbilityTwo = null;
                                     player.setAbilityOne(null);
                                }
                        currAbilityTwo = null;
                        player.setAbilityTwo(null);
                    }
                    player.removeAbility(selectedAbility);
                    selectedAbility = null;
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

    private void renderSelected(Graphics g) {
        if(selected != null || selectedAbility != null) {
            g.setColor(Color.white);
            g.fillRect(300, 650, 100, 50);
            g.fillRect(550, 650, 100, 50);
            g.setColor(Color.black);
            g.drawString("Equip", 315, 685);
            g.drawString("Drop", 565, 685);
        }
    }

    private void displayWeapons(Graphics g) {
        int startingWeapon = weaponPage * PER_PAGE;
        int startingY = 120;
        int startingX = 50;
        int everyPosition = 112;
        int count = 0;
        for(int i = startingWeapon; i < startingWeapon + PER_PAGE; i++) {
            if(i >= weapons.size())
                 break;
            g.drawString(weapons.get(i).getUniqueName(), startingX, startingY + everyPosition * count);
            count++;
        }
    }

    private void displayArmors(Graphics g) {
        int startArmor = armorPage * PER_PAGE;
        int startingY = 120;
        int startingX = 295;
        int everyPosition = 112;
        int count = 0;
        for(int i = startArmor; i < startArmor + PER_PAGE; i++) {
            if(i >= armors.size())
                break;
            g.drawString(armors.get(i).getUniqueName(), startingX, startingY + everyPosition * count);
            count++;
        }
    }

    private void displayItems(Graphics g) {
        int startingItem = itemPage * PER_PAGE;
        int startingY = 120;
        int startingX = 550;
        int everyPosition = 112;
        int count = 0;
        for(int i = startingItem; i < startingItem + PER_PAGE; i++) {
            if(i >= items.size())
                break;
            g.drawString(items.get(i).getUniqueName(), startingX, startingY + everyPosition * count);
            count++;
        }
    }

    private void displaySkills(Graphics g) {
        int startingSkills = skillPage * PER_PAGE;
        int startingY = 120;
        int startingX = 800;
        int everyPosition = 112;
        int count = 0;
        for(int i = startingSkills; i < startingSkills + PER_PAGE; i++) {
            if(i >= abilities.size())
                break;
            g.drawString(abilities.get(i).getName(), startingX, startingY + everyPosition * count);
            count++;
        }
    }


    private void displayEquipped(Graphics g) {
        if(currWeapon != null) {
            g.drawString(currWeapon.getUniqueName(), 90, 505);
        }
        else g.drawString("No Weapon", 90, 505);

        if(currArmor != null) {

            g.drawString(currArmor.getUniqueName(), 265, 505);
        }
        else g.drawString("No Armor", 265, 505);


        if(currrItem != null) {

            g.drawString(currrItem.getUniqueName(), 450, 505);
        }
        else g.drawString("No Item", 450, 505);

        if(currAbilityOne != null) {

            g.drawString(currAbilityOne.getName(), 627, 505);
        }
        else g.drawString("No Item", 627, 505);

        if(currAbilityTwo != null) {

            g.drawString(currAbilityTwo.getName(), 810, 505);
        }
        else g.drawString("No Item", 810, 505);
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);

        Font f2 = new Font("arial", 1, 15);
        Font f1 = new Font("arial", 1, 25);

        g.setFont(f1);
        g.setColor(Color.white);
        g.drawString("Secret Dimension space", 350, 30);

        g.setFont(f2);
        g.drawString("Weapon", 95, 70);
        g.drawRect(45, 85, 150, 300);
        g.drawLine(45, 183, 195, 183);
        g.drawLine(45, 291, 195, 291);

        displayWeapons(g);

        g.drawLine(30, 220, 10, 230);
        g.drawLine(10, 230, 30, 240);
        g.drawLine(30, 220, 30, 240);

        g.drawLine(210, 220, 230, 230);
        g.drawLine(230, 230, 210, 240);
        g.drawLine(210, 220, 210, 240);

        g.drawString("Armor", 345, 70);
        g.drawRect(290, 85, 150, 300);
        g.drawLine(290, 183, 440, 183);
        g.drawLine(290, 291, 440, 291);

        displayArmors(g);

        g.drawLine(275, 220, 255, 230);
        g.drawLine(255, 230, 275, 240);
        g.drawLine(275, 220, 275, 240);

        g.drawLine(455, 220, 475, 230);
        g.drawLine(475, 230, 455, 240);
        g.drawLine(455, 220, 455, 240);

        g.drawString("Items", 600, 70);
        g.drawRect(545, 85, 150, 300);
        g.drawLine(545, 183, 695, 183);
        g.drawLine(545, 291, 695, 291);

        displayItems(g);

        g.drawLine(530, 220, 510, 230);
        g.drawLine(510, 230, 530, 240);
        g.drawLine(530, 220, 530, 240);

        g.drawLine(710, 220, 730, 230);
        g.drawLine(730, 230, 710, 240);
        g.drawLine(710, 220, 710, 240);

        g.drawString("Skills", 850, 70);
        g.drawRect(795, 85, 150, 300);
        g.drawLine(795, 183, 945, 183);
        g.drawLine(795, 291, 945, 291);

        displaySkills(g);

        g.drawLine(780, 220, 760, 230);
        g.drawLine(760, 230, 780, 240);
        g.drawLine(780, 220, 780, 240);

        g.drawLine(960, 220, 980, 230);
        g.drawLine(980, 230, 960, 240);
        g.drawLine(960, 220, 960, 240);

        g.drawString("Equipped", 460, 425);
        g.drawRect(45, 440, 900, 150);

        g.drawLine(220, 440, 220, 590);
        g.drawString("Weapon", 100, 608);

        g.drawLine(405, 440, 405, 590);
        g.drawString("Armor", 290, 608);

        g.drawLine(582, 440, 582, 590);
        g.drawString("Items", 480, 608);

        g.drawLine(765, 440, 765, 590);
        g.drawString("Skill-1", 655, 608);
        g.drawString("Skill-2", 845, 608);

        displayEquipped(g);

        g.setColor(Color.yellow);
        g.setFont(f1);
        g.drawString("Gold: " + player.getGold(), 45, 675);

        g.setColor(Color.white);
        g.drawString("Bag Space: " + player.getTotalItems() + "/" + maxSize, 740, 675);

        g.fillRect(790, 715, 100, 50);
        g.setColor(Color.black);
        g.drawString("Back", 810, 750);

        renderSelected(g);
    }

}
