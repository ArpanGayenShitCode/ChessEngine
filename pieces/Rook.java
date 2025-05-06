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

}
