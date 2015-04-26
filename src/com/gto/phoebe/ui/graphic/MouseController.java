package com.gto.phoebe.ui.graphic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {

    private boolean clicked = false;
    private Point mousePosition = new Point(0, 0);
    private Point clickPosition = new Point(0, 0);

    @Override
    public void mouseClicked(MouseEvent e){
        clicked = true;
        clickPosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        mousePosition = e.getPoint();
    }

    public boolean isClicked() {
        return clicked;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    public Point getClickPosition() {
        return clickPosition;
    }

    public void setClicked() {
        this.clicked = false;
    }
}
