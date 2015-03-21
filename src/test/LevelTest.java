package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.ui.ConsoleInterface;
import com.gto.phoebe.logic.Glue;
import com.gto.phoebe.logic.Level;
import com.gto.phoebe.ui.UserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class LevelTest {

    UserInterface consoleInterface;
    
    @Before
    public void setUp(){
        consoleInterface = new ConsoleInterface();
    }
    
    @Test
    public void winnerTest() throws Exception {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;

        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), consoleInterface);

        level.getRobot(0).setSpeed(10);
        level.getRobot(0).jump(movement);

        level.getRobot(1).setSpeed(5);
        level.getRobot(1).jump(movement);

        Assert.assertTrue(level.getWinner().equals(level.getRobot(0)));
    }

    @Test
    public void checkCollisionOnRobotTest() throws Exception {
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), consoleInterface);
        level.addActorToLevel(new Glue(new Point(200, 200), 10));

        level.getRobot(0).setSpeed(100);
        level.getRobot(0).setPosition(new Point(200, 200));
        level.checkCollisionOnRobot(level.getRobot(0));

        Assert.assertEquals(50, level.getRobot(0).getSpeed());
    }
}
