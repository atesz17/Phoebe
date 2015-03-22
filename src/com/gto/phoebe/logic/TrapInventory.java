package com.gto.phoebe.logic;

import com.gto.phoebe.skeleton.Skeleton;

public class TrapInventory {

    // Mennyi olaj van a taroloban
    private int oilCount;
    // Mennyi ragacs van a taroloban
    private int glueCount;

    /**
     * Konstruktor, ami az olaj es ragacsok szamat beallitja 1-1-re
     */
    public TrapInventory() {
        oilCount = 1;
        glueCount = 1;
    }

    /**
     * Konstuktor, ami a kivant ertekek szerint beallitja az olajok es ragacsok szamat. Negativ ertek nem lehet.
     * @param oilCount az olaj darabja
     * @param glueCount a ragacs darabja
     * @throws Exception akkor dobja, ha negativ erteket adtunk meg
     */
    public TrapInventory(int oilCount, int glueCount) throws Exception {
        if (oilCount < 0 || glueCount < 0) {
            throw new Exception();
        }
        this.oilCount = oilCount;
        this.glueCount = glueCount;
    }

    /**
     * Metodus, ami visszaad egy oil tipusu objektumot, ha a taroloban a szamuk nagyobb, mint 1. Ha nem, null-t adunk
     * vissza
     * @return oil tipusu objektum
     */
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

    /**
     * Metodus, ami visszaad egy glue tipusu objektumot, ha a taroloban a szamuk nagyobb, mint 1. Ha nem, null-t adunk
     * vissza
     * @return glue tipusu objektum
     */
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

    /**
     * Ha ezt a fuggvenyt meghivjuk, az olaj es ragacsok szama 1-1 lesz
     */
    public void reloadTraps()   {
        Skeleton.methodCall("reloadTraps()");
        oilCount = 1;
        glueCount = 1;
        Skeleton.methodReturn("void");
    }
}
