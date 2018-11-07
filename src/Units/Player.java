package Units;

import Ability.*;
import Items.Armor;
import Items.Item;
import Items.Weapon;
import Launcher.Game;
import Movement.PathFinding;
import StatePackage.GameState;
import Worlds.Tile;
import Worlds.World;
import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by William1115 on 2016-06-02.
 */
public class Player extends BasicUnit {

    private Game game;
    private GameState gameState;
    private int additionalDamage;
    private World world;
    private double x, y;
    private int xSpeed = 0, ySpeed = 0;
    private  LinkedList<Tile> path;
    private int assignable;

    // Skills
    private LinkedList<Ability> abilities;
    private Ability abilityOne, abilityTwo;

    // Weapons
    private LinkedList<Weapon> weapons;
    private Weapon weapon;

    // Armors
    private LinkedList<Armor> armors;
    private Armor armor;

    // Items
    private LinkedList<Item> items;
    private Item item;

    private BufferedImage bufferedImage;
    Map<String, Integer> m = new HashMap<String, Integer>();

    private int hp, mp;
    private double hpRatio, mpRatio;
    private int level;
    private int gold;
    private int totalItems;

    public Player(double x, double y, Game game, GameState gameState, BufferedImage bufferedImage) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.gameState = gameState;
        path =  new LinkedList<Tile>();
        abilities = new LinkedList<Ability>();
        weapons = new LinkedList<Weapon>();
        armors = new LinkedList<Armor>();
        items = new LinkedList<Item>();
        this.bufferedImage = bufferedImage;
        hp = 1000;
        mp = 20;
        hpRatio = (double)hp / 100;
        mpRatio = (double)mp / 100;
        level = 1;
        gold = 100;
        totalItems = 0;
        additionalDamage = 0;
        assignable = 5;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public LinkedList<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability a) {
        abilities.add(a);
        totalItems++;
    }

    public void addWeapons(Weapon a) {
        weapons.add(a);
        totalItems++;
    }

    public void addArmor(Armor a) {
        armors.add(a);
        totalItems++;
    }

    public void addItem(Item a) {
        items.add(a);
        totalItems++;
    }

    public void removeAbility(Ability a) {
        abilities.remove(a);
        totalItems--;
    }

    public void removeWeapon(Weapon a) {
        weapons.remove(a);
        totalItems--;
    }

    public void removeArmor(Armor a) {
        armors.remove(a);
        totalItems--;
    }

    public void removeItem(Item a) {
        items.remove(a);
        totalItems--;
    }


    private void setSpeed() throws Exception {
        if(path.isEmpty()) {
            throw new Exception("No path to target");
        }
        Tile temp = path.get(0);

        int nextX = temp.getX();
        int nextY = temp.getY();
        if(x < nextX* Tile.getTILEWIDTH() + Tile.getTILEWIDTH() /2)
            xSpeed = 1;
        else if(x > nextX * Tile.getTILEWIDTH()+ Tile.getTILEWIDTH() / 2)
            xSpeed = -1;
        if(y > nextY * Tile.getTILEHEIGHT()+ Tile.getTILEHEIGHT() / 2)
            ySpeed = -1;
        else if(y < nextY * Tile.getTILEHEIGHT() + Tile.getTILEHEIGHT() / 2)
            ySpeed = 1;
        if(x == nextX * Tile.getTILEWIDTH() + Tile.getTILEWIDTH() / 2 && y == nextY * Tile.getTILEHEIGHT() + Tile.getTILEHEIGHT() / 2) {
            xSpeed = 0;
            ySpeed = 0;
            path.remove(0);
        }


    }


    private void Move() {
        try {
            setSpeed();
        } catch (Exception e) {
            xSpeed = 0;
            ySpeed = 0;
        }
        x += xSpeed;
        y += ySpeed;
        xSpeed = 0;
        ySpeed = 0;
    }


    @Override
    public void DropItem() {

    }

    @Override
    public void DropGold() {

    }


    public void useMp(int mp) {
        if(mp <= 0) {
            System.out.println("in here");
            return;
        }
        this.mp -= mp;
    }

    public void clicked(double targetX, double targetY) {
        PathFinding pathFinding = new PathFinding((int)x, (int)y, (int)targetX, (int)targetY, world);
        path =  pathFinding.findShortestPath();
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public void takeDamage(int damage) {
        if(hp <= 0)
            return;
        hp -= damage;
    }

    public int getLevel() {
        return level;
    }

    public double getHpRatio() {
        return hpRatio;
    }

    public double getMpRatio() {
        return mpRatio;
    }

    public int getGold() {
        return gold;
    }

    public LinkedList<Armor> getArmors() {
        return armors;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public LinkedList<Weapon> getWeapons() {
        return weapons;
    }

    public Ability getAbilityOne() {
        return abilityOne;
    }

    public Ability getAbilityTwo() {
        return abilityTwo;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Item getItem() {
        return item;
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }

    public void setWeapon(Weapon weapon) {
        if(this.weapon != null) {
            additionalDamage -= this.weapon.getDamage();
        }
        this.weapon = weapon;
        if(weapon != null) {
            additionalDamage += weapon.getDamage();
        }

    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getStr() {
        return str;
    }

    public int getEnd() {
        return end;
    }

    public int getAgility() {
        return agility;
    }

    public int getPercep() {
        return perception;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void usePoints() {
        if(assignable <= 0)
            return;
        assignable--;
    }

    public void addStats(int s, int c, int a, int end, int intelligence, int per) {
        str += s;
        charisma += c;
        agility += a;
        this.end += end;
        this.intelligence += intelligence;
        perception += per;
    }

    public int getBeginningValue() {
        return assignable;
    }

    public void addBeginningValue(int v) {
        assignable += v;
    }

    public void subBeginningValue(int v) {
        if(assignable - v >= 0)
            assignable -= v;
    }

    @Override
    public void tick() {
        Move();
        game.getGameCamera().center(this);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bufferedImage, (int) (x - Assets.WIDTH / 2 - game.getGameCamera().getxOffset()), (int) (y - Assets.HEIGHT /2 - game.getGameCamera().getyOffset()), null);
    }

    public void setAbilityOne(Ability abilityOne) {
        this.abilityOne = abilityOne;
    }

    public void setAbilityTwo(Ability abilityTwo) {
        this.abilityTwo = abilityTwo;
    }
}
