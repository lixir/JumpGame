package gameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * Created by lixir on 02.05.2017.
 */
public class PeristrephicCubeDecoy extends CubeDecoy{
    private double angle = 0;

    public PeristrephicCubeDecoy(int x, int y, Color color, int a){
        super(x,y,color,a);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        angle += Math.PI/30;
        AffineTransform at = AffineTransform.getRotateInstance(angle,this.getX() + this.getA()/2,
                this.getY() + this.getB()/2);
        g2d.setTransform(at);
        g2d.setColor(this.getColor());
        g2d.fillRect(this.getX(), this.getY(),this.getA(), this.getB());
        g2d.setTransform(new AffineTransform());
    }

    @Override
    public boolean kill(Flying flying){
        int x = getX() - flying.getX(), y = getY() - flying.getY(), a = flying.getA();
        Flying fly = new Flying(flying.getA(),(int) ((x + a/2)*cos(angle) - (y + a/2)*sin(angle)) + getX(),
                (int) ((x + a/2)*sin(angle) + (y + a/2)*cos(angle)) - a/2 + getY(), Color.white, 0,0);
        return super.kill(fly);
    }
}
