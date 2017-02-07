package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;

import static board.Move.*;

public class Knight extends Piece
{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
    
    Knight(final int piecePostion, final Allegiance pieceAllegiance) 
    {
        super(piecePostion, pieceAllegiance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<Move>();
        
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
        {
            candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEigthColumnExclusion(this.piecePosition, currentCandidateOffset))
                {
                    continue;
                }
                
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
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
    
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || 
                candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }
    
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 ||
                candidateOffset == 6);
    }
    
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 ||
                candidateOffset == 10);
    }
    private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGTH_COLUMN[currentPosition] && (candidateOffset == -15 ||
                candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
    }
}
