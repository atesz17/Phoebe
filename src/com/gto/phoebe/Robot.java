package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Robot extends Actor {

    private boolean speedChangeEnabled;
    private int speed;
    private Point direction;
    private double totalDistanceTraveled;
    private TrapInventory trapInventory;
    private boolean isDead;

    public Robot()  {
        super();
        speedChangeEnabled = true;
        speed = 0;
        direction = new Point(0, 0);
        totalDistanceTraveled = 0.0;
        trapInventory = new TrapInventory();
        isDead = false;
    }

    public Robot(Point position, int size)  {
        super(position, size);
        speedChangeEnabled = true;
        speed = 0;
        direction = new Point(0, 0);
        totalDistanceTraveled = 0.0;
        trapInventory = new TrapInventory();
        isDead = false;
    }

    public void jump(Point position)    {
        totalDistanceTraveled += position.distance(getPosition());
        setPosition(position);
    }

    public void die()   {
        totalDistanceTraveled = 0.0;
        isDead = true;
    }

    @Override
    public void activateEffectOn(Robot robot)   {

    }

    public Oil dropOil()    {
        return trapInventory.getOil();
    }

    public Glue dropGlue()  {
        return trapInventory.getGlue();
    }

    public void reloadTraps()   {
        trapInventory.reloadTraps();
    }

    public boolean getSpeedChangeEnabled()    {
        return speedChangeEnabled;
    }

    public void setSpeedChangeEnabled(boolean enabled) {
        speedChangeEnabled = enabled;
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
