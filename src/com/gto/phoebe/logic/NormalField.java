package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class NormalField extends Actor {

    public NormalField() {
        super();
    }

    public NormalField(Point position, int size) {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot) {
        Skeleton.methodCall("activateEffectOn(robot)");
        Skeleton.methodReturn("void");
    }
}
