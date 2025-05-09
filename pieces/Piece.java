package pieces;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.Board;

import java.io.IOException;

public class Piece {

    public int col, row;
    public int xPos, yPos;


    public boolean isRacist;
    public String name;
    public int value;

    public boolean isFirstMove = true;

    BufferedImage sheet;
    {
        try {
        sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("res\\images\\pieces.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;
    Board board;

    public Piece(Board board){
        this.board = board;
    }

    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean MoveCollideswithPiece(int col, int row) {
        return false;
    }

    public void paint(Graphics2D g2d) {

        g2d.drawImage(sprite, xPos, yPos, null);

    }





}
