package DisplayPreviousMatch;

import java.io.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import ChessGame.*;
import Piece.*;

class columnTable{
    int count;
    String firstMove = null;
    String secondMove = null;
    public columnTable(int count, String firstMove, String secondMove){
        this.count = count;
        this.firstMove = firstMove;
        this.secondMove = secondMove;
    }
    public columnTable(int count, String firstMove){
        this.count = count;
        this.firstMove = firstMove;
    }
}

public class ReadFile extends Board{
    private LinkedList<Movement> undoList = new LinkedList<>();
    private LinkedList<String> taskName = new LinkedList<>();
    private LinkedList<Integer> taskPerformed = new LinkedList<>();
    private LinkedList<columnTable> table = new LinkedList<>();
    private List<String[]> jTable = new ArrayList<>();
    DefaultTableModel model;
    int state_task = 0;
    int state_move = 0;
    public ReadFile(){
        // Draw Board
        super();
        
        // Read PreMove File
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("src/ChessGame/PreMove"));
            Scanner sc = new Scanner(br);
            while (sc.hasNextLine()){
                taskPerformed.add(Integer.parseInt(sc.nextLine()));
                //System.out.println(taskPerformed.peekLast());
                for (int i = 0; i < taskPerformed.peekLast(); i++){
                    taskName.add(sc.nextLine());
                    String input[] = sc.nextLine().split(" ");
                    int newColumn = Integer.parseInt(input[1]);
                    int newRow = Integer.parseInt(input[2]);
                    int preColumn = Integer.parseInt(input[3]);
                    int preRow = Integer.parseInt(input[4]);
                    undoList.add(new Movement(this, addPiece(input[0], newColumn, newRow, input[5]), newColumn, newRow));
                    undoList.peekLast().preColumn = preColumn;
                    undoList.peekLast().preRow = preRow;
                    //Movement move = undoList.peekLast();
                    //System.out.println(taskName.peekLast());
                    //System.out.println(move.piece.name + " " + move.piece.column + " " + move.piece.row + " " + move.preColumn + " " + move.preRow + " " + move.piece.isWhite);
                }
            }
            sc.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        // Read Move file
        try{
            br = new BufferedReader(new FileReader("src/ChessGame/Move"));
            Scanner sc = new Scanner(br);
            while (sc.hasNext()){
                String input[] = sc.nextLine().split("     ");
                if (input.length == 2){
                    table.add(new columnTable(Integer.parseInt(input[0]), input[1]));
                    //System.out.println(input[0] + input[1]);
                }
                else if (input.length == 3){
                    table.add(new columnTable(Integer.parseInt(input[0]), input[1], input[2]));
                    //System.out.println(input[0] + input[1] + input[2]);
                }
            }
            sc.close();
            addValuetoTable(table);
        }
        catch (Exception e){
            System.out.println(e);
        }
        
    }
    private void addValuetoTable(LinkedList<columnTable> table){
        for (int i = 0; i < table.size(); i++){
            jTable.add(new String[]{String.valueOf(table.get(i).count), table.get(i).firstMove, table.get(i).secondMove});
        }
        String column[] = new String[]{"", "", ""};
        model = new DefaultTableModel(jTable.toArray(new Object[0][]), column);
    }
    private Piece addPiece(String name, int column, int row, String isWhite){
        boolean W;
        if (isWhite.equals("true")) W = true;
        else W = false;
        if (name.equals("Pawn")){
            return new Pawn(this, column, row, W);
        }
        else if (name.equals("Rook")){
            return new Rook(this, column, row, W);
        }
        else if (name.equals("Bishop")){
            return new Bishop(this, column, row, W);
        }
        else if (name.equals("Queen")){
            return new Queen(this, column, row, W);
        }
        else if (name.equals("King")){
            return new King(this, column, row, W);
        }
        else if (name.equals("Knight")){
            return new Knight(this, column, row, W);
        }
        return null;
    }
    public void startState(){
        pieceList.clear();
        state_task = 0;
        state_move = 0;
        addPiece();
        repaint();
    }
    public void finalState(){
        while (state_task < taskPerformed.size()){
            nextStep();
        }
    }
    public void nextStep(){
        if (this.state_task < taskPerformed.size()){
            int t = taskPerformed.get(state_task);
            state_task += 1;
            //System.out.println(t);
            for (int i = 0; i < t; i++){
                if (!taskName.get(state_move).equals("castling")){
                    Movement move = undoList.get(state_move);
                    //System.out.println(move.piece.name + " " + move.newColumn + " " + move.newRow + " " + move.preColumn + " " + move.preRow + " " + move.piece.isWhite);
                    Move(new Movement(this, getPiece(move.preColumn, move.preRow), move.newColumn, move.newRow));
                }
                state_move += 1;
                repaint();
            }
        }
    }
    public void prevStep(){
        if (this.state_task > 0){
            state_task -= 1;
            int t = taskPerformed.get(state_task);
            //System.out.println(t);
            for (int i = 0; i < t; i++){
                state_move -= 1;
                String name = taskName.get(state_move);
                if (name.equals("remove")){
                    Movement move = undoList.get(state_move);
                    pieceList.add(move.piece);
                }
                else if (name.equals("add")){
                    Movement move = undoList.get(state_move);
                    if (move.piece.name.equals("King") && (state_move + 1) < undoList.size() && taskName.get(state_move + 1).equals("castling")){
                        Remove(getPiece(move.newColumn, move.newRow));
                        pieceList.add(new King(this, move.preColumn, move.preRow, move.piece.isWhite));
                    }
                    else {
                        //System.out.println(move.piece.name + " " + move.newColumn + " " + move.newRow + " " + move.preColumn + " " + move.preRow + " " + move.piece.isWhite);
                        Move(new Movement(this, getPiece(move.newColumn, move.newRow), move.preColumn, move.preRow));
                    }
                }
                else if (name.equals("castling")){
                    Movement move = undoList.get(state_move);
                    //System.out.println(move.piece.name + " " + move.newColumn + " " + move.newRow + " " + move.preColumn + " " + move.preRow + " " + move.piece.isWhite);
                    Move(new Movement(this, getPiece(move.newColumn, move.newRow), move.preColumn, move.preRow));
                }
                repaint();
            }
        }
    }
}
