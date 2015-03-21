import com.gto.phoebe.logic.Level;
import com.gto.phoebe.ui.ConsoleInterface;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {

        UserInterface userInterface = new ConsoleInterface();

        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), userInterface);
        level.gameCycle();

    }
}
