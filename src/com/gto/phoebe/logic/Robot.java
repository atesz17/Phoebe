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

    public Robot(Point position, int size, Color color, String name, Level level, UserInterface userInterface) {
        super(position, name, size, color);
        this.level = level;
        setDirection(new Point(position.x, position.y + 1));
        this.userInterface = userInterface;
    }

    public double getAngleInRad() {
        return Math.atan2(direction.y - position.y, direction.x - position.x);
    }

    protected Point translate(int speed, double angleInRad) {
        int newX = (int) Math.round(speed * Math.cos(angleInRad));
        int newY = (int) Math.round(speed * Math.sin(angleInRad));

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
