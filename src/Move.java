/*
How to move:
- When the mouse is clicked, we check if there is a piece there. If yes, it is the selected piece.
-
*/

public class Move {
    int oldCol;
    int newCol;
    int oldRow;
    int newRow;

    Piece piece;
    Piece capture;

    Move(Board board, Piece piece, int newCol, int newRow){
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow); //return the piece at the final position

    }

}
