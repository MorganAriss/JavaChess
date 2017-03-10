package Pieces;

import java.io.Serializable;

import javax.swing.ImageIcon;

import Board.*;
import Chess.*;

public class Pawn extends Piece implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private ImageIcon pieceIcon;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Pawn.png");
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Pawn.png");

    public Pawn(Player owner, int position) {
        super(owner, position);        
        chooseIcon(owner);
    }
    
    public ImageIcon getIcon(){
        System.out.println("Getting icon...");
        return this.pieceIcon;
    }
    
    public void chooseIcon(Player owner){
        if (owner == Chess.whitePlayer){
            this.pieceIcon = whiteIcon;
        }else{
            this.pieceIcon = blackIcon;
        }
    }
    
    @Override
    public Player getOwner(){
        System.out.println("Getting piece owner...");
        return owner;
    }
    
    public String type(){
        return "Pawn";
    }

    @Override
    public Tile[] validTiles(Tile passedTile) {
        Tile[] temp = null;
        return temp;
    }
}
