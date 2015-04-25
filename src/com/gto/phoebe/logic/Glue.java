package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Trap {

    private static final int START_LIFESPAN = 4;
    private static int SIZE = 10;
    private static Color COLOR = Color.GREEN;
    private static int UNIQUE_ID = 0;

    public Glue(Point position)    {
        super(position, "GLUE_" + ++UNIQUE_ID, SIZE, COLOR, START_LIFESPAN);
    }

    @Override
    public void turn() {

    }

    @Override
    public void effect(Robot robot) {
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
        if(0 == --lifeSpan){
            die();
        }
    }
}
