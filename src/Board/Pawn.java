package Board;

import javax.swing.ImageIcon;

public class Pawn extends Piece{
    
    private ImageIcon pieceIcon;
    private int piecePosition;
    private Allegiance pieceAllegiance;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Pawn.png");
    //Image blackImage = blackIcon.getImage();
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Pawn.png");
    //Image whiteImage = whiteIcon.getImage();

    public Pawn(int piecePosition, Allegiance pieceAllegiance) {
        super(piecePosition, pieceAllegiance);        
        chooseIcon(pieceAllegiance);
    }
    
    public ImageIcon getIcon(){
        return this.pieceIcon;
    }
    
    public void chooseIcon(Allegiance a){
        if (a == Allegiance.WHITE){
            this.pieceIcon = whiteIcon;
        }else{
            this.pieceIcon = blackIcon;
        }
    }
    
    public int getPieceLocation(){
        return this.piecePosition;
    }
    
    public Allegiance getPieceAllegiance(){
        return this.pieceAllegiance;
    }
    
    public String name(){
        return "pawn";
    }

    @Override
    public Tile[] calculateLegalMoves() {
        return null;
    }
}
