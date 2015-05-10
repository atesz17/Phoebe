package com.gto.phoebe.logic;

import com.gto.phoebe.util.ErrorDescriber;
import com.gto.phoebe.util.PhoebeException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    // pálya térképe: pontok listában tárolva
    private List<Point> map = new ArrayList<Point>();
    // pálya szélessége
    private int width;
    // pálya hosszúsága
    private int height;
    // kezdővonal
    private Line2D startLine;

    /**
     * Konstruktor, ami a megadott fájlból létrehozza a pályát.
     * @param inputStream megadott képfájl
     */
    public GameMap(InputStream inputStream) throws PhoebeException {
        readMap(inputStream);
    }

    /**
     * Beolvassa a pálya térképét a megadott fájlból, majd létrehozza az alappályát.
     * @param inputStream megadott képfájl
     */
    private void readMap(InputStream inputStream) throws PhoebeException {
        map = new ArrayList<Point>();
        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new PhoebeException(new ErrorDescriber(e.getMessage()));
        }

        List<Point> startPoints = new ArrayList<Point>();
        width = image.getWidth();
        height = image.getHeight();
        for (int xPixel = 0; xPixel < width; xPixel++) {
            for (int yPixel = 0; yPixel < height; yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                if (color == Color.BLACK.getRGB()) {
                    map.add(new Point(xPixel, yPixel));
                } else if (color == Color.RED.getRGB()) {
                    startPoints.add(new Point(xPixel, yPixel));
                }
            }
        }

        createStartLine(startPoints);
    }

    /**
     * Kezdővonal létrehozása a megadott kezdőpontokkal.
     * @param startPoints megadott kezdőpontok
     */
    private void createStartLine(List<Point> startPoints) throws PhoebeException {
        if (startPoints.size() != 2) {
            throw new PhoebeException(new ErrorDescriber("Invalid number of start points.", " " + startPoints.size()));
        }
        Point point1 = startPoints.get(0);
        Point point2 = startPoints.get(1);
        startLine = new Line2D.Double(point1.x, point1.y, point2.x, point2.y);
    }

    /**
     * Ellenőrzi, hogy a megadott robot átlépett-e a kezdővonalon a korábbi pozíció segítségével.
     * @param previousPosition korábbi pozíció
     * @param robot megadott robot
     * @return
     */
    public boolean checkRobotHasCrossedStartLine(Point previousPosition, Robot robot) {
        Line2D movement = new Line2D.Double(previousPosition.x, previousPosition.y, robot.getPosition().x, robot.getPosition().y);
        return startLine.intersectsLine(movement);
    }

    /**
     * Visszaadja a pálya elérhető pontjait.
     * @return
     */
    public Point getValidField() {
        int random = (int)(Math.random() * map.size());
        return map.get(random);
    }

    /**
     * Flag, ami megmondja, hogy elérhető-e a megadott pont a pályán.
     * @param point megadott pont
     * @return
     */
    public boolean isValidField(Point point) {
        return map.contains(point);
    }

    /**
     * Visszaadja pálya térképét.
     * @return
     */
    public List<Point> getMap() {
        return map;
    }

    /**
     * Visszaadja a kezdővonalat.
     * @return
     */
    public Line2D getStartLine() {
        return startLine;
    }

    /**
     * Visszaadja a pálya szélességét.
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Visszaadja a pálya hosszúságát.
     * @return
     */
    public int getHeight() {
        return height;
    }
}
