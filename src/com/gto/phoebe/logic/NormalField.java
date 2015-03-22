package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

import java.awt.*;

public class NormalField extends Actor {

    /**
     * Sima konstruktor, ami a sajat ososztalyanak konstruktorat hivja meg. Ekkor a pozicioja
     * es merete is nulla lesz.
     */
    public NormalField() {
        super();
    }

    /**
     * Egy konstruktor, ami konkret parametereket var, es arra allitja be a poziciojat es meretet
     * @param position a kivant pozicio
     * @param size a kivant meret
     */
    public NormalField(Point position, int size) {
        super(position, size);
    }

    /**
     * Ez egy ures fuggveny. Mivel az osztaly az Actorbol szarmazik, muszaj megvalositania, de mivel nem tortenik
     * semmi akkor, amikor egy robot egy ures mezore lep, ezert a fuggveny is ures
     * @param robot A robot, amelyik az ures mezore lepett
     */
    @Override
    public void activateEffectOn(Robot robot) {
        Skeleton.methodCall("activateEffectOn(robot)");
        Skeleton.methodReturn("void");
    }
}
