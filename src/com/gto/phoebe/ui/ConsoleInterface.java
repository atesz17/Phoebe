package com.gto.phoebe.ui;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.Trap;
import com.gto.phoebe.logic.Robot;

import java.util.Scanner;

public class ConsoleInterface implements UserInterface{

    public Movement getMovementInput(Robot robot) {
        Movement movement = new Movement();
        Scanner in = new Scanner(System.in);

        movement.speedChange = -2;
        if (robot.getSpeedChangeEnabled()) {
            while (!validateSpeed(movement.speedChange)) {
                System.out.println("Your speed change: (-1, 0, 1)");
                movement.speedChange = in.nextInt();
                if (!validateSpeed(movement.speedChange)) {
                    System.out.println("Invalid value.");
                }
            }
        }

        System.out.println("Your angle change: (in degrees)");
        movement.angleChange = in.nextInt() % 360;

        return movement;
    }

    public void print(String message) {
        System.out.println(message);
    }

    private boolean validateSpeed(int speedChange) {
        return !(speedChange > 1 || speedChange < -1);
    }

    public Trap getTrapInput(Robot robot){
        Scanner in = new Scanner(System.in);
        System.out.println("You can plant traps now.");
        while(true) {
            System.out.println("Type in oil, glue or none");
            String input = in.next();
            if ("oil".equals(input.toLowerCase())) {
                return Trap.OIL;
            } else if ("glue".equals(input.toLowerCase())) {
                return Trap.GLUE;
            } else if ("none".equals(input.toLowerCase())) {
                return Trap.NONE;
            }
        }
    }
}
