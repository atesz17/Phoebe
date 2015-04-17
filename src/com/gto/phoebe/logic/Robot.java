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
    protected Level level;

    public Robot() {
        super();
    }

    public Robot(Point position, int size, String name, Level level, UserInterface userInterface) {
        super(position, name, size);
        this.level = level;
        setDirection(new Point(position.x, position.y + 1));
        this.userInterface = userInterface;
    }

    protected double getAngle() {
        int a = Math.abs(direction.y - position.y);
        int b = Math.abs(direction.x - position.x);
        double c = Math.sqrt(a * a + b * b);
        return Math.asin(a / c) * 180 / Math.PI;
    }

    protected Point translate(int speed, double angle) {
        int newX = (int) Math.round(speed * Math.cos(angle * Math.PI / 180));
        int newY = (int) Math.round(speed * Math.sin(angle * Math.PI / 180));

        return new Point(position.x + newX, position.y + newY);
    }

    abstract public void jump();

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

    public abstract void steppedOnBy(TrapperRobot robot);

    public abstract void steppedOnBy(CleanerRobot robot);

    public abstract void collideWith(Robot robot);

}
