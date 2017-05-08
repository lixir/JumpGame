package gameObjects;

import java.awt.*;
import static java.lang.Math.abs;

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
    public boolean kill(Flying fly){
        //сверху
        if (fly.getY() + fly.getA()/2 < y) {
            //слева
            if (fly.getX() + fly.getA()/2 < x) return (fly.getX() + fly.getA()/2 - x)*(fly.getX() + fly.getA()/2 - x)
                    + (fly.getY() + fly.getA()/2 - y)*(fly.getY() + fly.getA()/2 - y) <= fly.getA() * fly.getA()/4;
            //справа
            if (fly.getX() + fly.getA()/2 > x + getA()) return (fly.getX() + fly.getA()/2 - x - getA())*(fly.getX() + fly.getA()/2 - x - getA())
                    + (fly.getY() + fly.getA()/2 - y)*(fly.getY() + fly.getA()/2 - y) <= fly.getA() * fly.getA()/4;
            //посередине
            return abs(y - fly.getY()) < fly.getA();
        }
        //снизу
        if (fly.getY() > y + getA()){
            //слева
            if (fly.getX() + fly.getA()/2 < x) return Math.abs((fly.getX() + fly.getA()/2 - x)*(fly.getX() + fly.getA()/2 - x))
                    + Math.abs((fly.getY() + fly.getA()/2 - y - getA())*(fly.getY() + fly.getA()/2 - y - getA())) <= fly.getA() * fly.getA()/4;
            //справа
            if (fly.getX() + fly.getA()/2 > x + getA()) return Math.abs((fly.getX() + fly.getA()/2 - x - getA())*(fly.getX() + fly.getA()/2 - x - getA()))
                    + Math.abs((fly.getY() + fly.getA()/2 - y - getA())*(fly.getY() + fly.getA()/2 - y - getA())) <= fly.getA() * fly.getA()/4;
            //посередине
            return abs(y - fly.getY() - getA()) < fly.getA();
        }
        //посередине

        if (fly.getX() + fly.getA()/2 < x) return abs(fly.getX() + fly.getA()/2 - x) < fly.getA()/2;
        if (fly.getX() + fly.getA()/2 > x + getA()) return abs(x + getA() - fly.getX() - fly.getA()/2) < fly.getA()/2;

        return Math.abs(x - fly.getX()) <= fly.getA() || Math.abs(x + getA() - fly.getX()) <= fly.getA();
    }
}
