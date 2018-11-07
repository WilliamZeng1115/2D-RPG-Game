package Launcher;

import Graphics.*;
import Input.KeyManager;
import Input.MouseManager;
import Items.Bag;
import StatePackage.*;
import Units.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by William on 2016-04-27.
 */
public class Game implements Runnable {

    private Display display;
    private KeyManager keyManager;

    private GameCamera gameCamera;

    private int width, height;
    private String title;

    private Thread thread;
    private Boolean running = false;

    private Player player;

    private GameState gameState;
    private MenuState menuState;
    private ShopState shopState;
    private LoseState loseState;
    private MouseManager mouseManager;
    private BagState bagState;
    private StatState statState;

    private Menu menu;
    private InGame inGame;
    private Shop shop;
    private LoseScreen loseScreen;
    private Bag bag;
    private Stats stats;

    // Variables for graphics/canvas
    private BufferStrategy bs;
    private Graphics g;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        menu = new Menu(this);
        inGame = new InGame(this);
        shop = new Shop(this);
        loseScreen = new LoseScreen(this);

    }



    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
//        display.getFrame().addMouseListener(mouseManager);
 ////       display.getFrame().addMouseMotionListener(mouseManager);
 //       display.getCanvas().addMouseListener(mouseManager);
 //       display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addMouseListener(menu);
        display.getFrame().addMouseMotionListener(menu);
        display.getCanvas().addMouseListener(menu);
        display.getCanvas().addMouseMotionListener(menu);
        display.getFrame().addMouseListener(inGame);
        display.getFrame().addMouseMotionListener(inGame);
        display.getCanvas().addMouseListener(inGame);
        display.getCanvas().addMouseMotionListener(inGame);
        display.getFrame().addMouseListener(shop);
        display.getFrame().addMouseMotionListener(shop);
        display.getCanvas().addMouseListener(shop);
        display.getCanvas().addMouseMotionListener(shop);
        display.getFrame().addMouseListener(loseScreen);
        display.getFrame().addMouseMotionListener(loseScreen);
        display.getCanvas().addMouseListener(loseScreen);
        display.getCanvas().addMouseMotionListener(loseScreen);


        // initialize all the images from the SpriteSheet
        Assets.init();

        gameCamera = new GameCamera(this, 0, 0);
        gameState = new GameState(this, mouseManager, keyManager);
        menuState = new MenuState(this, menu);
        shopState = new ShopState(this, shop);
        loseState = new LoseState(this, loseScreen);

        player = new Player(100, 100, this, gameState, Assets.player);
        gameState.setPlayer(player);
        menu.setMenuState(menuState);
        gameState.setInGame(inGame);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        stats = new Stats(this);

        display.getFrame().addMouseListener(stats);
        display.getFrame().addMouseMotionListener(stats);
        display.getCanvas().addMouseListener(stats);
        display.getCanvas().addMouseMotionListener(stats);

        statState = new StatState(this, stats);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        bag = new Bag(100, this);

        // gg need it after bag is initialized
        display.getFrame().addMouseListener(bag);
        display.getFrame().addMouseMotionListener(bag);
        display.getCanvas().addMouseListener(bag);
        display.getCanvas().addMouseMotionListener(bag);

        bagState = new BagState(this, bag);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        StateManager.setCurrentState(menuState);

    }

    private void tick(){
        keyManager.tick();

        if(StateManager.getCurrentState() != null)
            StateManager.getCurrentState().tick();

    }

    private void render() {
        // a way for computer to draw to the screen
        // it is a hidden screen producing new screen before it is rendered
        bs = display.getCanvas().getBufferStrategy();

        //single instance of buffer
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        // so it gets the paintbrush allowing it to draw on the frame
        // allowing ability to draw after g is initialized
        g = bs.getDrawGraphics();
        //clear screen before it starts drawing
        g.clearRect(0,0,width, height);

        /////////////////////////////////
        if(StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }


        ////////////////////////////////////
        // Last parameter is the Image observer

        // so the bufferstrategy brings the image on the frame
        bs.show();
        //after it dispose to the next buffer strategy can be rendered when render is called again in the game loop
        g.dispose();


    }

    // produce the game loop and run the game
    @Override
    public void run() {

        // use helper to init the Display and create a new Frame and Canvas rather than in the constructor
        init();


        // Setting up for ticking
        int fps = 60;
        // In nanoSeconds so 1 000 000 000 is 1 second
        // Thus time per tick is the amount of time require for 1 tick since we want 60 tick per second
        double timePerTick = 1000000000 / fps;
        // Previous time
        double delta = 0;
        long now;
        // Current Time in nanoSecnod
        long lastTime = System.nanoTime();

        //begin the game loop to tick the things and render it constantly if running is true, set from start
        while(running) {
            now = System.nanoTime();
            // if the amount of time passed from last time till now is larger than timePerTick then its >= 1 thus tick/render
            // if the amount of time passed is lower than timePerTick then its < 1 thus no tick/render
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
            }

        }

        // when running is false so we stop it;
        stop();

    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    // only when there is threading
    public synchronized void start() {
        if(running) {
            return;
        }
        running = true;
        //initialize thread to this Game(singleton)
        thread = new Thread(this);

        // it will call the RUN method when u begin thread.start
        thread.start();

    }

    // only when there is threading
    public synchronized void stop() {
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Player getPlayer() {
        return player;
    }

    public ShopState getShopState() {
        return shopState;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public InGame getInGame() {
        return inGame;
    }

    public LoseState getLoseState() {
        return loseState;
    }

    public BagState getBagState() {
        return bagState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public StatState getStatState() {
        return statState;
    }
}
