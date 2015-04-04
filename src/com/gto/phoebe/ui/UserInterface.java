package com.gto.phoebe.ui;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;

public interface UserInterface {
    Movement getMovementInput(TrapperRobot robot);
    void print(String message);
    TrapTypes getTrapInput(TrapperRobot robot);
}
