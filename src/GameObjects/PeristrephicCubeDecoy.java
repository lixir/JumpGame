package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by lixir on 02.05.2017.
 */
public class PeristrephicCubeDecoy extends Decoy{
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
    public boolean kill(int y, int a){
        return false;
    }
}
