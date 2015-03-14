package test;

import com.gto.phoebe.Actor;
import com.gto.phoebe.Control;
import com.gto.phoebe.Robot;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class RobotTest {

    @Test
    public void robotConstructorTest() {
        Robot robot = new Robot(new Point(100, 100), 1);

        Assert.assertTrue(robot.getPosition().equals(new Point(100, 100)));
    }

    @Test
    public void jumpTest() {
        Control control = new Control();
        control.speedChange = 1;
        control.angleChange = 90;

        Robot robot = new Robot(new Point(100, 100), 1);
        robot.jump(control);
        Point expectedPosition = new Point(99, 100);
        Point expectedDirection = new Point(98, 100);

        Assert.assertEquals(robot.getSpeed(), 1);
        Assert.assertTrue(expectedDirection.equals(robot.getDirection()));
        Assert.assertTrue(expectedPosition.equals(robot.getPosition()));
    }

    @Test
    public void emptyTrapsTest() {
        Robot robot = new Robot(new Point(100, 100), 1);

        robot.dropGlue();
        Actor trap = robot.dropGlue();
        Assert.assertNull(trap);
    }

    @Test
    public void reloadTrapsTest() {
        Robot robot = new Robot(new Point(100, 100), 1);
        robot.dropGlue();
        robot.reloadTraps();
        Actor trap = robot.dropGlue();
        Assert.assertNotNull(trap);
    }

    @Test
    public void totalDistanceTest() {
        Robot robot = new Robot(new Point(100, 100), 1);
        Control control = new Control();
        control.speedChange = 1;
        control.angleChange = 0;

        robot.jump(control);
        robot.jump(control);
        robot.jump(control);

        Assert.assertEquals(6D, robot.getTotalDistanceTraveled(), 0);
    }

}
