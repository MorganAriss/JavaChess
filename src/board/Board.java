package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    static Tile[][] tiles;
    static Tile[] oneD;
    
    public Board() {
        tiles = new Tile[BoardUtils.NUM_TILES_PER_ROW][BoardUtils.NUM_TILES_PER_ROW];
        oneD = new Tile[BoardUtils.NUM_TILES];

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
        this.setVisible(true);
        
        int k = 0;
        
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                oneD[k] = tiles[i][j];
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
        
        int passedTile = -1;
        
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            if (oneD[i] == t) {
                passedTile = i;
                
                System.out.println("Tile number " + passedTile + " has been selected.");
                
                if(Chess.checkTurn() == Allegiance.WHITE){
                    Chess.HandleAction(passedTile, Allegiance.WHITE);
                }else if(Chess.checkTurn() == Allegiance.BLACK){
                    Chess.HandleAction(passedTile, Allegiance.BLACK);
                }   
                
                /*
                if(oneD[x].check() == true){
                    if(Chess.checkTurn() == Allegiance.WHITE)
                        Chess.HandleAction(x, Allegiance.WHITE);
                    if(Chess.checkTurn() == Allegiance.BLACK)
                        Chess.HandleAction(x, Allegiance.BLACK);
                } else {
                    System.out.println("Please select a piece" + Chess.checkTurn().player() + "player.");
                }
                */
            }
        }
    }
    public boolean tileEqualsPiece(int tile){
        if(oneD[tile].pieceAllegiance().team() == Chess.checkTurn().team()){
            return true;
        } else return false;
    }
}