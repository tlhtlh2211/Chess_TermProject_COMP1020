package ChessGame;
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
        if (board.pieceList.size() <= 2) return true;
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
        for (int j = 0; j < 12; j++)
        {
            Movement move = board.undoList.pop();
            moves[j] = new Movement(board, move.piece, move.newColumn, move.newRow);
        }
        for (int z = 0; z < 4; z++)
        {
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
