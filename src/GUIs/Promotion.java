package GUIs;

import ChessGame.*;
import Piece.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Promotion extends javax.swing.JFrame implements ActionListener{

    public Promotion(Board board, Movement move) {
        initComponents();
        setVisible(true);
        this.move = move;
        this.board = board;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private Movement move;
    private Board board;
    // End of variables declaration  
                        
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton1.setText("Queen");
        jButton1.setActionCommand("");
        jButton1.addActionListener(this);

        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton2.setText("Rook");
        jButton2.addActionListener(this);

        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton3.setText("Bishop");
        jButton3.setActionCommand("Bishop");
        jButton3.addActionListener(this);

        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton4.setText("Knight");
        jButton4.addActionListener(this);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Promote to?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                                                           

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1){
            //queen
            board.pieceList.add(new Queen(board, move.newColumn, move.newRow, move.piece.isWhite));
        }
        else if (e.getSource() == jButton2){
            //rook
            board.pieceList.add(new Rook(board, move.newColumn, move.newRow, move.piece.isWhite));
        }
        else if (e.getSource() == jButton3){
            //bishop
            board.pieceList.add(new Bishop(board, move.newColumn, move.newRow, move.piece.isWhite));
        }
        else if (e.getSource() == jButton4){
            //knight
            board.pieceList.add(new Knight(board, move.newColumn, move.newRow, move.piece.isWhite));
        }
        Mouse_Move.playSound("src/Resource/promote.wav");
        board.Remove(move.piece);
        this.dispose();
    }
}
