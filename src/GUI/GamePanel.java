package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import gameObjects.*;

/**
 * Created by lixir on 27.04.2017.
 */
public class GamePanel extends JPanel {
    private int up, result;
    private final int height, width;
    private List<Decoy> decoys = new ArrayList<>();
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    private Flying flying;
    private boolean mouse = false, kill = false;
    private Window window = null;
    private final Game game;


    public GamePanel(Flying fly,int height, int width) {
        flying = fly;
        this.height = height;
        this.width = width;
        game = new Game(flying);
        setBackground(Color.DARK_GRAY);

        ActionListener timerListener = e -> {
            if (!kill) {

                game.step(mouse, up);
                decoys = game.getDecoys();
                flying = game.getFlying();
                result = game.getResult();
                kill = game.getKill();

            }else {
                window = new Window("Начать сначала", "Выход","Ваш результат: " + result, 0,0,700,400){
                    public void onButton1(){
                        kill = false;
                        game.setKill(false);
                        game.clearResult();
                        for (int i=0; i<decoys.size(); i++){
                            decoys.remove(i);
                        }
                        removeMouseListener(this.mouseListener);
                        window = null;
                    }

                    public void onButton2(){
                        System.exit(0);
                    }
                };
                this.addMouseListener(window.mouseListener);
            }
            repaint();
        };

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouse = true;
                    up = e.getY();
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) mouse = false;
            }

        };
        MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                up = e.getY();
            }
        };
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseMotionListener);

        Timer timer = new Timer(20, timerListener);
        timer.start();
    }

    public void keyUp() {
        mouse = true;
        up = 0;
    }

     public void keyDown() {
         mouse = true;
         up = flying.getBorderDown();
     }

     public void stop(){
            mouse = false;
        }

     @Override
     public void paintComponent (Graphics g) {
         super.paintComponent(g);
         if (!kill) {
             g.setColor(Color.WHITE);
             g.drawLine(0, flying.getBorderUp(), width, flying.getBorderUp());
             g.drawLine(0, flying.getBorderDown() + flying.getA(), width, flying.getBorderDown() + flying.getA());
             flying.paintFlying(g);


             for (Decoy decoy : decoys) {
                 decoy.paint(g);
             }
         }
         if (window != null) window.paintWindow(g);
     }
}
