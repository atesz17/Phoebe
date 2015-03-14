package com.gto.phoebe;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Robot> robots = new ArrayList<Robot>();

    private List<Actor> fields = new ArrayList<Actor>();
    private int remainingTurns;
    private Line2D startLine;

    public Level(int remainingTurns, int width, int height, Point startLinePointOne, Point startLinePointTwo) {
        this.remainingTurns = remainingTurns;
        startLine = new Line2D.Double(startLinePointOne.getX(), startLinePointOne.getY(), startLinePointTwo.getX(), startLinePointTwo.getY());
        robots.add(new Robot());
        robots.add(new Robot());
        initMap(width, height);
    }

    private void initMap(int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x * x + y * y > 10000 && x * x + y * y < 15000) {
                    fields.add(new NormalField(new Point(x, y), 1));
                } else {
                    fields.add(new DeathField(new Point(x, y), 1));
                }
            }
        }
    }

    public void gameCycle() {

    }

    public void checkCollisionOnRobot(Robot robot) {
        checkRobotHasCrossedStartLine(robot);
        for (Actor field : fields) {
            if (checkActorInRange(field, robot)) {
                field.activateEffectOn(robot);
                addActorToLevel(new NormalField(field.getPosition(), 1));
                removeActorFromLevel(field);
            }
        }
    }

    private boolean checkActorInRange(Actor field, Robot robot) {
        Point robotPosition = robot.getPosition();
        Point fieldPosition = field.getPosition();
        int fieldRange = field.getSize();

        return (robotPosition.x - fieldPosition.x) * (robotPosition.x - fieldPosition.x) + (robotPosition.y - fieldPosition.y) * (robotPosition.y - fieldPosition.y) < fieldRange * fieldRange;
    }

    public void addActorToLevel(Actor actor) {
        fields.add(actor);
    }

    public void removeActorFromLevel(Actor actor) {
        fields.remove(actor);
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public void checkRobotHasCrossedStartLine(Robot r) {
        // TODO
    }

    public Robot getRobot(int index) throws Exception {
        if (index > 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return robots.get(index);
        }
    }

    public Robot getWinner() {
        Robot winner = robots.get(0);
        for (Robot robot : robots) {
            if (winner.getTotalDistanceTraveled() < robot.getTotalDistanceTraveled()) {
                winner = robot;
            }
        }

        return winner;
    }
}
