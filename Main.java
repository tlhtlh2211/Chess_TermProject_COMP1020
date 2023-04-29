package ChessGame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame implements ActionListener{
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
        JLabel name = new JLabel();
        name.setText("USER NAME: ");
        text = new JTextField(20);

        panel.add(name);
        panel.add(text);

        JButton button = new JButton("CLICK TO PLAY GAME");
        panel.add(button);

        JLabel Dashboard = new JLabel();
        Dashboard.setText("DASHBOARD");

        panel.add(Dashboard);

        JLabel his = new JLabel();

        for (int i = 0; i < History.history.size(); i++){
            his.setText(History.history.get(i).name + "-------------------------------" + History.history.get(i).score);
            System.out.println(History.history.get(i).name);
            panel.add(his);
        }
        
        add(panel, BorderLayout.CENTER);

        button.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.remove(this.button);
        String txt = text.getText();
        //History t = new History(txt, 0);
        History.addName(txt, 0);
        this.dispose();
        new Frame(txt);
        //frame.setVisible(true);
        
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


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                open.setVisible(true);
            } 
        });
    }
}