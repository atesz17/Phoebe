package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Actor;
import com.gto.phoebe.logic.Robot;
import com.gto.phoebe.ui.ConsoleInterface;
import com.gto.phoebe.ui.UserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class RobotTest {

    UserInterface consoleInterface;

    @Before
    public void setUp(){
        consoleInterface = new ConsoleInterface();
    }


    @Test
    public void robotConstructorTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);

        Assert.assertTrue(robot.getPosition().equals(new Point(100, 100)));
    }

    @Test
    public void jumpTest() {
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;

        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);
        robot.jump(movement);
        Point expectedPosition = new Point(99, 100);
        Point expectedDirection = new Point(98, 100);

        Assert.assertEquals(robot.getSpeed(), 1);
        Assert.assertTrue(expectedDirection.equals(robot.getDirection()));
        Assert.assertTrue(expectedPosition.equals(robot.getPosition()));
    }

    @Test
    public void emptyTrapsTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);

        robot.dropGlue();
        Actor trap = robot.dropGlue();
        Assert.assertNull(trap);
    }

    @Test
    public void reloadTrapsTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);
        robot.dropGlue();
        robot.reloadTraps();
        Actor trap = robot.dropGlue();
        Assert.assertNotNull(trap);
    }

    @Test
    public void totalDistanceTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 0;

        robot.jump(movement);
        robot.jump(movement);
        robot.jump(movement);

        Assert.assertEquals(6D, robot.getTotalDistanceTraveled(), 0);
    }

}