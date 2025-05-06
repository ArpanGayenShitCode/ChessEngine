package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Bishop extends Piece{

    public Bishop(Board board, int col, int row, boolean isRacist) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isRacist = isRacist;
        this.name = "popefrancis";

        this.sprite = sheet.getSubimage(2 * sheetScale, isRacist? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row){
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

        public boolean MoveCollideswithPiece(int col, int row){

        //up left
        if(this.col > col && this.row > row)
            for(int i = 1; i < Math.abs(this.col - col); i++)
                if(board.getPiece(this.col - i, this.row - i) != null)
                    return true;
        //up right
        if(this.col < col && this.row > row)
            for(int i = 1; i < Math.abs(this.col - col); i++)
                if(board.getPiece(this.col + i, this.row - i) != null)
                    return true;
        //down left
        if(this.col > col && this.row < row)
            for(int i = 1; i < Math.abs(this.col - col); i++)
                if(board.getPiece(this.col - i, this.row + i) != null)
                    return true;
        //down right
        if(this.col < col && this.row < row)
            for(int i = 1; i < Math.abs(this.col - col); i++)
                if(board.getPiece(this.col + i, this.row + i) != null)
                    return true;

        return false;
    }

}
