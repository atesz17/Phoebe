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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private Map<Point, DeathField> map = new HashMap<Point, DeathField>();
    private int width;
    private int height;
    private Line2D startLine;

    public GameMap(InputStream inputStream) throws PhoebeException {
        readMap(inputStream);
    }

    private void readMap(InputStream inputStream) throws PhoebeException {
        map = new HashMap<Point, DeathField>();
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
                if (color == Color.WHITE.getRGB()) {
                    map.put(new Point(xPixel, yPixel), new DeathField());
                } else if (color == Color.RED.getRGB()) {
                    startPoints.add(new Point(xPixel, yPixel));
                }
            }
        }

        createStartLine(startPoints);
    }

    private void createStartLine(List<Point> startPoints) throws PhoebeException {
        if (startPoints.size() != 2) {
            throw new PhoebeException(new ErrorDescriber("Invalid number of start points.", " " + startPoints.size()));
        }
        Point point1 = startPoints.get(0);
        Point point2 = startPoints.get(1);
        startLine = new Line2D.Double(point1.x, point1.y, point2.x, point2.y);
    }

    public boolean isOnField(Point point) {
        return map.containsKey(point);
    }

    public boolean checkRobotHasCrossedStartLine(Point previousPosition, Robot robot) {
        Line2D movement = new Line2D.Double(previousPosition.x, previousPosition.y, robot.getPosition().x, robot.getPosition().y);
        return startLine.intersectsLine(movement);
    }

    public Point getValidField() {
        Point ret = null;
        boolean valid = false;
        while (!valid) {
            int randomX = (int) (width * Math.random());
            int randomY = (int) (height * Math.random());
            ret = new Point(randomX, randomY);
            valid = isValidField(ret);
        }
        return ret;
    }

    public boolean isValidField(Point point) {
        return map.containsKey(point);
    }
}
