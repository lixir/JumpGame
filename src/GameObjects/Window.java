package GameObjects;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by lixir on 02.05.2017.
 */
abstract public class Window implements GameObject {
    private final int x, y, a, b;
    private String button1Text, button2Text, message;
    private Button button1, button2;
    public MouseListener mouseListener;

    public Window(String button1Text, String button2Text, String message, int x, int y, int a, int b){
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.button1Text = button1Text;
        this.button2Text = button2Text;
        this.message = message;
        button1 = new Button(x + 20, y + 20, button1Text, a / 2 - 60, 140);
        button2 = new Button(x + a / 2 + 20, y + 20, button2Text, a / 2 - 60, 140);
        mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() > button1.getX() && e.getX() < button1.getX() + button1.getA()
                        && e.getY() > button1.getY() && e.getY() < button1.getY() + button1.getB()) onButton1();
                if (e.getX() > button2.getX() && e.getX() < button2.getX() + button2.getA()
                        && e.getY() > button2.getY() && e.getY() < button2.getY() + button2.getB()) onButton2();
            }
        };
    }

    abstract public void onButton1();

    abstract public void onButton2();

    public int getX() {return x;}
    public int getY() {return y;}

    public void paintWindow(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, a, b);
        button1.paintButton(g);
        button2.paintButton(g);
        g.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        g.drawString(message, x + a/3, y + 2*b/3);
    }

}
