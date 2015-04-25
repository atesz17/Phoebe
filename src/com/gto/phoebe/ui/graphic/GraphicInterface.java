package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;
import com.gto.phoebe.ui.UserInterface;

public class GraphicInterface implements UserInterface {

    private GamePanel gamePanel;

    public GraphicInterface(GamePanel gamePanel) {

    }

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
