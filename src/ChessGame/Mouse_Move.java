package ChessGame;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import Piece.Piece;

public class Mouse_Move extends MouseAdapter{

    Board board;
    public Movement move;
    public Mouse_Move(Board board){
        this.board = board;
    }
    @Override
    public void mouseClicked(MouseEvent e){
        int column = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        //System.out.println(column + " " + row);

        Piece xy = board.getPiece(column, row);
        if (xy != null && xy.isWhite == Main.isWhiteTurn){
            board.movePiece = xy; 
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        int column = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        //System.out.println(column + " " + row);

        Piece xy = board.getPiece(column, row);
        if (xy != null && xy.isWhite == Main.isWhiteTurn){
            board.movePiece = xy; 
        }
    }
    

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.movePiece != null){
            board.movePiece.xPOS = e.getX() - board.titleSize / 2;
            board.movePiece.yPOS = e.getY() - board.titleSize / 2;
            board.repaint();
        }
        board.CheckMate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int column = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;
        if (board.movePiece != null && (board.movePiece.column != column || board.movePiece.row != row)){
            Movement move = new Movement(board, board.movePiece, column, row);

            if (board.isValidMove(move)){
                board.Move(move);
                this.move = move;
                FiftyMoveRule.FiftyMove(move.piece, board.pieceList.size());
                Main.isWhiteTurn = !Main.isWhiteTurn;

                if (board.playMode.equals("Time") && !Main.isWhiteTurn){
                    board.countTimes.timer1.stop();
                    board.countTimes.timer2.start();
                    board.countTimes.turn = 2;
                }
                else if (board.playMode.equals("Time") && Main.isWhiteTurn){
                    board.countTimes.timer2.stop();
                    board.countTimes.timer1.start();
                    board.countTimes.turn = 1;
                }

                if (board.playMode.equals("Computer"))
                    board.Computer();

                if (move.newPiece == null && !board.playMode.equals(""))
                    playSound("src/Resource/move-self.wav");
                else if (move.newPiece != null && !board.playMode.equals("")) playSound("src/Resource/capture.wav");
            }
            else{
                board.movePiece.xPOS = board.movePiece.column * board.titleSize;
                board.movePiece.yPOS = board.movePiece.row * board.titleSize;
            }
        }
        else{
            board.movePiece.xPOS = board.movePiece.column * board.titleSize;
            board.movePiece.yPOS = board.movePiece.row * board.titleSize;
        }
        board.movePiece = null;
        board.repaint();
        board.CheckMate();
    }
    public static void playSound(String soundFile) {
        try {
            // Load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));

            // Get a clip to play the sound
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Start playing the sound
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
