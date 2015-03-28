package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.Actor;
import com.gto.phoebe.logic.Glue;
import com.gto.phoebe.logic.Oil;
import com.gto.phoebe.logic.TrapperRobot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TrapTest {

    TestInterface userInterface;

    @Before
    public void setUp(){
        userInterface = new TestInterface();
    }


    @Test
    public void glueTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);

        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;
        userInterface.setMovement(movement);

        robot.jump();
        robot.jump();
        robot.jump();
        robot.jump();

        Actor glue = new Glue(new Point(100, 100), 1);
        glue.collideWith(robot);

        Assert.assertEquals(2, robot.getSpeed());
    }

    @Test
    public void oilTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", userInterface);

        Actor oil = new Oil(new Point(100, 100), 1);
        oil.collideWith(robot);

        Assert.assertEquals(false, robot.getSpeedChangeEnabled());
    }
}