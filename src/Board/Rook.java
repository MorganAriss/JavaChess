package Board;

import javax.swing.ImageIcon;

public class Rook extends Piece{
    
    private ImageIcon pieceIcon;
    private int piecePosition;
    private Allegiance pieceAllegiance;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Rook.png");
    //Image blackImage = blackIcon.getImage();
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Rook.png");
    //Image whiteImage = whiteIcon.getImage();

    public Rook(int piecePosition, Allegiance pieceAllegiance) {
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

    @Override
    public Tile[] calculateLegalMoves() {
        return null;
    }
}
