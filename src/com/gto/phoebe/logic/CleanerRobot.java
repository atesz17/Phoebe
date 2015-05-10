package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class CleanerRobot extends Robot {

    // cleaner robot mérete, ez adott
    private static int SIZE = 10;
    // cleaner robot színe, ez adott
    private static Color COLOR = Color.LIGHT_GRAY;
    // cleaner robot sebessége, adott
    private static int SPEED = 10;
    // cleaner robot azonosítója
    private static int ID = 0;

    // a cleaner robot által megcélzott csapda
    private Trap target = null;

    /**
     * Konstruktor a CleanerRobotnak, a Robot konstruktora hívódik meg, valamint a sebességet is beállítja.
     * @param position
     * @param level
     * @param userInterface
     */
    public CleanerRobot(Point position, Level level, UserInterface userInterface) {
        super(position, SIZE, COLOR, "cleaner_robot_" + ID++, level, userInterface);
        this.speed = SPEED;
    }

    /**
     * A cleaner robot mozgatását végző metódus. Ehhez tudni kell a közelben lévő csapdák pozícióit,
     * és a legközelebbi csapda felé indul a cleaner.
     */
    @Override
    public void jump() {
        speed = SPEED;
        Point2D targetPosition = new Point2D.Double(target.position.x, target.position.y);
        if(position.distance(targetPosition) < target.getSize()){
            return;
        }

        Point newPosition = null;
        for(int i = 0; i < 6; i++){
            newPosition = translate(speed, getAngleInRad() + i * 60);
            if(level.isValidField(newPosition)){
                break;
            }
        }
        System.out.println("position " + position + " new position " + newPosition + " target " + targetPosition.toString());
        position = newPosition;
    }

    /**
     * A cleaner robot elpusztításáért felelős metódus, a cleaner helyén egy olajfoltot hagy.
     */
    @Override
    public void die() {
        isDead = true;
        level.addTrapToLevel(new Oil(position));
    }

    /**
     * Egy körön belüli események elvégzéséért felelős. Itt történik az ugrás, az ütközésellenőrzés
     * és a tisztítás.
     */
    @Override
    public void turn() {
        if (target == null) {
            getTarget();
        }
        if(target != null) {
            jump();
        }

        level.checkCollisionOnRobot(this);

        cleanUp(level.getTrapsCollidingWithRobot(this));
    }

    /**
     * Beállítja a legközelebbi csapdát a cleaner következő céljának.
     */
    private void getTarget() {
        List<Trap> traps = level.getTraps();
        if(traps.isEmpty()){
            return;
        }
        Trap min = traps.get(0);

        for (Trap trap : traps) {
            if(trap.equals(target)){
                continue;
            }
            if (position.distance(trap.getPosition()) < position.distance(min.getPosition())) {
                min = trap;
            }
        }
        target = min;
        direction = target.getPosition();
    }

    /**
     * A csapda tisztítása itt történik. A megfelelő csapda tisztító metódusa hívódik meg.
     * @param traps csapdák listája
     */
    private void cleanUp(List<Trap> traps) {
        for (Trap trap : traps) {
            trap.cleaning();
        }
    }

    @Override
    public void steppedOnBy(TrapperRobot robot) {
        die();
    }

    @Override
    public void steppedOnBy(CleanerRobot robot) {
        robot.changeTarget();
    }

    /**
     * Új célpont kiválasztása a cleaner számára.
     */
    public void changeTarget() {
        getTarget();
    }

    @Override
    public void collideWith(Robot robot) {
        robot.steppedOnBy(this);
    }

}
