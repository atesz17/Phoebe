import com.gto.phoebe.logic.Level;
import com.gto.phoebe.ui.ConsoleInterface;
import com.gto.phoebe.ui.UserInterface;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        UserInterface userInterface = new ConsoleInterface();

        InputStream map = ClassLoader.getSystemResourceAsStream("resources/map.bmp");
        Level level = new Level(100, map, userInterface);
        level.startGame();

    }
}
