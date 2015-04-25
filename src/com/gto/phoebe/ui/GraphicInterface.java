package com.gto.phoebe.ui;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;

public class GraphicInterface implements UserInterface {

    @Override
    public Movement getMovementInput(TrapperRobot robot) {
        return null;
    }

    @Override
    public void print(String message) {

    }

    @Override
    public TrapTypes getTrapInput(TrapperRobot robot) {
        return null;
    }
}
