package Puzzle;


import Piece.*;
import ChessGame.*;


public class Puzzle1 extends Board {

    public Puzzle1()
    {
        super("Puzzle", "1");
    }
    
    @Override
    public void addPiece()
    {
        pieceList.add(new Rook(this, 7, 0, false));
        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Rook(this, 4, 1, true));
        pieceList.add(new Pawn(this, 7, 1, false));
        pieceList.add(new King(this, 2, 2, false));
        pieceList.add(new Rook(this, 0, 3, false));
        pieceList.add(new Pawn(this, 2, 3, false));
        pieceList.add(new Pawn(this, 2, 4, true));
        pieceList.add(new Pawn(this, 3, 4, false));
        pieceList.add(new Pawn(this, 3, 5, true));
        pieceList.add(new Pawn(this, 7, 5, true));
        pieceList.add(new Rook(this ,4, 7, true));
        pieceList.add(new King(this, 6, 7, true));
    }
    public static boolean solution(Movement move){
        return (move.preColumn == 4 && move.preRow == 7 && move.newColumn == 4 && move.newRow == 2);
    }
}

