package Board;

public class Chess {
    
    private static int turnCounter = 1;
    private static Allegiance playerTurn;
    public static Hand WhitePlayer = new Hand(Allegiance.WHITE);
    public static Hand BlackPlayer = new Hand(Allegiance.BLACK);
    public static Board ChessBoard = new Board();
    private static int takenFrom = -1;

    public static void main(String[] args) {
        initializeChessBoard();
        startTurns();
    }
    
    private static void initializeChessBoard(){
        System.out.println("Creating Chessboard...");
        initializePieces(ChessBoard);
    }
    
    private static void initializePieces(Board b){
        
        System.out.println("Initializing Pieces...");
        initializePawns(b, 8, Allegiance.WHITE);
        initializePawns(b, 48, Allegiance.BLACK);
        initializePowerPieces(b, 0, Allegiance.WHITE);
        initializePowerPieces(b, 56, Allegiance.BLACK);
        System.out.println("Pieces Created...");
    }
    
    private static void initializePawns(Board b, int start, Allegiance a){
        
        for(int i = start; i < (start + 8); i++){
            b.oneD[i].putPiece(new Pawn(i, a));
        }
    }
    
    private static void initializePowerPieces(Board b, int start, Allegiance a){
        
        b.oneD[start].putPiece(new Rook(start, a));
        b.oneD[start+1].putPiece(new Knight(start+1, a));
        b.oneD[start+2].putPiece(new Bishop(start+2, a));
        b.oneD[start+3].putPiece(new Queen(start+3, a));
        b.oneD[start+4].putPiece(new King(start+4, a));
        b.oneD[start+5].putPiece(new Bishop(start+5, a));
        b.oneD[start+6].putPiece(new Knight(start+6, a));
        b.oneD[start+7].putPiece(new Rook(start+7, a));
    }
    
    private static void startTurns(){
        
        playerTurn = Allegiance.WHITE;
        System.out.println("The game has began and it is the white players turn.");
    }
    
    private static void passTurn(){
        
        if (playerTurn == Allegiance.WHITE){
            playerTurn = Allegiance.BLACK;
            turnCounter++;
            System.out.println("The turn passes to the black player.");
        } else {
            playerTurn = Allegiance.WHITE;
            turnCounter++;
            System.out.println("The turn passes to the white player.");
        }
    }
    
    public static void HandleAction(int z, Allegiance a){
        System.out.println("Handling Action...");
        if(Hand(a).check() == true){
            HandlePlacement(z, a);
        } else if(Hand(a).check() == false){
            if (ChessBoard.tileEqualsPiece(z)){
                HandleSelection(z, a);
            } else {
                System.out.println("Please select a " + a.player() + " piece.");
            }
        } else {
            System.out.println("Please select a " + a.player() + " piece.");
        }
    }
    
    public static Allegiance checkTurn(){
        return playerTurn;
    }

    public static void HandleSelection(int y, Allegiance a) {
        System.out.println("Handling Selection...");
        if (ChessBoard.oneD[y].check() == true){
            takenFrom = y;
            Hand(a).grabPiece(ChessBoard.oneD[y].takePiece());
        } else {
            System.out.println("Please select a piece " + checkTurn().player() + " player.");
        }
    }
    
    public static void HandlePlacement(int x, Allegiance a){
        boolean isValid = true;
        
        if (ChessBoard.oneD[x].check() == false && isValid){
            ChessBoard.oneD[x].putPiece(Hand(a).getPiece());
            passTurn();
        } else if (ChessBoard.oneD[x].check() == true && ChessBoard.oneD[x].pieceAllegiance() != checkTurn() && isValid){
            ChessBoard.oneD[x].nullifyTile();
            ChessBoard.oneD[x].putPiece(Hand(a).getPiece());
        } else {
            ChessBoard.oneD[takenFrom].putPiece(Hand(a).getPiece());
        }
    }
    private static Hand Hand(Allegiance a){
        if (a == Allegiance.WHITE){
            return WhitePlayer;
        } else {
            return BlackPlayer;
        }
    }
}