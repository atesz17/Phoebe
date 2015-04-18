import prototype.PrototypeInterface;
import prototype.TestRunner;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            PrototypeInterface prototypeInterface = new PrototypeInterface();
            prototypeInterface.readCommand();
        } else {
            TestRunner tr = new TestRunner(args);
            tr.executeAndExportTests();
        }
    }
}
