package GameObjects;

import java.awt.*;

/**
 * Created by lixir on 30.04.2017.
 */
public class OvalDecoy extends Decoy{

    public OvalDecoy(int x, int y, Color color, int a){
        super(x,y,color,a,a);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(this.getColor());
        g.fillOval(this.getX(), this.getY(),this.getA(), this.getB());
    }

    @Override
    public boolean kill(Flying flying){

        return Math.sqrt(Math.abs(flying.getX() + flying.getA()/2 - getX() - getA()/2) * Math.abs(flying.getX() + flying.getA()/2
                - getX() - getA()/2) + Math.abs(flying.getY() + flying.getA()/2 - getY() - getA()/2) * Math.abs(flying.getY()
                + flying.getA()/2 - getY() - getA()/2)) < (flying.getA() + getA())/2;
    }
}
