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
    private final int aFlying = 30, xFlying = 50, yFlying = 100, borderUp = 50, borderDown = 300, width = 700,
            minDecoyA = 30, maxDecoyA = 50;
    private Color colorFlying = Color.YELLOW;

    public Game(){ flying = new Flying(aFlying, xFlying, yFlying, colorFlying, borderUp, borderDown);}

    public List<Decoy> getDecoys(){return decoys;}
    public Flying getFlying(){return flying;}
    public int getResult(){return result;}
    public boolean getKill(){return kill;}

    public void step(boolean click, int clickY){
        if (click) flying.motion(clickY < flying.getY());
        for (Decoy decoy : decoys) {
            decoy.motion();
            kill = kill || decoy.kill(flying);
        }

        if (++checker % 20 == 0) {
            switch (checker % 3) {
                case 0:
                    decoys.add(new CubeDecoy(width, (int) (Math.floor(Math.random() *( borderDown - borderUp - maxDecoyA)) + borderUp),
                            colors[(int) (Math.random() * colors.length)], (int) (Math.random() * (maxDecoyA - minDecoyA)) + minDecoyA));
                    break;
                case 1:
                    decoys.add(new OvalDecoy(width, (int) (Math.floor(Math.random() * (borderDown - borderUp - maxDecoyA)) + borderUp),
                            colors[(int) (Math.random() * colors.length)], (int) (Math.random() * (maxDecoyA - minDecoyA)) + minDecoyA));
                    break;
                default:
                    decoys.add(new PeristrephicCubeDecoy(width, (int) (Math.floor(Math.random() * (borderDown - borderUp - maxDecoyA)) + borderUp),
                            colors[(int) (Math.random() * colors.length)], (int) (Math.random() * (maxDecoyA - minDecoyA)) + minDecoyA));
            }
            result++;
        }
        if (decoys.size() >= 1 && decoys.get(0).getX() == - decoys.get(0).getA()) decoys.remove(0);
    }
}
