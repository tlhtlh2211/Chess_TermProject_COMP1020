import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {
    Board board;
    Input(Board board){
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX()/board.title_size;
        int row = e.getY()/ board.title_size;
        Piece piece_xy = board.getPiece(col, row);
        if (piece_xy != null){
            board.selectedPiece = piece_xy;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null){
            board.selectedPiece.x_pos = e.getX() - board.title_size/2; //the piece will be at the center of the mouse
            board.selectedPiece.y_pos = e.getY() - board.title_size/2;
            board.repaint(); //why need this?
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX()/board.title_size;
        int row = e.getY()/ board.title_size;
        if (board.selectedPiece != null){
            Move newMove = new Move(board, board.selectedPiece, col, row);
            if (board.isValidMove(newMove)){
                board.makeMove(newMove);
            } else {
                board.selectedPiece.x_pos = board.selectedPiece.col * board.title_size;
                board.selectedPiece.y_pos = board.selectedPiece.row * board.title_size;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }







}
