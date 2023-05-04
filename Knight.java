import java.awt.image.BufferedImage;

public class Knight extends Piece{
    Knight(Board board, int col, int row, boolean isWhite){
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
        this.sprite = sheet.getSubimage(scale * 3, y_pos_sprite, scale, scale).getScaledInstance(board.title_size, board.title_size, BufferedImage.SCALE_SMOOTH);

    }
}
