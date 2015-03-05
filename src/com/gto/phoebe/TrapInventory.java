package com.gto.phoebe;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class TrapInventory {

    private int oilCount;
    private int glueCount;

    public TrapInventory() {
        oilCount = 0;
        glueCount = 0;
    }

    // TODO: mivan ha valaki negativot ad, le kell kezelni
    public TrapInventory(int oilCount, int glueCount)  {
        this.oilCount = oilCount;
        this.glueCount = glueCount;
    }

    public Oil getOil() {
        if (oilCount > 0)  {
            oilCount -= 1;
            return new Oil();
        } else  {
            return null;
        }
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
        oilCount += 1;
        glueCount += 1;
    }
}
