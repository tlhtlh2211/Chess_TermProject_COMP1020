package Chess_TermProject_COMP1020.ChessGame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame implements ActionListener{
    public static boolean isWhiteTurn;
    public static boolean canPlay = false;
    static int colorChange = 0;
    static boolean justClicked = true;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                Main lg = new Main();
                lg.setVisible(true);
            }
        });
    }
    final JTextField text;
    public Main(){
        setTitle("CHESS GAME");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        //JLable name = new JLabel("USERNAME: ");
        JLabel name1 = new JLabel();
        name1.setText("PLAYER'S NAME: ");
        text = new JTextField(25);

        panel.add(name1);
        panel.add(text);

        JLabel name2 = new JLabel("BLACK OR WHITE?", SwingConstants.CENTER);
        name2.setFont(new Font("Serif", Font.PLAIN, 16));
        name2.setPreferredSize(new Dimension(400, 50));

        panel.add(name2);

        JButton button_black = new JButton("BLACK");
        button_black.setPreferredSize(new Dimension(200, 50));
        button_black.setOpaque(true);
        button_black.setContentAreaFilled(true);
        button_black.setBorderPainted(false);
        button_black.setFocusPainted(false);
        button_black.setBackground(new Color(188, 198, 193)); 
        button_black.setForeground(Color.white);
        panel.add(button_black);

        JButton button_white = new JButton("WHITE");
        button_white.setPreferredSize(new Dimension(200, 50));
        button_white.setOpaque(true);
        //button_white.setContentAreaFilled(true);
        button_white.setBorderPainted(false);
        button_white.setFocusPainted(false);
        button_white.setBackground(new Color(188, 198, 193)); 
        button_white.setForeground(Color.white);
        panel.add(button_white);

        JButton playChess = new JButton("KLICK TO PLAY GAME");
        playChess.setPreferredSize(new Dimension(400, 50));
        panel.add(playChess);

        JLabel Dashboard = new JLabel("DASHBOARD", SwingConstants.CENTER);
        Dashboard.setPreferredSize(new Dimension(400, 50));

        panel.add(Dashboard);

        JLabel his = new JLabel();

        for (int i = 0; i < History.history.size(); i++){
            his.setText(History.history.get(i).name + "-------------------------------" + History.history.get(i).score);
            System.out.println(History.history.get(i).name);
            panel.add(his);
        }
        
        add(panel, BorderLayout.CENTER);

        playChess.addActionListener(this);



        ActionListener button = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_black){
                    isWhiteTurn = false;
                    canPlay = true;
                    if (colorChange == 0){
                        button_black.setBackground(Color.BLACK);
                        colorChange = -1;
                        justClicked = true;
                    }
                    else if (colorChange == 1 && justClicked == false){
                        button_black.setBackground(Color.BLACK);
                        button_white.setBackground(new Color(188, 198, 193));
                        colorChange = -1;
                        justClicked = true;
                    }
                    //button_black.addActionListener(this);
                }
                else if (e.getSource() == button_white){
                    isWhiteTurn = true;
                    canPlay = true;
                    if (colorChange == 0){
                        button_white.setBackground(Color.BLACK);
                        colorChange = 1;
                        justClicked = false;
                    }
                    else if (colorChange == -1 && justClicked == true){
                        button_white.setBackground(Color.BLACK);
                        button_black.setBackground(new Color(188, 198, 193));
                        colorChange = 1;
                        justClicked = false;

                    }
                    //button_white.addActionListener(this);
                }
            }
        };
        button_black.addActionListener(button);
        button_white.addActionListener(button);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.remove(this.button);
        if (canPlay){
            String txt = text.getText();
            //History t = new History(txt, 0);
            History.addName(txt, 0);
            this.dispose();
            new Frame(txt);
        //frame.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "PLEASE CHOOSE BLACK OR WHITE",
               "NOT CHOOSING TEAM", JOptionPane.WARNING_MESSAGE);
        }
        
    }
}
class Frame extends JFrame{
    public Frame(String text){
            
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new GridBagLayout());
        setSize(1000, 1000);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        
        Board board = new Board();   
        frame.add(board);
            
        //frame.addMouseListener(board.mouse);
        //frame.addMouseMotionListener(board.mouse);
        frame.setTitle("CHESS GAME OF " + text);
        frame.setVisible(true);

        JFrame open = frame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                open.setVisible(true);
            } 
        });
    }
}