package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;


public class DeathField extends Actor {

    public DeathField() {
        super();
    }

    public DeathField(Point position, int size) {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot)   {
        Skeleton.methodCall("activateEffectOn(robot)");
        robot.die();
        Skeleton.methodReturn("void");
    }
}
