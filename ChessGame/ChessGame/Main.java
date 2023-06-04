package ChessGame;

import javax.swing.*;

import GUIs.OptionMain;

public class Main extends JFrame{

    public static boolean isWhiteTurn = true;
    public static boolean canPlay = false;
    public static int colorChange = 0;
    public static boolean justClicked = true;
    public static boolean ini = true;

    public Main() {
        new OptionMain();
    }
                            
    public static void main(String[] args){
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Main();
                }
            });
    }
}