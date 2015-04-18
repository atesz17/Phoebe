import java.io.*;

public class Comparer {

    public static void main(String[] args)  {

        if (args.length != 2)   {
            System.err.print("Not valid input, param1 = reference file name, param2 = comparable file name");
        } else  {
            File refFile = new File(args[0]);
            File compFile = new File(args[1]);

            try {
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
}
