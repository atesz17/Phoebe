package com.gto.phoebe;

import java.awt.*;

public class Oil extends Actor {

    public Oil() {
        super();
    }

    public Oil(Point position, int size) {
        super(position, size);
    }

    @Override
    public void activateEffectOn(Robot robot) {
        robot.setSpeedChangeEnabled(false);
    }
}
