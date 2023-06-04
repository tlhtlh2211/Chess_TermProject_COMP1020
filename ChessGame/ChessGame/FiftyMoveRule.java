package ChessGame;

import javax.swing.JOptionPane;

import Piece.*;

public class FiftyMoveRule {
    static int size;
    static int times;
    public FiftyMoveRule(){
        size = 0;
        times = 0;
    }
    public static void FiftyMove(Piece piece, int s){
        if (size == 0 && times == 0){
            size = s;
            times += 1;
        }
        if (!piece.name.equals("Pawn") && s == size){
            times += 1;
        }
        else{
            size = s;
            times = 0;
        }
        //System.out.println(times);
        if (times == 50){
            JOptionPane.showMessageDialog(null, "DRAW",
                    "50 - MOVE RULE", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
