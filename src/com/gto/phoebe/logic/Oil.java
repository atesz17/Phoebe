package com.gto.phoebe.logic;

import java.awt.*;

public class Oil extends Trap {

    // olaj kezdő élete
    private static final int START_LIFESPAN = 10;
    // olaj mérete
    private static int SIZE = 40;
    // olaj színe
    private static Color COLOR = Color.MAGENTA;
    // olaj egyedi azonosítója
    private static int UNIQUE_ID = 0;

    /**
     * Konstruktor, ami a Trap konstruktorát hívja meg a megadott pozícióra.
     * @param position megadott pozíció
     */
    public Oil(Point position) {
        super(position, "OIL_" + ++UNIQUE_ID, SIZE, COLOR, START_LIFESPAN);
    }

    /**
     * Az olajnál minden körben csökkenteni kell az életét, majd ha elfogy, akkor megsemmisül.
     */
    @Override
    public void turn() {
        if(0 == --lifeSpan){
            die();
        }
    }

    /**
     * Az olaj hatása: a megadott robotnak nem módosítható a sebessége.
     * @param robot megadott robot
     */
    @Override
    public void effect(Robot robot) {
        robot.setSpeedChangeEnabled(false);
    }
}
