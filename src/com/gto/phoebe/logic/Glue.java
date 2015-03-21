package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Actor {

    public Glue()    {
        super();
    }

    public Glue(Point position, int size)    {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot) {
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
    }
}
