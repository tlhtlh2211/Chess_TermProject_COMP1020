package ChessGame;
import java.awt.event.*;
import Piece.Piece;

public class Mouse_Move extends MouseAdapter{

    Board board;
    public Mouse_Move(Board board){
        this.board = board;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int column = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        System.out.println(column + " " + row);

        Piece xy = board.getPiece(column, row);
        if (xy != null){
            board.movePiece = xy;
        }
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.movePiece != null){
            board.movePiece.xPOS = e.getX() - board.titleSize / 2;
            board.movePiece.yPOS = e.getY() - board.titleSize / 2;
            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int column = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        if (board.movePiece != null){
            Movement move = new Movement(board, board.movePiece, column, row);

            if (board.isValidMove(move)){
                board.Move(move);
            }
            else{
                board.movePiece.xPOS = board.movePiece.column * board.titleSize;
                board.movePiece.yPOS = board.movePiece.row * board.titleSize;
            }
        }

        board.movePiece = null;
        board.repaint();
    }


}
