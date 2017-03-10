package Board;

import Pieces.*;

import java.io.Serializable;

import Chess.*;

public class Hand implements Serializable {

    private static final long serialVersionUID = 1L;
    private Piece pieceInHand;
    private boolean isPieceHeld;
    
    public Hand()
    {
        this.isPieceHeld = false;
    }

    public Piece getPiece() {
        Piece tempPiece = this.pieceInHand;
        this.nullifyHand();
        return tempPiece;
    }

    public void nullifyHand() {
        this.pieceInHand = null;
        this.isPieceHeld = false;
    }
    
    public void grabPiece(Piece grabbedPiece){
        this.pieceInHand = grabbedPiece;
        System.out.println("The " + Chess.checkTurn().color() + " has selected a " + pieceInHand.type() + ".");
        this.isPieceHeld = true;
    }
    
    public boolean check(){
        return this.isPieceHeld;
    }
    
    public Piece checkPiece(){
        return this.pieceInHand;
    }
}
