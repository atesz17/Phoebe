package test;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.logic.*;
import com.gto.phoebe.util.PhoebeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;

public class TrapTest {

    private TestInterface userInterface;
    private Level level;

    @Before
    public void setUp() throws PhoebeException {
        userInterface = new TestInterface();
        InputStream map = ClassLoader.getSystemResourceAsStream("resources/map.bmp");
        level = new Level(1, map, userInterface);
    }


    @Test
    public void glueTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);

        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;
        userInterface.setMovement(movement);

        robot.jump();
        robot.jump();
        robot.jump();
        robot.jump();

        Actor glue = new Glue(new Point(100, 100));
        glue.steppedOnBy(robot);

        Assert.assertEquals(2, robot.getSpeed());
    }

    @Test
    public void oilTest() {
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);

        Actor oil = new Oil(new Point(100, 100));
        oil.steppedOnBy(robot);

        Assert.assertEquals(false, robot.getSpeedChangeEnabled());
    }

    @Test
    public void glueDisappearsAfterFourJumps(){
        TrapperRobot robot = new TrapperRobot(new Point(100, 100), "Bela", level, userInterface);

        Movement movement = new Movement();
        movement.speedChange = 1;
        movement.angleChange = 90;
        userInterface.setMovement(movement);
        robot.jump();

        Actor glue = new Glue(new Point(100, 100));
        glue.steppedOnBy(robot);
        glue.steppedOnBy(robot);
        glue.steppedOnBy(robot);
        glue.steppedOnBy(robot);

        Assert.assertTrue(glue.isDead());
    }
    @Test
    public void oilDisappearsAfterTenTurns(){

        Trap oil = new Oil(new Point(100, 100));
        for(int i = 0; i < 10; i++){
            oil.age();
        }

        Assert.assertTrue(oil.isDead());
    }

}