package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by William on 2016-04-28.
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    private boolean m, r;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        m = keys[KeyEvent.VK_M];
        r = keys[KeyEvent.VK_R];
    }

    public boolean isM() {
        return m;
    }

    public boolean isR() {
        return r;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }
}
