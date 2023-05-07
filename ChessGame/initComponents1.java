package ChessGame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class initComponents1 extends JFrame implements ActionListener{

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;

    public initComponents1(){
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
    
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(242,219,199));

        jLabel1.setFont(new java.awt.Font("Chalkduster", 1, 60)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHESS GAME");

        jLabel2.setFont(new java.awt.Font("Herculanum", 0, 24)); // NOI18N
        jLabel2.setText("BLACK OR WHITE");

        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jButton1.setText("WHITE");
        jButton1.setOpaque(true);
        jButton1.setContentAreaFilled(true);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setBackground(new Color(188, 198, 193)); 
        jButton1.setForeground(Color.white);
        

        jButton2.setFont(new java.awt.Font("Adelle Sans Devanagari", 1, 24)); // NOI18N
        jButton2.setText("BLACK");
        jButton2.setOpaque(true);
        jButton2.setContentAreaFilled(true);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setBackground(new Color(188, 198,  193)); 
        jButton2.setForeground(Color.white);
        

        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jButton3.setText("CLICK TO PLAY GAME");
        jButton3.addActionListener(this);
       

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(309, 309, 309))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        //jButton2.getAccessibleContext().setAccessibleName("jButton2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        ActionListener button = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButton2){
                    Main.isWhiteTurn = false;
                    Main.canPlay = true;
                    if (Main.colorChange == 0){
                        jButton2.setBackground(Color.BLACK);
                        Main.colorChange = -1;
                        Main.justClicked = true;
                    }
                    else if (Main.colorChange == 1 && Main.justClicked == false){
                        jButton2.setBackground(Color.BLACK);
                        jButton1.setBackground(new Color(188, 198, 193));
                        Main.colorChange = -1;
                        Main.justClicked = true;
                    }
                    //button_black.addActionListener(this);
                }
                else if (e.getSource() == jButton1){
                    Main.isWhiteTurn = true;
                    Main.canPlay = true;
                    if (Main.colorChange == 0){
                        jButton1.setBackground(Color.BLACK);
                        Main.colorChange = 1;
                        Main.justClicked = false;
                    }
                    else if (Main.colorChange == -1 && Main.justClicked == true){
                        jButton1.setBackground(Color.BLACK);
                        jButton2.setBackground(new Color(188, 198, 193));
                        Main.colorChange = 1;
                        Main.justClicked = false;

                    }
                    //button_white.addActionListener(this);
                }
                Main.ini = Main.isWhiteTurn;
            }
        };
        
        jButton1.addActionListener(button);
        jButton2.addActionListener(button);  
    }// </editor-fold>  
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.remove(this.button);
        if (Main.canPlay){
            //String txt = text.getText();
            //History t = new History(txt, 0);
            //History.addName(txt, 0);
            this.dispose();
            //new Frame("");
            new initComponents2();
            //frame.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "PLEASE CHOOSE BLACK OR WHITE",
               "NOT CHOOSING TEAM", JOptionPane.WARNING_MESSAGE);
        }
    }
}
