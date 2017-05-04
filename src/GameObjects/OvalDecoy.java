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
    public boolean kill(int y, int a){
//        System.out.println("" + (Math.abs(50 + a/2 - getX() - getA()/2) * Math.abs(50 + a/2 - getX() - getA()/2)
//                + Math.abs(y + a/2 - getY() - getA()/2) * Math.abs(y + a/2 - getY() - getA()/2)));
//        System.out.println("" + (a/2 + getA()/2));
        return Math.abs(50 + a/2 - getX() - getA()/2) * Math.abs(50 + a/2 - getX() - getA()/2)
                + Math.abs(y + a/2 - getY() - getA()/2) * Math.abs(y + a/2 - getY() - getA()/2) < (a + getA())*10;
    }

//    public int tempX(){
//        return getX() + getA()/2;
//    }
//
//    public int tempY(){
//        return getY() + getA()/2;
//    }

}
