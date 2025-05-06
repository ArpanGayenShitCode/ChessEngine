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
    CheckScanner cs = new CheckScanner(this);

    public int enPassantTile = -1;

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

        if(move.piece.name.equals("juicer")) {
            movePawn(move);
        }
        else {
            move.piece.col = move.newCol;
            move.piece.row = move.newRow;
            move.piece.xPos = move.newCol * tileSize;
            move.piece.yPos = move.newRow * tileSize;

            move.piece.isFirstMove = false;

            capture(move.capture);
        }
        
    }

    public void movePawn(Move move) {

        //en passant
        int colourIndex =  move.piece.isRacist? 1 : -1;

        if(getTileNum(move.newCol, move.newRow) == enPassantTile){
            move.capture = getPiece(move.newCol, move.newRow + colourIndex);
        }
        if(Math.abs(move.piece.row - move.newRow) == 2) {
            enPassantTile = getTileNum(move.newCol, move.newRow + colourIndex);
        }
        else {
            enPassantTile = -1;
        }

        //promotion
        colourIndex = move.piece.isRacist? 0 : 7;
        if(move.newRow == colourIndex) {
            promotePawn(move);
        }


        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false;

        capture(move.capture);
    }

    private void promotePawn(Move move) {
        Promotion dialog = new Promotion(this, move.piece.isRacist, tileSize);
        String choice = dialog.showDialog();
        
        Piece newPiece;
        switch (choice) {
            case "Rook":
                newPiece = new Rook(this, move.newCol, move.newRow, move.piece.isRacist);
                break;
            case "Knight":
                newPiece = new Knight(this, move.newCol, move.newRow, move.piece.isRacist);
                break;
            case "Bishop":
                newPiece = new Bishop(this, move.newCol, move.newRow, move.piece.isRacist);
                break;
            default:
                newPiece = new Queen(this, move.newCol, move.newRow, move.piece.isRacist);
                break;
        }
        pieceList.add(newPiece);
        capture(move.piece);
    }


    public void capture(Piece piece){
        pieceList.remove(piece);
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
        if(cs.isKingChecked(move)){
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

    public int getTileNum(int col, int row){
        return row * rows + col;
    }

    Piece findKing(boolean isRacist) {
        for(Piece piece : pieceList) {
            if(isRacist == piece.isRacist && piece.name.equals("crybaby")) {
                return piece;
            }
        }
        return null;
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

        for (boolean isRacist : new boolean[]{true, false}) {
            Piece king = findKing(isRacist);
            if (king != null) {
                // Create a dummy move to check the current position
                Move dummyMove = new Move(this, king, king.col, king.row);
                if (cs.isKingChecked(dummyMove)) {
                    g2d.setColor(new Color(200, 5,0,240));
                    g2d.fillRect(king.col * tileSize, king.row * tileSize, tileSize, tileSize);
                }
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

                    if(getPiece(c, r) != null && !sameTeam(getPiece(c,r), selectedPiece) && isValidMove(new Move(this, selectedPiece, c, r))){
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
