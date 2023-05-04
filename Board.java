package Chess_TermProject_COMP1020.ChessGame;


import javax.swing.*;

import Chess_TermProject_COMP1020.Piece.Bishop;
import Chess_TermProject_COMP1020.Piece.King;
import Chess_TermProject_COMP1020.Piece.Knight;
import Chess_TermProject_COMP1020.Piece.Pawn;
import Chess_TermProject_COMP1020.Piece.Piece;
import Chess_TermProject_COMP1020.Piece.Queen;
import Chess_TermProject_COMP1020.Piece.Rook;

import java.awt.*;
import java.util.*;

public class Board extends JPanel {
    public int titleSize = 85;

    int column = 8;
    int rows = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece movePiece;
    
    Mouse_Move mouse = new Mouse_Move(this);

    public ChessScanner chessScanner = new ChessScanner(this);

    public int enPassant = -1;


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
        if (move.piece.name.equals("Pawn")){
            movePawn(move);
        }
        else if (move.piece.name.equals("King")){
            moveKing(move);
        }
            move.piece.column = move.newColumn;
            move.piece.row = move.newRow;

            move.piece.xPOS = move.newColumn * titleSize;
            move.piece.yPOS = move.newRow * titleSize;

            move.piece.firstMove = false;

            Remove(move.newPiece);
    }
    private void moveKing(Movement move){
        if (Math.abs(move.piece.column - move.newColumn) == 2){
            Piece rook;
            if (move.piece.column < move.newColumn){
                rook = getPiece(7, move.piece.row);
                rook.column = 5;
            }
            else{
                rook = getPiece(0, move.piece.row);
                rook.column = 3;
            }
            rook.xPOS = rook.column * titleSize;
        }
    }
    private void movePawn(Movement move){

        int colorIndex = move.piece.isWhite ? 1 : -1;

        if (getTileNum(move.newColumn, move.newRow) == enPassant){
            move.newPiece = getPiece(move.newColumn, move.newRow + colorIndex);
        }
        if (Math.abs(move.piece.row - move.newRow) == 2){
            enPassant = getTileNum(move.newColumn, move.newRow + colorIndex);
        }
        else{
            enPassant = -1;
        }

        colorIndex = move.piece.isWhite ? 0 : 7;
        if (move.newRow == colorIndex){
            promotePawn(move);
        }
    }
    public int getTileNum(int column, int row){
        return row * this.rows * column; 
    }
    private void promotePawn(Movement move){
        pieceList.add(new Queen(this, move.newColumn, move.newRow, move.piece.isWhite));
        Remove(move.piece);
    }

    public void Remove(Piece piece){
        pieceList.remove(piece);
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
        if (chessScanner.isKingChecked(move)){
            return false;
        }
        return true;
    }

    Piece findKing(boolean isWhite){
        for (Piece piece : pieceList){
            if (isWhite == piece.isWhite && piece.name.equals("King")) return piece;
        }
        return null;
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