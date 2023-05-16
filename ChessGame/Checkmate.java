package ChessGame;
import Piece.*;
public class Checkmate {
    Board board;
    public Checkmate(Board board)
    {
        this.board= board;
    }

    public boolean isCheckmate(boolean isWhite)
    {
        int countPossibleMoves = 0;
        ChessScanner check = new ChessScanner(board);
        for (Piece piece : board.pieceList)
        {
            if (piece.isWhite == isWhite)
            {
                for (int i = 0; i < 8; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        if (piece.isvalidMove(i, j)) 
                        {
                            Movement move = new Movement(board, piece, i, j);
                            if (!check.isKingChecked(move))
                            {
                                countPossibleMoves++;
                            }
                        }
                    }
                }
            }
        }
        if (countPossibleMoves == 0) return true;
        return false;
    }
}
