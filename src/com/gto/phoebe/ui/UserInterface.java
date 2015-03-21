package com.gto.phoebe.ui;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.Trap;
import com.gto.phoebe.logic.Robot;

public interface UserInterface {
    public Movement getMovementInput(Robot robot);
    public void print(String message);
    public Trap getTrapInput(Robot robot);
}
