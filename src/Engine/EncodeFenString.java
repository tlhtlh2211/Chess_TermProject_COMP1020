package Engine;

import java.util.ArrayList;
import ChessGame.*;
import Piece.*;

public class EncodeFenString {
    Board board;
    ArrayList<Piece> pieceList;
    public String st;
    public EncodeFenString(Board board){
        this.board = board;
        this.pieceList = board.pieceList;
    }
    public String getColumn(int col){
        if (col == 0){
            return "a";
        }
        else if (col == 1){
            return "b";
        }
        else if (col == 2){
            return "c";
        }
        else if (col == 3){
            return "d";
        }
        else if (col == 4){
            return "e";
        }
        else if (col == 5){
            return "f";
        }
        else if (col == 6){
            return "g";
        }
        else if (col == 7){
            return "h";
        }
        return null;
    }
    public int getColumn(String col){
        if (col.equals("a")){
            return 0;
        }
        else if (col.equals("b")){
            return 1;
        }
        else if (col.equals("c")){
            return 2;
        }
        else if (col.equals("d")){
            return 3;
        }
        else if (col.equals("e")){
            return 4;
        }
        else if (col.equals("f")){
            return 5;
        }
        else if (col.equals("g")){
            return 6;
        }
        else if (col.equals("h")){
            return 7;
        }
        return -1;
    }
    private String getPieceName(String name, boolean isWhite){
        if (isWhite){
            if (name.equals("King")) return "K";
            else if (name.equals("Pawn")) return "P";
            else if (name.equals("Rook")) return "R";
            else if (name.equals("Knight")) return "N";
            else if (name.equals("Queen")) return "Q";
            else if (name.equals("Bishop")) return "B";
        }
        else if (!isWhite){
            if (name.equals("King")) return "k";
            else if (name.equals("Pawn")) return "p";
            else if (name.equals("Rook")) return "r";
            else if (name.equals("Knight")) return "n";
            else if (name.equals("Queen")) return "q";
            else if (name.equals("Bishop")) return "b";
        }
        return null;
    }
    public void encodeFEN(int timeMove, int timeCapture, boolean bk_Castle, boolean bq_Castle, boolean wk_Castle, boolean wq_Castle, boolean can_enPassant, Movement move){
        st = "";
        int increment;
        for (int i = 0; i < 8; i++){
            increment = 0;
            for (int j = 0; j < 8; j++){
                if (board.getPiece(j, i) == null) increment += 1;
                else if (board.getPiece(j, i) != null){
                    Piece piece = board.getPiece(j, i);
                    if (increment != 0){
                        st += String.valueOf(increment);
                        increment = 0;
                    }
                    st += getPieceName(piece.name, piece.isWhite);
                }
            }
            if (increment > 0) st += String.valueOf(increment);
            if (i <= 6) st += "/";
        }
        if (!Main.isWhiteTurn)
            st += " w "; 
        else st += " b ";
         
        if (wk_Castle) st += "K";
        if (wq_Castle) st += "Q";
        if (bk_Castle) st += "k";
        if (bq_Castle) st += "q";

        if (!wk_Castle && !wq_Castle && !bk_Castle && !bq_Castle) st += "-";

        if (!can_enPassant) st += " - ";
        else if (can_enPassant && move != null) st += (" " + getColumn(move.newRow) + String.valueOf(Math.abs(move.newRow + move.preRow)/2) + " ");
        st += String.valueOf(timeCapture) + " " + String.valueOf(timeMove);
    }
}
