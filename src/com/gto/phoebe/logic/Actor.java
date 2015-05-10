package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Actor {

    // Actor pozíciója
    protected Point position;
    // Actor mérete
    protected int size;
    // Actor színe
    protected Color color;
    // Flag, ami megmondja, hogy halott-e az adott actor
    protected boolean isDead;
    // Actor neve
    protected String name = "";

    /**
     * Konstruktor, ami beállítja a pozíciót és a méretet 0-ra.
     */
    public Actor() {
        this.position = new Point(0, 0);
        this.size = 0;
    }

    /**
     * Konstruktor, ami a paraméterek alapján beállítja a pozíciót, méretet, nevet és színt.
     * @param position Kívánt pozíció
     * @param name Kívánt név
     * @param size Kívánt méret
     * @param color Kívánt szín
     */
    public Actor(Point position, String name, int size, Color color) {
        this.position = position;
        this.name = name;
        this.size = size;
        this.color = color;
    }

    /**
     * Ezt a metódust származtatja a Robot és a Trap osztály, ez egy kört bonyolít le.
     * Actort nem lehet példányosítani, ezért abstract.
     */
    public abstract void turn();

    /**
     * Visszaadja az actor pozícióját.
     * @return
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Beállítja az actor pozícióját.
     * @param position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Visszadja az actor méretét.
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Visszaadja az actor színét.
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Beállítja az actor méretét.
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Visszaad egy flaget, ami megmondja, hogy az actor életben van-e.
     * @return
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Beállítja az actor isDead flagjét true-ra, vagyis megöli az actort, ezután eltűnik a pályáról.
     */
    public void die() {
        isDead = true;
    }
}
