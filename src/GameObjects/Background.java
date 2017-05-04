package GameObjects;

import java.awt.Image;

/**
 * Created by lixir on 27.04.2017.
 */
public class Background extends GameComponent{
    Image img;

    public Background(int x, int y, Image img){
        super(x,y,1);
        this.img = img;
    }

    public void motion(){
        x-=speed;
//        if (x == ) x =
    }
}
