package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.util.ErrorDescriber;
import com.gto.phoebe.util.ErrorList;
import com.gto.phoebe.util.PhoebeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuPanel extends JPanel implements ActionListener {
    private GraphicGame graphicGame;
    private JPanel loadMapPanel;
    private JLabel loadMapLabel;
    private JButton loadMapButton;
    private File loadedMap;
    private JFileChooser loadMapFileChooser;
    private JPanel numberOfTurnsPanel;
    private JLabel numberOfTurnsLabel;
    private JTextArea numberOfTurnsTextArea;
    private JButton startButton;

    public MenuPanel(GraphicGame graphicGame) {
        this.graphicGame = graphicGame;

        loadMapPanel = new JPanel(new FlowLayout());
        loadMapFileChooser = new JFileChooser();
        loadMapLabel = new JLabel("Load map");
        loadMapButton = new JButton("Browse");
        loadMapButton.addActionListener(this);
        loadMapPanel.add(loadMapLabel);
        loadMapPanel.add(loadMapButton);

        numberOfTurnsPanel = new JPanel(new FlowLayout());
        numberOfTurnsLabel = new JLabel("Number of turns");
        numberOfTurnsTextArea = new JTextArea("10");
        numberOfTurnsPanel.add(numberOfTurnsLabel);
        numberOfTurnsPanel.add(numberOfTurnsTextArea);

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(loadMapPanel);
        add(numberOfTurnsPanel);
        add(startButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loadMapButton){
            int returnVal = loadMapFileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                loadedMap = loadMapFileChooser.getSelectedFile();
                loadMapButton.setText(loadedMap.getName());
            }
        }
        if (actionEvent.getSource() == startButton){
            try {
                validateStartParameters();
                int numberOfTurns = Integer.parseInt(numberOfTurnsTextArea.getText());
                graphicGame.startGame(numberOfTurns, new FileInputStream(loadedMap));
            } catch (PhoebeException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (FileNotFoundException fnfe){
                // az elobb valasztottuk ki, ilyen elvileg nem lehet
                fnfe.printStackTrace();
            }
        }
    }

    private void validateStartParameters() throws PhoebeException {
        ErrorList errorList = new ErrorList();
        int numberOfTurns = 0;
        try {
            numberOfTurns = Integer.parseInt(numberOfTurnsTextArea.getText());
        } catch (Exception e){
            errorList.add(new ErrorDescriber("The number of turns must be an integer."));
        }
        if(numberOfTurns < 1){
            errorList.add(new ErrorDescriber("The number of turns must be positive."));
        }
        if(loadedMap == null){
            errorList.add(new ErrorDescriber("Map is not selected."));
        }
        if(!errorList.getErrors().isEmpty()){
            throw new PhoebeException(errorList);
        }
    }
}
