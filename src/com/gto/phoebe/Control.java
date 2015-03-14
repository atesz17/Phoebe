package com.gto.phoebe;

import java.util.Scanner;

public class Control {

    public int speedChange;
    public int angleChange;

    public static Control getInputFromConsole(Robot robot) {
        Control control = new Control();
        Scanner in = new Scanner(System.in);

        control.speedChange = -2;
        if (robot.getSpeedChangeEnabled()) {
            while (!validateSpeed(control.speedChange)) {
                System.out.println("Your speed change: (-1, 0, 1)");
                control.speedChange = in.nextInt();
                if (!validateSpeed(control.speedChange)) {
                    System.out.println("Invalid value.");
                }
            }
        }

        System.out.println("Your angle change: (in degrees)");
        control.angleChange = in.nextInt() % 360;

        return control;
    }

    public static void printToConsole(String message) {
        System.out.println(message);
    }

    private static boolean validateSpeed(int speedChange) {
        return !(speedChange > 1 || speedChange < -1);
    }
}
