package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class Oil extends Actor {

    /**
     * Sima konstruktor, ami a sajat ososztalyanak konstruktorat hivja meg. Ekkor a pozicioja
     * es merete is nulla lesz.
     */
    public Oil() {
        super();
        Skeleton.createObject("oil");
    }

    /**
     * Egy konstruktor, ami konkret parametereket var, es arra allitja be a poziciojat es meretet
     * @param position a kivant pozicio
     * @param size a kivant meret
     */
    public Oil(Point position, int size) {
        super(position, size);
        Skeleton.createObject("oil");
    }

    /**
     * Ez a fuggveny hivodik meg akkor, mikor a robot erre a mezore lep. Ennek hatasara a kovetkezo korben nem lesz
     * lehetosege sebesseget valtani.
     * @param robot Ez a robot nem fog tudni a kovetkezo korben sebesseget valtoztatni
     */
    @Override
    public void activateEffectOn(Robot robot) {
        Skeleton.methodCall("activateEffectOn(robot)");
        robot.setSpeedChangeEnabled(false);
        Skeleton.methodReturn("void");
    }
}
