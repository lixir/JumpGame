package GameObjects;

import java.awt.*;

/**
 * Created by lixir on 02.05.2017.
 */
public class Button implements GameObject {
    private int x, y, a, b;
    private String text;

    public Button(int x, int y, String text, int a, int b){
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.text = text;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getA(){return a;}
    public int getB(){return b;}

    public void paintButton(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(x, y, x + a, y);
        g2d.drawLine(x + a, y, x + a, y + b);
        g2d.drawLine(x + a, y + b, x, y + b);
        g2d.drawLine(x, y + b, x, y);
        g2d.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        g2d.drawString(text, x + 50, y + 70);
    }
}
