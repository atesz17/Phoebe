package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static int PLAYER_STATUS_PANEL_WIDTH = 200;
    private GraphicGame graphicGame;
    private MouseController mouseController = new MouseController();

    private JPanel playerStatusPanel;
    private JTextArea playerStatusTextArea;
    private JButton glueButton;
    private JButton oilButton;
    private JButton noneButton;

    private JTextArea messageArea;

    private GameActionPanel gameActionPanel;

    public GamePanel(GraphicGame graphicGame) {
        this.graphicGame = graphicGame;

        playerStatusPanel = new JPanel();
        playerStatusPanel.setSize(PLAYER_STATUS_PANEL_WIDTH, getLevel().getGameMap().getHeight());
        playerStatusTextArea = new JTextArea("asdads");
        playerStatusTextArea.setSize(PLAYER_STATUS_PANEL_WIDTH, getLevel().getGameMap().getHeight());
        playerStatusPanel.add(playerStatusTextArea);

        messageArea = new JTextArea();

        gameActionPanel = new GameActionPanel(this);
        gameActionPanel.addMouseListener(mouseController);
        gameActionPanel.addMouseMotionListener(mouseController);

        setLayout(new BorderLayout());
        add(messageArea, BorderLayout.PAGE_START);
        add(gameActionPanel, BorderLayout.CENTER);
        add(playerStatusPanel, BorderLayout.LINE_END);
    }

    public Level getLevel() {
        return graphicGame.getLevel();
    }

    public boolean isClicked() {
        return mouseController.isClicked();
    }

    public Point getMousePosition(){
        return mouseController.getMousePosition();
    }

    public Point getClickPosition(){
        return mouseController.getClickPosition();
    }

    public void drawArrow(Point robotPosition) {
        gameActionPanel.drawArrow(robotPosition, mouseController.getMousePosition());
    }

    public void approveClicked() {
        mouseController.setClicked();
    }

    public void setMessage(String message){
        messageArea.setText(message);
    }

    public void gameOver() {
        graphicGame.finishGame();
    }
}
