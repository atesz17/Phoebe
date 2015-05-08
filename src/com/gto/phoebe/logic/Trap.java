package com.gto.phoebe.logic;

import java.awt.*;

public abstract class Trap extends Actor {
    // a csapda élete, eddig marad a pályán
    protected int lifeSpan;
    // a csapda tisztításához szükséges idő
    protected int cleaningTime = 2;

    /**
     * Konstruktor, ami az Actor konstruktorát hívja meg. Valamint a csapda életét beállítja.
     * @param position
     * @param name
     * @param size
     * @param color
     * @param lifeSpan a csapda élete
     */
    public Trap(Point position, String name, int size, Color color, int lifeSpan) {
        super(position, name, size, color);
        this.lifeSpan = lifeSpan;
    }

    /**
     * A csapda tisztítása. Ha letelik az idő, akkor megsemmisül a csapda.
     */
    public void cleaning() {
        cleaningTime--;
        if (cleaningTime == 0) {
            die();
        }
    }

    /**
     * Ha a megadott robot belelép egy csapdába, annak hatása lesz a robotra. Absztrakt metódus,
     * mivel a konkrét csapdatípusoknál van definiálva.
     * @param robot megadott robot
     */
    public abstract void effect(Robot robot);

}
