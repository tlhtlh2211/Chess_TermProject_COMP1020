import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Board extends JPanel{
    //why use this?
    int col = 8;
    int row = 8;
    public int title_size = 85;

    ArrayList<Piece> piece_list = new ArrayList<Piece>();
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





//        piece_list.add(black_knight_1);
//        piece_list.add(black_knight_2);

    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(150,75,0));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 4; col ++) {
                int y = 85*row;
                int x;
                if (row % 2 == 0) {
                    x = 85 + 170*col;
                } else {
                    x = 170*col;
                }
                g2d.fillRect(x,y,85,85);
            }

        }

        for (Piece piece : piece_list){
            piece.printpiece(g2d);
        }


    }
}
