package ChessGame;

import javax.swing.*;

import java.awt.*;


public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        Board board = new Board();
        
        frame.add(board);
        
        frame.addMouseListener(board.mouse);
        frame.addMouseMotionListener(board.mouse);

        frame.setVisible(true);
    }
}