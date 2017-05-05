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
        Decoy decoy = new CubeDecoy(50, 50,Color.BLACK, 50);
    }

    @Test
    public void killOvalDecoy() throws Exception {
//        Decoy decoy = new OvalDecoy();
    }

    @Test
    public void killPeristrephicCubeDecoy() throws Exception {
//        Decoy decoy = new PeristrephicCubeDecoy();
    }
}