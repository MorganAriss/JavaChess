package Board;

public class Hand {
    
    private Piece pieceInHand;
    private int tileCoord;
    private boolean isPieceHeld;
    private Allegiance handAllegiance;
    
    Hand(Allegiance a)
    {
        this.handAllegiance = a;
        this.isPieceHeld = false;
    }

    public Piece getPiece() {
        Piece tempPiece = this.pieceInHand;
        this.nullifyHand();
        return tempPiece;
    }

    public void nullifyHand() {
        this.pieceInHand = null;
        this.setIsPieceHeld(false);
    }
    
    public void setIsPieceHeld(boolean b){
        this.isPieceHeld = b;
    }

    public int getTileCoord() {
        return tileCoord;
    }

    public void setTileCoord(int tileCoord) {
        this.tileCoord = tileCoord;
    }
    
    public void grabPiece(Piece grabbedPiece){
        this.pieceInHand = grabbedPiece;
        System.out.println("The " + handAllegiance.player() + " has selected a " + pieceInHand.name() + ".");
        this.setIsPieceHeld(true);
    }
    
    public boolean check(){
        return this.isPieceHeld;
    }
    
    public Piece checkPiece(){
        return this.pieceInHand;
    }
    
    public Allegiance getAllegiance(){
        return this.handAllegiance;
    }
}
