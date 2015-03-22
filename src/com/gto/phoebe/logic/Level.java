package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Trap;
import com.gto.phoebe.skeleton.Skeleton;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Level {

    // Taroljuk a robotokat
    private List<Robot> robots = new ArrayList<Robot>();
    // Taroljuk az osszes roboton kivuli elemet (magat a palyat)
    private List<Actor> fields = new ArrayList<Actor>();
    // Hatralevo korok
    private int remainingTurns;
    // Startvonal
    private Line2D startLine;
    // Skeleton input
    private UserInterface userInterface;

    /**
     * Konstruktor, ami a megadott parameterekkel letrehoz egy uj palyat. Meg kell adni, hogy hany korbol fog allni
     * az adott meccs, szelesseget es magassagat, valamint a kezdovonal elhelyezkedeset. A szkeletonban szukseg
     * van meg egy parameterre
     * @param remainingTurns hatralevo korok
     * @param width szelesseg
     * @param height magassag
     * @param startLinePointOne startvonal egyik vegpontja
     * @param startLinePointTwo startvonal masik vegpontja
     * @param userInterface skeleton input
     */
    public Level(int remainingTurns, int width, int height, Point startLinePointOne, Point startLinePointTwo, UserInterface userInterface) {
        Skeleton.createObject("level");
        this.remainingTurns = remainingTurns;
        this.userInterface = userInterface;
        startLine = new Line2D.Double(startLinePointOne.getX(), startLinePointOne.getY(), startLinePointTwo.getX(), startLinePointTwo.getY());
        Skeleton.createObject("startLine");
        robots.add(new Robot(new Point(110, 100), 1, userInterface));
        robots.add(new Robot(new Point(110, 110), 1, userInterface));
        initMap(width, height);
    }

    /**
     * A kivant parameterrel letrehoz egy palyat ures mezokkel egy kivetelevel, ami a tesztelhetoseget szolgalja
     * @param width palya szelessege
     * @param height palya magassaga
     */
    private void initMap(int width, int height) {
        Skeleton.methodCall("initMap(" + Integer.toString(width) + ", " + Integer.toString(height) + ")");
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 110 && y == 99) {
                    fields.add(new DeathField(new Point(x, y), 1));
                } else {
                    fields.add(new NormalField(new Point(x, y), 1));
                }
            }
        }
        Skeleton.methodReturn("void");
    }

    /**
     * A jatek fociklusa. Ebben tortenik a robotok mozgatasa, collision checkeles, illetve a jatek koreinek kezelese is
     */
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

    /**
     * Minden robot minden interrakciojat a sajat koreben vegzi. Erre szolgal ez a fuggveny, amiben lehetosege
     * van a robotnak lepnie, es csapdat raknia
     * @param robot Ennek a robotnak van most a kore
     */
    private void turn(Robot robot) {
        Point previousPosition = robot.getPosition();
        robot.jump();

        if (checkRobotHasCrossedStartLine(previousPosition, robot)) {
            robot.reloadTraps();
        }
        checkCollisionOnRobot(robot);
        plantTrap(robot);
    }

    /**
     * Szinten egy segedfuggveny a szkeleton celjabol, hogy normalisan tudjuk kezelni az elore beallitott inputot
     * @param robot Ez a robot fog csapdat lerakni
     */
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

    /**
     * Ellenorzi, hogy az osszes robot nem lepett-e meg tiltott mezore
     * @return boolean, ami megmondja, hogy valamelyik robot mar halott
     */
    private boolean isEverybodyAlive() {
        for (Robot robot : robots) {
            if (robot.isDead()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ellenorizzuk az adott robotra, hogy utkozott-e valamivel a palyan. Ha igen, meghivjuk a megfelelo mezo
     * activateEffectOn(robot) fuggvenyet, eltavolitjuk a mezok kozul, es egy uj normal-t rakunk a helyere
     * @param robot Ezt a robotot ellenorizzuk, hogy utkozott-e
     */
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

    /**
     * Ellenorzi, hogy az adott Actor-ba "belelog-e" az adott robot
     * @param field adott actor
     * @param robot adott robot
     * @return igaz, ha az actor erintkezik a robottal
     */
    private boolean checkActorInRange(Actor field, Robot robot) {
        Point robotPosition = robot.getPosition();
        Point fieldPosition = field.getPosition();
        int fieldRange = field.getSize();

        return (robotPosition.x - fieldPosition.x) * (robotPosition.x - fieldPosition.x) + (robotPosition.y - fieldPosition.y) * (robotPosition.y - fieldPosition.y) < fieldRange * fieldRange;
    }

    /**
     * Hozzadunk egy uj actor-t a palya mezoihez. Ha az actor pozicioja megegyezik mar egy meglevo elem poziciojaval,
     * akkor azt kivesszuk a listabol
     * @param actor
     */
    public void addActorToLevel(Actor actor) {
        Skeleton.methodCall("addActorToLevel(actor)");
        for(int i = 0; i<fields.size(); i++)    {
            if (fields.get(i).getPosition().equals(actor.getPosition()))   {
                fields.remove(i);
                break;
            }
        }
        fields.add(actor);
        Skeleton.methodReturn("void");
    }

    /**
     * A megadott actor-t toroljuk a mezok listajabol
     * @param actor megadott actor
     */
    public void removeActorFromLevel(Actor actor) {
        Skeleton.methodCall("removeActorFromLevel(actor)");
        fields.remove(actor);
        Skeleton.methodReturn("void");
    }

    /**
     * Ez a fuggveny ellenorzi, hogy az adott robot az elozo koreben es a mostaniban nem keresztezte-e a startvonalat
     * @param previousPosition robot elozo pozicioja
     * @param robot adott robot
     * @return boolean, ami igaz, ha a robot keresztezte a startvonalat
     */
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
