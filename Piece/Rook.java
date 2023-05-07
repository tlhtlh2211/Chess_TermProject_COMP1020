package Piece;
import java.awt.image.BufferedImage;

import ChessGame.Board;

public class Rook extends Piece {
    public Rook(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Rook";

        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isvalidMove(int column, int row){
        return this.column == column || this.row == row;
    }
    public boolean Collide(int column, int row){
        // Check Left most
        if (this.column > column){
            for (int c = this.column - 1; c > column; c --)
                if (board.getPiece(c, this.row) != null) return true;
        }
        // Check Right most
        if (this.column < column){
            for (int c = this.column + 1; c < column; c ++)
                if (board.getPiece(c, this.row) != null) return true;
        }
        // Check Up
        if (this.row > row){
            for (int r = this.row - 1; r > row; r --)
                if (board.getPiece(this.column, r) != null) return true;
        }
        // Check Down
        if (this.row > row){
            for (int r = this.row + 1; r < row; r ++)
                if (board.getPiece(this.column, r) != null) return true;
        }
        return false;
    }
}