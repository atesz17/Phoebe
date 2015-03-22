package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Actor {

    // Ez az ojektum pozicioja
    private Point position;
    // Ez az objektum merete
    private int size;

    /**
     * Konstruktor, ami beallitja a poziciot es a meretet nullara
     */
    public Actor() {
        this.position = new Point(0, 0);
        this.size = 0;
    }

    /**
     * Konstruktor, ami a megadott parameterek alapjan beallitja a poziciot es a meretet
     * @param position Kivant pozicio
     * @param size Kivant merert
     */
    public Actor(Point position, int size) {
        this.position = position;
        this.size = size;
    }

    /**
     * Ez a metodus fog meghivodni a roboton, ha a poziciojuk egyezik az actorral. Mivel Actort nem lehet peldanyositani
     * ezert a fuggveny megirasanak se lett volna sok ertelme, ezert abstract
     * @param robot Az a robot, amelyiknek a pozicioja megegyezik az Actor-eval
     */
    public abstract void activateEffectOn(Robot robot);


    /**
     * Visszaadja a actor poziciojat
     * @return actor pozicioja
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Beallitja a actor poziciojat
     * @param position kivant pozicio
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Visszaadja az actor meretet
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Beallitja az actor meretet
     * @param size Kivant meret
     */
    public void setSize(int size) {
        this.size = size;
    }
}
