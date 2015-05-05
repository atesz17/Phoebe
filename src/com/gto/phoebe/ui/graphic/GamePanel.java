package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.Level;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static int PLAYER_STATUS_PANEL_WIDTH = 200;
    private GraphicGame graphicGame;
    private ControlHandler controlHandler = new ControlHandler();

    private JPanel playerStatusPanel;
    private JTextArea playerStatusTextArea;
    public static JButton glueButton;
    public static JButton oilButton;
    public static JButton noneButton;

    private JTextArea messageArea;

    private GameActionPanel gameActionPanel;

    public GamePanel(GraphicGame graphicGame) {
        this.graphicGame = graphicGame;

        playerStatusPanel = new JPanel(new GridLayout(4,1));
        playerStatusPanel.setSize(PLAYER_STATUS_PANEL_WIDTH, getLevel().getGameMap().getHeight());
        playerStatusTextArea = new JTextArea();
        playerStatusTextArea.setSize(PLAYER_STATUS_PANEL_WIDTH, getLevel().getGameMap().getHeight());

        glueButton = new JButton("RAGACS");
        glueButton.addActionListener(controlHandler);
        glueButton.setEnabled(false);
        oilButton = new JButton("OLAJ");
        oilButton.addActionListener(controlHandler);
        oilButton.setEnabled(false);
        noneButton = new JButton("SEMMI");
        noneButton.addActionListener(controlHandler);
        noneButton.setEnabled(false);

        playerStatusPanel.add(playerStatusTextArea);
        playerStatusPanel.add(glueButton);
        playerStatusPanel.add(oilButton);
        playerStatusPanel.add(noneButton);


        messageArea = new JTextArea();

        gameActionPanel = new GameActionPanel(this);
        gameActionPanel.addMouseListener(controlHandler);
        gameActionPanel.addMouseMotionListener(controlHandler);

        setLayout(new BorderLayout());
        add(messageArea, BorderLayout.PAGE_START);
        add(gameActionPanel, BorderLayout.CENTER);
        add(playerStatusPanel, BorderLayout.LINE_END);
    }

    public Level getLevel() {
        return graphicGame.getLevel();
    }

    public boolean isClicked() {
        return controlHandler.isClicked();
    }

    public Point getMousePosition(){
        return controlHandler.getMousePosition();
    }

    public Point getClickPosition(){
        return controlHandler.getClickPosition();
    }

    public void drawArrow(Point robotPosition) {
        gameActionPanel.drawArrow(robotPosition, controlHandler.getMousePosition());
    }

    public void approveClicked() {
        controlHandler.setClicked();
    }

    public void setMessage(String message){
        messageArea.setText(message);
    }

    public void setStatus(String status){
        playerStatusTextArea.setText(status);
    }

    public void gameOver() {
        graphicGame.finishGame();
    }

    public boolean isTrapped() {
        glueButton.setEnabled(true);
        oilButton.setEnabled(true);
        noneButton.setEnabled(true);
        return controlHandler.isTrapped();
    }

    public TrapTypes getTrapType(){
        return controlHandler.getTrapType();
    }

    public void setTrapped() {
        controlHandler.setTrapped();
        glueButton.setEnabled(false);
        oilButton.setEnabled(false);
        noneButton.setEnabled(false);
    }
}
