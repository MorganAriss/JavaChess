package Board;

import javax.swing.ImageIcon;

public class Bishop extends Piece{
    
    private ImageIcon pieceIcon;
    private int piecePosition;
    private Allegiance pieceAllegiance;
    
    static ImageIcon blackIcon = new ImageIcon("pictures/Black_Bishop.png");
    //Image blackImage = blackIcon.getImage();
    static ImageIcon whiteIcon = new ImageIcon("pictures/White_Bishop.png");
    //Image whiteImage = whiteIcon.getImage();

    public Bishop(int piecePosition, Allegiance pieceAllegiance) {
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
