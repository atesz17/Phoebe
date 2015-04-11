package com.gto.phoebe.logic;

import com.gto.phoebe.util.ErrorDescriber;
import com.gto.phoebe.util.PhoebeException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class GameMap {
    private List<Point> map = new ArrayList<Point>();
    private int width;
    private int height;
    private Line2D startLine;

    public GameMap(InputStream inputStream) throws PhoebeException {
        readMap(inputStream);
    }

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

    private void createStartLine(List<Point> startPoints) throws PhoebeException {
        if (startPoints.size() != 2) {
            throw new PhoebeException(new ErrorDescriber("Invalid number of start points.", " " + startPoints.size()));
        }
        Point point1 = startPoints.get(0);
        Point point2 = startPoints.get(1);
        startLine = new Line2D.Double(point1.x, point1.y, point2.x, point2.y);
    }

    public boolean checkRobotHasCrossedStartLine(Point previousPosition, Robot robot) {
        Line2D movement = new Line2D.Double(previousPosition.x, previousPosition.y, robot.getPosition().x, robot.getPosition().y);
        return startLine.intersectsLine(movement);
    }

    public Point getValidField() {
        int random = (int)(Math.random() * map.size());
        return map.get(random);
    }

    public boolean isValidField(Point point) {
        return map.contains(point);
    }
}
