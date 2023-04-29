package ChessGame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import Piece.Piece;
import Piece.Knight;
import Piece.Pawn;
import Piece.Bishop;
import Piece.King;
import Piece.Queen;
import Piece.Rook;

public class Board extends JPanel {
    public int titleSize = 85;

    int column = 8;
    int rows = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece movePiece;
    
    Mouse_Move mouse = new Mouse_Move(this);


    public Board(){
        this.setPreferredSize(new Dimension(column * titleSize, rows * titleSize));
        
        //this.setBackground(Color.BLACK);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        addPiece();
    }
    

    public Piece getPiece(int column, int row){
        for (Piece piece : pieceList){
            if (piece.column == column && piece.row == row) return piece;
        }
        return null;
    }

    public void Move(Movement move){

        move.piece.column = move.newColumn;
        move.piece.row = move.newRow;

        move.piece.xPOS = move.newColumn * titleSize;
        move.piece.yPOS = move.newRow * titleSize;

        move.piece.firstMove = false;

        Remove(move);
    }

    public void Remove(Movement move){
        pieceList.remove(move.newPiece);
    }

    public boolean Team(Piece piece1, Piece piece2){
        if (piece1 == null || piece2 == null) return false;

        return piece1.isWhite == piece2.isWhite;
    }

    public boolean isValidMove(Movement move){
        if (Team(move.piece, move.newPiece)){
            return false;
        }
        if (!move.piece.isvalidMove(move.newColumn, move.newRow)){
            return false;
        }
        if (move.piece.Collide(move.newColumn, move.newRow)){
            return false;
        }
        return true;
    }
    public void addPiece(){
        // add King
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new King(this, 4, 7, true));

        // add Queen
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new Queen(this, 3, 7, true));

        // add Bishop
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        
        // add Knight
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));

        // addRook
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        // addPawn
        for (int i = 0; i < 8; i++){
            pieceList.add(new Pawn(this, i, 1, false));
            pieceList.add(new Pawn(this, i, 6, true));
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g_2d = (Graphics2D) g;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < column; j++){
                if ((i+j) % 2 == 0) g_2d.setColor(Color.WHITE);
                else g_2d.setColor(new Color(236, 148, 148));
                g_2d.fillRect(j * titleSize, i * titleSize, titleSize, titleSize);
            }

        if (movePiece != null)
            for (int i = 0; i < rows; i++ )
                for (int j = 0; j < column; j++){
                    if (isValidMove(new Movement(this, movePiece, j ,i))){
                        g_2d.setColor(new Color(120, 120, 120));
                        g_2d.fillRect(j * titleSize, i*titleSize, titleSize, titleSize);
                    }
                }

        for (Piece piece : pieceList){
            piece.paint(g_2d);
        }
    }
}