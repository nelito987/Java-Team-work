package game;

import display.Display;

public class Launcher {
    public static void main(String[] args) {

        Game game = new Game("Need For Speed", 800, 600);
        game.start();
    }
}
