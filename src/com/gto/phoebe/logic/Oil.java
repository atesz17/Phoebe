package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    private static final int START_LIFESPAN = 10;
    private static int SIZE = 10;
    private static int UNIQUE_ID = 0;

    public Oil(Point position) {
        super(position, "OIL_" + ++UNIQUE_ID, SIZE, START_LIFESPAN);
    }

    public Oil(Point position, String name) {
        super(position, name, SIZE, START_LIFESPAN);
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

    @Override
    public String getInfo(){
        String ret = "";
        ret += "Tipusa: Olaj \n";
        ret += "Pozicioja: (" + position.x + "," + position.y + ") \n";
        ret += "Ennyi ido mulva szarad fel: " + lifeSpan;

        return ret;
    }
}
