package com.gto.phoebe.logic;

import java.awt.*;


public class DeathField extends Actor {

    public DeathField() {
        super();
    }

    public DeathField(Point position, int size) {
        super(position, size);
    }

    @Override
    public void steppedOnBy(TrapperRobot robot) {
        robot.die();
    }

    @Override
    public void steppedOnBy(CleanerRobot robot) {
        robot.die();
    }
}
