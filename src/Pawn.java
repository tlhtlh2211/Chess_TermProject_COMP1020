import java.awt.image.BufferedImage;

public class Pawn extends Piece{

    Pawn(Board board, int col, int row, boolean isWhite){
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
        this.sprite = sheet.getSubimage(scale * 5, y_pos_sprite, scale, scale).getScaledInstance(board.title_size, board.title_size, BufferedImage.SCALE_SMOOTH);

    }
    boolean isValidMovement(int col, int row){ //to check if a piece can reach a square: (col,row)
        if (board.getPiece(col,row) == null){
            if (isWhite){
                if (isFirstmove) {
                    if ((this.row - row == 2 || this.row - row == 1) && col == this.col){
                        return true;
                    }
                } else{
                    if (this.row - row == 1 && col == this.col){
                        return true;
                    }
                }
            } else {
                if (isFirstmove) {
                    if ((this.row - row == -2 || this.row - row == -1) && col == this.col){
                        return true;
                    }
                } else{
                    if (this.row - row == -1 && col == this.col){
                        return true;
                    }
                }
            }
        } else{
            if (isWhite){
                if (this.row - row == 1 && (this.col - col == 1 || this.col - col == -1)){
                    return true;
                }

            } else {
                if (this.row - row == -1 && (this.col - col == 1 || this.col - col == -1)){
                    return true;
                }
            }
        }

        return false;
    }



}
