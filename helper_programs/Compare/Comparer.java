import java.io.*;

public class Comparer {

    public static void main(String[] args)  {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Reference file name: ");
            String ref = br.readLine();
            System.out.println("Comparable file name: ");
            String comp = br.readLine();

            File refFile = new File(ref);
            File compFile = new File(comp);

            BufferedReader refBr = new BufferedReader(new InputStreamReader(new FileInputStream(refFile)));
            BufferedReader compBr = new BufferedReader(new InputStreamReader(new FileInputStream(compFile)));

            StringBuilder refSb = new StringBuilder();
            StringBuilder compSb = new StringBuilder();

            String refLine = null;
            String compLine = null;
            int lineNumber = 0;
            boolean identical = true;
            while ((refLine = refBr.readLine()) != null)   {
                lineNumber++;
                if ((compLine = compBr.readLine()) != null) {
                    if (!refLine.equals(compLine))  {
                        identical = false;
                        break;
                    }
                } else {
                    identical = false;
                    break;
                }
            }

            String newLine = null;
            if (((newLine = compBr.readLine()) != null) && (identical == true)) {
                compLine = newLine;
                identical = false;
                lineNumber++;
            }

            if (identical)  {
                System.out.println("Test was a success!");
            } else  {
                System.err.println("Test result is not correct. Difference compared to reference file at: " + lineNumber);
                System.err.println("Expected: " + refLine + ", actual: " + compLine);
            }

            refBr.close();
            compBr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)   {
            e.printStackTrace();
        }

    }
}
