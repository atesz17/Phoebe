package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.util.PhoebeException;

import javax.swing.*;
import java.io.InputStream;
import java.util.List;

public class GraphicGame {

    private JFrame frame;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private GraphicInterface graphicInterface;
    private Level level;


    public GraphicGame() {
        menuPanel = new MenuPanel(this);
        graphicInterface = new GraphicInterface();
        frame = new JFrame("Phoebe");
        frame.setContentPane(menuPanel);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
            frame.revalidate();
            frame.setSize(level.getGameMap().getWidth() + GamePanel.PLAYER_STATUS_PANEL_WIDTH, level.getGameMap().getHeight());

            Thread t1 = new Thread(level);
            t1.start();
        } catch (PhoebeException e) {
            JOptionPane.showMessageDialog(gamePanel, e.getMessage());
        }
    }

    public void finishGame(){
        frame.remove(gamePanel);
        frame.setContentPane(menuPanel);
        frame.setSize(600, 600);
        frame.revalidate();
    }

    public Level getLevel() {
        return level;
    }
}
