package com.gto.phoebe;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by atesz17 on 3/5/2015.
 */
public class Level {

    private ArrayList<Robot> robots;
    // ennek a tombnek nem kell 2D-nek lennie ugye?
    private ArrayList<Actor> fields;
    private int remainingTurns;
    private Line2D startLine;

    public Level(int remainingTurns, int width, int height, Point startLinePointOne, Point startLinePointTwo)    {
        this.remainingTurns = remainingTurns;
        startLine = new Line2D.Double(startLinePointOne.getX(), startLinePointOne.getY(), startLinePointTwo.getX(), startLinePointTwo.getY());
        robots = new ArrayList<Robot>();
        robots.add(new Robot());
        robots.add(new Robot());
        fields = new ArrayList<Actor>();
        for (int x = 0; x < width; x++)  {
            for (int y = 0; y<height; y++)  {
                fields.add(new Glue(new Point(x, y), 0));
            }
        }
    }

    /*
    public void checkCollisionOnRobot(Robot robot)  {
       for(int i = 0; i<fields.size(); i++) {
            // nyilvan nem csak a pos-t kell nezni, hanem a kor sugarat, de ez csak prototipus
            if (fields.get(i).getPosition().equals(robot.getPosition())) {
                fields.get(i).activateEffectOn(robot);
                removeActorFromLevel(fields.get(i));
                // es hozza is kell adni egy sima mezot a regi helyere
            }
            }
        }
    }
    */

    public void addActorToLevel(Actor actor)    {
        Point position = actor.getPosition();
    }

    public Point removeActorFromLevel(Actor actor)   {
        return null; //nincs kesz
    }

    public int getRemainingTurns()  {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns)   {
        this.remainingTurns = remainingTurns;
    }

    public void checkRobotHasCrossedStartLine(Robot r)  {
        // TODO
    }

    public Robot getRobot(int index) throws Exception   {
        if (index > 1)  {
            throw new Exception();
        } else  {
            return robots.get(index);
        }
    }

    public Robot getWinner()    {
        double maxDistance = robots.get(0).getTotalDistanceTraveled();
        if (maxDistance < robots.get(1).getTotalDistanceTraveled()) {
            return robots.get(1);
        } else  {
            return robots.get(0);
        }
    }
}
