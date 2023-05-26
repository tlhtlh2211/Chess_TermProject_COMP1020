import javax.swing.JFrame;
import java.awt.*;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        //frame.setLayout(null);
        //frame.setLayout(new FlowLayout());
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);


        Board board = new Board();

        //guitimer.setBounds(20,20,200,200);
        frame.add(board);




        frame.setVisible(true);
    }
}
