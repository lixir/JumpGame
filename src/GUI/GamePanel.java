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

import GameObjects.*;

/**
 * Created by lixir on 27.04.2017.
 */
public class GamePanel extends JPanel {
    private int checker = -1, up, result = 0;
    private List<Decoy> decoys = new ArrayList<>();
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    private final Flying flying;
    private double angle = 0;
    private boolean mouse = false, kill = false;
    private Window window = null;


    public GamePanel() {
        flying = new Flying(30, 50, 100, Color.YELLOW, 50, 300);

        setBackground(Color.DARK_GRAY);
        ActionListener timerListener = e -> {
            if (!kill) {
                if (mouse) flying.motion(up < flying.getY());
                for (Decoy decoy : decoys) {
                    decoy.motion();
                    kill = kill || decoy.kill(flying.getY(), flying.getA());
                }

                if (++checker % 20 == 0) {
                    switch (checker % 3) {
                        case 0:
                            decoys.add(new CubeDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                                    colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
                            break;
                        case 1:
                            decoys.add(new OvalDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                                    colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
                            break;
                        default:
                            decoys.add(new PeristrephicCubeDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                                    colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
                    }
                    result++;
                }
                if (decoys.size() >= 1 && decoys.get(0).getX() == -50) decoys.remove(0);
            }else {
                window = new Window("Начать сначала", "Выход","Ваш результат: " + (result - 4), 0,0,700,400){
                    public void onButton1(){
                        kill = false;
                        result = 0;
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
         up = 400;
     }

     public void stop(){
            mouse = false;
        }

     @Override
     public void paintComponent (Graphics g) {
         super.paintComponent(g);
         if (!kill) {
             g.setColor(Color.WHITE);
             g.drawLine(0, flying.getBorderUp(), 700, flying.getBorderUp());
             g.drawLine(0, flying.getBorderDown() + flying.getA(), 700, flying.getBorderDown() + flying.getA());
             flying.paintFlying(g);


             for (Decoy decoy : decoys) {
                 decoy.paint(g);
             }
         }
         if (window != null) window.paintWindow(g);
     }
}
