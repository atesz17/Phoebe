package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.TrapTypes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlHandler extends MouseAdapter implements ActionListener {

    private boolean clicked = false;
    private Point mousePosition = new Point(0, 0);
    private Point clickPosition = new Point(0, 0);

    private boolean trapped = false;
    private TrapTypes trapType;

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == GamePanel.oilButton){
            trapped = true;
            trapType = TrapTypes.OIL;
        }
        if (actionEvent.getSource() == GamePanel.glueButton){
            trapped = true;
            trapType = TrapTypes.GLUE;
        }
        if (actionEvent.getSource() == GamePanel.noneButton){
            trapped = true;
            trapType = TrapTypes.NONE;
        }
    }

    public boolean isTrapped() {
        return trapped;
    }

    public TrapTypes getTrapType(){
        return trapType;
    }

    public void setTrapped() {
        trapped = false;
    }
}
