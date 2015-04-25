package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    private static final int START_LIFESPAN = 10;
    private static int SIZE = 10;
    private static Color COLOR = Color.MAGENTA;
    private static int UNIQUE_ID = 0;

    public Oil(Point position) {
        super(position, "OIL_" + ++UNIQUE_ID, SIZE, COLOR, START_LIFESPAN);
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
