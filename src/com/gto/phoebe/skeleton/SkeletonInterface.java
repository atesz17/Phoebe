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
        }
        return trap;
    }
}
