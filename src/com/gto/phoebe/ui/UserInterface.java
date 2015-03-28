package com.gto.phoebe.ui;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;

public interface UserInterface {
    public Movement getMovementInput(TrapperRobot robot);
    public void print(String message);

    public TrapTypes getTrapInput(TrapperRobot robot);
}
