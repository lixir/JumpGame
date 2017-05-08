package gameObjects;

import java.awt.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lixir on 05.05.2017.
 */
public class GameTest {
    Flying flying = new Flying(40, 100,100, Color.BLACK, 1,500);
    Game game = new Game(flying);

    @Test
    public void step() throws Exception {
        game.step(true, 50);
        assertEquals(game.getFlying().getY(), 95);
        game.step(true, 150);
        game.step(true, 150);
        assertEquals(game.getFlying().getY(), 105);
        game.step(false, 150);
        assertEquals(game.getFlying().getY(), 105);
        assertEquals(game.getDecoys().size(), 1);
        for (int i = 0; i < 17; i++){
            game.step(false,0);
        }
        assertEquals(game.getDecoys().size(), 2);
        for (int i = 0; i < 20; i++){
            game.step(false,0);
        }
        assertEquals(game.getDecoys().size(), 3);
        assertTrue(game.getDecoys().get(0) instanceof CubeDecoy);
        assertTrue(game.getDecoys().get(1) instanceof PeristrephicCubeDecoy);
        assertTrue(game.getDecoys().get(2) instanceof OvalDecoy);
    }

}