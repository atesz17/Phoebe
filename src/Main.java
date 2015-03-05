import com.gto.phoebe.Robot;
import com.gto.phoebe.Trapper;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Main {

    public static void main(String[] asd)  {
        Trapper t = new Trapper();
        System.out.println(t.dropGlue());
        t.reloadTraps();
        System.out.println(t.dropGlue());
        System.out.println(t.dropGlue());
        t.jump(new Point(2, 0));
        t.jump(new Point(-2, 0));
        t.jump(new Point(0, 3));
    }
}
