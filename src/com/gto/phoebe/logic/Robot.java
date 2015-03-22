package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.skeleton.Skeleton;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class Robot extends Actor {

    // Valtoztathat-e sebesseget a robot
    private boolean speedChangeEnabled = true;
    // Robot sebessege
    private int speed = 0;
    // Robot milyen iranyba nez
    private Point direction;
    // Robot eddig megtett tavolsaga
    private double totalDistanceTraveled = 0D;
    // Robotnak a csapdataroloja
    private TrapInventory trapInventory = new TrapInventory();
    // Robot halott-e
    private boolean isDead = false;
    // Skeletonhoz tartozo user input
    private UserInterface userInterface;

    public Robot()  {
        super();
    }

    /**
     * Robot konstruktora, meghivodik eloszor az Actor() konstruktor, majd beallitjuk, hogy merre nezzen a robot
     * @param position Ezzel a pozicioval fog megegyezni a robot pozicioja
     * @param size Ekkora merete lesz a robotnak, a collisioncheck-nel erdekes
     * @param userInterface Mivel skeletonban nem "hagyomanyos" modon tortenik az inputkeres, ezert ezt is meg kell adni
     */
    public Robot(Point position, int size, UserInterface userInterface)  {
        super(position, size);
        Skeleton.createObject("robot");
        setDirection(new Point(position.x, position.y + 1));
        this.userInterface = userInterface;
    }

    /**
     * Ez a metodus hivodik meg minden egyes kornel. O felelos azert, hogy a robot tudjon poziciot valtaltani. Ebben a
     * fuggvenyben tortenik az uj sebesseg es uj irany megadasa, majd maga a pozicio valtas is.
     */
    public void jump() {
        Skeleton.methodCall("jump()");
        Movement input = userInterface.getMovementInput(this);
        jump(input);
        Skeleton.methodReturn("void");
    }

    /**
     * jump() metodus kiegeszitese, szinten azert van ra szukseg, mert a szkeletonban elore meghatarozott utasitasok
     * tortennek
     * @param movement Ez az elore megadott utasitas
     */
    public void jump(Movement movement) {
        Point newPosition = calculatePosition(movement);
        speed += movement.speedChange;
        direction = new Point((newPosition.x - getPosition().x) + newPosition.x, (newPosition.y - getPosition().y) + newPosition.y);

        totalDistanceTraveled += newPosition.distance(getPosition());
        setPosition(newPosition);
        speedChangeEnabled = true;
    }

    /**
     * Ez a fuggveny felelos azert, hogy a bejovo inputbol (speed es direction), es a jelenlegi poziciobol ki tudjuk
     * szamitan a robot kovetkezo poziciojat
     * @param movement Skeleton input
     * @return
     */
    private Point calculatePosition(Movement movement) {
        int newSpeed = speed + movement.speedChange;
        double newAngle = getAngle() + movement.angleChange;

        int newX = (int) Math.round(newSpeed * Math.cos(newAngle * Math.PI / 180));
        int newY = (int) Math.round(newSpeed * Math.sin(newAngle * Math.PI / 180));

        return new Point(getPosition().x + newX, getPosition().y + newY);
    }

    private double getAngle() {
        int a = Math.abs(getDirection().y - getPosition().y);
        int b = Math.abs(getDirection().x - getPosition().x);
        double c = Math.sqrt(a * a + b * b);
        return Math.asin(a / c) * 180 / Math.PI;
    }

    /**
     * A tiltott mezo ezt a metodusat hivja meg a robotnak, ha ralep. Ekkor vege a jateknak, a robot automatikusan
     * veszitett, tehat lenullazzuk a totalDistaneTraveled-t is, es bebillentjuk az isDead flaget igazra
     */
    public void die()   {
        Skeleton.methodCall("die()");
        totalDistanceTraveled = 0D;
        isDead = true;
        Skeleton.methodReturn("void");
    }

    /**
     * Mivel Actorbol oroklunk, ezert muszaj megvalositani a fuggvenyt, de mivel a jatekban nem kezeljuk le, ha ket
     * robot utkozik egymassal, ezert ez a fuggveny is ures
     * @param robot
     */
    @Override
    public void activateEffectOn(Robot robot)   {

    }

    /**
     * Ha a robot le akar tenni a palyara egy olajat, akkor ezt a fuggvenyt hivja meg
     * @return Az ujonnan letrehozott oil objektumot adjuk vissza
     */
    public Oil dropOil()    {
        Skeleton.methodCall("dropOil()");
        Oil oil = trapInventory.getOil();
        Skeleton.methodReturn("oil");
        return oil;
    }

    /**
     * Ha a robot le akar tenni a palyara egy ragacsot, akkor ezt a fuggvenyt hivja meg
     * @return
     */
    public Glue dropGlue()  {
        Skeleton.methodCall("dropGlue()");
        Glue glue = trapInventory.getGlue();
        Skeleton.methodReturn("glue");
        return glue;
    }

    /**
     * Ez a fuggveny ujratolti a robot csapdakeszletet, ami azt jelenti, hogy 1-1 uj ragacs es olajat hozzatesz
     */
    public void reloadTraps()   {
        Skeleton.methodCall("reloadTraps()");
        trapInventory.reloadTraps();
        Skeleton.methodReturn("void");
    }

    /**
     * Megadja, hogy lehet-e sebesseget valtoztatni a robtnak
     * @return boolean
     */
    public boolean getSpeedChangeEnabled()    {
        return speedChangeEnabled;
    }

    /**
     * Beallitjuk, hogy lehet-e a robotnak sebesseget valtoztatni
     * @param enabled sebesseg valtas engedelyezett-e
     */
    public void setSpeedChangeEnabled(boolean enabled) {
        Skeleton.methodCall("setSpeedChangeEnabled(" + Boolean.toString(enabled) + ")");
        speedChangeEnabled = enabled;
        Skeleton.methodReturn("void");
    }

    /**
     * Visszaadja a robot sebesseget
     * @return robot sebessege
     */
    public int getSpeed()   {
        Skeleton.methodCall("getSpeed");
        Skeleton.methodReturn(Integer.toString(speed));
        return speed;
    }

    /**
     * Beallitja a robot uj sebesseget
     * @param newSpeed a kivant sebesseg
     */
    public void setSpeed(int newSpeed)  {
        Skeleton.methodCall("setSpeed(1)");
        speed = newSpeed;
        Skeleton.methodReturn("void");
    }

    /**
     * Visszaadja, hogy a robot milyen iranyba nez
     * @return Koordinataban, hogy milyen iranyba nez a robot
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Beallitjuk, hogy milyen iranyba nezzen a robot
     * @param direction Kivant irany
     */
    public void setDirection(Point direction)   {
        this.direction = direction;
    }

    /**
     * Megadja, hogy mekkora tavolsagot tett meg eddig a robot
     * @return Tavolsag merteke
     */
    public double getTotalDistanceTraveled()    {
        Skeleton.methodCall("getTotalDistanceTraveled()");
        Skeleton.methodReturn(Double.toString(totalDistanceTraveled));
        return totalDistanceTraveled;
    }

    /**
     * Lekerdezzuk, hogy a robot meghalt-e mar
     * @return halott-e a robot
     */
    public boolean isDead() {
        return isDead;
    }
}
