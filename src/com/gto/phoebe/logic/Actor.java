package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Actor {

    protected Point position;
    protected int size;
    protected Color color;
    protected boolean isDead;
    protected String name = "";

    public Actor() {
        this.position = new Point(0, 0);
        this.size = 0;
    }

    public Actor(Point position, String name, int size, Color color) {
        this.position = position;
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public abstract void turn();

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
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
