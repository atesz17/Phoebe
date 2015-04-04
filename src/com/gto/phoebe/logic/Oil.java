package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    private static final int START_LIFESPAN = 10;
    private static int SIZE = 10;

    public Oil() {
        super();
    }

    public Oil(Point position) {
        super(position, SIZE, START_LIFESPAN);
    }

    @Override
    public void age() {
        if(0 == --lifeSpan){
            isDead = true;
        }
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
        robot.setSpeedChangeEnabled(false);
    }
}
