package prototype;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.*;
import com.gto.phoebe.logic.Robot;
import com.gto.phoebe.ui.UserInterface;
import com.gto.phoebe.util.PhoebeException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PrototypeInterface implements UserInterface {

    private InputStream map = ClassLoader.getSystemResourceAsStream("resources/map.bmp");
    private Level level;

    private ArrayList<TrapperRobot> players = new ArrayList<TrapperRobot>();
    private String output = "";
    private boolean finish = false;

    private String trapToPut = "semmi";

    public void readCommand() {
        Scanner in = new Scanner(System.in);
        while (!finish) {
            String commandLine = in.nextLine();
            executeCommand(commandLine);
        }
    }

    private void executeCommand(String commandLine) {

        String[] commandParts = commandLine.split(" ");
        String command = commandParts[0].toLowerCase();

        if (command.equals("exit")) {
            finish = true;
        } else if (command.equals("tesztsorozatbeolvas")) {
            tesztSorozatBeolvas(commandParts);
        } else if (command.equals("tesztkimenetmentese")) {
            tesztKimenetMentese(commandParts);
        } else if (command.equals("palyabetolt")) {
            palyaBetolt(commandParts);
        } else if (command.equals("palyainfo")) {
            palyaInfo();
        } else if (command.equals("actorletrehozas")) {
            actorLetrehozas(commandParts);
        } else if (command.equals("actorinfo")) {
            actorInfo(commandParts);
        } else if (command.equals("osszesactorlistazas")) {
            osszesActorListazas();
        } else if (command.equals("kor")) {
            kor();
        } else if (command.equals("robotujpozicio")) {
            robotUjPozicio(commandParts);
        } else if (command.equals("robotsebesseg")) {
            robotSebesseg(commandParts);
        } else if (command.equals("robotirany")) {
            robotIrany(commandParts);
        } else if (command.equals("robotcsapdalerakas")) {
            robotCsapdaLerakas(commandParts);
        } else if (command.equals("actornevatnevezes")) {
            actorNevAtnevezes(commandParts);
        } else {
            print("Ismeretlen parancs: " + commandParts[0]);
        }
    }

    private void actorNevAtnevezes(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 3)) {
            return;
        }

        String name = commandParts[1];
        String newName = commandParts[2];

        for(Robot robot : level.robots) {
            if(name.equals(robot.name)){
                robot.name = newName;
            }
        }
        for(Robot robot : level.robots) {
            if(name.equals(robot.name)){
                robot.name = newName;
            }
        }

        print("Atnevezve, uj nev: " + newName);
    }

    private void robotCsapdaLerakas(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 3)) {
            return;
        }

        if(players.size() == 0){
            return;
        }

        trapToPut = commandParts[2];
        TrapTypes trapType = getTrapInput(players.get(0));

        String name = commandParts[1];
        for(TrapperRobot trapperRobot : players) {
            if(name.equals(trapperRobot.name)) {
                int prevTrapCount = level.traps.size();
                trapperRobot.plantTrap();
                if (prevTrapCount != level.traps.size()) {
                    String newName = level.traps.get(level.traps.size() - 1).name;
                    print("Uj " + trapType.toString() + " ezen a pozicion: (" + trapperRobot.getPosition().x + "," + trapperRobot.getPosition().y + "). Nev: " + newName);
                } else  {
                    System.out.println("0 db ilyen tipusu csapdaja van jelenleg a robotnak, nem tud lerakni.");
                }
            }
        }
        trapToPut = "semmi";
    }

    private void robotIrany(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 4)) {
            return;
        }

        String name = commandParts[1];

        int x = Integer.parseInt(commandParts[2]);
        int y = Integer.parseInt(commandParts[3]);
        int width = level.gameMap.width;
        int height = level.gameMap.height;

        if(x < 0 || y < 0 || x > width || y > height){
            print("A koordinataknak (0,0) es (" + width + "," + height + ") koze kell esniuk.");
        }

        for(Robot robot : level.robots) {
            if(name.equals(robot.name)){
                robot.setDirection(new Point(x, y));
            }
        }

        print(name + " iranya: " + "(" + x + "," + y + ")");
    }

    private void robotSebesseg(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 3)) {
            return;
        }

        String name = commandParts[1];

        int speed = Integer.parseInt(commandParts[2]);

        for(Robot robot : level.robots){
            if(name.equals(robot.name)){
                robot.setSpeed(speed);
            }
        }

        print(name + " sebessege: " + speed);
    }

    private void robotUjPozicio(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 4)) {
            return;
        }

        String name = commandParts[1];

        int x = Integer.parseInt(commandParts[2]);
        int y = Integer.parseInt(commandParts[3]);
        int width = level.gameMap.width;
        int height = level.gameMap.height;

        if(x < 0 || y < 0 || x > width || y > height){
            print("A koordinataknak (0,0) es (" + width + "," + height + ") koze kell esniuk.");
        }

        for(Robot robot : level.robots){
            if(name.equals(robot.name)){
                robot.setPosition(new Point(x, y));
            }
        }

        print(name + " pozicioja: " + "(" + x + "," + y + ")");
    }

    private void kor() {
        level.turn();
        Iterator robotIterator = players.iterator();
        while (robotIterator.hasNext()){
            Robot player = (Robot)robotIterator.next();
            boolean alive = false;
            for(Robot robot : level.robots){
                if(robot.name.equals(player.name)){
                    alive = true;
                    break;
                }
            }
            if(!alive){
                robotIterator.remove();
            }
        }
        print("Egy kor eltelt.");
    }

    private void osszesActorListazas() {
        for(Actor actor : level.traps){
            print(actor.name);
        }

        for(Actor actor : level.robots){
            if(actor.isDead())
                print("-");
            else
                print(actor.name);
        }
    }

    private void actorInfo(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 2)) {
            return;
        }

        String name = commandParts[1];

        for(Actor actor : level.traps){
            if(actor.name.equals(name)) {
                print(actor.getInfo());
            }
        }

        for(Actor actor : level.robots){
            if(actor.name.equals(name)) {
                print(actor.getInfo());
            }
        }
    }

    private void actorLetrehozas(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 5)) {
            return;
        }

        String name = commandParts[1];
        if(nameUsed(name)){
            print("Ez a nev mar foglalt: " + name);
            return;
        }

        int x = Integer.parseInt(commandParts[3]);
        int y = Integer.parseInt(commandParts[4]);
        int width = level.gameMap.width;
        int height = level.gameMap.height;

        if(x < 0 || y < 0 || x > width || y > height){
            print("A koordinataknak (0,0) es (" + width + "," + height + ") koze kell esniuk.");
        }

        String robotType = commandParts[2].toLowerCase();
        if("robot".equals(robotType)){
            TrapperRobot robot = new TrapperRobot(new Point(x, y), name, level, this);
            level.addPlayer(robot);
            players.add(robot);
        } else if("kisrobot".equals(robotType)){
            level.robots.add(new CleanerRobot(new Point(x, y), name, level, this));
        } else if("olaj".equals(robotType)){
            level.traps.add(new Oil(new Point(x, y), name));
        } else if("ragacs".equals(robotType)){
            level.traps.add(new Glue(new Point(x, y), name));
        }

        print(commandParts[2] + " letrehozva ezen a pozicion: (" + x + "," + y + "). Nev: " + name);
    }

    private boolean nameUsed(String name) {
        boolean nameUsed = false;
        for(Actor actor : level.traps){
            if(name.equals(actor.name)){
                nameUsed = true;
                break;
            }
        }

        for(Actor actor : level.robots){
            if(name.equals(actor.name)){
                nameUsed = true;
                break;
            }
        }
        return nameUsed;
    }

    private void palyaInfo() {
        print("Hatralevo korok szama: " + level.remainingTurns);
    }

    private void palyaBetolt(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 2)) {
            return;
        }

        int remainingTurns = Integer.parseInt(commandParts[1]);
        if(!(remainingTurns > 0)) {
            print("A korok szamanak pozitiv egesz szamnak kell lennie.");
        }

        try {
            level = new Level(remainingTurns, map, this);
        } catch (PhoebeException e) {
            print(e.getMessage());
        }
        print("Palya " + remainingTurns + " korrel inicializalasra kerult.");
    }

    private void tesztKimenetMentese(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 2)) {
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(commandParts[1]);
            String[] lines = output.split("\n");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void tesztSorozatBeolvas(String[] commandParts) {
        if (!checkArgumentNumber(commandParts.length, 2)) {
            return;
        }

        try {
            Scanner scanner = new Scanner(new File(commandParts[1]));
            while (scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            print("A parameterkent megadott fajl nem talalhato: " + commandParts[1]);
        }

    }

    private boolean checkArgumentNumber(int actual, int expected) {
        if (actual != expected) {
            print("A megadott parameterek szama nem megfelelo. Elvart: " + expected + " kapott: " + actual);
            return false;
        }
        return true;
    }

    @Override
    public Movement getMovementInput(TrapperRobot robot) {
        Movement movement = new Movement();
        movement.angleChange = 0;
        movement.speedChange = 0;
        return movement;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
        output += message + "\n";
    }

    @Override
    public TrapTypes getTrapInput(TrapperRobot robot) {
        if ("olaj".equals(trapToPut.toLowerCase())) {
            return TrapTypes.OIL;
        } else if ("ragacs".equals(trapToPut.toLowerCase())) {
            return TrapTypes.GLUE;
        } else if ("semmi".equals(trapToPut.toLowerCase())) {
            return TrapTypes.NONE;
        }

        return TrapTypes.NONE;
    }
}
