package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            Player.isMovingLeft = true;
        }else if(keyCode == KeyEvent.VK_RIGHT){
            Player.isMovingRight = true;
        }
        if(keyCode == KeyEvent.VK_UP){
            Player.isMovingUp = true;
        }else if(keyCode == KeyEvent.VK_DOWN){
            Player.isMovingDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            Player.isMovingLeft = false;
        }else if(keyCode == KeyEvent.VK_RIGHT){
            Player.isMovingRight = false;
        }
        if(keyCode == KeyEvent.VK_UP){
            Player.isMovingUp = false;
        }else if(keyCode == KeyEvent.VK_DOWN){
            Player.isMovingDown = false;
        }
    }
}
