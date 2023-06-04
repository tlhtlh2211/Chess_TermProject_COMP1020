package ChessGame;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import java.io.*;
import java.util.*;

public class MoveList {
    private Board board;
    public javax.swing.JList<String> jList1;
    public MoveList(Board board){
        this.board = board;
        jList1 = new JList<>();
        deleteMove();
    }
    public void deleteMove(){
        File file = new File("src/ChessGame/Move");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
            //System.out.println("File emptied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = new File("src/ChessGame/PreMove");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
            //System.out.println("File emptied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void display(){
        DefaultListModel<String> Move;
        BufferedReader br = null;
        try{
            Move = new DefaultListModel<>();
            br = new BufferedReader(new FileReader("src/ChessGame/Move"));
            Scanner sc = new Scanner(br);
            //sc.nextLine();
            while (sc.hasNextLine())
                Move.addElement(sc.nextLine());
            jList1.setModel(Move);
            sc.close();
        }
        catch(Exception e){
            System.out.print(e);
        }
        board.movelist.jList1 = jList1;
    }
    public void addMove(boolean state, Stack<Movement> undoList, Stack<Integer> taskPerformed, Stack<String> taskName){
        String s = "";
        DefaultListModel<String> Move;
        BufferedReader br = null;
        try{
            Move = new DefaultListModel<>();
            br = new BufferedReader(new FileReader("src/ChessGame/Move"));
            Scanner sc = new Scanner(br);
            while (sc.hasNextLine())
                Move.addElement(sc.nextLine());
            
            String[] t = Move.lastElement().split("     ");
            s = moveState(Integer.parseInt(t[0]), t, state, undoList, taskPerformed, taskName);
            if (t.length == 2){
                Move.set(Move.size() - 1, s);
            }
            else Move.addElement(s);  
            sc.close();      
        }
        catch(Exception e){
            Move = new DefaultListModel<>();
            String[] t = new String[1];
            s = moveState(0, t, state, undoList, taskPerformed, taskName);
            Move.addElement(s);
            //System.out.println(e);
        }
        try{
            PrintWriter out = new PrintWriter("src/ChessGame/Move");
            for (int i = 0; i < Move.size(); i++){
                out.println(Move.get(i));
            }

            out.close();
            
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void undoStep(){
        String s = "";
        DefaultListModel<String> Move;
        BufferedReader br = null;
        try{
            Move = new DefaultListModel<>();
            br = new BufferedReader(new FileReader("src/ChessGame/Move"));
            Scanner sc = new Scanner(br);
            while (sc.hasNextLine())
                Move.addElement(sc.nextLine());
            
            String[] t = Move.lastElement().split("     ");
            if (t.length == 3){
                s = t[0];
                s += "     " + t[1];
                Move.set(Move.size()-1, s);
            }
            else if (t.length == 2){
                Move.remove(Move.size()-1);
            }  
            sc.close();     
        }
        catch(Exception e){
            Move = new DefaultListModel<>();
            System.out.println(e);
        }
        try{
            PrintWriter out = new PrintWriter("src/ChessGame/Move");
            for (int i = 0; i < Move.size(); i++){
                out.println(Move.get(i));
            }
            out.close();
            display();
            
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private String moveState(int count, String[] st, boolean state, Stack<Movement> undoList, Stack<Integer> taskPerformed, Stack<String> taskName){
        String s = "";
        if (count == 0){
            s = "1";
            s += "     ";
            String t = taskName.peek();
            if (t.equals("add")){
                Movement move = undoList.peek();
                s += encodePiece(move.piece.name, move.piece.isWhite);
                s += encodeColumn(move.newColumn);
                s += String.valueOf(8-move.newRow);
            }
        }
        else if (count != 0){
            if (st.length == 2){
                s = String.valueOf(count);
                s += "     ";
                s += st[1];
            }
            else s = String.valueOf(count + 1);
            s += "     ";
            if (taskName.peek().equals("add") && !taskName.get(taskName.size()-2).equals("castling")){
                Movement move = undoList.peek();
                s += encodePiece(move.piece.name, move.piece.isWhite);
                s += encodeColumn(move.newColumn);
                s += String.valueOf(8-move.newRow); 
            }
            else if (taskName.get(taskName.size()-2).equals("castling")){
                Movement moveK = undoList.peek();
                Movement moveR = undoList.get(undoList.size()-2);
                if (Math.abs(moveK.newColumn - moveR.newColumn) == 1) s += "O-O";
                else if (Math.abs(moveK.newColumn - moveR.newColumn) == 2) s += "O-O-O";
            }
            else if (taskName.peek().equals("remove")){
                Movement move = undoList.get(undoList.size()-2);
                s += encodePiece(move.piece.name, move.piece.isWhite);
                s += "x";
                s += encodeColumn(move.newColumn);
                s += String.valueOf(8-move.newRow);
            }
        }
        return s;
    }
    private String encodePiece(String name, boolean isWhite){
        if (!isWhite){
            if (name.equals("Queen")) return "q";
            else if (name.equals("King")) return "k";
            else if (name.equals("Rook")) return "r";
            else if (name.equals("Bishop")) return "b";
            else if (name.equals("Knight")) return "n";
            else if (name.equals("Pawn")) return "";
        }
        else{
            if (name.equals("Queen")) return "Q";
            else if (name.equals("King")) return "K";
            else if (name.equals("Rook")) return "R";
            else if (name.equals("Bishop")) return "B";
            else if (name.equals("Knight")) return "N";
            else if (name.equals("Pawn")) return "";
        }
        return null;
    }
    private String encodeColumn(int col){
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
    public void Record(Stack<Movement> undoList, Stack<Integer> taskPerformed, Stack<String> taskName){
        DefaultListModel<String> Move;
        BufferedReader br = null;
        try{
            Move = new DefaultListModel<>();
            br = new BufferedReader(new FileReader("src/ChessGame/PreMove"));
            Scanner sc = new Scanner(br);
            while (sc.hasNextLine())
                Move.addElement(sc.nextLine());
            Move.addElement(String.valueOf(taskPerformed.peek()));
            int count = taskPerformed.peek();
            for (int i = 0; i < count; i++){
                Move.addElement(taskName.elementAt(taskName.size() - 1 - i));
                Movement move = undoList.elementAt(undoList.size() - 1 - i);
                Move.addElement(move.piece.name + " " + move.piece.column + " " + move.piece.row + " " + move.preColumn + " " + move.preRow + " " + move.piece.isWhite);
            }
            sc.close();
        }
        catch(Exception e){
            Move = new DefaultListModel<>();
            Move.addElement(String.valueOf(taskPerformed.peek()));
            Move.addElement(taskName.peek());
            Movement move = undoList.elementAt(undoList.size() - 1);
            Move.addElement(move.piece.name + " "  + move.piece.column + " " + move.piece.row + " " + move.preColumn + " " + move.preRow + move.piece.isWhite);
        }
        try{
            PrintWriter out = new PrintWriter("src/ChessGame/PreMove");
            for (int i = 0; i < Move.size(); i++){
                out.println(Move.get(i));
            }
            out.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void undoRecord(){
        DefaultListModel<String> Move;
        BufferedReader br = null;
        try{
            Move = new DefaultListModel<>();
            br = new BufferedReader(new FileReader("src/ChessGame/PreMove"));
            Scanner sc = new Scanner(br);
            while (sc.hasNextLine())
                Move.addElement(sc.nextLine());
            sc.close();
        }
        catch(Exception e){
            Move = new DefaultListModel<>();
        }
        try{
            PrintWriter out = new PrintWriter("src/ChessGame/PreMove");
            int count = Move.size() - 1;
            while (Move.get(count).length() != 1){
                Move.remove(count);
                count -= 1;
            }
            Move.remove(count);
            for (int i = 0; i < Move.size(); i++){
                out.println(Move.get(i));
            }
            out.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
