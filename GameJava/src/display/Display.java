package display;

import javax.swing.*;
import java.awt.*;

public class Display {
    private String name;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH/12*9;

    private JFrame frame;
    private Canvas canvas;

    //constructor
    public Display(String name){
        this.name = name;
        init(name);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void init(String name) {
        this.frame = new JFrame(name);
        //setting the frame
        this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.canvas = new Canvas();
        //setting canvas' dimension same as the frame
        this.canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setVisible(true);

        this.frame.add(canvas); //adding canvas to the frame
        this.frame.pack();
    }
}
