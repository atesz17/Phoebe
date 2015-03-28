package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class TrapperRobot extends Robot {

    private static int SIZE = 10;

    protected TrapInventory trapInventory = new TrapInventory();

    public TrapperRobot(Point position, String name, UserInterface userInterface) {
        super(position, SIZE, name, userInterface);
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
        double newAngle = getAngle() + movement.angleChange;

        int newX = (int) Math.round(newSpeed * Math.cos(newAngle * Math.PI / 180));
        int newY = (int) Math.round(newSpeed * Math.sin(newAngle * Math.PI / 180));

        return new Point(position.x + newX, position.y + newY);
    }

    private double getAngle() {
        int a = Math.abs(direction.y - position.y);
        int b = Math.abs(direction.x - position.x);
        double c = Math.sqrt(a * a + b * b);
        return Math.asin(a / c) * 180 / Math.PI;
    }

    public void die()   {
        totalDistanceTraveled = 0D;
        isDead = true;
    }

    @Override
    public void turn(Level level) {
        Point previousPosition = position;
        jump();

        level.checkRobotHasCrossedStartLine(this, previousPosition);

        //TODO ez nem az igazi
        level.checkCollisionOnRobot(this);

        plantTrap(level);
    }


    private void plantTrap(Level level) {
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
    public void collideWith(TrapperRobot robot) {
        int newSpeed = (speed + robot.getSpeed()) / 2;
        if (speed > robot.getSpeed()) {
            speed = newSpeed;
        } else {
            robot.setSpeed(newSpeed);
        }
    }

    @Override
    public void collideWith(CleanerRobot robot) {
        robot.changeTarget();
    }

    @Override
    public void collideWith(Actor actor) {
        actor.collideWith(this);
    }

    public Oil dropOil() {
        return trapInventory.getOil();
    }

    public Glue dropGlue() {
        return trapInventory.getGlue();
    }

    public void reloadTraps() {
        trapInventory.reloadTraps();
    }

}
