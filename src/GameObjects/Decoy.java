package GameObjects;

import java.awt.*;

/**
 * Created by lixir on 27.04.2017.
 */
public abstract class Decoy extends GameComponent{
    private int a;
    private Color color;
    private int b;


    public Decoy( int x, int y, Color color, int a, int b){
        super(x,y);
        this.color = color;
        this.a = a;
        this.b = b;
    }

    public Decoy(int x, int y, Color color, int a){
        this(x,y,color,a,a);
    }

    //getters
    public int getA(){return a;}
    public int getB(){return b;}
    public Color getColor(){return color;}

    //setters
    protected void setA(int a){this.a = a;}
    protected void setB(int b){this.b = b;}
    public void setColor(Color color){this.color = color;}

    public void motion(){
        x-=speed;
    }

    abstract public boolean kill(int y, int a);

    abstract public void paint(Graphics g);

}
