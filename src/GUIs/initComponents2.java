package GUIs;

import javax.swing.*;

import ChessGame.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class initComponents2 extends JFrame{
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static boolean suggest = false;

    public initComponents2(){
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(880, 880));

        Board board = new Board("Free", 5);
        jList1 = board.movelist.jList1;

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            //.addGap(0, 880, Short.MAX_VALUE)
            .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(226, 223, 203));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1163, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jList1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jButton1.setIcon(new javax.swing.ImageIcon("src/Resource/Restart_button.png")); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(0);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setToolTipText("NEW GAME");


        jButton4.setIcon(new javax.swing.ImageIcon("src/Resource/Return_button.png")); // NOI18N
        jButton4.setAlignmentY(0.0F);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setIconTextGap(0);
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setToolTipText("RETURN MOVE");
        

        jButton5.setIcon(new javax.swing.ImageIcon("src/Resource/Suggest_button.png")); // NOI18N
        jButton5.setAlignmentY(0.0F);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setIconTextGap(0);
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.setToolTipText("SUGGESTION");
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        ActionListener button = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mouse_Move.playSound("src/Resource/notify.wav");
                int n = JOptionPane.showConfirmDialog(null,"YOU ARE TRYING TO UNDO", "UNDO",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)  board.undoPiece();
                }
        };

        ActionListener restart = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mouse_Move.playSound("src/Resource/notify.wav");
                int n = JOptionPane.showConfirmDialog(null,"YOU ARE TRYING TO RESTART", "RESTART",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)  board.Restart();
                }
        };
        ActionListener suggestion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.suggestion == null){
                    JOptionPane.showMessageDialog(null, "NO SUGGESTION AVAILABLE",
                    "SUGGESTION", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    suggest = true;
                    //System.out.print(suggest);
                    repaint();
                    }
                }
        };

        jButton4.addActionListener(button);
        jButton1.addActionListener(restart);
        jButton5.addActionListener(suggestion);

        pack();

        
    }// </editor-fold>  
}