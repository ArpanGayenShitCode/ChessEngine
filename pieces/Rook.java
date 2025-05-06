package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Rook extends Piece{

    public Rook(Board board, int col, int row, boolean isRacist) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isRacist = isRacist;
        this.name = "rookie boi";

        this.sprite = sheet.getSubimage(4 * sheetScale, isRacist? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row){
        return this.col == col || this.row == row;
    }

    public boolean MoveCollideswithPiece(int col, int row){

        //left
        if(this.col > col){
            for(int c = this.col - 1; c > col; c--)
                if(board.getPiece(c, this.row) != null)
                    return true;
        }
        //right
        if(this.col < col){
            for(int c = this.col + 1; c < col; c++)
                if(board.getPiece(c, this.row) != null)
                    return true;
        }
        //up
        if(this.row > row){
            for(int r = this.row - 1; r > row; r--)
                if(board.getPiece(this.col, r) != null)
                    return true;
        }
        //down
        if(this.row < row){
            for(int r = this.row + 1; r < row; r++)
                if(board.getPiece(this.col, r) != null)
                    return true;
        }

        return false;
    }
}
