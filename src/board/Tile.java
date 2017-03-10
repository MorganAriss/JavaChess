package Board;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import javax.swing.JButton;

import Chess.*;
import Pieces.*;

public class Tile extends JButton implements Serializable {

    private static final long serialVersionUID = 1L;
    private Piece pieceOnTile;
    private Image pieceImage;
    private boolean isOccupied;
    private int position;
    private ImageObserver imageObserver;
    
    public Tile(){
        this.pieceOnTile = null;
        this.isOccupied = false;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public void putPiece(final Piece givenPiece){
        if(givenPiece == null){
            this.repaint();
        }else{
            System.out.println("Placing piece...");
            this.pieceOnTile = givenPiece;
            this.isOccupied = true;
            this.pieceImage = this.pieceOnTile.getIcon().getImage();
            this.imageObserver = this.pieceOnTile.getIcon().getImageObserver();
            givenPiece.setPosition(this.position);
            this.repaint();
        }
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
     
     public Player pieceOwner(){
         if(this.isOccupied){
             return pieceOnTile.getOwner();
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
