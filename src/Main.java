import com.gto.phoebe.Glue;
import com.gto.phoebe.Level;
import com.gto.phoebe.Oil;
import com.gto.phoebe.Robot;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Main {

    public static void main(String[] args)  {
        /*
        Robot r1 = new Robot();
        Robot r2 = new Robot();
        Oil o1 = new Oil(new Point(1, 2), 1);
        Glue g1 = new Glue(new Point(2, 2), 1);
        Level l = new Level(10, r1, r2);
        l.addTrapToLevel(o1);
        l.addTrapToLevel(g1);
        r1.jump(new Point(1, 2));
        l.checkCollisionOnRobot(r1);
        r2.jump(new Point(2, 2));
        l.checkCollisionOnRobot(r2);
        System.out.print("na mukodik?");
        */

        // INIT
        Robot player1 = new Robot();
        Robot player2 = new Robot();
        Level level = new Level(10, player2, player1);
        Oil o1 = new Oil();
        level.addTrapToLevel(o1);

        //GAME
        while(level.getRemainingTurns() != 0)   {
            for (int i=0; i < 2; i++)  {
                Robot robot = level.getRobot(i);
                robot.jump(new Point(1, 2));
                level.checkCollisionOnRobot(robot);
            }
            level.setRemainingTurns(level.getRemainingTurns()-1);
        }

        // VEGE
        System.out.print(level.getWinner());
    }
}
