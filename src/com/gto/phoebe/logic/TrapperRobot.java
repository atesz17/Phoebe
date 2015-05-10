package com.gto.phoebe.logic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class TrapperRobot extends Robot {

    // robot mérete, ez adott
    private static int SIZE = 20;
    // robot színe, ez adott
    private static Color COLOR = Color.DARK_GRAY;

    // csapda tároló
    protected TrapInventory trapInventory = new TrapInventory();

    /**
     * Konstruktor a TrapperRobotnak, a Robot konstruktora hívódik meg.
     * @param position
     * @param name
     * @param level
     * @param userInterface
     */
    public TrapperRobot(Point position, String name, Level level, UserInterface userInterface) {
        super(position, SIZE, COLOR, name, level, userInterface);
    }

    /**
     * Robot mozgatásához szükséges.
     */
    public void jump() {
        Movement input = userInterface.getMovementInput(this);
        jump(input);
    }

    /**
     * Robot mozgatása itt történik, egyik pontról a másikra ugrik a robot a megadott paraméterrel.
     * @param movement irány és sebesség
     */
    private void jump(Movement movement) {
        Point newPosition = calculatePosition(movement);
        speed += movement.speedChange;
        direction = new Point((newPosition.x - getPosition().x) + newPosition.x, (newPosition.y - getPosition().y) + newPosition.y);

        totalDistanceTraveled += newPosition.distance(position);
        setPosition(newPosition);
        speedChangeEnabled = true;
    }

    /**
     * Kiszámolja a robot új sebességét és irányát, tehát az új pozíciót.
     * @param movement
     * @return
     */
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


    /**
     * Csapda lerakása: olaj, ragacs, vagy semmi.
     */
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

    /**
     * Visszaadja az olaj pozícióját, ahová le akarjuk tenni.
     * @return
     */
    public Oil dropOil() {
        return trapInventory.getOil(position);
    }

    /**
     * Visszaadja a ragacs pozícióját, ahová le akarjuk tenni.
     * @return
     */
    public Glue dropGlue() {
        return trapInventory.getGlue(position);
    }

    /**
     * Újratölti a csapdakészletet.
     */
    public void reloadTraps() {
        trapInventory.reloadTraps();
    }

    /**
     * Különböző információkat kérdez le a játékkal kapcsolatban.
     * @return
     */
    public String getStatus() {
        String ret = "";
        ret += "Játékos: " + name + " \n";
        ret += "Pozíció: (" + position.x + "," + position.y + ") \n";
        ret += "Megtett távolság: " + totalDistanceTraveled + "\n";
        ret += "Sebesség: " + speed + "\n";
        ret += "Olaj csapdák száma: " + trapInventory.getOilCount() + "\n";
        ret += "Ragacs csapdák száma: " + trapInventory.getGlueCount() + "\n";
        ret += "Sebességmódosítás: " + (this.speedChangeEnabled?"Engedélyezett":"Tiltott");

        return ret;
    }
}
