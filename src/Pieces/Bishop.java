package Pieces;

import java.io.Serializable;

import javax.swing.ImageIcon;

import Board.*;
import Chess.*;

public class Bishop extends Piece implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private ImageIcon pieceIcon;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Bishop.png");
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Bishop.png");

    public Bishop(final Player owner, int position) {
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
    
    public String type(){
        return "Bishop";
    }

    @Override
    public Tile[] validTiles(Tile passedTile) {
        Tile[] temp = null;
        return temp;
    }
}
