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
}
