package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Trap {

    private static final int START_LIFESPAN = 4;
    private static int SIZE = 10;

    public Glue()    {
        super();
    }

    public Glue(Point position)    {
        super(position, SIZE, START_LIFESPAN);
    }

    @Override
    public void age() {

    }

    @Override
    public void steppedOnBy(TrapperRobot robot) {
        effect(robot);
    }

    @Override
    public void steppedOnBy(CleanerRobot robot) {
        effect(robot);
    }

    public void effect(Robot robot) {
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
        if(0 == --lifeSpan){
            isDead = true;
        }
    }
}
