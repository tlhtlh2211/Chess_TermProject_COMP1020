import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
public class GUItimer extends JPanel {
    JLabel label;
    JLabel label1;
    Timer timer1;
    Timer timer2;
    int turn = 1;
    int second1;
    int second2;

    int minute1;
    int minute2;
    GUItimer(){
        label = new JLabel("new label");
//        label.setBounds(20,20,100,100);
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        label1 = new JLabel("new label1");
        second1 = 0;
        minute1 = 3;

        second2 = 0;
        minute2 = 3;

        countdown();
        timer1.start();
        //timer2.start();

        label.setText("3:00");
        label1.setText("3:00");

        this.add(label);
        this.add(label1);
    }

    public void countdown(){
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
                }
            }
        });
    }


}
