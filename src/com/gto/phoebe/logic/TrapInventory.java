package com.gto.phoebe.logic;

import java.awt.*;

public class TrapInventory {

    // a ragacsfoltok maximális száma
    private static int MAX_GLUE = 1;
    // az olajfoltok maximális száma
    private static int MAX_OIL = 1;

    // az olajfoltok száma
    private int oilCount;
    // a ragacsfoltok száma
    private int glueCount;

    /**
     * Paraméter nélküli konstruktor, beállítja mindkét csapda számát 1-re.
     */
    public TrapInventory() {
        oilCount = 1;
        glueCount = 1;
    }

    /**
     * Konstruktor paraméterekkel, beállítja a megadott paraméterekkel a csapdák számát.
     * @param oilCount olajfoltok száma
     * @param glueCount ragacsfoltok száma
     */
    public TrapInventory(int oilCount, int glueCount) throws Exception {
        if (oilCount < 0 || glueCount < 0) {
            throw new Exception();
        }
        this.oilCount = oilCount;
        this.glueCount = glueCount;
    }

    /**
     * Visszaadja a megadott pozíción újonnan létrehozott olajfoltot, ha le tud még rakni a robot.
     * @param position megadott pozíció
     * @return
     */
    public Oil getOil(Point position) {
        if (oilCount > 0)  {
            oilCount -= 1;
            return new Oil(position);
        } else  {
            return null;
        }
    }

    /**
     * Visszaadja a megadott pozíción újonnan létrehozott ragacsfoltot, ha le tud még rakni a robot.
     * @param position megadott pozíció
     * @return
     */
    public Glue getGlue(Point position)   {
        if (glueCount > 0)  {
            glueCount -= 1;
            return new Glue(position);
        } else  {
            return null;
        }
    }

    /**
     * Beállítja a csapdák számát a maximálisra, vagyis újratölt.
     */
    public void reloadTraps()   {
        oilCount = MAX_OIL;
        glueCount = MAX_GLUE;
    }

    /**
     * Visszaadja az olajfoltok számát.
     * @return
     */
    public int getOilCount() {
        return oilCount;
    }

    /**
     * Visszaadja a ragacsfoltok számát.
     * @return
     */
    public int getGlueCount() {
        return glueCount;
    }
}
