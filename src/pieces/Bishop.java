package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import board.Move.AttackMove;
import board.Move.MajorMove;

public class Bishop extends Piece 
{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9};
    
    Bishop(final int piecePosition, final Allegiance pieceAllegiance) {
        super(piecePosition, pieceAllegiance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {
        final List<Move> legalMoves = new ArrayList<>();
        
        for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES)
        {
            int candidateDestinationCoordinate = this.piecePosition;
            
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEigthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))
                {
                    break;
                }
                
                candidateDestinationCoordinate += candidateCoordinateOffset;
                
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
                {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied())
                    {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                    else
                    {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Allegiance pieceAllegiance = pieceAtDestination.getPieceAllegiance();       
                        if(this.pieceAllegiance != pieceAllegiance)
                        {
                            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        
        return Collections.unmodifiableList(legalMoves);
    }
    
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }
    
    private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
