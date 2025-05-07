package main;

import pieces.Piece;

public class CheckScanner {

    Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {

        Piece king = board.findKing(move.piece.isRacist);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        if(board.selectedPiece != null && board.selectedPiece.name.equals("crybaby")) {
            kingCol = move.newCol;
            kingRow = move.newRow;
        }

        return hitbyRook  (move.newCol, move.newRow, king, kingCol, kingRow, 0, 1) || //up
               hitbyRook  (move.newCol, move.newRow, king, kingCol, kingRow, 1, 0)  || //right
               hitbyRook  (move.newCol, move.newRow, king, kingCol, kingRow, 0, -1) || //down
               hitbyRook  (move.newCol, move.newRow, king, kingCol, kingRow, -1, 0) || //left

               hitbyBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, -1) || //up left
               hitbyBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, -1) || //up right
               hitbyBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, 1) || //down right
               hitbyBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, 1) || //down left

               hitbyKinght(move.newCol, move.newRow, king, kingCol, kingRow)||
               hitbyPawn(move.newCol, move.newRow, king, kingCol, kingRow) ||
               hitbyKing(king, kingCol, kingRow);
    }

    private boolean hitbyRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for(int i = 1; i < 8; i++) {
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            }

            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece) {
                if(!board.sameTeam(piece, king) && (piece.name.equals("rookie boi") || piece.name.equals("her"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitbyBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for(int i = 1; i < 8; i++) {
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row) {
                break;
            }

            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
            if(piece != null && piece != board.selectedPiece) {
                if(!board.sameTeam(piece, king) && (piece.name.equals("popefrancis") || piece.name.equals("her"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitbyKinght(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkKight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
               checkKight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
               checkKight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
               checkKight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
               checkKight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
               checkKight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
               checkKight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
               checkKight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }

    private boolean checkKight(Piece p, Piece king, int col, int row){
        return p != null && !board.sameTeam(p, king) && p.name.equals("horsie") && !(p.col == col && p.row == row);
    }

    private boolean hitbyKing(Piece king, int kingCol, int kingRow){
        return checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) ||
               checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
               checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
               checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
               checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
               checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) ||
               checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
               checkKing(board.getPiece(kingCol, kingRow + 1), king);
    }

    private boolean checkKing(Piece p, Piece king) {
        return p != null && !board.sameTeam(p, king) && p.name.equals("crybaby");
    }

    private boolean hitbyPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colourVal = king.isRacist ? -1 : 1;
        return checkPawn(board.getPiece(kingCol + 1, kingRow + colourVal), king, col, row) ||
               checkPawn(board.getPiece(kingCol - 1, kingRow + colourVal), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece king, int col, int row){
        return p != null && !board.sameTeam(p, king) && p.name.equals("juicer") && !(p.col == col && p.row == row);
    }

    public boolean isGameOver(Piece king) {
        for(Piece piece: board.pieceList){
            if(board.sameTeam(piece, king)) {
                board.selectedPiece = piece == king ? king : null;
                for( int row = 0; row < board.rows; row++) {
                    for( int col = 0; col < board.rows; col++) {
                        Move move = new Move(board, piece, col, row);
                        if(board.isValidMove(move)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}