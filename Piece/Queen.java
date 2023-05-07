package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;

public class Queen extends Piece {
    public Queen(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Queen";

        this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isvalidMove(int column, int row){
        return this.column == column || this.row == row || Math.abs(this.column - column) == Math.abs(this.row - row);
    }
    public boolean Collide(int column, int row){
        if (this.column == column || this.row == row){
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
        else
        {
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
}
