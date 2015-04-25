package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.util.PhoebeException;

import javax.swing.*;

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

    public void startGame(){
        try {
            level = new Level(menuPanel.getNumberOfTurns(), menuPanel.getMap(), graphicInterface);
        } catch (PhoebeException e) {
            //TODO normalisan...
            e.printStackTrace();
        }
    }

    public void finishGame(){

    }
}
