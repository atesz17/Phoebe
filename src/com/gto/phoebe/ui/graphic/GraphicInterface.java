package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;

public class GraphicInterface implements UserInterface {

    private GamePanel gamePanel;

    @Override
    public Movement getMovementInput(TrapperRobot robot) {
        while(!gamePanel.isClicked()){
            gamePanel.drawArrow(robot.getPosition());

        }

        Point position = gamePanel.getClickPosition();
        gamePanel.approveClicked();
        return getMovementFromPoint(robot, position);
    }

    private Movement getMovementFromPoint(TrapperRobot robot, Point newPosition) {
        Movement movement = new Movement();
        //sebesseg egyseg: 15;
        Point robotPosition = robot.getPosition();
        if(robotPosition.distance(newPosition.x, newPosition.y) < robot.getSpeed() - 15){
            movement.speedChange = -15;
        } else if(robotPosition.distance(newPosition.x, newPosition.y) > robot.getSpeed() + 15) {
            movement.speedChange = 15;
        } else {
            movement.speedChange = 0;
        }

        double angle = Math.atan2(robot.getDirection().y - robot.getPosition().y, robot.getDirection().x - robot.getPosition().x);
        double newAngle = Math.atan2(newPosition.y - robot.getPosition().y, newPosition.x - robot.getPosition().x);

        movement.angleChange = (int)((angle - newAngle)/Math.PI * 180);

        return movement;
    }

    @Override
    public void print(String message) {

    }

    @Override
    public TrapTypes getTrapInput(TrapperRobot robot) {
        return TrapTypes.NONE;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
