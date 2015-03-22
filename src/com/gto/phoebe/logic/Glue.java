package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class Glue extends Actor {

    public Glue()    {
        super();
        Skeleton.createObject("glue");
    }

    public Glue(Point position, int size)    {
        super(position, size);
        Skeleton.createObject("glue");
    }

    @Override
    public void activateEffectOn(Robot robot) {
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
    }
}
