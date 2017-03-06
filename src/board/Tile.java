package Board;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Tile extends JButton {

    private Piece pieceOnTile;
    private Image pieceImage;
    private boolean isOccupied;
    private int position;

    private ImageObserver imageObserver;
        
    public Tile(Piece p){
        this.pieceOnTile = p;
        this.isOccupied = true;
    }
    
    public Tile(){
        this.pieceOnTile = null;
        this.isOccupied = false;
    }
    
    public void putPiece(Piece givenPiece){
        this.pieceOnTile = givenPiece;
        this.isOccupied = true;
        this.pieceImage = this.pieceOnTile.getIcon().getImage();

        this.imageObserver = this.pieceOnTile.getIcon().getImageObserver();
        
        this.repaint();
    } 

     public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(pieceImage,  0 , 0 , 
                    getWidth() , getHeight() , 
                    imageObserver);
        }        
    
    public Piece takePiece(){
        if (this.isOccupied == true){
            Piece tempPiece = this.pieceOnTile;
            nullifyTile();
            return tempPiece;
        }
        else return null;
    }
    
    public Allegiance pieceAllegiance(){
        if(isOccupied){
            return pieceOnTile.getPieceAllegiance();
        } else return null;
    }
    
    public boolean check(){
        return this.isOccupied;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public void nullifyTile(){
        this.isOccupied = false;
        this.pieceOnTile = null;
        this.pieceImage = null;
        putPiece(null);
    }
}
