package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/8/2015.
 */
public class DeathField extends Actor {

    public DeathField() {
        super();
    }

    public DeathField(Point position, int size) {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot)   {
        robot.die();
    }
}
