package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;

public class Bishop extends Piece {
    public Bishop(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isvalidMove(int column, int row){
        return Math.abs(column - this.column) == Math.abs(row - this.row);
    }
    public boolean Collide(int column, int row){
        // Check Up Left
        if (this.column > column && this.row > row){
            for (int i = 1; i < Math.abs(this.column - column); i ++)
                if (board.getPiece(this.column - i, this.row - i) != null) return true;
        }
        // Check Up Right
        if (this.column < column && this.row > row){
            for (int i = 1; i < Math.abs(this.column - column); i ++)
                if (board.getPiece(this.column + i, this.row - i) != null) return true;
        }
        // Check Down Left
        if (this.column > column && this.row < row){
            for (int i = 1; i < Math.abs(this.column - column); i ++)
                if (board.getPiece(this.column - i, this.row + i) != null) return true;
        }
        // Check Down Right
        if (this.column < column && this.row < row){
            for (int i = 1; i < Math.abs(this.column - column); i ++)
                if (board.getPiece(this.column + i, this.row + i) != null) return true;
        }
        return false;
    }
}
