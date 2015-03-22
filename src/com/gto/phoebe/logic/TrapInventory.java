package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

public class TrapInventory {

    private int oilCount;
    private int glueCount;

    public TrapInventory() {
        oilCount = 0;
        glueCount = 0;
    }

    public TrapInventory(int oilCount, int glueCount) throws Exception {
        if (oilCount < 0 || glueCount < 0) {
            throw new Exception();
        }
        this.oilCount = oilCount;
        this.glueCount = glueCount;
    }

    public Oil getOil() {
        Skeleton.methodCall("getOil()");
        Oil oil = null;
        if (oilCount > 0)  {
            oilCount -= 1;
            oil = new Oil();
        }
        Skeleton.methodReturn("Oil");
        return oil;
    }

    public Glue getGlue()   {
        if (glueCount > 0)  {
            glueCount -= 1;
            return new Glue();
        } else  {
            return null;
        }
    }

    public void reloadTraps()   {
        Skeleton.methodCall("reloadTraps()");
        oilCount = 1;
        glueCount = 1;
        Skeleton.methodReturn("void");
    }
}
