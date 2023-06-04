package Puzzle;


import Piece.*;
import ChessGame.*;


public class Puzzle2 extends Board {

    public Puzzle2()
    {
        super("Puzzle", "2");
    }

    @Override
    public void addPiece()
    {
        pieceList.add(new King(this, 0, 0, false));
        pieceList.add(new Rook(this, 3, 0, false));
        pieceList.add(new Rook(this, 6, 0, false));
        pieceList.add(new Knight(this, 0, 1, false));
        pieceList.add(new Rook(this, 1, 1, true));
        pieceList.add(new Rook(this, 7, 1, true));
        pieceList.add(new Pawn(this, 0, 2,false));
        pieceList.add(new Bishop(this, 3, 2, true));
        pieceList.add(new Pawn(this, 6, 2, false));
        pieceList.add(new Bishop(this, 3, 3, false));
        pieceList.add(new Pawn(this, 5, 3, false));
        pieceList.add(new King(this, 0, 4, true));
        pieceList.add(new Pawn(this, 5, 4, false));
        pieceList.add(new Pawn(this, 2, 5, true));
        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));
    }

    public static boolean solution(Movement move){
        return (move.preColumn == 1 && move.preRow == 1 && move.newColumn == 0 && move.newRow == 1);
    }

}
