package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.util.PhoebeException;

import javax.swing.*;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

public class GraphicGame {

    // játék kerete
    private JFrame frame;
    // menüpanel
    private MenuPanel menuPanel;
    // játékpanel
    private GamePanel gamePanel;
    // grafikus felhasználói felület
    private GraphicInterface graphicInterface;
    // pálya
    private Level level;


    /**
     * Konstruktor, ami létrehozza a menüt.
     */
    public GraphicGame() {
        try {
            menuPanel = new MenuPanel(this);
        } catch (URISyntaxException e) {
            //TODO teszt utan kivenni
            e.printStackTrace();
        }
        graphicInterface = new GraphicInterface();
        frame = new JFrame("Phoebe");
        frame.setContentPane(menuPanel);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * A játék inditása itt történik, ehhez meg kell adni a körök számát, a térképet tartalmazó fájlt
     * és a játékosok listáját. Ekkor a menü helyén megjelenik a pálya térképe.
     * @param numberOfTurns körök száma
     * @param map képfájl ami a térképet tartalmazza
     * @param players játékosok listája
     */
    public void startGame(int numberOfTurns, InputStream map, List<String> players){
        try {
            level = new Level(numberOfTurns, map, graphicInterface);
            for(String playerName : players){
                level.addPlayer(playerName);
            }
            gamePanel = new GamePanel(this);
            graphicInterface.setGamePanel(gamePanel);

            frame.remove(menuPanel);
            frame.setContentPane(gamePanel);
            frame.pack();
            gamePanel.revalidate();
            frame.setSize(level.getGameMap().getWidth() + GamePanel.PLAYER_STATUS_PANEL_WIDTH, level.getGameMap().getHeight());

            Thread t1 = new Thread(level);
            t1.start();
        } catch (PhoebeException e) {
            JOptionPane.showMessageDialog(gamePanel, e.getMessage());
        }
    }

    /**
     * A játék befejezése, a játékpanel eltűnik, a menü jön elő újra.
     */
    public void finishGame(){
        frame.remove(gamePanel);
        frame.setContentPane(menuPanel);
        frame.setSize(600, 600);
        menuPanel.revalidate();
    }

    /**
     * Visszaadja a pályát.
     * @return
     */
    public Level getLevel() {
        return level;
    }
}
