package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

public class TrapInventory {

    private int oilCount;
    private int glueCount;

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

    public Oil getOil() {
        Skeleton.methodCall("getOil()");
        Oil oil = null;
        if (oilCount > 0)  {
            oilCount -= 1;
            oil = new Oil();
        }
        Skeleton.methodReturn("oil");
        return oil;
    }

    public Glue getGlue()   {
        Skeleton.methodCall("getGlue()");
        Glue glue = null;
        if (glueCount > 0)  {
            glueCount -= 1;
            glue = new Glue();
        }
        Skeleton.methodReturn("glue");
        return glue;
    }

    public void reloadTraps()   {
        Skeleton.methodCall("reloadTraps()");
        oilCount = 1;
        glueCount = 1;
        Skeleton.methodReturn("void");
    }
}
