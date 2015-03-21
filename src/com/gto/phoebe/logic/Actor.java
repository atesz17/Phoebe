package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Actor {

    private Point position;
    private int size;

    public Actor() {
        this.position = new Point(0, 0);
        this.size = 0;
    }

    public Actor(Point position, int size) {
        this.position = position;
        this.size = size;
    }

    public abstract void activateEffectOn(Robot robot);


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
}
