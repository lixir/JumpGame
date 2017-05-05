package GUI;

import gameObjects.Flying;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

/**
 * Created by lixir on 27.04.2017.
 */
public class GameFrame extends JFrame {
    private GamePanel panel;
    private final int aFlying = 30, xFlying = 50, yFlying = 100, borderUp = 50, borderDown = 300,
        height = 400, width = 700;
    private Color colorFlying = Color.YELLOW;

    public GameFrame(String s){
        super(s);
        setSize(width, height);
        panel = new GamePanel(new Flying(aFlying, xFlying, yFlying, colorFlying, borderUp, borderDown), height, width);
        this.setContentPane(panel);

        KeyListener keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                 panel.keyUp();
                } else
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                   panel.keyDown();
                }
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) panel.stop();
            }
        };
        addKeyListener(keyListener);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame("JumpGame"));
    }
}
