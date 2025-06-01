package main;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

import pieces.*;

public class Board extends JPanel {
    
    public int tileSize = 100;

    int cols = 8;
    int rows = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;
    private MoveHistoryPanel historyPanel;
    Input input = new Input(this);
    public CheckScanner cs = new CheckScanner(this);

    public int enPassantTile = -1;
    boolean isRacistMove = true;
    private boolean isGameOver = false;
    private boolean justReset = false;
    private String lastPromotionResult = null;

    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row)
                return piece;
        }
        return null;
    }

    public void makeMove(Move move) {
        boolean wasFirstMove = move.piece.isFirstMove;

        if (move.piece.name.equals("juicer")) {
            movePawn(move);
        } else if (move.piece.name.equals("crybaby")) {
            moveKing(move);
        }

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false;

        capture(move.capture);

        isRacistMove = !isRacistMove;
        repaint();

        if (historyPanel != null) {
            String notation = convertToAlgebraic(move, wasFirstMove);
            historyPanel.addMove(notation, move.piece.isRacist);

            historyPanel.revalidate();
            historyPanel.repaint();
        }

        justReset = false;
        updateGameState("makeMove");
    }

    private void moveKing(Move move) {
        if (Math.abs(move.piece.col - move.newCol) == 2) {
            Piece rook;
            if (move.piece.col < move.newCol) {
                rook = getPiece(7, move.piece.row);
                rook.col = 5;
            } else {
                rook = getPiece(0, move.piece.row);
                rook.col = 3;
            }
            rook.xPos = rook.col * tileSize;
        }
    }

    public void movePawn(Move move) {
        int colourIndex = move.piece.isRacist ? 1 : -1;
        if (getTileNum(move.newCol, move.newRow) == enPassantTile) {
            move.capture = getPiece(move.newCol, move.newRow + colourIndex);
        }
        if (Math.abs(move.piece.row - move.newRow) == 2) {
            enPassantTile = getTileNum(move.newCol, move.newRow + colourIndex);
        } else {
            enPassantTile = -1;
        }
        colourIndex = move.piece.isRacist ? 0 : 7;
        if (move.newRow == colourIndex) {
            repaint();
            promotePawn(move);
        }
    }

    private void promotePawn(Move move) {
        Promotion dialog = new Promotion(this, move.piece.isRacist, tileSize);
        String choice = dialog.showDialog();
        
        Piece newPiece;
        switch (choice) {
            case "Rook":
                newPiece = new Rook(this, move.newCol, move.newRow, move.piece.isRacist);
                lastPromotionResult = "R";
                break;
            case "Knight":
                newPiece = new Knight(this, move.newCol, move.newRow, move.piece.isRacist);
                lastPromotionResult = "N";
                break;
            case "Bishop":
                newPiece = new Bishop(this, move.newCol, move.newRow, move.piece.isRacist);
                lastPromotionResult = "B";
                break;
            default:
                newPiece = new Queen(this, move.newCol, move.newRow, move.piece.isRacist);
                lastPromotionResult = "Q";
                break;
        }
        pieceList.add(newPiece);
        capture(move.piece);
    }

    private String convertToAlgebraic(Move move, boolean wasFirstMove) {
        // Detect castling
        if (move.piece.name.equals("crybaby") && Math.abs(move.newCol - move.oldCol) == 2) {
            String castlingNotation = move.newCol > move.oldCol ? "O-O" : "O-O-O";
            // Detect for Castling Checks/Mate
            Piece oppKing = findKing(!move.piece.isRacist);
            if (oppKing != null) {
                boolean isCheck = CheckKing(oppKing.col, oppKing.row, oppKing.isRacist);
                if (isCheck) {
                    boolean isCheckmate = cs.isGameOver(oppKing);
                    return castlingNotation + (isCheckmate ? "#" : "+");
                }
            }
            return castlingNotation;
        }

        // Piece notation
        String pieceCode = switch (move.piece.name) {
            case "crybaby" -> "K";
            case "her" -> "Q";
            case "rookie boi" -> "R";
            case "popefrancis" -> "B";
            case "horsie" -> "N";
            case "juicer" -> "";
            default -> "";
        };

        // Disambiguation
        String disambiguation = "";
        if (!pieceCode.isEmpty() && !pieceCode.equals("K")) {
            boolean sameFile = false;
            boolean sameRank = false;

            for (Piece other : pieceList) {
                if (other == move.piece || !other.name.equals(move.piece.name) || other.isRacist != move.piece.isRacist)
                    continue;

                if (other.isValidMovement(move.newCol, move.newRow) && !other.MoveCollideswithPiece(move.newCol, move.newRow)) {
                    if (other.col == move.oldCol) sameFile = true;
                    if (other.row == move.oldRow) sameRank = true;
                }
            }

            if (sameFile) {
                disambiguation = String.valueOf(8 - move.oldRow);
            } 
            else if (sameRank) {
                disambiguation = String.valueOf((char)('a' + move.oldCol));
            }
        }

        // Capture logic
        String capture = (move.capture != null) ? "x" : "";
        String pawnPrefix = "";
        if (move.piece.name.equals("juicer") && move.capture != null) {
            pawnPrefix = (char) ('a' + move.oldCol) + "";
        }

        // Target square
        String file = String.valueOf((char) ('a' + move.newCol));
        int rank = 8 - move.newRow;

        String notation = pieceCode + disambiguation + pawnPrefix + capture + file + rank;

        //Promotion
        if(lastPromotionResult != null && pieceCode.equals("")){
            notation += "=" + lastPromotionResult;
            lastPromotionResult = null;
        }

        // Check and checkmate
        String checkSuffix = "";
        Piece oppKing = findKing(!move.piece.isRacist);
        if (oppKing != null) {
            boolean isCheck = CheckKing(oppKing.col, oppKing.row, oppKing.isRacist);
            if (isCheck) {
                boolean isCheckmate = cs.isGameOver(oppKing);
                checkSuffix = isCheckmate ? "#" : "+";
            }
        }
        
        notation += checkSuffix;
        return notation;
    }


    private boolean CheckKing(int kingCol, int kingRow, boolean kingIsRacist) {
        for (Piece piece : pieceList) {
            if (piece != null && piece.isRacist != kingIsRacist) {
                if (piece.isValidMovement(kingCol, kingRow) && !piece.MoveCollideswithPiece(kingCol, kingRow)) {
                    if (piece.name.equals("juicer")) {
                        int colourVal = !piece.isRacist ? 1 : -1;
                        if (Math.abs(piece.col - kingCol) == 1 && kingRow == piece.row + colourVal) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void capture(Piece piece) {
        pieceList.remove(piece);
    }

    public boolean isValidMove(Move move) {
        if (isGameOver) {
            return false;
        }
        if (move.piece.isRacist != isRacistMove) {
            return false;
        }
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false;
        }
        if (move.piece.MoveCollideswithPiece(move.newCol, move.newRow)) {
            return false;
        }
        if (cs.isKingChecked(move)) {
            return false;
        }
        return true;
    }

    public boolean sameTeam(Piece x, Piece y) {
        if (x == null || y == null) {
            return false;
        }
        return x.isRacist == y.isRacist;
    }

    public int getTileNum(int col, int row) {
        return row * rows + col;
    }

    Piece findKing(boolean isRacist) {
        for (Piece piece : pieceList) {
            if (isRacist == piece.isRacist && piece.name.equals("crybaby")) {
                return piece;
            }
        }
        return null;
    }

    public void resetBoard() {
        pieceList.clear();
        addPieces();
        isRacistMove = true;
        isGameOver = false;
        enPassantTile = -1;
        selectedPiece = null;
        justReset = true;
        this.removeMouseListener(input);
        this.removeMouseMotionListener(input);
        input = new Input(this);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        if (historyPanel != null) 
            historyPanel.reset();
    }

    public void addPieces() {
        // Negro Pieces
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        // Pawns
        for(int col = 0; col < cols; col++)
            pieceList.add(new Pawn(this, col, 1, false));

        // Racist Pieces
        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));
        // Pawns
        for(int col = 0; col < cols; col++)
            pieceList.add(new Pawn(this, col, 6, true));
        for (Piece piece : pieceList) {
            piece.isFirstMove = true;
        }
    }

    private void updateGameState(String context) {
        if (justReset || pieceList.isEmpty() || pieceList.size() < 2) {
            
            return;
        }
        Piece king = findKing(isRacistMove);
        if (king != null && cs.isGameOver(king)) {
            boolean isCheckmate = cs.isKingChecked(new Move(this, king, king.col, king.row));
            isGameOver = true;
            if (historyPanel != null) {
                historyPanel.revalidate();
                historyPanel.repaint();
            }
            GameOverDialog dialog = new GameOverDialog(this, isCheckmate, isRacistMove);
            dialog.showDialog();
        }
    }

    public void setMoveHistoryPanel(MoveHistoryPanel panel) {
        this.historyPanel = panel;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // paint board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(145, 170, 192) : new Color(67, 90, 106));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }

        // paint king in check highlight
        for (boolean isRacist : new boolean[]{true, false}) {
            Piece king = findKing(isRacist);
            if (king != null) {
                Move dummy = new Move(this, king, king.col, king.row);
                if (cs.isKingChecked(dummy)) {
                    g2d.setColor(new Color(200, 5, 0, 240));
                    g2d.fillRect(king.col * tileSize, king.row * tileSize, tileSize, tileSize);
                }
            }
        }

        // paint legal move highlights
        if (selectedPiece != null) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(68, 180, 5, 190)); // rgb(68, 180, 5, 190)
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                    //paint capturable piece
                    if (getPiece(c, r) != null && !sameTeam(getPiece(c, r), selectedPiece) && isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(138, 43, 226, 180)); // rgb(138, 43, 226, 180)
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }

        // paint pieces
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }
    }
}