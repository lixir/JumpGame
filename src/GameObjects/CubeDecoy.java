package GameObjects;

import java.awt.*;

/**
 * Created by lixir on 30.04.2017.
 */
public class CubeDecoy extends Decoy {

    public CubeDecoy(int x, int y, Color color, int a, int b){
        super(x,y,color,a,b);
    }

    public CubeDecoy(int x, int y, Color color, int a){
        this(x,y,color,a,a);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(this.getColor());
        g.fillRect(this.getX(), this.getY(),this.getA(), this.getB());
    }

    @Override
    public boolean kill(int y, int a){
        return this.x <= 50 + a && this.x >= 50 - this.getA() && this.y < y + a && this.y >= y - getB();
    }
}
