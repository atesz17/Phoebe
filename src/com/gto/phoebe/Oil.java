package com.gto.phoebe;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Oil implements Trap {

    @Override
    public void activateEffectOn(Robot robot) {
        robot.setRotationEnabled(false);
    }
}
