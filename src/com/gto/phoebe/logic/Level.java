package com.gto.phoebe.logic;

import com.gto.phoebe.ui.UserInterface;
import com.gto.phoebe.util.PhoebeException;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level implements Runnable{

    // A pálya
    private GameMap gameMap;
    // Hátralévő körök száma
    private int remainingTurns;
    // Felhasználói input
    private UserInterface userInterface;

    // Tároljuk a robotokat.
    private List<Robot> robots = new ArrayList<Robot>();
    // Tároljuk a csapdákat.
    private List<Trap> traps = new ArrayList<Trap>();

    // Életben lévő robotok száma
    private int playersAlive = 0;

    /**
     * Konstruktor, amely a megadott paraméterekkel létrehoz egy új játékot. Ehhez meg kell adni
     * a hátralévő körök számát, egy a pálya térképét tartalmazó képfájlt, és a felhasználói inputot.
     * @param remainingTurns hátralévő körök száma
     * @param map pálya
     * @param userInterface felhasználói input
     */
    public Level(int remainingTurns, InputStream map, UserInterface userInterface) throws PhoebeException {
        gameMap = new GameMap(map);
        this.remainingTurns = remainingTurns;
        this.userInterface = userInterface;
    }

    /**
     *  Ez a metódus felelős egy-egy kör lebonyolításáért. Ehhez iterátorokkal végigiterálunk a pályán lévő robotok és
     *  csapdák között, majd a megfelelő metódusokat meghívva működik a játék logikája.
     */
    public void turn() {
        Iterator robotIterator = robots.iterator();
        while (robotIterator.hasNext()){
            Robot robot = (Robot)robotIterator.next();
            userInterface.print(robot.name + "'s turn");
            robot.turn();
            if(robot.isDead){
                robotIterator.remove();
                playersAlive--;
            }
            //TODO ha pl robot lep kisrobotra, nem kerul ki a listabol meg
        }
        Iterator trapIterator = traps.iterator();
        while (trapIterator.hasNext()){
            Trap trap = (Trap)trapIterator.next();
            trap.turn();
            if(trap.isDead){
                trapIterator.remove();
            }
        }
        if(!isAnybodyAlive()){
            remainingTurns = 0;
        }
        spawnCleaners();
        remainingTurns--;
    }

    /**
     * Ez a metódus helyezi el a tisztító robotokat a pályán.
     */
    private void spawnCleaners() {
        if (5 * Math.random() < 1) {
            for (int i = 0; i < 4 * Math.random(); i++) {
                addCleaner();
            }
        }
    }

    /**
     * Ellenőrzi, hogy a megadott robot áthaladt-e a kezdővonalon. Ehhez az előző pozíciót is meg kell adni.
     * @param robot megadott robot
     * @param previousPosition az előző pozíció
     */
    public void checkRobotHasCrossedStartLine(TrapperRobot robot, Point previousPosition) {
        if (gameMap.checkRobotHasCrossedStartLine(previousPosition, robot)) {
            robot.reloadTraps();
        }
    }

    /**
     * Egy flag, ami megmutatja, hogy valamelyik robot életben van-e még. Ha 1-nél több robot van életben, akkor
     * true, egyébként false.
     */
    public boolean isAnybodyAlive() {
        return playersAlive > 1;
    }

    /**
     * A megadott robot esetleges ütközéseit ellenőrzi. Ütközés lehet másik robottal, illetve csapdával.
     * Valamint azt is ellenőrzi, hogy a robot a pályán van-e még
     * @param robot megadott robot
     */
    public void checkCollisionOnRobot(Robot robot) {

        checkPositionOnMap(robot);

        checkCollisionWithTraps(robot);

        checkCollisionWithRobots(robot);
    }

    /**
     * A megadott robot másik robottal való ütközését ellenőrzi.
     * @param robot megadott robot
     */
    private void checkCollisionWithRobots(Robot robot) {
        for (Robot otherRobot : robots) {
            if (checkActorInRange(otherRobot, robot)) {
                robot.collideWith(otherRobot);
            }
        }
    }

    /**
     * A megadott robot csapdával való ütközését(csapdára való rálépést) ellenőrzi.
     * @param robot megadott robot
     */
    private void checkCollisionWithTraps(Robot robot) {
        for (Trap trap : getTrapsCollidingWithRobot(robot)) {
            trap.effect(robot);
        }
    }

    /**
     * Visszaadja azokat a csapdákat, amikre a megadott robot lépett.
     * @param robot megadott robot
     * @return List<Trap> A csapdák listája, amikre a megadott robot lépett.
     */
    public List<Trap> getTrapsCollidingWithRobot(Robot robot) {
        List<Trap> ret = new ArrayList<Trap>();
        for (Trap trap : traps) {
            if (checkActorInRange(trap, robot)) {
                ret.add(trap);
            }
        }
        return ret;
    }

    /**
     * Ellenőrzi, hogy a megadott robot a pályán van-e. Ha nem, akkor a robot meghal.
     * @param robot megadott robot
     */
    private void checkPositionOnMap(Robot robot) {
        if (!isValidField(robot.getPosition())) {
            robot.die();
        }
    }

    /**
     * Megnézi, hogy a megadott robot a megadott actortól a megfelelő távolságra van-e, ahhoz hogy ütközzön vele.
     * @param actor megadott actor
     * @param robot megadott robot
     * @return Ez a flag megadja, hogy benne vannak-e az ütközési távolságon belül a robot és az actor.
     */
    private boolean checkActorInRange(Actor actor, Robot robot) {
        Point robotPosition = robot.getPosition();
        Point fieldPosition = actor.getPosition();
        int range = actor.getSize();

        return (robotPosition.x - fieldPosition.x) * (robotPosition.x - fieldPosition.x) + (robotPosition.y - fieldPosition.y) * (robotPosition.y - fieldPosition.y) < range * range;
    }

    /**
     * Lerak a pályára egy csapdát.
     * @param trap megadott csapda
     */
    public void addTrapToLevel(Trap trap) {
        traps.add(trap);
    }

    /**
     * Visszaadja a megadott indexű robotot.
     * @param index megadott index
     * @return Robot A megfelelő indexű robot
     */
    public Robot getRobot(int index) throws Exception {
        if (index > 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return robots.get(index);
        }
    }

    /**
     * Visszaadja a győztes robotot. Vagyis azt, amelyik nagyobb távolságot tett meg.
     * @return Robot A győztes robot
     */
    public Robot getWinner() {
        Robot winner = robots.get(0);
        for (Robot robot : robots) {
            if (winner.getTotalDistanceTraveled() < robot.getTotalDistanceTraveled()) {
                winner = robot;
            }
        }

        return winner;
    }

    /**
     * Hozzáad egy robotot a pályához.
     * @param robot megadott robot
     */
    public void addPlayer(Robot robot) {
        //TODO valahogy magatol is le kellene tudnia rakni a start vonalra...
        robots.add(robot);
        playersAlive++;
    }

    /**
     * A megadott névvel ellátott játékost hozzáadja a játékhoz.
     * @param name megadott név
     */
    public void addPlayer(String name){
        Point playerPosition = gameMap.getValidField();
        addPlayer(new TrapperRobot(playerPosition, name, this, userInterface));
    }

    /**
     * Hozzáadja a cleaner robotokat a pályához.
     */
    public void addCleaner() {
        robots.add(new CleanerRobot(gameMap.getValidField(), this, userInterface));
    }

    /**
     * Visszaadja a csapdák listáját.
     * @return List<Trap> csapdák listája
     */
    public List<Trap> getTraps() {
        return traps;
    }

    /**
     * Visszaadja a robotok listáját.
     * @return List<Robot> robotok listája
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Megmutatja, hogy érvényes mező-e a megadott pont.
     * @param point megadott pont
     * @return flag
     */
    public boolean isValidField(Point point){
        return gameMap.isValidField(point);
    }

    /**
     * Visszaadja a pályát.
     * @return GameMap a pálya
     */
    public GameMap getGameMap(){
        return gameMap;
    }

    /**
     * Ez a megfelelő szál működéséhez szükséges futtató metódus.
     */
    @Override
    public void run() {
        while (remainingTurns > 0) {
            turn();
        }
        userInterface.gameOver(getWinner().name);
    }
}
