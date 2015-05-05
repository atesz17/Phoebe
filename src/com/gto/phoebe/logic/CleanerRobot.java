package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class CleanerRobot extends Robot {

    private static int SIZE = 10;
    private static Color COLOR = Color.LIGHT_GRAY;
    private static int SPEED = 10;
    private static int ID = 0;

    private Trap target = null;

    public CleanerRobot(Point position, Level level, UserInterface userInterface) {
        super(position, SIZE, COLOR, "cleaner_robot_" + ID++, level, userInterface);
        this.speed = SPEED;
    }

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

    @Override
    public void die() {
        isDead = true;
        level.addTrapToLevel(new Oil(position));
    }

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

    public void changeTarget() {
        getTarget();
    }

    @Override
    public void collideWith(Robot robot) {
        robot.steppedOnBy(this);
    }

}
