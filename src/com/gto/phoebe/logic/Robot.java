package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public abstract class Robot extends Actor {

    protected boolean speedChangeEnabled = true;
    protected int speed = 0;
    protected Point direction;
    protected double totalDistanceTraveled = 0D;
    protected boolean isDead = false;
    protected UserInterface userInterface;
    protected String name = "";

    public Robot() {
        super();
    }

    public Robot(Point position, int size, String name, UserInterface userInterface) {
        super(position, size);
        setDirection(new Point(position.x, position.y + 1));
        this.userInterface = userInterface;
    }

    abstract public void jump();

    abstract public void die();

    public boolean getSpeedChangeEnabled() {
        return speedChangeEnabled;
    }

    public void setSpeedChangeEnabled(boolean enabled) {
        speedChangeEnabled = enabled;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public double getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    public boolean isDead() {
        return isDead;
    }

    public abstract void turn(Level level);

    public abstract void collideWith(Actor actor);
}
