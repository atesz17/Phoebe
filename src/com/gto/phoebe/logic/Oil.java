package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class Oil extends Actor {

    public Oil() {
        super();
        Skeleton.createObject("oil");
    }

    public Oil(Point position, int size) {
        super(position, size);
        Skeleton.createObject("oil");
    }

    @Override
    public void activateEffectOn(Robot robot) {
        Skeleton.methodCall("activateEffectOn(robot)");
        robot.setSpeedChangeEnabled(false);
        Skeleton.methodReturn("void");
    }
}
