package test;

import com.gto.phoebe.Control;
import com.gto.phoebe.Glue;
import com.gto.phoebe.Level;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class LevelTest {


    @Test
    public void winnerTest() throws Exception {
        Control control = new Control();
        control.speedChange = 1;
        control.angleChange = 90;

        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0));

        level.getRobot(0).setSpeed(10);
        level.getRobot(0).jump(control);

        level.getRobot(1).setSpeed(5);
        level.getRobot(1).jump(control);

        Assert.assertTrue(level.getWinner().equals(level.getRobot(0)));
    }

    @Test
    public void checkCollisionOnRobotTest() throws Exception {
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0));
        level.addActorToLevel(new Glue(new Point(200, 200), 10));

        level.getRobot(0).setSpeed(100);
        level.getRobot(0).setPosition(new Point(200, 200));
        level.checkCollisionOnRobot(level.getRobot(0));

        Assert.assertEquals(50, level.getRobot(0).getSpeed());
    }
}
