package GUIs;

import javax.swing.*;

import ChessGame.History;
import ChessGame.Main;
import DisplayPreviousMatch.BoardGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class initComponents1 extends JFrame implements ActionListener{

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    //private javax.swing.JButton jButton5;

    public initComponents1(){
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane();

        jButton1.setBackground(new java.awt.Color(193, 64, 94));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jButton1.setText("START GAME");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(this);

        getContentPane().add(jButton1);
        jButton1.setBounds(160, 220, 250, 60);

        jButton2.setBackground(new java.awt.Color(193, 64, 94));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton2.setText("WHITE");
        jButton2.setOpaque(true);
        jButton2.setContentAreaFilled(true);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setBackground(new Color(188, 198, 193)); 
        jButton2.setForeground(Color.red);
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        getContentPane().add(jButton2);
        jButton2.setBounds(310, 170, 100, 40);

        jButton3.setBackground(new java.awt.Color(193, 64, 94));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        jButton3.setText("PREVIOUS MATCH");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(this);

        getContentPane().add(jButton3);
        jButton3.setBounds(170, 70, 110, 30);

        jButton4.setBackground(new java.awt.Color(193, 64, 94));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton4.setText("BLACK");
        //jButton4.setOpaque(true);
        //jButton4.setContentAreaFilled(true);
        //jButton4.setBorderPainted(false);
        //jButton4.setFocusPainted(false);
        jButton4.setBackground(new Color(188, 198,  193)); 
        jButton4.setForeground(Color.red);
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        getContentPane().add(jButton4);
        jButton4.setBounds(160, 170, 100, 40);

        jButton5.setBackground(new java.awt.Color(193, 64, 94));
        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton5.setText("DASHBOARD");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(this);

        getContentPane().add(jButton5);
        jButton5.setBounds(290, 70, 100, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BLACK OR WHITE");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 110, 140, 50);

        jLabel1.setBackground(new java.awt.Color(37, 26, 26));
        jLabel1.setForeground(new java.awt.Color(160, 119, 119));
        jLabel1.setIcon(new javax.swing.ImageIcon("src/Resource/freemodeboardv3.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 580, 380);

        pack();

        ActionListener button = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButton4){
                    Main.isWhiteTurn = false;
                    Main.canPlay = true;
                    if (Main.colorChange == 0){
                        jButton4.setBackground(Color.BLACK);
                        jButton4.setForeground(Color.WHITE);
                        Main.colorChange = -1;
                        Main.justClicked = true;
                    }
                    else if (Main.colorChange == 1 && Main.justClicked == false){
                        jButton4.setForeground(Color.WHITE);
                        jButton4.setBackground(Color.BLACK);
                        jButton2.setBackground(new Color(188, 198, 193));
                        jButton2.setForeground(Color.red);
                        Main.colorChange = -1;
                        Main.justClicked = true;
                    }
                    //button_black.addActionListener(this);
                }
                else if (e.getSource() == jButton2){
                    Main.isWhiteTurn = true;
                    Main.canPlay = true;
                    if (Main.colorChange == 0){
                        jButton2.setBackground(Color.BLACK);
                        jButton2.setForeground(Color.WHITE);
                        Main.colorChange = 1;
                        Main.justClicked = false;
                    }
                    else if (Main.colorChange == -1 && Main.justClicked == true){
                        jButton2.setForeground(Color.WHITE);
                        jButton2.setBackground(Color.BLACK);
                        jButton4.setBackground(new Color(188, 198, 193));
                        jButton4.setForeground(Color.red);
                        Main.colorChange = 1;
                        Main.justClicked = false;

                    }
                }
                Main.ini = Main.isWhiteTurn;
            }
        };
        
        jButton2.addActionListener(button);
        jButton4.addActionListener(button);  
        
    }// </editor-fold>  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton5){
            this.dispose();
            new DashBoard();
        }
        else if (e.getSource() == jButton3){
            this.dispose();
            new BoardGUI();
        }
        else if (e.getSource() == jButton1){
            if (Main.canPlay){
                new History(100);
                this.dispose();
                new initComponents2();
            }
            else{
                JOptionPane.showMessageDialog(null, "PLEASE CHOOSE BLACK OR WHITE",
                   "NOT CHOOSING TEAM", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
