package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.Movement;
import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.TrapperRobot;
import com.gto.phoebe.ui.UserInterface;

import javax.swing.*;
import java.awt.*;

public class GraphicInterface implements UserInterface {

    // játékpanel
    private GamePanel gamePanel;

    /**
     * A megadott robotnak az input általi mozgatását adja vissza, vagyis hogy milyen sebessége és iránya
     * lesz a robotnak.
     * @param robot megadott robot
     * @return
     */
    @Override
    public Movement getMovementInput(TrapperRobot robot) {

        gamePanel.setStatus(robot.getStatus());

        while(!gamePanel.isClicked()){
            gamePanel.drawArrow(robot.getPosition());
        }

        Point position = gamePanel.getClickPosition();
        gamePanel.approveClicked();
        return getMovementFromPoint(robot, position);
    }

    /**
     * A megadott robotnak egy megadott pontra való mozgatását adja vissza, vagyis hogy milyen sebessége és iránya
     * lesz a robotnak.
     * @param robot megadott robot
     * @param newPosition megadott pont
     * @return
     */
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

        double angleInRad = Math.atan2(robot.getDirection().y - robot.getPosition().y, robot.getDirection().x - robot.getPosition().x);
        double newAngleInRad = Math.atan2(newPosition.y - robot.getPosition().y, newPosition.x - robot.getPosition().x);

        movement.angleChangeInRad = newAngleInRad - angleInRad;

        return movement;
    }

    /**
     * Beállítja a játékpanelen az üzenetet a megadott üzenetre.
     * @param message megadott üzenet
     */
    @Override
    public void print(String message) {
        gamePanel.setMessage(message);
    }

    /**
     * Visszaadja a csapda típusát, amit a megadott robot rakott le.
     * @param robot megadott robot
     * @return
     */
    @Override
    public TrapTypes getTrapInput(TrapperRobot robot) {
        while(!gamePanel.isTrapped())
            ;
        gamePanel.setTrapped();
        return gamePanel.getTrapType();
    }

    /**
     * A játék befejezése, egy dialógusablakot jelenít meg, amelyben a paraméterként
     * megadott győztes nevét láthatjuk.
     * @param winner megadott győztes
     */
    @Override
    public void gameOver(String winner) {
        if (winner != null) {
            JOptionPane.showMessageDialog(gamePanel, "Game Over \nThe winner is " + winner);
        } else  {
            JOptionPane.showMessageDialog(gamePanel, "Game Over \nResult is a DRAW");
        }
        gamePanel.gameOver();
    }

    /**
     * Beállítja a játékpanelt a megadott játékpanelre.
     * @param gamePanel megadott játékpanel
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
