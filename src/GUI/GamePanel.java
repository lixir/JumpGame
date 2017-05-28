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

import gameObjects.*;

/**
 * Created by lixir on 27.04.2017.
 */
public class GamePanel extends JPanel {
    private int up;
    private final int height, width;
    private boolean mouse = false, kill = false;
    private Window window = null;
    private Game game;


    public GamePanel(int height, int width) {
        this.height = height;
        this.width = width;
        game = new Game(width, height);
        setBackground(new  Color(0,0,0,50));

        ActionListener timerListener = e -> {
            if (!kill) {
                game.step(mouse, up);
                kill = game.getKill();

            }else {
                window = new Window("Начать сначала", "Выход","Ваш результат: " + game.getResult(), 0,0,width,height){
                    public void onButton1(){
                        kill = false;
                        game = new Game(width, height);
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
         up = height;
     }

     public void stop(){
            mouse = false;
        }

     @Override
     public void paintComponent (Graphics g) {
         super.paintComponent(g);
         if (!kill) {
             g.setColor(Color.WHITE);
             g.drawLine(0, game.getFlying().getBorderUp(), width, game.getFlying().getBorderUp());
             g.drawLine(0, game.getFlying().getBorderDown() + game.getFlying().getA(), width, game.getFlying().getBorderDown()
                     + game.getFlying().getA());
             game.getFlying().paintFlying(g);


             for (Decoy decoy : game.getDecoys()) {
                 decoy.paint(g);
             }
         }
         if (window != null) window.paintWindow(g);
     }
}
