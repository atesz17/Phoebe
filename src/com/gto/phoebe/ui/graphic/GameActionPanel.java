package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.logic.Robot;
import com.gto.phoebe.logic.Trap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GameActionPanel extends JPanel {

    private GamePanel gamePanel;
    private Level level;

    public GameActionPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.level = gamePanel.getLevel();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawStartLine(g);
        drawRobots(g);
        drawTraps(g);
    }

    private void drawTraps(Graphics g) {
        for(Trap trap : level.getTraps()){
            g.setColor(trap.getColor());
            g.fillOval(trap.getPosition().x - trap.getSize() / 2, trap.getPosition().y - trap.getSize() / 2, trap.getSize(), trap.getSize());
        }
    }

    private void drawRobots(Graphics g) {
        for(Robot robot : level.getRobots()){
            g.setColor(robot.getColor());
            g.fillOval(robot.getPosition().x - robot.getSize() / 2, robot.getPosition().y - robot.getSize() / 2, robot.getSize(), robot.getSize());
        }
    }

    private void drawMap(Graphics g) {
        g.setColor(Color.GREEN);
        for(Point point : level.getGameMap().getMap()){
            g.drawLine(point.x, point.y, point.x, point.y);
        }
    }

    private void drawStartLine(Graphics g) {
        g.setColor(Color.RED);
        Line2D startLine = level.getGameMap().getStartLine();
        g.drawLine((int) startLine.getP1().getX(), (int) startLine.getP1().getY(), (int) startLine.getP2().getX(), (int) startLine.getP2().getY());
    }

    public void drawArrow(Point mousePosition, Point robotPosition) {
        repaint();
        Graphics g = getGraphics();
        g.setColor(Color.BLUE);
        g.drawLine(mousePosition.x, mousePosition.y, robotPosition.x, robotPosition.y);
    }
}
