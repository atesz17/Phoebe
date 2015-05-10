package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.TrapTypes;
import com.gto.phoebe.logic.Level;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // a játékosok adatainak tárolására alkalmas panel szélessége
    public static int PLAYER_STATUS_PANEL_WIDTH = 200;
    // maga a játék
    private GraphicGame graphicGame;
    // az irányításért felelős
    private ControlHandler controlHandler = new ControlHandler();

    // a játékosok adatainak tárolására alkalmas panel
    private JPanel playerStatusPanel;
    // a játékosok adatainak tárolására alkalmas szövegterületek
    private JTextArea playerStatusTextArea;
    // ragacs lerakására alkalmas gomb
    public static JButton glueButton;
    // olaj lerakására alkalmas gomb
    public static JButton oilButton;
    // gomb, ha nem akarunk lerakni csapdát
    public static JButton noneButton;

    // üzenetekre alkalmas szövegterület
    private JTextArea messageArea;

    // a pálya komponenseinek megrajzolásáért felelős
    private GameActionPanel gameActionPanel;

    /**
     * Konstruktor, ami létrehozza a játék felületét: a pályát, a gombokat, a különböző szövegdobozokat.
     * @param graphicGame
     */
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

    /**
     * Visszaadja a pályát.
     * @return
     */
    public Level getLevel() {
        return graphicGame.getLevel();
    }

    /**
     * Visszaadja a controlHandler isClicked flag értékét.
     * @return
     */
    public boolean isClicked() {
        return controlHandler.isClicked();
    }

    /**
     * Visszaadja a controlHandlerben található egérpozíció értékét.
     * @return
     */
    public Point getMousePosition(){
        return controlHandler.getMousePosition();
    }

    /**
     * Visszaadja a controlHandlerben található kattintási pozíció értékét.
     * @return
     */
    public Point getClickPosition(){
        return controlHandler.getClickPosition();
    }

    /**
     * A megadott robot pozíciójához rajzol egy nyilat, ahova akar lépni a felhasználó.
     * @param robotPosition megadott robot pozíciója
     */
    public void drawArrow(Point robotPosition) {
        gameActionPanel.drawArrow(robotPosition, controlHandler.getMousePosition());
    }

    /**
     * Beállítja, hogy le van nyomva az egérgomb.
     */
    public void approveClicked() {
        controlHandler.setClicked();
    }

    /**
     * A megadott üzenet kiírása a szüvegdobozba.
     * @param message megadott üzenet
     */
    public void setMessage(String message){
        messageArea.setText(message);
    }

    /**
     * A megadott státusz kiírása a szüvegdobozba.
     * @param status megadott státusz
     */
    public void setStatus(String status){
        playerStatusTextArea.setText(status);
    }

    /**
     * Játék vége. Meghívja a GraphicGame játékot befejező metódusát.
     */
    public void gameOver() {
        graphicGame.finishGame();
    }

    /**
     * Beállítja az isTrapped flaget a controlHandlerben.
     * @return
     */
    public boolean isTrapped() {
        glueButton.setEnabled(true);
        oilButton.setEnabled(true);
        noneButton.setEnabled(true);
        return controlHandler.isTrapped();
    }

    /**
     * Visszaadja a csapda típusát.
     * @return
     */
    public TrapTypes getTrapType(){
        return controlHandler.getTrapType();
    }

    /**
     * Beállítja, hogy már volt kiválasztva csapda, tehát nem lehet többet lerakni.
     */
    public void setTrapped() {
        controlHandler.setTrapped();
        glueButton.setEnabled(false);
        oilButton.setEnabled(false);
        noneButton.setEnabled(false);
    }
}
