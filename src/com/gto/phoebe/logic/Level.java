package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Trap;
import com.gto.phoebe.skeleton.Skeleton;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Level {

    private List<Robot> robots = new ArrayList<Robot>();

    private List<Actor> fields = new ArrayList<Actor>();
    private int remainingTurns;
    private Line2D startLine;
    private UserInterface userInterface;

    public Level(int remainingTurns, int width, int height, Point startLinePointOne, Point startLinePointTwo, UserInterface userInterface) {
        this.remainingTurns = remainingTurns;
        this.userInterface = userInterface;
        startLine = new Line2D.Double(startLinePointOne.getX(), startLinePointOne.getY(), startLinePointTwo.getX(), startLinePointTwo.getY());
        robots.add(new Robot(new Point(110, 100), 1, userInterface));
        robots.add(new Robot(new Point(110, 110), 1, userInterface));
        initMap(width, height);
    }

    private void initMap(int width, int height) {
        Skeleton.methodCall("initMap(" + Integer.toString(width) + ", " + Integer.toString(height) + ")");
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x * x + y * y > 10000 && x * x + y * y < 15000) {
                    fields.add(new NormalField(new Point(x, y), 1));
                } else {
                    fields.add(new DeathField(new Point(x, y), 1));
                }
            }
        }
        Skeleton.methodReturn("void");
    }

    public void gameCycle() {
        Skeleton.methodCall("gameCycle()");
        Skeleton.turn = 1;
        while (remainingTurns > 0) {
            for (Robot robot : robots) {
                if (!isEverybodyAlive()) {
                    remainingTurns = 0;
                    break;
                }
                userInterface.print("Robot " + (robots.indexOf(robot) + 1) + ": ");
                turn(robot);

                Skeleton.turn++;
            }

            remainingTurns--;
        }
        Skeleton.methodReturn("void");
    }

    private void turn(Robot robot) {
        Point previousPosition = robot.getPosition();
        robot.jump();

        if (checkRobotHasCrossedStartLine(previousPosition, robot)) {
            robot.reloadTraps();
        }
        checkCollisionOnRobot(robot);
        plantTrap(robot);
    }

    private void plantTrap(Robot robot) {
        Trap trap = userInterface.getTrapInput(robot);
        switch (trap){
            case OIL:
                Oil oil = robot.dropOil();
                if(oil != null){
                    addActorToLevel(oil);
                }
                break;
            case GLUE:
                Glue glue = robot.dropGlue();
                if(glue != null){
                    addActorToLevel(glue);
                }
                break;
            default:
                break;
        }
    }

    private boolean isEverybodyAlive() {
        for (Robot robot : robots) {
            if (robot.isDead()) {
                return false;
            }
        }
        return true;
    }

    public void checkCollisionOnRobot(Robot robot) {
        Skeleton.methodCall("checkCollisionOnRobot(robot)");
        ListIterator<Actor> iter = fields.listIterator();
        while (iter.hasNext()) {
            Actor field = iter.next();
            if (checkActorInRange(field, robot)) {
                field.activateEffectOn(robot);
                Skeleton.methodCall("removeActorFromLevel(field)");
                iter.remove();
                Skeleton.methodReturn("void");
                Skeleton.createObject("newField");
                Skeleton.methodCall("addActorToLevel(newField)");
                iter.add(new NormalField(field.getPosition(), 1));
                Skeleton.methodReturn("void");
            }
        }
        Skeleton.methodReturn("void");
    }

    private boolean checkActorInRange(Actor field, Robot robot) {
        Point robotPosition = robot.getPosition();
        Point fieldPosition = field.getPosition();
        int fieldRange = field.getSize();

        return (robotPosition.x - fieldPosition.x) * (robotPosition.x - fieldPosition.x) + (robotPosition.y - fieldPosition.y) * (robotPosition.y - fieldPosition.y) < fieldRange * fieldRange;
    }

    public void addActorToLevel(Actor actor) {
        Skeleton.methodCall("addActorToLevel(actor)");
        fields.add(actor);
        Skeleton.methodReturn("void");
    }

    public void removeActorFromLevel(Actor actor) {
        Skeleton.methodCall("removeActorFromLevel(actor)");
        fields.remove(actor);
        Skeleton.methodReturn("void");
    }

    public boolean checkRobotHasCrossedStartLine(Point previousPosition, Robot robot) {
        Skeleton.methodCall("checkRobotHasCrossedStartLine(previousPosition, robot)");
        Line2D movement = new Line2D.Double(previousPosition.x, previousPosition.y, robot.getPosition().x, robot.getPosition().y);
        Skeleton.methodReturn(Boolean.toString(startLine.intersectsLine(movement)));
        return startLine.intersectsLine(movement);
    }

    public Robot getRobot(int index) throws Exception {
        if (index > 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return robots.get(index);
        }
    }

    /**
     * Ez a fuggveny felelos azert, hogyha vege van a jateknak, akkor visszaadja azt a robotot, amelyik nyert az alapjan
     * hogy melyik tett meg nagyobb tavolsagot
     * @return Robot Ez a robot tette meg a nagyobb tavolsagot, tehat nyert
     */
    public Robot getWinner() {
        Skeleton.methodCall("getWinner()");
        Robot winner = robots.get(0);
            if (winner.getTotalDistanceTraveled() < robots.get(1).getTotalDistanceTraveled()) {
                winner = robots.get(1);
            }
        Skeleton.methodReturn("winner");
        return winner;
    }
}
