package ChessGame;

import Chess_TermProject_COMP1020.Piece.Piece;

public class ChessScanner {
    Board board;

    public ChessScanner(Board board){
        this.board = board;
    }

    public boolean isKingChecked(Movement move){
         
        Piece king = board.findKing(move.piece.isWhite);

        assert king != null;

        int kingColumn = king.column;
        int kingRow = king.row;

        if (board.movePiece != null && board.movePiece.name.equals("King")){
            kingColumn = move.newColumn;
            kingRow = move.newRow;
        }
        return hitByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 0, 1) ||
        hitByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, 0) ||
        hitByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 0, -1) ||
        hitByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, 0) ||

        hitByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, -1) ||
        hitByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, -1) ||
        hitByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, 1) ||
        hitByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, 1) ||

        hitByKnight(move.newColumn, move.newRow, king, kingColumn, kingRow) ||

        hitByPawn(move.newColumn, move.newRow, king, kingColumn, kingRow) ||

        hitByKing(king, kingColumn, kingRow);
    }
    
    // Hit By Rook
    private boolean hitByRook(int column, int row, Piece king, int kingColumn, int kingRow, int colVal, int rowVal){
        for (int i = 1; i < 8; i++){
            if (kingColumn - (i * colVal) == column && kingRow - (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingColumn - (i * colVal), kingRow - (i * rowVal));
            if (piece != null && piece != board.movePiece){
                if (!board.Team(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    // Hit By Bishop
    private boolean hitByBishop(int column, int row, Piece king, int kingColumn, int kingRow, int colVal, int rowVal){
        for (int i = 1; i < 8; i++){
            if (kingColumn - (i * colVal) == column && kingRow - (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingColumn - (i * colVal), kingRow - (i * rowVal));
            if (piece != null && piece != board.movePiece){
                if (!board.Team(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }


    // Hit By Knight
    private boolean hitByKnight(int column, int row, Piece king, int kingColumn, int kingRow){
        return checkKnight(board.getPiece(kingColumn - 1, kingRow - 2), king, column, row) || 
                checkKnight(board.getPiece(kingColumn + 1, kingRow - 2), king, column, row) ||
                checkKnight(board.getPiece(kingColumn + 2, kingRow - 1), king, column, row) ||
                checkKnight(board.getPiece(kingColumn + 2, kingRow + 1), king, column, row) ||
                checkKnight(board.getPiece(kingColumn + 1, kingRow + 2), king, column, row) ||
                checkKnight(board.getPiece(kingColumn - 1, kingRow + 2), king, column, row) ||
                checkKnight(board.getPiece(kingColumn - 2, kingRow + 1), king, column, row) ||
                checkKnight(board.getPiece(kingColumn - 2, kingRow - 1), king, column, row);
    }
    private boolean checkKnight(Piece p, Piece k, int column, int row){
        return p != null && !board.Team(p, k) && p.name.equals("Knight") && !(p.column == column && p.row == row);
    }


    // Hit By King
    private boolean hitByKing(Piece king, int kingColumn, int kingRow){
        return checkKing(board.getPiece(kingColumn - 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingColumn + 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingColumn, kingRow - 1), king) ||
                checkKing(board.getPiece(kingColumn - 1, kingRow), king) ||
                checkKing(board.getPiece(kingColumn + 1, kingRow), king) ||
                checkKing(board.getPiece(kingColumn - 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingColumn + 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingColumn, kingRow + 1), king);
    }
    private boolean checkKing(Piece p, Piece k){
        return p != null && !board.Team(p, k) && p.name.equals("King");
    }


    // Hit By Pawn
    private boolean hitByPawn(int column, int row, Piece king, int kingColumn, int kingRow){
        int colorVal = king.isWhite ? -1 : 1;
        return checkPawn(board.getPiece(kingColumn + 1, kingRow + colorVal), king, column, row) ||
        checkPawn(board.getPiece(kingColumn - 1, kingRow + colorVal), king, column, row);
    }
    private boolean checkPawn(Piece p, Piece k, int column, int row){
        return p != null && !board.Team(p, k) && p.name.equals("Pawn");
    }
}
