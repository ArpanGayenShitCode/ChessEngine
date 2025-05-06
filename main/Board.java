package main;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import pieces.*;

public class Board extends JPanel{
    
    public int tileSize = 100;

    int cols = 8;
    int rows = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    public Board(){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }


    public Piece getPiece(int col, int row) {

        for(Piece piece : pieceList) {
            if(piece.col == col && piece.row == row)
                return piece;
        }
        return null;
    }

    public void makeMove(Move move) {


        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move);
    }

    public void capture(Move move){
        pieceList.remove(move.capture);
    }

    public boolean isValidMove(Move move){

        if(sameTeam(move.piece, move.capture)) {
            return false;
        }

        if(!move.piece.isValidMovement(move.newCol, move.newRow)){
            return false;
        }

        if(move.piece.MoveCollideswithPiece(move.newCol, move.newRow)){
            return false;
        }
        return true;
    }

    public boolean sameTeam(Piece x, Piece y){
        if (x == null || y == null) {
            return false;
        }
        return x.isRacist == y.isRacist;
    }


    public void addPieces() {
        //Negro Pieces
            //Main Items
            pieceList.add(new Rook(this, 0, 0, false));
            pieceList.add(new Knight(this, 1, 0, false));
            pieceList.add(new Bishop(this, 2, 0, false));
            pieceList.add(new Queen(this, 3, 0, false));
            pieceList.add(new King(this, 4, 0, false));
            pieceList.add(new Bishop(this, 5, 0, false));
            pieceList.add(new Knight(this, 6, 0, false));
            pieceList.add(new Rook(this, 7, 0, false));

            //Juicers
            pieceList.add(new Pawn(this, 0, 1, false));
            pieceList.add(new Pawn(this, 1, 1, false));
            pieceList.add(new Pawn(this, 2, 1, false));
            pieceList.add(new Pawn(this, 3, 1, false));
            pieceList.add(new Pawn(this, 4, 1, false));
            pieceList.add(new Pawn(this, 5, 1, false));
            pieceList.add(new Pawn(this, 6, 1, false));
            pieceList.add(new Pawn(this, 7, 1, false));

        //Racist Pieces
            //Main Items
            pieceList.add(new Rook(this, 0, 7, true));
            pieceList.add(new Knight(this, 1, 7, true));
            pieceList.add(new Bishop(this, 2, 7, true));
            pieceList.add(new Queen(this, 3, 7, true));
            pieceList.add(new King(this, 4,7, true));
            pieceList.add(new Bishop(this, 5,7, true));
            pieceList.add(new Knight(this, 6, 7, true));
            pieceList.add(new Rook(this, 7, 7, true));

        //Juicers
            pieceList.add(new Pawn(this, 0, 6, true));
            pieceList.add(new Pawn(this, 1, 6, true));
            pieceList.add(new Pawn(this, 2, 6, true));
            pieceList.add(new Pawn(this, 3, 6, true));
            pieceList.add(new Pawn(this, 4, 6, true));
            pieceList.add(new Pawn(this, 5, 6, true));
            pieceList.add(new Pawn(this, 6, 6, true));
            pieceList.add(new Pawn(this, 7, 6, true));

    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //paint board
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                g2d.setColor((c+r) % 2 ==0? new Color(145,170,192) : new Color(67, 90, 106));
                g2d.fillRect(c * tileSize, r * tileSize , tileSize , tileSize);
            }
        }

        //paint highlights
        if(selectedPiece != null)
            for(int r = 0; r < rows; r++){
                for(int c = 0; c < cols; c++){
                    
                    if(isValidMove(new Move(this, selectedPiece, c, r))) {

                        g2d.setColor(new Color(68, 180,5,190));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

                    }

                    if(getPiece(c, r) != null && getPiece(c, r).isRacist != selectedPiece.isRacist && isValidMove(new Move(this, selectedPiece, c, r))){
                        g2d.setColor(new Color(200, 5,0,240));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }

        //paint pieces
        for(Piece piece : pieceList){
            piece.paint(g2d);
        }

    }

}
