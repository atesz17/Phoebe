package prototype;

/**
 * Created by atesz17 on 4/18/2015.
 */
public class TestRunner {

    private String[] testNames = null;

    public TestRunner(String[] allTests)   {
        this.testNames = allTests;
    }

    public void executeAndExportTests() {
        int i = 0;
        while(i < testNames.length) {
            PrototypeInterface prototypeInterface = new PrototypeInterface();
            prototypeInterface.executeCommand("tesztsorozatbeolvas " + testNames[i]);
            String resultName = testNames[i];
            resultName = resultName.substring(0, resultName.length()-4); // levagjuk a .txt-t
            prototypeInterface.executeCommand("tesztkimenetmentese " + resultName + "_result.txt");
            i++;
        }
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("All tests have been executed and exported.");
    }
}
