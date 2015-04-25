package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Trap extends Actor {
    protected int lifeSpan;
    protected int cleaningTime = 2;

    public Trap(Point position, String name, int size, Color color, int lifeSpan) {
        super(position, name, size, color);
        this.lifeSpan = lifeSpan;
    }

    public void cleaning() {
        cleaningTime--;
        if (cleaningTime == 0) {
            die();
        }
    }

    public abstract void effect(Robot robot);

}
