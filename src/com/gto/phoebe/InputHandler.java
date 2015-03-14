package com.gto.phoebe;

import java.util.Scanner;

public class InputHandler {

    public int speedChange = 0;
    public int angleChange = 0;

    public static InputHandler getFromConsole(Robot robot) {
        InputHandler inputHandler = new InputHandler();
        Scanner in = new Scanner(System.in);

        if (robot.getSpeedChangeEnabled()) {
            while (!validateSpeed(inputHandler.speedChange)) {
                System.out.println("Your speed change: (-1, 0, 1)");
                inputHandler.speedChange = in.nextInt();
                if (!validateSpeed(inputHandler.speedChange)) {
                    System.out.println("Invalid value.");
                }
            }
        }

        System.out.println("Your angle change: (in degrees)");
        inputHandler.angleChange = in.nextInt() % 360;

        return inputHandler;
    }

    private static boolean validateSpeed(int speedChange) {
        if (speedChange > 1 || speedChange < -1) {
            return false;
        }
        return true;
    }
}
