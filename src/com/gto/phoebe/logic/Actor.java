package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Actor {

    protected Point position;
    protected int size;
    protected boolean isDead;

    public Actor() {
        this.position = new Point(0, 0);
        this.size = 0;
    }

    public Actor(Point position, int size) {
        this.position = position;
        this.size = size;
    }

    public abstract void steppedOnBy(TrapperRobot robot);

    public abstract void steppedOnBy(CleanerRobot robot);

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDead() {
        return isDead;
    }

    public void die() {
        isDead = true;
    }
}
