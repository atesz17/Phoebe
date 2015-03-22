package com.gto.phoebe.skeleton;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.Trap;
import com.gto.phoebe.logic.Robot;
import com.gto.phoebe.ui.UserInterface;

public class SkeletonInterface implements UserInterface {
    @Override
    public Movement getMovementInput(Robot robot) {
        Movement movement = new Movement();

        switch (Skeleton.testCase) {
            case 1:
                movement.speedChange = 0;
                movement.angleChange = 0;
                break;
            case 2:
                movement.speedChange = 0;
                movement.angleChange = 0;
                break;
            case 3:
                if (Skeleton.turn == 1) {
                    movement.speedChange = 1;
                    movement.angleChange = 0;
                } else {
                    movement.speedChange = 0;
                    movement.angleChange = 0;
                }
                break;
            case 4:
                if (Skeleton.turn == 1) {
                    movement.speedChange = 1;
                    movement.angleChange = 0;
                } else {
                    movement.speedChange = 0;
                    movement.angleChange = 0;
                }
                break;
            case 5:
                movement.speedChange = -100;
                movement.angleChange = 0;
                break;
            case 6:
                movement.speedChange = 1;
                movement.angleChange = 180;
                break;
            case 7:
                movement.speedChange = 1;
                movement.angleChange = 0;
                break;
            case 9:
                if (Skeleton.turn == 1) {
                    movement.speedChange = 10;
                    movement.angleChange = 0;
                } else  {
                    movement.speedChange = 5;
                    movement.angleChange = 0;
                }
                break;
        }

        return movement;
    }

    @Override
    public void print(String message) {

    }

    @Override
    public Trap getTrapInput(Robot robot) {
        Trap trap = Trap.NONE;
        switch (Skeleton.testCase) {
            case 1:
                trap = Trap.OIL;
                break;
            case 2:
                trap = Trap.GLUE;
                break;
        }
        return trap;
    }
}
