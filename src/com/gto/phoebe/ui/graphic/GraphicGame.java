package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.util.PhoebeException;

import javax.swing.*;
import java.io.InputStream;

public class GraphicGame {

    private JFrame frame;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private GraphicInterface graphicInterface;
    private Level level;


    public GraphicGame() {
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        graphicInterface = new GraphicInterface(gamePanel);
        frame = new JFrame("Phoebe");
        frame.setContentPane(menuPanel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public void startGame(int numberOfTurns, InputStream map){
        System.out.println("elindult...");
        frame.remove(menuPanel);
        frame.setContentPane(gamePanel);
        try {
            level = new Level(numberOfTurns, map, graphicInterface);
            level.startGame();
        } catch (PhoebeException e) {
            //TODO normalisan...
            e.printStackTrace();
        }
    }

    public void finishGame(){

    }
}
