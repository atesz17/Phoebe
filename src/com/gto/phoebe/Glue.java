package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Glue extends Trap {

    public Glue()    {
        super();
    }

    public Glue(Point position, int size)    {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot) {
        int newSpeed = (int)Math.round(robot.getSpeed()/2.0);
        robot.setSpeed(newSpeed);
    }
}
