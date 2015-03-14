package com.gto.phoebe;

import java.awt.*;

public class Robot extends Actor {

    private boolean speedChangeEnabled = true;
    private int speed = 0;
    private Point direction = new Point(0, 0);
    private double totalDistanceTraveled = 0D;
    private TrapInventory trapInventory = new TrapInventory();
    private boolean isDead = false;

    public Robot()  {
        super();
    }

    public Robot(Point position, int size)  {
        super(position, size);
    }

    public void jump(Point position)    {
        totalDistanceTraveled += position.distance(getPosition());
        setPosition(position);
    }

    public void die()   {
        totalDistanceTraveled = 0D;
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
