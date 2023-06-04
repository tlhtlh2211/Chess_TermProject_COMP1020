package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;

public class Pawn extends Piece {
    public Pawn(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.times = 0; 
        
        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isvalidMove(int column, int row) {

        int color = isWhite ? 1 : -1;

        if (this.column == column && row == this.row - color && board.getPiece(column, row) == null){
            return true;
        }
        if (firstMove && this.column == column && row == this.row - color * 2 && board.getPiece(column, row) == null && board.getPiece(column, row + color) == null){
            return true;
        }
        if (column == this.column - 1 && row == this.row - color && board.getPiece(column, row) != null){
            return true;
        }
        if (column == this.column + 1 && row == this.row - color && board.getPiece(column, row) != null){
            return true;
        }
        if (board.getTileNum(column, row) == board.enPassant && column == this.column - 1 && row == this.row - color && board.getPiece(column, row + color) != null){
            return true;
        }
        if (board.getTileNum(column, row) == board.enPassant && column == this.column + 1 && row == this.row - color && board.getPiece(column, row + color) != null){
            return true;
        }
        return false;
    }
    public boolean Collide(int column, int row) {return false;}
}
