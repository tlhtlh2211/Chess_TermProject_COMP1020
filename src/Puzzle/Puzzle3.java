package Puzzle;


import Piece.*;
import ChessGame.*;

public class Puzzle3 extends Board {

    public Puzzle3()
    {
        super("Puzzle", "3");
    }

    @Override
    public void addPiece()
    {
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new King(this, 4, 7, true));


        // add Bishop
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 6, 1, false));
        pieceList.add(new Bishop(this, 6, 6, true));
        
        // add Knight
        
        pieceList.add(new Knight(this, 3, 3, true));

        // addRook
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        pieceList.add(new Rook(this, 3, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        //addPawn
        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 3, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 2, false));
        pieceList.add(new Pawn(this, 7, 1, false));

        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 5, 5, true));
        pieceList.add(new Pawn(this, 7, 6, true));
        
    }

    public static boolean solution(Movement move){
        return (move.preColumn == 3 && move.preRow == 3 && move.newColumn == 2 && move.newRow == 1);
    }

}
