package Chess;

import java.io.Serializable;

import Board.*;
import Pieces.*;

public class Chess implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int turnCounter = 1;
    public static Player whitePlayer = new Player("White", 1);
    public static Player blackPlayer = new Player("Black", 2);
    private static Player playerTurn;
    public static Hand gameHand = new Hand();
    public Board ChessBoard = new Board();
    private static Tile takenFrom;
    private static int numOcc;

    public static void main(String[] args) {
        initializeChessBoard();
        startTurns();
    }
    
    private static void initializeChessBoard(){
        System.out.println("Creating Chessboard...");
        initializePieces();
    }
    
    private static void initializePieces(){
        
        System.out.println("Initializing Pieces...");
        initializePawns(8, whitePlayer);
        initializePawns(48, blackPlayer);
        initializePowerPieces(0, whitePlayer);
        initializePowerPieces(56, blackPlayer);
        System.out.println("Pieces Created...");
    }
    
    private static void initializePawns(int start, Player owner){
        
        for(int i = start; i < (start + 8); i++){
            Board.oneD[i].putPiece(new Pawn(owner, i));
        }
    }
    
    private static void initializePowerPieces(int start, Player owner){
        
        Board.oneD[start].putPiece(new Rook(owner, start));
        Board.oneD[start+1].putPiece(new Knight(owner, start+1));
        Board.oneD[start+2].putPiece(new Bishop(owner, start+2));
        Board.oneD[start+3].putPiece(new Queen(owner, start+3));
        Board.oneD[start+4].putPiece(new King(owner, start+4));
        Board.oneD[start+5].putPiece(new Bishop(owner, start+5));
        Board.oneD[start+6].putPiece(new Knight(owner, start+6));
        Board.oneD[start+7].putPiece(new Rook(owner, start+7));
    }
    
    private static void startTurns(){
        
        playerTurn = whitePlayer;
        System.out.println("The game has began and it is the white players turn.");
    }
    
    private static void passTurn(){
        
        if (playerTurn == whitePlayer){
            playerTurn = blackPlayer;
            turnCounter++;
            System.out.println("The turn passes to the black player.");
            System.out.println("It is now turn: " + turnCounter);
        } else {
            playerTurn = whitePlayer;
            turnCounter++;
            System.out.println("The turn passes to the white player.");
            System.out.println("It is now turn: " + turnCounter);
        }
    }
    
    public static void HandleAction(Tile passedTile){
        if(gameHand.check() == true){
            HandlePlacement(passedTile);
        } else if(gameHand.check() == false){
            HandleSelection(passedTile);
        } else {
            System.out.println("Please select a " + playerTurn.color() + " piece.");
        }
    }
    
    public static Player checkTurn(){
        return playerTurn;
    }

    private static void HandleSelection(Tile passedTile) {
        System.out.println("Handling Selection of tile " + passedTile.getPosition() + "...");
        if (passedTile.check() && checkTurn() == passedTile.pieceOwner()){
            takenFrom = passedTile;
            gameHand.grabPiece(passedTile.takePiece());
        } else {
            System.out.println("Please select a " + playerTurn.color() + " piece.");
        }
    }
    
    private static void HandlePlacement(Tile passedTile){
        
        if (passedTile.check() == false && isValid(passedTile)){
            passedTile.putPiece(gameHand.getPiece());
            passTurn();
        } else if (passedTile.check() == true && passedTile.pieceOwner() != checkTurn() && isValid(passedTile)){
            passedTile.nullifyTile();
            passedTile.putPiece(gameHand.getPiece());
        } else {
            takenFrom.putPiece(gameHand.getPiece());
        }
    }
    
    private static boolean isValid(Tile passedTile){
        Tile[] temp = gameHand.checkPiece().validTiles(passedTile);
        for(int i = 0; i < numOcc; i++){
            if(temp[i] == passedTile){
                return true;
            }
        }        
        return false;
    }
    
    public static void setNum(int num){
        numOcc = num;
    }
}