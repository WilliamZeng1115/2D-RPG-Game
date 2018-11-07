package StatePackage;

import Ability.*;
import Input.KeyManager;
import Input.MouseManager;
import Items.*;
import Launcher.Game;
import Launcher.InGame;
import Units.Monsters;
import Units.Player;
import Units.Slime;
import Worlds.Tile;
import Worlds.World;
import Graphics.Assets;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by William on 2016-04-27.
 */
public class GameState extends State {

    private Player player;
    private World world;
    private InGame inGame;
    private LinkedList<Monsters> monsters;
    private Monsters selectedMonster;
    private String name;
    private String level;
    private String description;
    private double sizeOfRed = 100;
    private int worldSizeHeight, worldSizeWidth;
    private MouseManager mouseManager;
    private KeyManager keyManager;

    public GameState(Game game, MouseManager mouseManager, KeyManager keyManager) {
        super(game);
        world = new World(game, "res/map.txt");
        worldSizeHeight = game.getHeight();
        worldSizeWidth = game.getWidth();
        this.mouseManager = mouseManager;
        this.keyManager = keyManager;
        monsters = new LinkedList<Monsters>();
        spawnMonsters(2);

    }


    // player to new character;
    public void setPlayer(Player player) {
        this.player = player;
        player.setWorld(world);
        Ability a = new Melee(Assets.punch, player);
        Ability b = new Range(Assets.punch, player);
        player.addAbility(a);
        player.setAbilityOne(a);
        player.setAbilityTwo(b);
        player.addAbility(b);
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "lol", 0, 0));
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "dam", 0, 0));
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "gg", 0, 0));
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "wow", 0, 0));
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "MF ;)", 0, 0));
        player.addWeapons(new Weapon(1, Items.Rarity.Common, "", "GOD SLAYING KATANA", 0, 0));


        player.addArmor(new Armor(1, Items.Rarity.Common, "", "rip", 0, 0));
        player.addArmor(new Armor(1, Items.Rarity.Common, "", "wow", 0, 0));
        player.addArmor(new Armor(1, Items.Rarity.Common, "", "haha", 0, 0));
        player.addArmor(new Armor(1, Items.Rarity.Common, "", "mate", 0, 0));


        player.addItem(new Item(1, Items.Rarity.Common, "", "Potion", 0, 0));
        player.addItem(new Item(1, Items.Rarity.Common, "", "Pots ;)", 0, 0));
        player.addItem(new Item(1, Items.Rarity.Common, "", "ammo", 0, 0));
        player.addItem(new Item(1, Items.Rarity.Common, "", "keys", 0, 0));
        player.addItem(new Item(1, Items.Rarity.Common, "", "memes", 0, 0));
    }

    public void setInGame(InGame inGame) {
        if(this.inGame == null) {
            this.inGame = inGame;
        }
        inGame.setPlayer(player);
        inGame.setGameState(this);

    }

    private void spawnMonsters(int n) {
        for(int i = 0; i < n; i++) {
            float randomX = (float)(Math.random() * (worldSizeWidth + game.getGameCamera().getxOffset()));
            float randomY = (float) Math.random() * (worldSizeHeight + game.getGameCamera().getyOffset());
            while(world.getTile((int)randomX / Tile.getTILEWIDTH(), (int)randomY / Tile.getTILEHEIGHT()) == null || world.getTile((int)randomX / Tile.getTILEWIDTH(), (int)randomY / Tile.getTILEHEIGHT()).isSolid()) {
                randomX = (float)(Math.random() * (worldSizeWidth + game.getGameCamera().getxOffset()));
                randomY = (float) Math.random() * (worldSizeHeight + game.getGameCamera().getyOffset());
            }
            Monsters s = new Slime(game, randomX, randomY, world);
            monsters.add(s);
        }
    }

    private void fight() {
        if(selectedMonster == null)
            return;
        if(keyManager.isM()) {
            Ability temp = null;
            for(Ability a: player.getAbilities()) {
                if(a.getId() == 1) {
                    temp = a;
                    break;
                }
            }
            if(temp == null) {
                System.out.println("Don't have the ability");
                return;
            }
            Melee temp2 = (Melee)temp;
            temp2.punch((int)player.getX(), (int)player.getY(), (int)selectedMonster.getX(), (int)selectedMonster.getY(), selectedMonster);


        }
        if(keyManager.isR()) {
            Ability temp = null;
            for(Ability a: player.getAbilities()) {
                if(a.getId() == 2) {
                    temp = a;
                    break;
                }
            }
            if(temp == null) {
                System.out.println("Don't have the ability");
                return;
            }
            Range temp2 = (Range)temp;
            temp2.shoot((int)player.getX(), (int)player.getY(), (int)selectedMonster.getX(), (int)selectedMonster.getY(), selectedMonster);
        }
    }

    private long now;
    private long lastTime = System.nanoTime();

    @Override
    public void tick() {

        world.tick();
        player.tick();
        for (Monsters m : monsters) {
            try {
                m.aggro(player);
            } catch (Exception e) {
                m.neutral();
            }
            m.tick();
            if (m.getHp() <= 0) {
                monsters.remove(m);
                selectedMonster = null;
            }
        }

        if(player.getHp() <= 0)
            StateManager.setCurrentState(game.getLoseState());

        inGame.tick();

        now = System.nanoTime();

        if((now - lastTime) / 1000000000 >= 1) {
            fight();
            lastTime = now;
        }

    }



    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        for (Monsters m : monsters) {

            m.render(g);
        }


        inGame.render(g);

        if(selectedMonster != null) {
            g.setColor(Color.white);
            g.fillRect(10, 10, 250, 55);

            g.drawImage(selectedMonster.getB(), Math.max(15, (int)player.getX() - (worldSizeWidth + (int)game.getGameCamera().getxOffset())), (Math.max(20, (int)player.getY() - (worldSizeHeight + (int)game.getGameCamera().getyOffset()))), null);
            g.setColor(Color.blue);
            Font f = new Font("arial", 1, 15);
            g.setFont(f);
            g.drawString(name, 50, 27);

            g.drawString(level, 95, 27);
            g.setColor(Color.red);
            g.fillRect(50, 35, (int)sizeOfRed, 10);
            g.setColor(Color.CYAN);
            g.fillRect(50, 50, (int)sizeOfRed, 10);

            g.setColor(Color.green);
            g.fillRect(50, 35, (int)(selectedMonster.getHp() / selectedMonster.getHpRatio()), 10);
            g.setColor(Color.blue);
            g.fillRect(50, 50, (int)(selectedMonster.getMp() / selectedMonster.getMpRatio()), 10);

            g.drawString("More Details", 160, 50);

            double xOffset = game.getGameCamera().getxOffset();
            double yOffset = game.getGameCamera().getyOffset();
            int tempA = (int)(mouseManager.getMouseX() + xOffset);
            int tempB = (int)(mouseManager.getMouseY() + yOffset);
            if(mouseManager.isLeftPressed() && tempA > (160 + xOffset) && tempA < 250 + xOffset && tempB >35 + yOffset && tempB < 60 + yOffset) {
                g.setColor(Color.black);
                g.fillRect(260, 10, 200, 55);
                g.setColor(Color.white);
                String[] temp = description.split("\n");
                g.drawString(temp[0], 270, 25);
                g.drawString(temp[1], 270, 40);
            }
        }
    }

    public World getWorld() {
        return world;
    }

    public void getMonsterInfo(int mx, int my) {
        for(Monsters m: monsters) {


            if(mx / Tile.getTILEWIDTH() == (int)(m.getX() / Tile.getTILEWIDTH()) && my / Tile.getTILEHEIGHT() == (int)(m.getY() / Tile.getTILEHEIGHT())) {
                selectedMonster = m;
                name = m.getName();
                level = "Level: " + m.getLevel();
                description = m.getDescription();
                return;
            }
            else if(mx < 10 + game.getGameCamera().getxOffset() || my < 10 + game.getGameCamera().getyOffset() || mx > 260 + game.getGameCamera().getxOffset() || my > 65 + game.getGameCamera().getyOffset())
                setMonsterInfoDefault();
        }
    }

    private void setMonsterInfoDefault() {
        selectedMonster = null;
    }
}
