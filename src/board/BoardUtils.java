package board;

public class BoardUtils 
{
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGTH_COLUMN = initColumn(7);
    
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils()
    {
        throw new RuntimeException("Not to be instantiated.");
    }
    
    /**
     * Takes a column number and stores a boolean value of true in a boolean
     * array at the position corresponding the that tile coordinate, then adds 
     * the number of tiles in a row to the column number and repeating, until 
     * it exceeds the 64 tiles on the board. This will fill the column array
     * with all coordinates of the given column.
     * 
     * @param columnNumber
     * @return column boolean array
     */
    private static boolean[] initColumn(int columnNumber) 
    {
        final boolean[] column = new boolean[NUM_TILES];
        
        do
        {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        }
        while (columnNumber < NUM_TILES);
        return column;
    }

    public static boolean isValidTileCoordinate(final int coordinate)
    {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }
}
