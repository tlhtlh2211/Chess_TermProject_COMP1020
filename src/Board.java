import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Board extends JPanel{
    int col = 8;
    int row = 8;
    public int title_size = 85;

    ArrayList<Piece> piece_list = new ArrayList<Piece>();
    Piece selectedPiece; //for moving piece
    Input input = new Input(this);
    Board(){
        this.setPreferredSize(new Dimension(col*title_size,row*title_size));
        //this.setBackground(Color.red);
        piece_list.add(new Knight(this, 1,0,false));
        piece_list.add(new Knight(this, 6,0,false));
        piece_list.add(new Knight(this, 1,7,true));
        piece_list.add(new Knight(this, 6,7,true));

        piece_list.add(new Bishop(this, 2,0,false));
        piece_list.add(new Bishop(this, 5,0,false));
        piece_list.add(new Bishop(this, 2,7,true));
        piece_list.add(new Bishop(this, 5,7,true));

        piece_list.add(new Rook(this, 0,0,false));
        piece_list.add(new Rook(this, 7,0,false));
        piece_list.add(new Rook(this, 0,7,true));
        piece_list.add(new Rook(this, 7,7,true));

        piece_list.add(new Queen(this, 3,0,false));
        piece_list.add(new Queen(this, 3,7,true));

        piece_list.add(new King(this, 4,0,false));
        piece_list.add(new King(this, 4,7,true));

        for (int i = 0; i < 8; i++){
            piece_list.add(new Pawn(this,i,1,false));
        }

        for (int i = 0; i < 8; i++){
            piece_list.add(new Pawn(this,i,6,true));
        }


        this.addMouseListener(input);
        this.addMouseMotionListener(input);


//        piece_list.add(black_knight_1);
//        piece_list.add(black_knight_2);

    }

    Piece getPiece(int col, int row){  //return a piece in the piece_list with given collumn and row
        for (Piece piece : piece_list) {
            if (piece.col == col && piece.row == row) {
                return piece;

            }
        }
        return null;
    }

    void makeMove(Move move){
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.x_pos = move.piece.col * title_size;
        move.piece.y_pos = move.piece.row * title_size;
        capture(move);
        move.piece.isFirstmove = false;
    }

    void capture(Move move){
        piece_list.remove(move.capture);
    } //to perform capture
    boolean isValidMove(Move move){
        if (sameTeam(move.piece,move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol,move.newRow)){
            return false;
        }
        if (move.piece.collide(move.newCol, move.newRow) == true){
            return false;
        }
        return true;
    }

    boolean sameTeam(Piece p1, Piece p2){
        if (p1 == null || p2 == null){
            return false;
        } else {
            return p1.isWhite == p2.isWhite;
        }
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(new Color(150,75,0));
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 4; col ++) {
//                int y = 85*row;
//                int x;
//                if (row % 2 == 0) {
//                    x = 85 + 170*col;
//                } else {
//                    x = 170*col;
//                }
//                g2d.fillRect(x,y,85,85);
//            }
//
//        }
        for (int r = 0; r < row; r++){
            for (int c = 0; c < col; c++){
                if ((c+r) % 2 == 0){
                    g2d.setColor(Color.white);
                } else {
                    g2d.setColor(new Color(150,75,0));
                }
                g2d.fillRect(c*title_size, r*title_size, title_size,title_size);

            }
        }
        if (selectedPiece != null)
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (isValidMove(new Move(this, selectedPiece, c,r))){
                    g2d.setColor(new Color(73, 68, 68));
                    g2d.fillRect(c*title_size,r*title_size,title_size,title_size);
                }
            }
        }

        for (Piece piece : piece_list){
            piece.printpiece(g2d);
        }




    }
}
