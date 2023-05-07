package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;
import ChessGame.Movement;

public class King extends Piece {
    public King(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "King";

        this.sprite = sheet.getSubimage(0 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isvalidMove(int column, int row) {
        return Math.abs((column - this.column)*(row - this.row)) == 1 || Math.abs(column - this.column) + Math.abs(row - this.row) == 1 || canCastle(column, row);
    }

    public boolean Collide(int column, int row) {return false;}

    private boolean canCastle(int column, int row){

        if (this.row == row){
            if (column == 6){
                Piece rook = board.getPiece(7, row);
                if (rook != null && rook.firstMove && firstMove){
                    return board.getPiece(5, row) == null && board.getPiece(6, row) == null &&
                        !board.chessScanner.isKingChecked(new Movement(board, this, 5, row));
                }
            }
            else if  (column == 2){
                Piece rook = board.getPiece(0, row);
                if (rook != null && rook.firstMove && firstMove){ 
                    return board.getPiece(3, row) == null && board.getPiece(2, row) == null && board.getPiece(1, row) == null &&
                        !board.chessScanner.isKingChecked(new Movement(board, this, 3, row));
                }
            }
        }
        return false;
    }
}
