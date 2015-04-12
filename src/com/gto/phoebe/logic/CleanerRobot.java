package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class CleanerRobot extends Robot {

    private static int SIZE = 5;
    private static int SPEED = 5;
    private static int id = 0;

    private Trap target = null;

    public CleanerRobot(Point position, String name, Level level, UserInterface userInterface) {
        super(position, SIZE, name, level, userInterface);
        this.speed = SPEED;
    }

    public CleanerRobot(Point position, Level level, UserInterface userInterface) {
        super(position, SIZE, "cleaner_robot_" + id++, level, userInterface);
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
            newPosition = translate(speed, getAngle() + i * 60);
            if(level.isValidField(newPosition)){
                break;
            }
        }
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

    @Override
    public String getInfo(){
        String ret = "";
        ret += "Tipusa: KisRobot \n";
        ret += "Pozicioja: (" + position.x + "," + position.y + ") \n";
        ret += "Sebesseg: " + speed + "\n";
        ret += "Irany: (" + direction.x + "," + direction.y + ") \n";

        return ret;
    }

}
