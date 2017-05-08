package gameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixir on 04.05.2017.
 */
public class Game {
    private int checker = -1, result = -4;
    private final Flying flying;
    private final List<Decoy> decoys = new ArrayList<>();
    private final Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA,
            Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    private boolean kill = false;

    public Game(Flying flying){
        this.flying = flying;
    }

    public List<Decoy> getDecoys(){return decoys;}
    public Flying getFlying(){return flying;}
    public int getResult(){return result;}
    public boolean getKill(){return kill;}

    public void setKill(boolean kill){this.kill = kill;}
    public void clearResult(){
        result = -4;
    }

    public void step(boolean click, int clickY){
        if (click) flying.motion(clickY < flying.getY());
        for (Decoy decoy : decoys) {
            decoy.motion();
            kill = kill || decoy.kill(flying);
        }

        if (++checker % 20 == 0) {
            switch (checker % 3) {
                case 0:
                    decoys.add(new CubeDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                            colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
                    break;
                case 1:
                    decoys.add(new OvalDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                            colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
                    break;
                default:
                    decoys.add(new PeristrephicCubeDecoy(800, (int) (Math.floor(Math.random() * 200) + 50),
                            colors[(int) (Math.random() * 11)], (int) (Math.random() * 20) + 30));
            }
            result++;
        }
        if (decoys.size() >= 1 && decoys.get(0).getX() == -50) decoys.remove(0);
    }
}
