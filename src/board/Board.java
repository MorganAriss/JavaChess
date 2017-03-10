package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Chess.*;

public class Board extends JFrame implements ActionListener, Serializable {

    private static final long serialVersionUID = 1L;
    public static Tile[][] tiles = new Tile[BoardUtils.NUM_TILES_PER_ROW][BoardUtils.NUM_TILES_PER_ROW];
    public static Tile[] oneD = new Tile[BoardUtils.NUM_TILES];
    
    public Board() {

        JPanel panel = new JPanel(new GridLayout(BoardUtils.NUM_TILES_PER_ROW, BoardUtils.NUM_TILES_PER_ROW));
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].addActionListener(this);
                setDefaultTile(i, j);
                panel.add(tiles[i][j]);
            }
        }
        this.add(panel);
        this.setSize(600, 600);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGameAction();
            }
        });

        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGameAction();
            }
        });
        file.add(load);
        file.add(save);
        
        this.setJMenuBar(menuBar);
        
        this.setVisible(true);
        
        
        int k = 0;
        
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                Board.oneD[k] = tiles[i][j];
                Board.oneD[k].setPosition(k);
                k++;
            }
        }
    }
    
    private void setDefaultTile(int row, int col) {
        tiles[row][col].setText(null);
        tiles[row][col].setBackground((row + col) % 2 == 0 ? Color.white : Color.red);
    }

    public void remove(int row, int col) {
        setDefaultTile(row, col);
    }

    public void actionPerformed(ActionEvent e) {
        Tile t = (Tile) e.getSource();

        Tile passedTile = t;
        
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            if (oneD[i] == t) {
                passedTile = (Tile) Board.oneD[i];
                
                System.out.println("Tile number " + passedTile.getPosition() + " has been selected.");
                
                Chess.HandleAction(passedTile);
            }   
        }
    }
    
    public void saveGameAction() {
        try {
            FileOutputStream fs = new FileOutputStream("test.gam");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadGameAction() {
        try {
            this.dispose();
            FileInputStream fileStream = new FileInputStream("test.gam");
            ObjectInputStream is = new ObjectInputStream(fileStream);
            JFrame restore = (JFrame) is.readObject();
            restore.setVisible(true);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}