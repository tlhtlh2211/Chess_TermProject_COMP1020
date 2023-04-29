package ChessGame;
import Piece.Piece;

public class Movement {
    int preColumn, preRow;
    int newColumn, newRow;

    Piece piece;
    Piece newPiece;

    public Movement(Board board, Piece piece, int newColumn, int newRow){
        this.preColumn = piece.column;
        this.preRow = piece.row;
        this.newColumn = newColumn;
        this.newRow = newRow;

        this.piece = piece;
        this.newPiece = board.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                getPiece(newColumn, newRow);
    }
}
