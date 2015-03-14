package test;

import com.gto.phoebe.*;
import com.gto.phoebe.Robot;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class TrapTest {


    @Test
    public void glueTest() {
        Robot robot = new Robot(new Point(100, 100), 1);

        InputHandler inputHandler = new InputHandler();
        inputHandler.speedChange = 1;
        inputHandler.angleChange = 90;

        robot.jump(inputHandler);
        robot.jump(inputHandler);
        robot.jump(inputHandler);
        robot.jump(inputHandler);

        Actor glue = new Glue(new Point(100, 100), 1);
        glue.activateEffectOn(robot);

        Assert.assertEquals(2, robot.getSpeed());
    }

    @Test
    public void oilTest() {
        Robot robot = new Robot(new Point(100, 100), 1);

        Actor oil = new Oil(new Point(100, 100), 1);
        oil.activateEffectOn(robot);

        Assert.assertEquals(false, robot.getSpeedChangeEnabled());
    }
}