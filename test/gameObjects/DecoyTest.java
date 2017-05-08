package gameObjects;

import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

/**
 * Created by lixir on 05.05.2017.
 */
public class DecoyTest {

    @Test
    public void killCubeDecoy() throws Exception {
        Decoy decoy = new CubeDecoy(50, 50, Color.BLACK, 50);
        //Справа по центру
        Flying flying = new Flying(40, 10, 50, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(40, 11, 50, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));

        flying = new Flying(40, 50, 10, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(40, 50, 11, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));

        flying = new Flying(40, 100, 50, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(40, 99, 50, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));

        flying = new Flying(40, 50, 101, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(40, 50, 100, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));
    }

    @Test
    public void killOvalDecoy() throws Exception {
        Decoy decoy = new OvalDecoy(50, 50, Color.BLACK, 50);

        Flying flying = new Flying(50, 0, 50, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(50, 1, 50, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));

        flying = new Flying(50, 10, 10, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        flying = new Flying(50, 20, 20, Color.BLACK, 0, 400);
        assertTrue(decoy.kill(flying));
    }

    @Test
    public void killPeristrephicCubeDecoy() throws Exception {
        PeristrephicCubeDecoy decoy = new PeristrephicCubeDecoy(82, 50, Color.BLACK, 50);

        Flying flying = new Flying(40, 50, 50, Color.BLACK, 0, 400);
        assertFalse(decoy.kill(flying));

        decoy.setAngle(Math.PI);
        assertFalse(decoy.kill(flying));

        decoy = new PeristrephicCubeDecoy(78, 50, Color.BLACK, 50);
        assertTrue(decoy.kill(flying));

        decoy.setAngle(Math.PI/2);
        assertTrue(decoy.kill(flying));
    }
}