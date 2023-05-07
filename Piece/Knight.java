package Piece;

import java.awt.image.BufferedImage;

import ChessGame.Board;

public class Knight extends Piece {
    public Knight(Board board, int column, int row, boolean isWhite){
        super(board);
        this.column = column;
        this.row = row;
        this.xPOS = column * board.titleSize;
        this.yPOS = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.sprite = sheet.getSubimage(3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isvalidMove(int column, int row){
        return Math.abs(column - this.column) * Math.abs(row - this.row) == 2;
    }
}