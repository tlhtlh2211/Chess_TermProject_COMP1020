package ChessGame;

import java.util.*;

import javax.swing.JOptionPane;

import Piece.Piece;

public class StaleMate {
    private Board board;
    StaleMate(Board board){
        this.board = board;
    }
    public void isStaleMate(){
        //1 v.s 1 King
        //1 King, 1 Knight || 1 Bishop v.s. 1 King
        if (board.pieceList.size() == 2){
            if (board.pieceList.get(0).name.equals("King") && board.pieceList.get(1).name.equals("King"))
                JOptionPane.showMessageDialog(null, "DRAW",
                "1 KING v.s. 1 KING", JOptionPane.INFORMATION_MESSAGE);
        }

        else if (board.pieceList.size() == 3){
            int num_king = 0;
            int num_knight = 0;
            int num_bishop = 0;
            for (Piece piece: board.pieceList)
                if (piece.name.equals("King")) num_king += 1;
                else if (piece.name.equals("Bishop")) num_bishop += 1;
                else if (piece.name.equals("Knight")) num_knight += 1;
            if (num_king == 2 && (num_bishop == 1))
                JOptionPane.showMessageDialog(null, "DRAW",
            "1 KING, 1 BISHOP v.s. 1 KING", JOptionPane.INFORMATION_MESSAGE);
            else if (num_king == 2 && (num_knight == 1))
                JOptionPane.showMessageDialog(null, "DRAW",
                "1 KING, 1 KNIGHT v.s. 1 KING", JOptionPane.INFORMATION_MESSAGE);
        }
 
        Piece king = board.findKing(Main.isWhiteTurn);
        int col = king.column;
        int row = king.row;
        ArrayList<Boolean> arr = new ArrayList<>();
        int total = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if ((i != 0 || j != 0) && col + i >= 0 && row + j >= 0 && col + i <= 7 && row + j <= 7)
                    if (board.valid(new Movement(board, king, col + i, row + j))){
                        total += 1;
                        if (board.chessScanner.isKingChecked(new Movement(board, king, col + i, row + j))) arr.add(true);
                    }
        //System.out.println(board.valid(new Movement(board, king, col + 1, row)) + " " + (col + 1) + " " + row);
        //System.out.println(arr.size() + " " + total);
        boolean check = false;
        if (total == arr.size()){
            for (Piece piece: board.pieceList)
                if (piece.isWhite == Main.isWhiteTurn){
                    if (piece.name.equals("Pawn")) {check = checkPawn(piece, col, row);  if (check) break; }
                    else if (piece.name.equals("Knight")) {check = checkKnight(piece, col, row);  if (check) break;}
                    else if (piece.name.equals("Rook")) {check = checkRook(piece, col, row);  if (check) break;}
                    else if (piece.name.equals("Bishop")) {check = checkBishop(piece, col, row);  if (check) break;}
                    else if (piece.name.equals("Queen")) {check = checkQueen(piece, col, row);  if (check) break;}
                }
            if (check == false)
                JOptionPane.showMessageDialog(null, "DRAW",
            "STALEMATE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean checkPawn(Piece piece, int col, int row){
        int colVal = Main.isWhiteTurn ? -1 : 1;
        return (board.valid(new Movement(board, piece, col, row + colVal)) && (row + colVal >= 0) && (row + colVal <= 7)) ||
        (board.valid(new Movement(board, piece, col + 1, row + colVal)) && (row + colVal >= 0) && (row + colVal <= 7)) ||
        (board.valid(new Movement(board, piece, col - 1, row + colVal)) && (row + colVal >= 0) && (row + colVal <= 7));
    }

    public boolean checkKnight(Piece piece, int col, int row){
        return (board.valid(new Movement(board, piece, col + 1, row + 2)) && (col + 1 <= 7) && (row + 2 <= 7)) ||
        (board.valid(new Movement(board, piece, col - 1, row + 2)) && (col - 1 >= 0) && (row + 2 <= 7)) ||
        (board.valid(new Movement(board, piece, col + 1, row - 2)) && (col + 1 <= 7) && (row - 2 >= 0)) ||
        (board.valid(new Movement(board, piece, col - 1, row - 2)) && (col - 1 >= 0) && (row - 2 >= 0)) ||
        (board.valid(new Movement(board, piece, col + 2, row + 1)) && (col + 2 <= 7) && (row + 1 <= 7)) ||
        (board.valid(new Movement(board, piece, col + 2, row - 1)) && (col + 2 <= 7) && (row - 1 >= 0)) ||
        (board.valid(new Movement(board, piece, col - 2, row + 1)) && (col - 2 >= 0) && (row + 1 <= 7)) ||
        (board.valid(new Movement(board, piece, col - 2, row - 1)) && (col - 2 >= 0) && (row - 1 >= 0));
    }

    public boolean checkBishop(Piece piece, int col, int row){
        for (int i = 1; i < 8; i++)
            if ((board.valid(new Movement(board, piece, col + i, row + i)) && (col + i <= 7) && (row + i <= 7)) ||
            (board.valid(new Movement(board, piece, col - i, row + i)) && (col - i >= 0) && (row + i <= 7))||
            (board.valid(new Movement(board, piece, col + i, row - i)) && (col + i <= 7) && (row - i >= 0))||
            (board.valid(new Movement(board, piece, col - i, row - i)) && (col - i >= 0) && (row - i >= 0))) 
                return true;
        
        return false;
    }

    public boolean checkRook(Piece piece, int col, int row){
        for (int i = 1; i < 8; i++)
            if ((board.valid(new Movement(board, piece, col + 0, row + i)) && (row + i <= 7))||
            (board.valid(new Movement(board, piece, col - i, row + 0)) && (col - i >= 0))||
            (board.valid(new Movement(board, piece, col + 0, row - i)) && (row - i >= 0))||
            (board.valid(new Movement(board, piece, col + i, row + 0)) && (col + i <= 7))) 
                return true;

        return false;
    }

    public boolean checkQueen(Piece piece, int col, int row){
        return checkBishop(piece, col, row) || checkRook(piece, col, row);
    }

}
