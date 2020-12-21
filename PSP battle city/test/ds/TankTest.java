package ds;

import BC.Board;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TankTest extends TestCase {
    Tank tank;
    @Before
    public void tankSetUp(){
        tank = new Tank(10,10,5);
    }
    @After
    public void clean(){}

    @Test
    public void testTank(){
        tankSetUp();
        tank.downHealth();
        assertEquals(4,tank.getHealth());
    }

    @Test
    public void testGameOver(){
        tankSetUp();
        tank.downHealth();
        tank.downHealth();
        tank.downHealth();
        assertEquals(false,Board.gameOver);
    }
}