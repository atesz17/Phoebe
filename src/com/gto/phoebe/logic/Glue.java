package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class Glue extends Actor {

    /**
     * Sima konstruktor, ami a sajat ososztalyanak konstruktorat hivja meg. Ekkor a pozicioja
     * es merete is nulla lesz.
     */
    public Glue()    {
        super();
        Skeleton.createObject("glue");
    }

    /**
     * Egy konstruktor, ami konkret parametereket var, es arra allitja be a poziciojat es meretet
     * @param position a kivant pozicio
     * @param size a kivant meret
     */
    public Glue(Point position, int size)    {
        super(position, size);
        Skeleton.createObject("glue");
    }

    /**
     * Ez a fuggveny fog meghivodni akkor, mikor a robot ralep erre a mezore. Ekkor a robot a kovetkezo korben csak
     * fele akkora sebesseggel tud majd tovabbhaladni
     * @param robot Ennek a robotnak lesz megfelezve a sebessege
     */
    @Override
    public void activateEffectOn(Robot robot) {
        Skeleton.methodCall("activateEffectOn(robot)");
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
        Skeleton.methodReturn("void");
    }
}
