package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Pawn extends Piece{

    public Pawn(Board board, int col, int row, boolean isRacist) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isRacist = isRacist;
        this.name = "juicer";

        this.sprite = sheet.getSubimage(5 * sheetScale, isRacist? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row) {
        
        int colourIndex = isRacist? 1 : -1;

        //push 1
        if(this.col == col && row == this.row - colourIndex && board.getPiece(col, row) == null)
            return true;

        //push 2
        if(isFirstMove && this.col == col && row == this.row - colourIndex * 2 && board.getPiece(col, row) == null && board.getPiece(col, row + colourIndex) == null)
            return true;

        //capture left
        if(col == this.col - 1 && row == this.row - colourIndex && board.getPiece(col, row) != null)
            return true;

        //capture right
        if(col == this.col + 1 && row == this.row - colourIndex && board.getPiece(col, row) != null)
            return true; 
            
        //en passant left
        if(board.getTileNum(col, row) == board.enPassantTile && col == this.col - 1 && row == this.row - colourIndex && board.getPiece(col , row + colourIndex) != null)
            return true;

        //en passant right
        if(board.getTileNum(col, row) == board.enPassantTile && col == this.col + 1 && row == this.row - colourIndex && board.getPiece(col , row + colourIndex) != null)
            return true;

        return false;
    }

}
