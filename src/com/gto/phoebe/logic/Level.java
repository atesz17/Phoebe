package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;
import com.gto.phoebe.util.PhoebeException;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level {

    private GameMap gameMap;
    private int remainingTurns;
    private UserInterface userInterface;

    private List<Robot> robots = new ArrayList<Robot>();
    private List<Trap> traps = new ArrayList<Trap>();

    private int playersAlive = 0;

    public Level(int remainingTurns, InputStream map, UserInterface userInterface) throws PhoebeException {
        gameMap = new GameMap(map);
        this.remainingTurns = remainingTurns;
        this.userInterface = userInterface;
        addPlayer(new TrapperRobot(new Point(100, 100), "Bela", this, userInterface));
        addPlayer(new TrapperRobot(new Point(100, 110), "Ubul", this, userInterface));
    }

    public void startGame() {
        gameCycle();

        //TODO itt lesz a jatek vege
    }

    private void gameCycle() {
        while (remainingTurns > 0) {
            for (Robot robot : robots) {
                removeDead();
                if (!isAnybodyAlive()) {
                    remainingTurns = 0;
                    break;
                }
                userInterface.print("Robot " + (robots.indexOf(robot) + 1) + ": ");
                robot.turn();
            }
            for(Trap trap : traps){
                trap.turn();
            }
            remainingTurns--;
        }
        spawnCleaners();
    }

    private void spawnCleaners() {
        if (5 * Math.random() < 1) {
            for (int i = 0; i < 4 * Math.random(); i++) {
                addCleaner();
            }
        }
    }

    private void removeDead() {

        Iterator<Trap> trapIterator = traps.iterator();
        while (trapIterator.hasNext()) {
            Trap trap = trapIterator.next();
            if (trap.isDead) {
                trapIterator.remove();
            }
        }

        Iterator<Robot> robotIterator = robots.iterator();
        while (robotIterator.hasNext()) {
            Robot robot = robotIterator.next();
            if (robot.isDead) {
                robotIterator.remove();
            }
        }
    }

    public void checkRobotHasCrossedStartLine(TrapperRobot robot, Point previousPosition) {
        if (gameMap.checkRobotHasCrossedStartLine(previousPosition, robot)) {
            robot.reloadTraps();
        }
    }

    private boolean isAnybodyAlive() {
        return playersAlive > 1;
    }

    public void checkCollisionOnRobot(Robot robot) {

        checkPositionOnMap(robot);

        checkCollisionWithTraps(robot);

        checkCollisionWithRobots(robot);
    }

    private void checkCollisionWithRobots(Robot robot) {
        for (Robot otherRobot : robots) {
            if (checkActorInRange(otherRobot, robot)) {
                robot.collideWith(otherRobot);
            }
        }
    }

    private void checkCollisionWithTraps(Robot robot) {
        for (Trap trap : getTrapsCollidingWithRobot(robot)) {
            trap.effect(robot);
        }
    }

    public List<Trap> getTrapsCollidingWithRobot(Robot robot) {
        List<Trap> ret = new ArrayList<Trap>();
        for (Trap trap : traps) {
            if (checkActorInRange(trap, robot)) {
                ret.add(trap);
            }
        }
        return ret;
    }

    private void checkPositionOnMap(Robot robot) {
        if (!isValidField(robot.getPosition())) {
            robot.die();
        }
    }

    private boolean checkActorInRange(Actor actor, Robot robot) {
        Point robotPosition = robot.getPosition();
        Point fieldPosition = actor.getPosition();
        int range = actor.getSize();

        return (robotPosition.x - fieldPosition.x) * (robotPosition.x - fieldPosition.x) + (robotPosition.y - fieldPosition.y) * (robotPosition.y - fieldPosition.y) < range * range;
    }

    public void addTrapToLevel(Trap trap) {
        traps.add(trap);
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

    public void addPlayer(Robot robot) {
        //TODO valahogy magatol is le kellene tudnia rakni a start vonalra...
        robots.add(robot);
        playersAlive++;
    }

    public void addCleaner() {
        robots.add(new CleanerRobot(gameMap.getValidField(), this, userInterface));
    }

    public List<Trap> getTraps() {
        return traps;
    }

    public boolean isValidField(Point point){
        return gameMap.isValidField(point);
    }
}
