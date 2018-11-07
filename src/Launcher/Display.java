package Launcher;

import javax.swing.*;
import java.awt.*;

/**
 * Created by William on 2016-04-27.
 */
public class Display {

    // need title width and height
    // JFrame is the window which canvas is used to paint on to
    private JFrame frame;
    // Graphics is using canvas
    private Canvas canvas;

    private int width, height;
    private String title;

    // Constructor
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        // initialize new JFrame
        // using a helper
        createDisplay();

    }

    public JFrame getFrame() {
        return frame;
    }

    private void createDisplay() {
        // initializing JFrame
        frame = new JFrame(title);
        frame.setSize(width, height);
        //so it closes when it x
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // initializing canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        // Now adding canvas on to the frame; painting on canvas
        frame.add(canvas);
        frame.pack(); // to make sure canvas is packed in to the frame
    }


    public Canvas getCanvas() {
        return canvas;
    }




}
