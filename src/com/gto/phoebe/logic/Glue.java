package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Trap {

    public Glue()    {
        super();
    }

    public Glue(Point position, int size)    {
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
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
    }
}
