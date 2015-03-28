package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.util.List;

public class CleanerRobot extends Robot {

    private static int SIZE = 5;
    private static int SPEED = 5;
    private static int id = 0;

    private Point target = null;

    public CleanerRobot(Point position, UserInterface userInterface) {
        super(position, SIZE, "cleaner_robot_" + id++, userInterface);
        this.speed = SPEED;
    }

    @Override
    public void jump() {
        //TODO ugras a target iranyaba
        //megnezi, h. hova erkezne, ha nem halal, ugrik, ha halal, elfordul jobbra 90 fokot, es ujra
    }

    @Override
    public void die() {
        isDead = true;
        //TODO olajfoltot hagy
    }

    @Override
    public void turn(Level level) {
        if (target == null) {
            getTarget(level);
        }
        jump();

        //TODO ez nem az igazi
        level.checkCollisionOnRobot(this);

        cleanUp(level.getTrapsCollidingWithRobot(this));
    }

    private void getTarget(Level level) {
        List<Trap> traps = level.getTraps();
        Point min = traps.get(0).getPosition();

        for (Trap trap : traps) {
            if (position.distance(trap.getPosition()) < position.distance(min)) {
                min = trap.getPosition();
            }
        }
        target = min;
    }

    private void cleanUp(List<Trap> traps) {
        for (Trap trap : traps) {
            trap.cleaning();
        }
    }

    @Override
    public void collideWith(TrapperRobot robot) {
        die();
    }

    @Override
    public void collideWith(CleanerRobot robot) {

    }

    public void changeTarget() {
        //TODO ...
    }

    @Override
    public void collideWith(Actor actor) {
        actor.collideWith(this);
    }

}
