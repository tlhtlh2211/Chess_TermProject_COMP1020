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
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 8; j++)
            {
                for (Piece piece : board.pieceList)
                {
                    if (piece.isWhite == isWhite && board.isva(i, j)) return true;
                }
            }
        }
        return false;
    }


}
