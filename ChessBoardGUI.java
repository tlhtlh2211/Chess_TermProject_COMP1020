import javax.swing.*;
import java.awt.*;

public class ChessBoardGUI extends JFrame {

    public ChessBoardGUI() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel chessBoardPanel = new JPanel(new GridLayout(8, 8));
        chessBoardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel();
                if ((i + j) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.BLACK);
                }
                chessBoardPanel.add(square);
            }
        }

        add(chessBoardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ChessBoardGUI();
    }
}
