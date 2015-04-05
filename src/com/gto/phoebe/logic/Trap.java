package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Trap extends Actor {
    protected int lifeSpan;
    protected int cleaningTime = 2;

    public Trap() {
        super();
    }

    public Trap(Point position, String name, int size, int lifeSpan) {
        super(position, name, size);
        this.lifeSpan = lifeSpan;
    }

    public void cleaning() {
        cleaningTime--;
        if (cleaningTime == 0) {
            isDead = true;
        }
    }

    public abstract void effect(Robot robot);

}
