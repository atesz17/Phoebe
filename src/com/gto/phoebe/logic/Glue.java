package com.gto.phoebe.logic;

import java.awt.*;

public class Glue extends Trap {

    // a ragacs kezdő élete
    private static final int START_LIFESPAN = 4;
    // ragacs mérete
    private static int SIZE = 40;
    // ragacs színe
    private static Color COLOR = Color.BLUE;
    // ragacs egyedi azonosítója
    private static int UNIQUE_ID = 0;

    /**
     * Konstruktor, amely a Trap konstruktorát hívja meg a megadott pozícióra.
     * @param position megadott pozíció
     */
    public Glue(Point position)    {
        super(position, "GLUE_" + ++UNIQUE_ID, SIZE, COLOR, START_LIFESPAN);
    }

    @Override
    public void turn() {

    }

    /**
     * A ragacs hatása a megadott robotra: megfelezi a sebességet. Valamint, ha az élete elfogy, akkor
     * megsemmisül a ragacs.
     * @param robot megadott robot
     */
    @Override
    public void effect(Robot robot) {
        int newSpeed = Math.round(robot.getSpeed() / 2.0f);
        robot.setSpeed(newSpeed);
        if(0 == --lifeSpan){
            die();
        }
    }
}
