package ChessGame;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import Engine.*;
import GUIs.*;
import Piece.*;
import Puzzle.*;

import java.awt.*;
import java.util.*;

public class Board extends JPanel {
    public int titleSize = 90;

    int column = 8;
    int rows = 8;

    public ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece movePiece;
    
    Mouse_Move mouse = new Mouse_Move(this);

    public ChessScanner chessScanner = new ChessScanner(this);

    public int enPassant = -1;

    public EncodeFenString encode = new EncodeFenString(this);

    public MoveList movelist;

    private Stack<Movement> undoList = new Stack<>();
    private Stack<Integer> taskPerformed = new Stack<>();
    private Stack<String> taskName = new Stack<>();

    private int timeMove;
    private int timeCapture;

    private boolean wk_Castle;
    private boolean bk_Castle;
    private boolean can_enPassant;
    private boolean wq_Castle;
    private boolean bq_Castle;
    public String FEN;

    public String suggestion;
    private int[] position_suggestion = new int[4];
    public String playMode = "";
    private int dificulty;
    private String puzzleNumber;

    public GUItimer countTimes;
    StockFish client;

    public Board(){
        this.setPreferredSize(new Dimension(column * titleSize, rows * titleSize));
        addPiece();
    }
    public Board(String playMode, int dificulty){
        this();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        new FiftyMoveRule();
        movelist = new MoveList(this);
        this.playMode = playMode;
        this.dificulty = dificulty;
        if (playMode.equals("Computer") || playMode.equals("Free")) StartEngine(true);
        else if (playMode.equals("Time")) countTimes = new GUItimer(dificulty);
    }
    public Board(String playMode, String puzzleNumber){
        this();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        new FiftyMoveRule();
        this.playMode = playMode;
        this.puzzleNumber = puzzleNumber;
    }
    private void StartEngine(boolean computer){
        //this();
        timeMove = 1;
        timeCapture = 0;
        wk_Castle = true;
        bk_Castle = true;
        wq_Castle = true;
        bq_Castle = true;
        can_enPassant = false;
        encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, can_enPassant, null);

