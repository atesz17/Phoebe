package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Actor;
import com.gto.phoebe.logic.TrapperRobot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TrapperRobotTest {

    TestInterface userInterface;

    @Before
    public void setUp() {
        userInterface = new TestInterface();
    }


    @Test
    public void robotConstructorTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);

        Assert.assertTrue(robot.getPosition().equals(new Point(100, 100)));
    }

    @Test
    public void jumpTest() {
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;
        userInterface.setMovement(movement);

        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);
        robot.jump();
        Point expectedPosition = new Point(99, 100);
        Point expectedDirection = new Point(98, 100);

        Assert.assertEquals(robot.getSpeed(), 1);
        Assert.assertTrue(expectedDirection.equals(robot.getDirection()));
        Assert.assertTrue(expectedPosition.equals(robot.getPosition()));
    }

    @Test
    public void emptyTrapsTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);

        robot.dropGlue();
        Actor trap = robot.dropGlue();
        Assert.assertNull(trap);
    }

    @Test
    public void reloadTrapsTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);
        robot.dropGlue();
        robot.reloadTraps();
        Actor trap = robot.dropGlue();
        Assert.assertNotNull(trap);
    }

    @Test
    public void totalDistanceTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 0;
        userInterface.setMovement(movement);

        robot.jump();
        robot.jump();
        robot.jump();

        Assert.assertEquals(6D, robot.getTotalDistanceTraveled(), 0);
    }

}