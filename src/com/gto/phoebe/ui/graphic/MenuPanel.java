package com.gto.phoebe.ui.graphic;

import com.gto.phoebe.Main;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel implements ActionListener {

    // maga a játék
    private GraphicGame graphicGame;

    // pálya kiválasztásához szükséges panel
    private JPanel loadMapPanel;
    // pálya kiválasztásához szükséges címke
    private JLabel loadMapLabel;
    // pálya kiválasztásához szükséges gomb
    private JButton loadMapButton;
    // pálya ebbe a fájlba kerül
    private File loadedMap;
    // pálya kiválasztásáért felelős fájlkiválasztó
    private JFileChooser loadMapFileChooser;

    // körök kiválasztásához szükséges panel
    private JPanel numberOfTurnsPanel;
    // körök kiválasztásához szükséges címke
    private JLabel numberOfTurnsLabel;
    // körök kiválasztásához szükséges szövegdoboz
    private JTextField numberOfTurnsTextArea;

    // játékosok kiválasztásához szükséges panel
    private JPanel playersPanel;
    // játékosok kiválasztásához szükséges címke
    private JLabel playersLabel;
    // játékosok nevei ebbe a listába kerülnek
    private List<JTextField> playersList = new ArrayList<JTextField>();
    // új játékos felvételéhez szükséges gomb
    private JButton addPlayerButton;

    // játék kezdéséért felelős gomb
    private JButton startButton;

    /**
     * Konstruktor, létrehozza a graphicGame segítségével a menüpanelt.
     * @param graphicGame maga a játék
     */
    public MenuPanel(GraphicGame graphicGame) throws URISyntaxException {
        this.graphicGame = graphicGame;

        loadMapPanel = new JPanel(new FlowLayout());
        loadMapFileChooser = new JFileChooser();
        loadMapLabel = new JLabel("Load map");
        loadMapButton = new JButton("Browse");
        loadMapButton.addActionListener(this);
        loadMapPanel.add(loadMapLabel);
        loadMapPanel.add(loadMapButton);

        //TODO felesleges exception majd, ha ezt kiveszem
        if(Main.RUNLEVEL.equals("DEBUG")){
            loadedMap = new File(ClassLoader.getSystemResource("resources/map.bmp").toURI());
        }

        numberOfTurnsPanel = new JPanel(new FlowLayout());
        numberOfTurnsLabel = new JLabel("Number of turns");
        numberOfTurnsTextArea = new JTextField("10");
        numberOfTurnsPanel.add(numberOfTurnsLabel);
        numberOfTurnsPanel.add(numberOfTurnsTextArea);

        playersPanel = new JPanel(new FlowLayout());
        playersLabel = new JLabel("Set players");
        addPlayerButton = new JButton("Add new player");
        addPlayerButton.addActionListener(this);
        playersPanel.add(playersLabel);
        playersPanel.add(addPlayerButton);

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(loadMapPanel);
        add(numberOfTurnsPanel);
        add(playersPanel);

        if(Main.RUNLEVEL.equals("DEBUG")){
            playersList.add(new JTextField("UBUL"));
            playersList.add(new JTextField("BELA"));
            int playerNum = playersList.size();
            for(int i = 0; i < playerNum; i++) {
                JPanel playerPanel = new JPanel(new FlowLayout());
                playerPanel.add(new JLabel("Player" + i + 1));
                playerPanel.add(playersList.get(i));
                add(playerPanel);
            }
        }

        add(startButton);
    }

    /**
     * A különböző események kezelése itt történik(pl. a gombok megnyomása).
     * @param actionEvent esemény
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loadMapButton){
            int returnVal = loadMapFileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                loadedMap = loadMapFileChooser.getSelectedFile();
                loadMapButton.setText(loadedMap.getName());
            }
        }

        if(actionEvent.getSource() == addPlayerButton){
            remove(startButton);
            JTextField player = new JTextField("");
            player.setColumns(20);
            playersList.add(player);
            int playerNum = playersList.size();
            JPanel playerPanel = new JPanel(new FlowLayout());
            playerPanel.add(new JLabel("Player" + (playerNum)));
            playerPanel.add(playersList.get(playerNum - 1));
            add(playerPanel);
            add(startButton);
            revalidate();
        }

        if (actionEvent.getSource() == startButton){
            try {
                validateStartParameters();
                int numberOfTurns = Integer.parseInt(numberOfTurnsTextArea.getText());
                graphicGame.startGame(numberOfTurns, new FileInputStream(loadedMap), getPlayerNames());
            } catch (PhoebeException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (FileNotFoundException fnfe){
                // az elobb valasztottuk ki, ilyen elvileg nem lehet
                fnfe.printStackTrace();
            }
        }
    }

    /**
     * Visszaadja a játékosok neveinek listáját és szövegdobozba teszi.
     * @return
     */
    private List<String> getPlayerNames() {
        List<String> ret = new ArrayList<String>();
        for(JTextField jTextField : playersList){
            ret.add(jTextField.getText());
        }
        return ret;
    }

    /**
     * A különböző kezdeti feltételek megadása(pl. hogy a felhasználó a megfelelő értékeket írja be a szövegdobozokba).
     */
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

        if(playersList.size() < 2){
            errorList.add(new ErrorDescriber("Minimum number of players: 2"));
        }
        for(JTextField player : playersList){
            String playerName = player.getText();
            if(playerName.isEmpty()){
                errorList.add(new ErrorDescriber("Player name must be set."));
            }
            for(JTextField otherPlayer : playersList){
                if(otherPlayer != player && playerName.equals(otherPlayer.getText())){
                    //TODO ne ketszer irja ki
                    errorList.add(new ErrorDescriber("Player names must be different."));
                }
            }
        }

        if(!errorList.getErrors().isEmpty()){
            throw new PhoebeException(errorList);
        }
    }
}
