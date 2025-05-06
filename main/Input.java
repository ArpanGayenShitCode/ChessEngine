package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pieces.Piece;

public class Input implements MouseListener, MouseMotionListener{

    Board board;

    public Input(Board board){
        this.board = board;
    }

    public void mousePressed(MouseEvent e) {
        
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null){
            board.selectedPiece = pieceXY;
        }


    }

    public void mouseDragged(MouseEvent e) {
        
        if(board.selectedPiece != null){
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            board.repaint();
        }


    }

    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if(board.selectedPiece != null){
            Move move = new Move(board, board.selectedPiece, col, row);

            if(board.isValidMove(move)){
                board.makeMove(move);
            }
            else{
                board.selectedPiece.xPos  = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos  = board.selectedPiece.row * board.tileSize;
            }

        }

        board.selectedPiece = null;
        board.repaint();


    }


    @Override
    public void mouseMoved(MouseEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}


    @Override
    public void mouseExited(MouseEvent e) {}
}
