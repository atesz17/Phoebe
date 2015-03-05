package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public abstract class Trap extends Actor {

    public Trap() {
        super();
    }

    public Trap(Point position, int size)   {
        super(position, size);
    }

    public abstract void activateEffectOn(Robot robot);
}
