package Pieces;

import java.io.Serializable;

import javax.swing.ImageIcon;
import Chess.*;
import Board.*;

public abstract class Piece implements Serializable {

    private static final long serialVersionUID = 1L;
    protected final Player owner;
    protected int piecePosition;
    private ImageIcon pieceIcon;
    
    public Piece(Player owner, int position){
        this.owner = owner;
        this.piecePosition = position;
    }
    
    public void setPosition(int position){
        this.piecePosition = position;
    }
    
    public int getPosition(){
        return this.piecePosition;
    }
    
    public ImageIcon getIcon(){
        System.out.println("Getting icon...");
        return this.pieceIcon;
    }
    
    public Player getOwner(){
        System.out.println("Getting piece owner...");
        return this.owner;
    }
    
    public String type(){
        return "Piece";
    }
    
    public abstract Tile[] validTiles(Tile passedTile);
}
