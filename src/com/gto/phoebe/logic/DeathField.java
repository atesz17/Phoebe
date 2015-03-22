package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;


public class DeathField extends Actor {

    /**
     * Sima konstruktor, ami a sajat ososztalyanak konstruktorat hivja meg. Ekkor a pozicioja
     * es merete is nulla lesz.
     */
    public DeathField() {
        super();
    }

    /**
     * Egy konstruktor, ami konkret parametereket var, es arra allitja be a poziciojat es meretet
     * @param position a kivant pozicio
     * @param size a kivant meret
     */
    public DeathField(Point position, int size) {
        super(position, size);
    }

    /**
     * Ez a fuggveny fog meghivodni akkor, amikor a robot tipusu objektum ramegy a Tiltott mezore. Ekkor a robot
     * "meghal", meghivjuk annak die() fuggvenyet
     * @param robot Ez a robot fog kiesni a jatekbol
     */
    @Override
    public void activateEffectOn(Robot robot)   {
        Skeleton.methodCall("activateEffectOn(robot)");
        robot.die();
        Skeleton.methodReturn("void");
    }
}
