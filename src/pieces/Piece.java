package pieces;

import java.util.Collection;

import board.Board;
import board.Move;

/**
 * Piece
 * 
 * 
 * 
 * @author Morgan
 * @version 1
 */
public abstract class Piece {
    
    protected final int piecePosition;
    protected final Allegiance pieceAllegiance;
    
    Piece(final int piecePosition, final Allegiance pieceAllegiance)
    {
        this.piecePosition = piecePosition;
        this.pieceAllegiance = pieceAllegiance;
    }
    
    public Allegiance getPieceAllegiance()
    {
        return this.pieceAllegiance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
