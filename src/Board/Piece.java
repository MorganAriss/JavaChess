package Board;

import javax.swing.ImageIcon;

public abstract class Piece {
    
    private ImageIcon pieceIcon;
    private int piecePosition;
    private Allegiance pieceAllegiance;
    
    Piece(final int piecePosition, final Allegiance pieceAllegiance)
    {
        this.piecePosition = piecePosition;
        this.pieceAllegiance = pieceAllegiance;
    } 
    
    public ImageIcon getIcon(){
        return this.pieceIcon;
    }
    
    public int getPieceLocation(){
        return this.piecePosition;
    }
    
    public Allegiance getPieceAllegiance(){
        return this.pieceAllegiance;
    }
    
    public String name(){
        return "Piece";
    }
    
    public abstract Tile[] calculateLegalMoves();
}
