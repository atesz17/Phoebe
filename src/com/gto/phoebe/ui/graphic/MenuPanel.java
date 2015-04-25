package com.gto.phoebe.ui.graphic;

import javax.swing.*;
import java.io.InputStream;

public class MenuPanel extends JPanel{
    private GraphicGame graphicGame;

    public MenuPanel(GraphicGame graphicGame) {
        this.graphicGame = graphicGame;
    }

    public int getNumberOfTurns() {
        //TODO implement
        return 0;
    }

    public InputStream getMap() {
        //TODO implement
        return null;
    }
}
