package com.gto.phoebe;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Glue implements Trap {

    @Override
    public void activateEffectOn(Robot robot) {
        int newSpeed = (int)Math.round(robot.getSpeed()/2.0);
        robot.setSpeed(newSpeed);
    }
}