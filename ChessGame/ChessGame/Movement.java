package ChessGame;

import Piece.Piece;

public class Movement {
    public int preColumn, preRow;
    public int newColumn, newRow;

    public Piece piece;
    public Piece newPiece;

    public Movement(Board board, Piece piece, int newColumn, int newRow){
        this.preColumn = piece.column;
        this.preRow = piece.row;
        this.newColumn = newColumn;
        this.newRow = newRow;

        this.piece = piece;
        this.newPiece = board.getPiece(newColumn, newRow);
    }
}
