/*package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Actor;
import com.gto.phoebe.logic.Glue;
import com.gto.phoebe.logic.Oil;
import com.gto.phoebe.logic.Robot;
import com.gto.phoebe.ui.ConsoleInterface;
import com.gto.phoebe.ui.UserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TrapTest {

    UserInterface consoleInterface;

    @Before
    public void setUp(){
        consoleInterface = new ConsoleInterface();
    }


    @Test
    public void glueTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);

        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;

        robot.jump(movement);
        robot.jump(movement);
        robot.jump(movement);
        robot.jump(movement);

        Actor glue = new Glue(new Point(100, 100), 1);
        glue.activateEffectOn(robot);

        Assert.assertEquals(2, robot.getSpeed());
    }

    @Test
    public void oilTest() {
        Robot robot = new Robot(new Point(100, 100), 1, consoleInterface);

        Actor oil = new Oil(new Point(100, 100), 1);
        oil.activateEffectOn(robot);

        Assert.assertEquals(false, robot.getSpeedChangeEnabled());
    }
}
*/