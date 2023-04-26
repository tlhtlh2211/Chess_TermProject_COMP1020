package Piece;

import javax.imageio.ImageIO;

import ChessGame.Board;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.File;

public class Piece {
    public int column, row;
    public int xPOS, yPOS;

    public boolean isWhite;
    public String name;
    public int value;


    BufferedImage sheet;    
    {
    try {
        File file = new File("/Users/tranlehai/OOP/Chess/src/resouces/Pieces.png");
        sheet = ImageIO.read(file);
        } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    protected int sheetScale = sheet.getWidth()/6;
    
    Image sprite;

    Board board;


    public Piece(Board board){
        this.board = board;
    }

    public void paint(Graphics2D g_2d){
        g_2d.drawImage(sprite, xPOS, yPOS, null);
    }
}

