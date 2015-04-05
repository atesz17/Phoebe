package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Trap extends Actor {
    protected int lifeSpan;
    protected int cleaningTime = 2;

    public Trap() {
        super();
    }

    public Trap(Point position, int size, int lifeSpan) {
        super(position, size);
        this.lifeSpan = lifeSpan;
    }

    public void cleaning() {
        cleaningTime--;
        if (cleaningTime == 0) {
            die();
        }
    }

    public abstract void age();
}
