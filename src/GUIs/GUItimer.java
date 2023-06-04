package GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class GUItimer extends JPanel {
    public JLabel label;
    public JLabel label1;
    public Timer timer1;
    public Timer timer2;
    public int turn;
    private int second1;
    private int second2;

    private int minute1;
    private int minute2;

    private int time;
    public GUItimer(int time){
        this.time = time;
        label = new JLabel();
        label1 = new JLabel();
//        label.setBounds(20,20,100,100);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label1.setFont(new Font("Arial", Font.PLAIN, 20));

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //jLabel2.setText("10 : 00");

        second1 = 0;
        minute1 = time;

        second2 = 0;
        minute2 = time;

        turn = 2;
        countdown(turn);
        timer1.start();

        label.setText(String.valueOf(time) + ":00");
        label1.setText(String.valueOf(time) + ":00");

        this.add(label);
        
        this.add(label1);
    }
    public void Restart(){
        second1 = 0;
        minute1 = time;

        second2 = 0;
        minute2 = time;

        turn = 2;
        timer1.start();

        label.setText(String.valueOf(time) + ":00");
        label1.setText(String.valueOf(time) + ":00");

    }

    public void countdown(int turn){
        timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second1 --;
                label.setText("" + minute1 + ":" + second1);


                if (second1 == -1){
                    second1 = 59;
                    minute1 -= 1;
                    label.setText("" + minute1 + ":" + second1);

                }
                if (minute1 == 0 && second1 == 0){
                    timer1.stop();
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
                }
            }

        });

        timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn == 2) {
                    second2--;
                    label1.setText("" + minute2 + ":" + second2);
                }

                if (second2 == -1){
                    second2 = 59;
                    minute2 -= 1;
                    label1.setText("" + minute2 + ":" + second2);

                }
                if (minute2 == 0 && second2 == 0){
                    timer2.stop();
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
                }
            }
        });
    }


}
