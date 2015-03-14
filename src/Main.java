import com.gto.phoebe.Level;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0));
        level.gameCycle();

    }
}
