package Pieces;

import java.io.Serializable;

import javax.swing.ImageIcon;

import Chess.*;
import Board.*;

public class Knight extends Piece implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private ImageIcon pieceIcon;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Knight.png");
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Knight.png");
    
    public Knight(Player owner, int position) {
        super(owner, position);       
        chooseIcon(owner);
    }
    
    public ImageIcon getIcon(){
        return this.pieceIcon;
    }
    
    public void chooseIcon(Player owner){
        if (owner == Chess.whitePlayer){
            this.pieceIcon = whiteIcon;
        }else{
            this.pieceIcon = blackIcon;
        }
    }
    
    public Player getOwner(){
        return this.owner;
    }
    
    public int getPosition(){
        return this.piecePosition;
    }
    
    public String type(){
        return "Knight";
    }

    @Override
    public Tile[] validTiles(Tile passedTile) {
        Tile[] temp = new Tile[10];
        int k = 0;
        int piecePos = Chess.gameHand.checkPiece().getPosition();
        if (BoardUtils.FIRST_COLUMN[piecePos]){
            temp = firstColumnRestrictions(temp, piecePos, k);
        } else if (BoardUtils.SECOND_COLUMN[piecePos]){
            temp = secondColumnRestrictions(temp, piecePos, k);
        } else if (BoardUtils.SEVENTH_COLUMN[piecePos]){
            temp = seventhColumnRestrictions(temp, piecePos, k);
        } else if (BoardUtils.EIGTH_COLUMN[piecePos]){
            temp = eigthColumnRestrictions(temp, piecePos, k);
        } else {
            temp = noRestrictions(temp, piecePos, k);
        }
        return temp;
    }
    
    private static Tile[] firstColumnRestrictions(Tile[] temp, int piecePos, int k){
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            int current = Board.oneD[i].getPosition();
            if(current == piecePos + 10 || current == piecePos + 17 
                    || current == piecePos - 6 || current == piecePos - 15){
                temp[k] = Board.oneD[i];
                k++;
            }
        } 
        Chess.setNum(k);
        return temp;
    }
    
    private static Tile[] secondColumnRestrictions(Tile[] temp, int piecePos, int k){
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            int current = Board.oneD[i].getPosition();
            if(current == piecePos + 10 || current == piecePos + 15 || current == piecePos + 17 
                    || current == piecePos - 6 || current == piecePos - 15 || current == piecePos - 17){
                temp[k] = Board.oneD[i];
                k++;
            }
        } 
        Chess.setNum(k);
        return temp;
    }
    
    private static Tile[] seventhColumnRestrictions(Tile[] temp, int piecePos, int k){
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            if(i == piecePos + 6 || i == piecePos + 15 || i == piecePos + 17 
                    || i == piecePos - 10 || i == piecePos - 15 || i == piecePos - 17){
                temp[k] = Board.oneD[i];
                k++;
            }
        } 
        Chess.setNum(k);
        return temp;
    }
    
    private static Tile[] eigthColumnRestrictions(Tile[] temp, int piecePos, int k){
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            int current = Board.oneD[i].getPosition();
            if(current == piecePos + 6 || current == piecePos + 15 
                    || current == piecePos - 10 || current == piecePos - 17){
                temp[k] = Board.oneD[i];
                k++;
            }
        } 
        Chess.setNum(k);
        return temp;
    }
    
    private static Tile[] noRestrictions(Tile[] temp, int piecePos, int k){
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            int current = Board.oneD[i].getPosition();
            if(current == piecePos + 6 || current == piecePos + 10 || current == piecePos + 15 || current == piecePos + 17 
                    || current == piecePos - 6 || current == piecePos - 10 || current == piecePos - 15 || current == piecePos - 17){
                temp[k] = Board.oneD[i];
                k++;
            }
        } 
        Chess.setNum(k);
        return temp;
    }
}
