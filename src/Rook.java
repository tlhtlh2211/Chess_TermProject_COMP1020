import java.awt.image.BufferedImage;

public class Rook extends Piece{
    Rook(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.x_pos = col * board.title_size;
        this.y_pos = row * board.title_size;

        int y_pos_sprite;
        if (isWhite){
            y_pos_sprite = 0;
        } else {
            y_pos_sprite = scale;
        }
        this.sprite = sheet.getSubimage(scale * 4, y_pos_sprite, scale, scale).getScaledInstance(board.title_size, board.title_size, BufferedImage.SCALE_SMOOTH);

    }
    boolean isValidMovement(int col, int row){ //to check if a piece can reach a square: (col,row)
        if (col == this.col || row == this.row){
            return true;
        }
        return false;
    }
}
