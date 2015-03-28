package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    public Oil() {
        super();
    }

    public Oil(Point position, int size) {
        super(position, size);
    }

    @Override
    public void collideWith(TrapperRobot robot) {
        effect(robot);
    }

    @Override
    public void collideWith(CleanerRobot robot) {
        effect(robot);
    }

    public void effect(Robot robot) {
        robot.setSpeedChangeEnabled(false);
    }
}