        if (dificulty != 0){
            client = new StockFish();
            if (client.startEngine()) {
                System.out.println("Engine has started..");
            } else {
                System.out.println("Oops! Something went wrong..");
            }
            client.sendCommand("uci");
            client.getOutput(0);
            client.sendCommand("setoption name Skill Level value " + String.valueOf(dificulty));
            if (playMode.equals("Computer")) client.sendCommand("go depth 10");
            else client.sendCommand("go depth 1");
        }
    }
    public void Restart(){
        pieceList.clear();
        addPiece();
        mouse.move = null;
        Main.isWhiteTurn = Main.ini;
        enPassant = -1;
        repaint();
        //client = new StockFish();
        if (!playMode.equals("Time"))
            StartEngine(true);
        else {
            countTimes.Restart();
        }
        movelist.deleteMove();
        movelist.display();
    }

    public Piece getPiece(int column, int row){
        for (Piece piece : pieceList){
            if (piece.column == column && piece.row == row) return piece;
        }
        return null;
    }

    private void addStep(Movement move){
        undoList.add(move);
    }

    public void undoPiece(){
        if (taskPerformed.isEmpty() || undoList.isEmpty() ){
            JOptionPane.showMessageDialog(null, "YOU CAN NOT UNDO!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else{
            movelist.undoStep();
            movelist.undoRecord();
            int step = taskPerformed.pop();
            //System.out.print(step);
            for (int i = 0; i < step; i++){
                Movement move = undoList.pop();

                if (!undoList.isEmpty())
                    mouse.move = undoList.peek();
                else mouse.move = null;

                String task = taskName.pop();
                if (task.equals("add")){
                    //System.out.print("add");
                    if (move.piece.name.equals("Pawn")){
                        //System.out.println("times" + move.piece.times);
                        pieceList.add(new Pawn(this, move.preColumn, move.preRow, move.piece.isWhite));
                        if (move.piece.times > 1){
                            getPiece(move.preColumn, move.preRow).firstMove = false;
                        }
                        move.piece.times -= 1;
                        enPassant = -1;
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                    else if (move.piece.name.equals("Queen")){
                        pieceList.add(new Queen(this, move.preColumn, move.preRow, move.piece.isWhite));
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                    else if (move.piece.name.equals("Bishop")){
                        pieceList.add(new Bishop(this, move.preColumn, move.preRow, move.piece.isWhite));
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                    else if (move.piece.name.equals("King")){
                        pieceList.add(new King(this, move.preColumn, move.preRow, move.piece.isWhite));
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                    else if (move.piece.name.equals("Knight")){
                        pieceList.add(new Knight(this, move.preColumn, move.preRow, move.piece.isWhite));
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                    else if (move.piece.name.equals("Rook")){
                        pieceList.add(new Rook(this, move.preColumn, move.preRow, move.piece.isWhite));
                        Remove(getPiece(move.newColumn, move.newRow));
                    }
                }
                else if (task.equals("remove")){
                    //System.out.print("remove");
                    //move.piece.column = move.newColumn;
                    //move.piece.row = move.newRow;
                    pieceList.add(move.piece);
                }
                else if (task.equals("promote")){
                    //System.out.print("promote");
                    pieceList.add(new Pawn(this, move.preColumn, move.preRow, move.piece.isWhite));
                    if (move.piece.times > 1){
                        getPiece(move.preColumn, move.preRow).firstMove = false;
                    }
                    move.piece.times -= 1;
                    Remove(getPiece(move.newColumn, move.newRow));
                }
                else if (task.equals("enpassant")){

                }
                else if (task.equals("castling")){
                    System.out.println("castling");
                    pieceList.add(new Rook(this, move.preColumn, move.preRow, move.piece.isWhite));
                    Remove(getPiece(move.piece.column, move.piece.row));
                }
            }
            if (!playMode.equals("Puzzle") && !playMode.equals("Time")){
                if (mouse.move != null && mouse.move.piece.name.equals("Pawn") && Math.abs(mouse.move.newRow - mouse.move.preRow) == 2){
                    encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, true, mouse.move);
                } 
                else encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, false, null);
                FEN = encode.st;
                bestMove(client.getBestMove(FEN, 50));
            }
            Main.isWhiteTurn = !Main.isWhiteTurn;
            repaint();
        }
    }

    public void Move(Movement move){
        if (move.piece.name.equals("Pawn")){
            movePawn(move);
            timeCapture = - 1;
        }
        else if (move.piece.name.equals("King")){
            moveKing(move);
        }

        move.piece.column = move.newColumn;
        move.piece.row = move.newRow;

        move.piece.xPOS = move.newColumn * titleSize;
        move.piece.yPOS = move.newRow * titleSize;

        move.piece.firstMove = false;

            if (move.piece.name.equals("King"))
                if (move.piece.isWhite){
                    wk_Castle = false;
                    wq_Castle = false;
                }
                else if (!move.piece.isWhite){
                    bk_Castle = false;
                    bq_Castle = false;
                }

            if (move.piece.name.equals("Pawn")){
                move.piece.times += 1;
        }
        //System.out.print(move.piece.name);
        //System.out.print(move.piece);
        //System.out.print(move.piece.column);
        //System.out.println(move.piece.row);
        addStep(move);
        if ((!taskName.isEmpty()) && (taskName.peek().equals("castling"))){
            taskPerformed.set(taskPerformed.size()-1,taskPerformed.peek() + 1);
        }
        else{
            taskPerformed.add(1);
        }
        taskName.add("add");
        if (move.newPiece == null && playMode.equals(""))  Mouse_Move.playSound("src/Resource/move-self.wav");
        else if (move.newPiece != null && playMode.equals("")) Mouse_Move.playSound("src/Resource/capture.wav");
        Remove(move.newPiece);
        if (move.newPiece != null){
            addStep(new Movement(this, move.newPiece, move.newPiece.column, move.newPiece.row));
            taskPerformed.set(taskPerformed.size()-1,taskPerformed.peek() + 1);
            taskName.add("remove");
            timeCapture = - 1; 
        }
        
        timeMove += 1;
        timeCapture += 1;
        if (move.piece.name.equals("Pawn") && Math.abs(move.newRow - move.preRow) == 2){
            encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, true, move);
        } 
        else encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, false, null);
         
        if (!playMode.equals("Time") && !playMode.equals("Puzzle") && !playMode.equals("")){
            FEN = encode.st;
            client.drawBoard(FEN);
            if (playMode.equals("Free"))
                bestMove(client.getBestMove(FEN, 50));
            movelist.addMove(Main.isWhiteTurn, undoList, taskPerformed, taskName);
            movelist.display();
            movelist.Record(undoList, taskPerformed, taskName);
        }
        if (playMode.equals("Puzzle")){
            if (puzzleNumber.equals("1") && Puzzle1.solution(move)){
                //repaint();
                JOptionPane.showMessageDialog(null, "YOU MADE IT",
                    "TRUE", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (puzzleNumber.equals("2") && Puzzle2.solution(move)){
                //repaint();
                JOptionPane.showMessageDialog(null, "YOU MADE IT",
                    "TRUE", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (puzzleNumber.equals("3") && Puzzle3.solution(move)){
                //repaint();
                JOptionPane.showMessageDialog(null, "YOU MADE IT",
                    "TRUE", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (puzzleNumber.equals("4") && Puzzle4.solution(move)){
                //repaint();
                JOptionPane.showMessageDialog(null, "YOU MADE IT",
                    "TRUE", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "WRONG",
                    "WRONG", JOptionPane.ERROR_MESSAGE);
                undoPiece();
            }
        }
        if (!move.piece.name.equals("Pawn")) CheckMate();
    }
    
    public void bestMove(String st){
        String[] s = st.split("");
        if (Character.isDigit(st.toCharArray()[1])){
            int row1 = 8 - Integer.parseInt(s[1]);
            int col1 = encode.getColumn(s[0]);
            //System.out.println("Move " + s[0] + s[1] + " to " + s[2] + s[3]);
            suggestion = "Move " + (getPiece(col1, row1).isWhite ? "White" : "Black") + " " + getPiece(col1, row1).name + " at " + s[0] + s[1] + " to " + s[2] + s[3];
            position_suggestion[0] = col1;
            position_suggestion[1] = row1;
            position_suggestion[2] = encode.getColumn(s[2]);
            position_suggestion[3] = 8 - Integer.parseInt(s[3]);
        }
    }

    public void Computer(){
        if (!Main.isWhiteTurn && playMode.equals("Computer")){
            Board board = this;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String[] s = client.getBestMove(FEN, 50).split("");
                if (Character.isDigit(client.getBestMove(FEN, 50).toCharArray()[1])){
                    int row1 = 8 - Integer.parseInt(s[1]);
                    int col1 = encode.getColumn(s[0]);
                    int row2 = 8 - Integer.parseInt(s[3]);
                    int col2 = encode.getColumn(s[2]);

                    Main.isWhiteTurn = !Main.isWhiteTurn;

                    mouse.move = new Movement(board, getPiece(col1, row1), col2, row2);
                    Move(new Movement(board, getPiece(col1, row1), col2, row2));
                    mouse.move.preColumn = col1;
                    mouse.move.preRow = row1;

                    //For suggesting move
                    Main.isWhiteTurn = !Main.isWhiteTurn;
                    if (mouse.move.piece.name.equals("Pawn") && Math.abs(mouse.move.newRow - mouse.move.preRow) == 2){
                        encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, true, mouse.move);
                    } 
                    else encode.encodeFEN(timeMove, timeCapture, bk_Castle, bq_Castle, wk_Castle, wq_Castle, false, null);
                    Main.isWhiteTurn = !Main.isWhiteTurn;
                    FEN = encode.st;
                    bestMove(client.getBestMove(FEN, 50));

                    repaint();
                    if (mouse.move.newPiece == null)
                        Mouse_Move.playSound("src/Resource/move-self.wav");
                    else Mouse_Move.playSound("src/Resource/capture.wav");
                    CheckMate();
                }
            }
            }, 1500);
        }

    }
    public boolean valid(Movement move){
        if (Team(move.piece, getPiece(move.newColumn, move.newRow))){
            return false;
        }

        if (!move.piece.isvalidMove(move.newColumn, move.newRow)){
            return false;
        }

        if (move.piece.Collide(move.newColumn, move.newRow)){
            return false;
        }

        if (chessScanner.isKingChecked(move) && !Team(move.piece, getPiece(move.newColumn, move.newRow))){
            return false;
        }
        return true;
    }
    private boolean blockRook(Piece piece, Board board, boolean isWhite, Piece king)
    {
        // find piece to block in row
        for (int i = Integer.min(piece.column, king.column); i < Integer.max(piece.column, king.column); i++)
        {
            for (Piece anotherPiece : pieceList)
            {
                if (anotherPiece.isWhite == king.isWhite && isValidMove(new Movement(this, anotherPiece, i, king.row))) return true;
            }
        }
        // find piece to block in column
        for (int i = Integer.min(piece.row, king.row); i < Integer.max(piece.row, king.row); i++)
        {
            for (Piece anotherPiece : pieceList)
            {
                if (anotherPiece.isWhite == king.isWhite && isValidMove(new Movement(this, anotherPiece, king.column, i))) return true;
            }
        }
        return false;
    }

    public static boolean blockBishop(Piece piece, Board board, boolean isWhite, Piece king)
    {
        // find piece to block in row
        int current_row = piece.row;
        int current_col = piece.column;
        while (current_col >= 0 && current_row >= 0){
            for (Piece anotherPiece : board.pieceList){
                if (anotherPiece.isWhite == king.isWhite && board.isValidMove(new Movement(board, anotherPiece, current_col, current_row))) return true;
                
            }
            current_col -= 1;
            current_row -= 1;
        }

        //find piece to block in right down
        current_row = piece.row;
        current_col = piece.column;
        while (current_col <= 7 && current_row <= 7){
            for (Piece anotherPiece : board.pieceList){
                if (anotherPiece.isWhite == king.isWhite && board.isValidMove(new Movement(board, anotherPiece, current_col, current_row))) return true;
                
            }
            current_col += 1;
            current_row += 1;
        }

        //find piece to block in right up
        current_row = piece.row;
        current_col = piece.column;
        while (current_col <= 7 && current_row >= 0){
            for (Piece anotherPiece : board.pieceList){
                if (anotherPiece.isWhite == king.isWhite && board.isValidMove(new Movement(board, anotherPiece, current_col, current_row))) return true;
                
            }
            current_col += 1;
            current_row -= 1;
        }

        //find piece to block in left down
        current_row = piece.row;
        current_col = piece.column;
        while (current_col >= 0 && current_row <= 7){
            for (Piece anotherPiece : board.pieceList){
                if (anotherPiece.isWhite == king.isWhite && board.isValidMove(new Movement(board, anotherPiece, current_col, current_row))) return true;
                
            }
            current_col -= 1;
            current_row += 1;
        }
        return false;
    } 

    private boolean checkMate(Piece king)
    {
        int col = king.column;
        int row = king.row;
        if (!chessScanner.isKingChecked(new Movement(this, king, col, row))) return false;
        if (isValidMove(new Movement(this, king, col + 1, row + 0)) && col + 1 <= 7)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col - 1, row + 0)) && col - 1 >= 0)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col + 0, row + 1)) && row + 1 <= 7)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col + 0, row - 1)) && row - 1 >= 0)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col + 1, row + 1)) && col + 1 <= 7 && row + 1 <= 7)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col - 1, row + 1)) && col - 1 >= 0 && row + 1 <= 7)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col + 1, row - 1)) && row - 1 >= 0 && col + 1 <= 7)
            {
                return false;
            }
            if (isValidMove(new Movement(this, king, col - 1, row - 1)) && col - 1 >= 0 && row - 1 >= 0)
            {
                return false;
            }
        boolean availableMove = false;
        for (Piece piece : pieceList)
        {
            // determine which piece is checking our king
            if (piece.isWhite != king.isWhite && isValidMove(new Movement(this, piece, king.column, king.row)))
            {
                if (piece.name.equals("Rook"))
                {
                    availableMove = blockRook(piece, this, king.isWhite, king);
                }
                else if (piece.name.equals("Bishop"))
                {
                    availableMove = blockBishop(piece, this, king.isWhite, king);
                }
                else if (piece.name.equals("Queen"))
                {
                    if (piece.column == king.column || piece.row == king.row)
                    {
                        availableMove = blockRook(piece, this, king.isWhite, king);
                    }
                    else
                    {
                        availableMove = blockBishop(piece, this, king.isWhite, king);
                    }
                }
            }
        }
        // cehck is king checked in column
        return !availableMove;
    }

    private boolean staleMate(boolean isWhite)
    {
        //return false if not stalemate and true if stalemate
        int countBlack = 0;
        int countWhite = 0;
        if (pieceList.size() == 2) return true;

        if (pieceList.size() <= 4)
        {
            for (Piece piece : pieceList)
            {
                if (piece.isWhite && piece.name.equals("Knight") || piece.name.equals("Bishop")) countWhite++;
                else if (!piece.isWhite && piece.name.equals("Knight") || piece.name.equals("Bishop")) countBlack++;
            }
            if (countBlack == 1 && countWhite == 1) return true;
        }

        for (Piece piece : pieceList)
        {
            if (piece.isWhite == isWhite)
            {
                for (int i = 0; i < 8; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        if (isValidMove(new Movement(this, piece, i, j))) return false;
                    }
                }
            }
        }
        return true;
    }

    public void CheckMate(){
        Piece king = findKing(Main.isWhiteTurn);
        int col = king.column;
        int row = king.row;
        

        if (chessScanner.isKingChecked(new Movement(this, king, col, row))){
            if (checkMate(king)) {
                boolean check = false;
                
                if (check == false) 
                    if (Main.isWhiteTurn == true){
                        repaint();
                        int n = JOptionPane.showConfirmDialog(
                        null,
                        "BLACK WIN",
                    "Wanna play again?",
                        JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION){
                            Restart();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "GOODBYE");
                        }
                        suggestion = "NO SUGGESTION AVAILABLE";
                        client.stopEngine();
                    }
                    else if (Main.isWhiteTurn == false){
                        repaint();
                        int n = JOptionPane.showConfirmDialog(
                        null,
                "WHITE WIN",
                  "Wanna play again?",
                        JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION){
                            Restart();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "GOODBYE");
                        }
                        suggestion = "NO SUGGESTION AVAILABLE";
                        client.stopEngine();
                    }
            }
        }
        else if (staleMate(Main.isWhiteTurn)) JOptionPane.showMessageDialog(null, "Stalemate",
        "Stalemate", JOptionPane.WARNING_MESSAGE);
        
    }
    private void moveKing(Movement move){
        if (Math.abs(move.piece.column - move.newColumn) == 2){
            Piece rook;
            if (move.piece.column < move.newColumn){
                rook = getPiece(7, move.piece.row);
                addStep(new Movement(this, rook, rook.column, rook.row));
                rook.column = 5;
                if (Main.isWhiteTurn){
                    wk_Castle = false;
                    wq_Castle = false;
                }
                else if (!Main.isWhiteTurn){
                    bk_Castle = false;
                    bq_Castle = false;
                }
            }
            else{
                rook = getPiece(0, move.piece.row);
                addStep(new Movement(this, rook, rook.column, rook.row));
                rook.column = 3;
                if (Main.isWhiteTurn){
                    wk_Castle = false;
                    wq_Castle = false;
                }
                else if (!Main.isWhiteTurn){
                    bk_Castle = false;
                    bq_Castle = false;
                }
            }
            
            Mouse_Move.playSound("src/Resource/castle.wav");
            taskPerformed.add(1);
            taskName.add("castling");
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
            new Promotion(this, move); 
            taskPerformed.set(taskPerformed.size() - 1,taskPerformed.peek() + 1);
            taskName.add("promote"); 
        }
        CheckMate();
    }

    public int getTileNum(int column, int row){
        return row * this.rows * column; 
    }

    public void Remove(Piece piece){
        pieceList.remove(piece);
    }

    public boolean Team(Piece piece1, Piece piece2){
        if (piece1 == null || piece2 == null) return false;

        return piece1.isWhite == piece2.isWhite;
    }

    public boolean isValidMove(Movement move){
        if (Team(move.piece, move.newPiece) || Team(move.piece, getPiece(move.newColumn, move.newRow))){
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
           // DEFAULT CASE
        
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
        //CheckMate();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < column; j++){
                if ((i+j) % 2 == 0) g_2d.setColor(Color.WHITE);
                else g_2d.setColor(new Color(242,219,199));

                if (mouse.move != null && ((j == mouse.move.preColumn && i == mouse.move.preRow) || 
                                           (j == mouse.move.newColumn && i == mouse.move.newRow)))
                    g_2d.setColor(new Color(236, 147, 68));
                g_2d.fillRect(j * titleSize, i * titleSize, titleSize, titleSize);
            }
        if (initComponents2.suggest || GUIsComputer.suggest){
            g_2d.setColor(new Color(245,245,194));
            g_2d.fillRect(position_suggestion[0] * titleSize, position_suggestion[1] * titleSize, titleSize, titleSize);
            g_2d.fillRect(position_suggestion[2] * titleSize, position_suggestion[3] * titleSize, titleSize, titleSize);
            if (initComponents2.suggest) initComponents2.suggest = false;
            else if (GUIsComputer.suggest) GUIsComputer.suggest = false;
        }
        for (int i = 0; i < rows; i++){
            g_2d.setFont(new java.awt.Font("Helvetica Neue", 1, 18));
            if (i%2 == 0) g_2d.setColor(Color.WHITE);
            else g_2d.setColor(new Color(242,219,199));
            if (i == 0){
                g_2d.setColor(new Color(242,219,199));
                g_2d.drawString("8", 0, 15);
                g_2d.setColor(Color.WHITE);
            }
            g_2d.drawString(String.valueOf(8-i-1), 0, 15 + (i+1)*titleSize);
            //g_2d.drawString("1", 0, 8*titleSize - (titleSize - 15));
            g_2d.drawString(encode.getColumn(i), (i+1)*(titleSize) - 12, 8*titleSize);
        }
        if (movePiece != null)
            for (int i = 0; i < rows; i++ )
                for (int j = 0; j < column; j++){
                    if (isValidMove(new Movement(this, movePiece, j ,i))){
                        g_2d.setColor(new Color(120, 120, 120));
                        g_2d.fillRect(j*titleSize, i*titleSize, titleSize, titleSize);
                    }
                }
        for (Piece piece : pieceList){
            piece.paint(g_2d);
        }
    }
}
