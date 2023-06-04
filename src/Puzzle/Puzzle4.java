package Puzzle;

import Piece.*;
import ChessGame.*;

public class Puzzle4 extends Board {

    public Puzzle4()
    {
        super("Puzzle", "4");
    }

    @Override
    public void addPiece()
    {
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new Queen(this, 1, 3, true));

        // add Bishop
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        
        // add Knight
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 4, 1, false));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));

        // addRook
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));
        
        for (int i = 0; i < 8; i++){
            if (i == 3 || i == 4) continue;
            pieceList.add(new Pawn(this, i, 6, true));
        }
        pieceList.add(new Pawn(this, 3, 5, true));
        pieceList.add(new Pawn(this, 4, 4, true));

        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));
        pieceList.add(new Pawn(this, 0, 2, false));
        pieceList.add(new Pawn(this, 1, 2, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 2, 3, false));
        pieceList.add(new Pawn(this, 4, 3, false));
        pieceList.add(new Pawn(this, 3, 1, false));
    }

    public static boolean solution(Movement move){
        return (move.preColumn == 1 && move.preRow == 3 && move.newColumn == 1 && move.newRow == 2);
    }

}
