package com.gto.phoebe.logic;

import java.awt.*;

public class TrapInventory {

    public int oilCount;
    public int glueCount;

    public TrapInventory() {
        oilCount = 1;
        glueCount = 1;
    }

    public TrapInventory(int oilCount, int glueCount) throws Exception {
        if (oilCount < 0 || glueCount < 0) {
            throw new Exception();
        }
        this.oilCount = oilCount;
        this.glueCount = glueCount;
    }

    public Oil getOil(Point position) {
        if (oilCount > 0)  {
            oilCount -= 1;
            return new Oil(position);
        } else  {
            return null;
        }
    }

    public Glue getGlue(Point position)   {
        if (glueCount > 0)  {
            glueCount -= 1;
            return new Glue(position);
        } else  {
            return null;
        }
    }

    public void reloadTraps()   {
        oilCount = 1;
        glueCount = 1;
    }
}
