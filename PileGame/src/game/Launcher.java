package game;

import display.Display;
import gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher {
    public static void main(String[] args) {

        //launcher frame
        JFrame f = new JFrame("Launcher");
        f.setMaximumSize(new Dimension(360, 360));
        f.setPreferredSize(new Dimension(360, 360));
        f.setMinimumSize(new Dimension(360, 360));
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setFocusable(true);
        f.setLocationRelativeTo(null);


        JPanel p = new JPanel();
        p.setBackground(Color.BLUE);
        f.add(p);

        JButton startButton = new JButton("Play Game");
        startButton.setPreferredSize(new Dimension(100,40));

        startButton.addActionListener(new Action());

        p.add(startButton);

        f.pack();
    }

        static class Action implements ActionListener{

            public void actionPerformed(ActionEvent e){
                Game game = new Game("Pile Game", 800, 600);
                game.start();
            }
        }

    }

