package ChessGame;
import java.util.ArrayList;

import Piece.*;

public class Stalemate {
    Board board;
    public Stalemate(Board board)
    {
        this.board = board;
    }

    public boolean isStalemate(boolean isWhite)
    {
        int sameMoves = 0;
        Movement[] moves = new Movement[12];

        ChessScanner scanner = new ChessScanner(board);

        int countWhite;
        int countBlack;

        if (board.pieceList.size() <= 2) return true;
       
        if (board.countBlackPiece <= 2 && board.countWhitePiece <= 2)
        {
            countBlack = 0;
            countWhite = 0;
            for (Piece piece : board.pieceList)
            {
                if (piece.name.equals("Knight") || piece.name.equals("Bishop"))
                {
                    if (piece.isWhite) countWhite++;
                    else countBlack++;
                }
            }
            if (countBlack == 1 && countWhite == 1) return true;
        }
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 8; j++)
            {
                for (Piece piece : board.pieceList)
                {
                    if (piece.isWhite == isWhite && piece.isvalidMove(i, j) && scanner.isKingChecked(new Movement(board, piece, i, j))) return true;
                }
            }
        }


        if (board.undoList.empty()) return false;
        if (board.undoList.size() < 12) return false; 

        for (int j = 0; j < 12; j++)
        {
            Movement move = board.undoList.pop();
            moves[j] = new Movement(board, move.piece, move.newColumn, move.newRow);
        }
        for (int z = 0; z < 4; z++)
        {
            sameMoves = 0;
            if (moves[z].piece.name.equals(moves[z + 4].piece.name) && moves[z].piece.name.equals(moves[z + 8].piece.name))
            {
                if ((moves[z].newColumn == moves[z + 4].newColumn && moves[z].newRow == moves[z + 4].newRow)
                && (moves[z].newColumn == moves[z + 8].newColumn && moves[z].newRow == moves[z + 8].newRow)) sameMoves++;
            }
            if (sameMoves == 3)
            {
                return true;
            }
        }
        
        return false;
    }

    
}
