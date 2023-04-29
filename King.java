package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;

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
        return Math.abs((column - this.column)*(row - this.row)) == 1 || Math.abs(column - this.column) + Math.abs(row - this.row) == 1;
    }
    public boolean Collide(int column, int row) {return false;}
}
