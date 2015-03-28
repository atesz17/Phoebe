package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Glue;
import com.gto.phoebe.logic.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;

public class LevelTest {

    TestInterface userInterface;
    InputStream map;

    @Before
    public void setUp() {
        userInterface = new TestInterface();
        map = ClassLoader.getSystemResourceAsStream("resources/map.bmp");
    }

    @Test
    public void winnerTest() throws Exception {
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;
        userInterface.setMovement(movement);

        Level level = new Level(100, map, userInterface);

        level.getRobot(0).setSpeed(10);
        level.getRobot(0).jump();

        level.getRobot(1).setSpeed(5);
        level.getRobot(1).jump();

        Assert.assertTrue(level.getWinner().equals(level.getRobot(0)));
    }

    @Test
    public void checkCollisionOnRobotTest() throws Exception {
        Level level = new Level(100, map, userInterface);
        level.addTrapToLevel(new Glue(new Point(200, 200), 10));

        level.getRobot(0).setSpeed(100);
        level.getRobot(0).setPosition(new Point(200, 200));
        level.checkCollisionOnRobot(level.getRobot(0));

        Assert.assertEquals(50, level.getRobot(0).getSpeed());
    }
}
