package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public abstract class Robot extends Actor {

    private boolean rotationEnabled;
    private int speed;
    private Point direction;
    private double totalDistanceTraveled;

    public Robot()  {
        super();
        rotationEnabled = true;
        speed = 0;
        direction = new Point(0, 0);
        totalDistanceTraveled = 0.0;
    }

    public Robot(Point position, int size)  {
        super(position, size);
        rotationEnabled = true;
        speed = 0;
        direction = new Point(0, 0);
        totalDistanceTraveled = 0.0;
    }

    public void jump(Point position)    {
        totalDistanceTraveled += position.distance(getPosition());
        setPosition(position);
    }

    public boolean getRotationEnabled()    {
        return rotationEnabled;
    }

    public void setRotationEnabled(boolean enabled) {
        rotationEnabled = enabled;
    }

    public int getSpeed()   {
        return speed;
    }

    public void setSpeed(int newSpeed)  {
        speed = newSpeed;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction)   {
        this.direction = direction;
    }

    public double getTotalDistanceTraveled()    {
        return totalDistanceTraveled;
    }
}
