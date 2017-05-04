package GameObjects;

import java.awt.*;

/**
 * Created by lixir on 27.04.2017.
 */
public class Flying extends GameComponent{

    private final int a;
    private Color color;
    private final int borderUp;
    private final int borderDown;

    public Flying(int a, int x, int y, Color color, int borderUp, int borderDown){
        super(x,y);
        this.a = a;
        this.color = color;
        this.borderDown = borderDown - a;
        this.borderUp = borderUp;
    }

    public int getA(){return a;}
    public int getBorderUp(){return borderUp;}
    public int getBorderDown(){return borderDown;}
    public Color getColor(){
        return color;
    }

    public void setColor(Color color){this.color = color;}

    public void motion(boolean up){
        if (up){
            if (y != borderUp) y -= speed / 2;
        } else  if (y != borderDown) y += speed / 2;
    }

    public void paintFlying(Graphics g){
        g.setColor(this.getColor());
        g.fillOval(this.getX(), this.getY(),this.getA(), this.getA());
    }

}
