package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.domain.TrapTypes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlHandler extends MouseAdapter implements ActionListener {

    // flag: le let-e nyomva az egérgomb
    private boolean clicked = false;
    // egérkurzor pozíciója
    private Point mousePosition = new Point(0, 0);
    // kattintás pozíciója
    private Point clickPosition = new Point(0, 0);

    // flag: van-e csapda lerakva
    private boolean trapped = false;
    // milyen csapda van lerakva
    private TrapTypes trapType;

    /**
     * Egérkattintást kezelő metódus.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e){
        clicked = true;
        clickPosition = e.getPoint();
    }

    /**
     * Az egér elmozdulása esetén beállítja az új pozíciót.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e){
        mousePosition = e.getPoint();
    }

    /**
     * Visszaadja clicked flaget.
     * @return
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * Visszaadja az egérkurzor pozícióját.
     * @return
     */
    public Point getMousePosition() {
        return mousePosition;
    }

    /**
     * Visszaadja az egérkattintás pozícióját.
     * @return
     */
    public Point getClickPosition() {
        return clickPosition;
    }

    /**
     * Beállítja a clicked flaget false-ra.
     */
    public void setClicked() {
        this.clicked = false;
    }

    /**
     * Itt zajlik a megadott esemény hatására(vagyis ha a megfelelő gombra nyomunk) a csapdák beállítása.
     * @param actionEvent megadott esemény
     */
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

    /**
     * Visszaadja a trapped flag értékét.
     * @return
     */
    public boolean isTrapped() {
        return trapped;
    }

    /**
     * Visszaadja a csapda típusát.
     * @return
     */
    public TrapTypes getTrapType(){
        return trapType;
    }

    /**
     * Beállítja a csapda típusát.
     */
    public void setTrapped() {
        trapped = false;
    }
}
