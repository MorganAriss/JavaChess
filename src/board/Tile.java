package board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

/*
* This class represents the tiles that will appear on the board, an abstract 
* class for tile is created as a holder, and the two states of the tile are 
* created as extending classes of tile. This is because a tile will always be 
* either occupied or not, there is no need for a tile that is neither occupied
* or empty. However, extending a shared class, will keep the tiles very
* similar.
*/

/**
 * Tile
 * 
 * Abstract definition of the tiles that make up the board. Each tile has two
 * states that are defined by the extending classes, 'occupiedTile' and 
 * 'emptyTile', which represent the two states of a tile during the game.
 * 
 * @author Morgan
 * @version 1
 */
public abstract class Tile 
{
    /*
     * This int is declared as protected and final so that once the constructor
     * creates a tile, its position will not change. This promotes immutability
     */
    protected final int tileCoordinate;
    
    /*
     * This HashMap will hold the locations of all the empty tiles at any given
     * time. This is accomplished using the method 'createAllPossibleEmptyTiles'
     */
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CASHE = createAllPossibleEmptyTiles();
    
    /*
     * This method will cycle through the 64 tiles on the game board create the
     * necessary instances of emptyTiles, and store them by tile coordinate in
     * the HashMap.
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() 
    {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        
        for (int i = 0; i < BoardUtils.NUM_TILES; i++)
        {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        
        return Collections.unmodifiableMap(emptyTileMap);
    }
    
    /*
     * This method is the only way that a tile should be created. It will
     * create an occupiedTile feeding in the occupying piece and coordinate,
     * or if there is no piece, it will return the emptyTile that already 
     * exists in the HashMap.
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece)
    {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CASHE.get(tileCoordinate);
    }
    
    private Tile(final int tileCoordinate)
    {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    
    public abstract Piece getPiece();
    
    public static final class EmptyTile extends Tile
    {
        private EmptyTile(final int coordinate)
        {
            super(coordinate);
        }
        
        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        
        @Override
        public Piece getPiece()
        {
            return null;
        }
    }
    
    public static final class OccupiedTile extends Tile
    {
        /*
         * The piece taken in by the occupied tile, should not be changed by 
         * anyone, there should be no way to reference this piece from outside
         * the class except for the getPiece method; therefore it is set to be 
         * final and private.
         */
        private final Piece pieceOnTile;
        
        private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile)
        {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        
        @Override
        public boolean isTileOccupied()
        {
            return true;
        }
        
        @Override
        public Piece getPiece()
        {
            return this.pieceOnTile;
        }
    }
}
