package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    private static final int START_LIFESPAN = 10;
    private static int SIZE = 10;
    private static int NUM_OILS = 0;

    public Oil() {
        super();
    }

    public Oil(Point position) {
        super(position, "OIL_" + ++NUM_OILS, SIZE, START_LIFESPAN);
    }

    @Override
    public void turn() {
        if(0 == --lifeSpan){
            die();
        }
    }

    @Override
    public void effect(Robot robot) {
        robot.setSpeedChangeEnabled(false);
    }
}
