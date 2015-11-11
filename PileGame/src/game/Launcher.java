package game;

import display.Display;
import gfx.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Launcher {
    private static Graphics g;

    public static void main(String[] args) {
        JFrame window = new JFrame("Launcher");
        window.setVisible(true);
        window.setSize(400,400);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(true);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        window.add(panel);
        //Beckground ??????????
        panel.setBackground(Color.white);




        JButton startButton = new JButton("START");
        startButton.setBounds(150,70,100,50);
        panel.add(startButton);
        startButton.addActionListener(new Action());


        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(150,280,100,50);
        panel.add(exitButton);
        //fincion needed
        JButton controlsButton = new JButton("CONTROLS");
        controlsButton.setBounds(150,140,100,50);
        panel.add(controlsButton);
        // funcion needed
        JButton creditsButton = new JButton("CREDITS");
        creditsButton.setBounds(150,210,100,50);
        panel.add(creditsButton);
        // funcion needed

    }

        static class Action implements ActionListener{

            public void actionPerformed(ActionEvent e){
                Game game = new Game("Pile Game", 800, 600);
                game.start();
            }
        }

    }

