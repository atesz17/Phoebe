package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public abstract class Robot extends Actor {

    // Változtathat-e sebességet a robot
    protected boolean speedChangeEnabled = true;
    // Robot sebessége
    protected int speed = 0;
    // Robot milyen irányba néz
    protected Point direction;
    // Robot megtett távolsága
    protected double totalDistanceTraveled = 0D;
    // Robot halott-e
    protected boolean isDead = false;
    // Felhasználói input
    protected UserInterface userInterface;
    // A pályát működtető logika
    protected Level level;

    /**
     * Konstruktor, először meghívódik az actor konstruktora, majd beállítjuk milyen irányba nézzen a robot.
     * @param position robot pozíció
     * @param size robot mérete
     * @param color robot színe
     * @param name robot neve
     * @param level pálya
     * @param userInterface felhasználói input
     */
    public Robot(Point position, int size, Color color, String name, Level level, UserInterface userInterface) {
        super(position, name, size, color);
        this.level = level;
        setDirection(new Point(position.x, position.y + 1));
        this.userInterface = userInterface;
    }

    /**
     * Visszaadja az irányt radiánban
     * @return
     */
    public double getAngleInRad() {
        return Math.atan2(direction.y - position.y, direction.x - position.x);
    }

    /**
     * A megadott paraméterekből visszadja a megfelelő pozíciót, ahova lép a robot.
     * @param speed robot sebessége
     * @param angleInRad robot iránya
     * @return
     */
    protected Point translate(int speed, double angleInRad) {
        int newX = (int) Math.round(speed * Math.cos(angleInRad));
        int newY = (int) Math.round(speed * Math.sin(angleInRad));

        return new Point(position.x + newX, position.y + newY);
    }

    abstract public void jump();

    /**
     * Visszaadja azt a flaget, ami megmondja hogy változtathat-e sebességet a robot
     * @return
     */
    public boolean getSpeedChangeEnabled() {
        return speedChangeEnabled;
    }

    /**
     * Beállítja azt a flaget, ami megmondja hogy változtathat-e sebességet a robot
     * @param enabled
     */
    public void setSpeedChangeEnabled(boolean enabled) {
        speedChangeEnabled = enabled;
    }

    /**
     * Visszadja a sebességet
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Beállítja a sebességet
     * @param newSpeed
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Visszaadja az irányt
     * @return
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Beállítja az irányt
     * @param direction
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }


    /**
     * Visszaadja a megtett távolságot
     * @return
     */
    public double getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    public abstract void steppedOnBy(TrapperRobot robot);

    public abstract void steppedOnBy(CleanerRobot robot);

    public abstract void collideWith(Robot robot);

}
