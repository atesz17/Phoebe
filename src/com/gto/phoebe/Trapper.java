package com.gto.phoebe;

import java.awt.*;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Trapper extends Robot {

    private TrapInventory trapInventory;

    public Trapper()   {
        super();
        trapInventory = new TrapInventory();
    }

    public Trapper(Point position, int size)    {
        super(position, size);
        trapInventory = new TrapInventory();
    }

    public Oil dropOil()    {
        return trapInventory.getOil();
    }

    public Glue dropGlue()  {
        return trapInventory.getGlue();
    }

    public void reloadTraps()   {
        trapInventory.reloadTraps();
    }
}
