package com.gto.phoebe;

import java.awt.*;

public class Robot extends Actor {

    private boolean speedChangeEnabled = true;
    private int speed = 0;
    private Point direction;
    private double totalDistanceTraveled = 0D;
    private TrapInventory trapInventory = new TrapInventory();
    private boolean isDead = false;

    public Robot()  {
        super();
    }

    public Robot(Point position, int size)  {
        super(position, size);
        setDirection(new Point(position.x, position.y + 1));
    }

    public void jump() {
        Control input = Control.getInputFromConsole(this);
        jump(input);
    }

    public void jump(Control input) {
        Point newPosition = calculatePosition(input);
        speed += input.speedChange;
        direction = new Point((newPosition.x - getPosition().x) + newPosition.x, (newPosition.y - getPosition().y) + newPosition.y);

        totalDistanceTraveled += newPosition.distance(getPosition());
        setPosition(newPosition);
        speedChangeEnabled = true;
    }

    private Point calculatePosition(Control input) {
        int newSpeed = speed + input.speedChange;
        double newAngle = getAngle() + input.angleChange;

        int newX = (int) Math.round(newSpeed * Math.cos(newAngle));
        int newY = (int) Math.round(newSpeed * Math.sin(newAngle));

        return new Point(getPosition().x + newX, getPosition().y + newY);
    }

    private double getAngle() {
        int a = Math.abs(getDirection().y - getPosition().y);
        int b = Math.abs(getDirection().x - getPosition().x);
        double c = Math.sqrt(a * a + b * b);
        return Math.asin(a / c);
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

    public boolean isDead() {
        return isDead;
    }
}
