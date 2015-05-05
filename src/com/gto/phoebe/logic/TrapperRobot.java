package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class TrapperRobot extends Robot {

    private static int SIZE = 10;
    private static Color COLOR = Color.DARK_GRAY;

    protected TrapInventory trapInventory = new TrapInventory();

    public TrapperRobot(Point position, String name, Level level, UserInterface userInterface) {
        super(position, SIZE, COLOR, name, level, userInterface);
    }

    public void jump() {
        Movement input = userInterface.getMovementInput(this);
        jump(input);
    }

    private void jump(Movement movement) {
        Point newPosition = calculatePosition(movement);
        speed += movement.speedChange;
        direction = new Point((newPosition.x - getPosition().x) + newPosition.x, (newPosition.y - getPosition().y) + newPosition.y);

        totalDistanceTraveled += newPosition.distance(position);
        setPosition(newPosition);
        speedChangeEnabled = true;
    }

    private Point calculatePosition(Movement movement) {
        int newSpeed = speed + movement.speedChange;
        double newAngleInRad = getAngleInRad() + movement.angleChangeInRad;

        return translate(newSpeed, newAngleInRad);
    }

    @Override
    public void die()   {
        totalDistanceTraveled = 0D;
        isDead = true;
    }

    @Override
    public void turn() {
        Point previousPosition = position;
        jump();

        level.checkRobotHasCrossedStartLine(this, previousPosition);

        level.checkCollisionOnRobot(this);

        plantTrap();
    }


    public void plantTrap() {
        TrapTypes trap = userInterface.getTrapInput(this);
        switch (trap) {
            case OIL:
                Oil oil = dropOil();
                if (oil != null) {
                    level.addTrapToLevel(oil);
                }
                break;
            case GLUE:
                Glue glue = dropGlue();
                if (glue != null) {
                    level.addTrapToLevel(glue);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void steppedOnBy(TrapperRobot robot) {
        int newSpeed = (speed + robot.getSpeed()) / 2;
        if (speed > robot.getSpeed()) {
            speed = newSpeed;
        } else {
            robot.setSpeed(newSpeed);
        }
    }

    @Override
    public void steppedOnBy(CleanerRobot robot) {
        robot.changeTarget();
    }

    @Override
    public void collideWith(Robot robot) {
        robot.steppedOnBy(this);
    }

    public Oil dropOil() {
        return trapInventory.getOil(position);
    }

    public Glue dropGlue() {
        return trapInventory.getGlue(position);
    }

    public void reloadTraps() {
        trapInventory.reloadTraps();
    }

}
