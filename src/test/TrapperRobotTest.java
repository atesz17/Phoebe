package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Actor;
import com.gto.phoebe.logic.Level;
import com.gto.phoebe.logic.TrapperRobot;
import com.gto.phoebe.util.PhoebeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;

public class TrapperRobotTest {

    private TestInterface userInterface;
    private Level level;

    @Before
    public void setUp() throws PhoebeException {
        userInterface = new TestInterface();
        InputStream map = ClassLoader.getSystemResourceAsStream("resources/map.bmp");
        level = new Level(1, map, userInterface);
    }


    @Test
    public void robotConstructorTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);

        Assert.assertTrue(robot.getPosition().equals(new Point(100, 100)));
    }

    @Test
    public void jumpTest() {
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChangeInRad = 90;
        userInterface.setMovement(movement);

        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);
        robot.jump();
        Point expectedPosition = new Point(99, 100);
        Point expectedDirection = new Point(98, 100);

        Assert.assertEquals(robot.getSpeed(), 1);
        Assert.assertTrue(expectedDirection.equals(robot.getDirection()));
        Assert.assertTrue(expectedPosition.equals(robot.getPosition()));
    }

    @Test
    public void emptyTrapsTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);

        robot.dropGlue();
        Actor trap = robot.dropGlue();
        Assert.assertNull(trap);
    }

    @Test
    public void reloadTrapsTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);
        robot.dropGlue();
        robot.reloadTraps();
        Actor trap = robot.dropGlue();
        Assert.assertNotNull(trap);
    }

    @Test
    public void totalDistanceTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChangeInRad = 0;
        userInterface.setMovement(movement);

        robot.jump();
        robot.jump();
        robot.jump();

        Assert.assertEquals(6D, robot.getTotalDistanceTraveled(), 0);
    }

}