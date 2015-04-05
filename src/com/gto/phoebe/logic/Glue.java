package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Trap {

    private static final int START_LIFESPAN = 4;
    private static int SIZE = 10;
    private static int NUM_GLUES = 0;

    public Glue()    {
        super();
    }

    public Glue(Point position)    {
        super(position, "GLUE_" + ++NUM_GLUES, SIZE, START_LIFESPAN);
    }

    public Glue(Point position, String name)    {
        super(position, name, SIZE, START_LIFESPAN);
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
