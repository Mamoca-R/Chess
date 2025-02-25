package Chess2;

import java.util.Objects;

public class Board {
    private Piece[][] pieces;
    private boolean turn;
    private Piece selectedPiece;

    /*
    Board constructor
     */
    public Board(Piece[][] pieces, String turn) {
        this.pieces = pieces;
        this.turn = Objects.equals(turn, "white");
    }

    /*
    Turn getter
     */
    public boolean getTurn() {
        return this.turn;
    }
    public Piece[][] getPieces() {
        return this.pieces;
    }
    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
    }

    public void resetBoard() {
        this.pieces = new Piece[8][8];
    }

    public void movePiece(Piece piece, int file, int row) {
        if (piece.getLegalMoves(this.pieces).contains(new int[]{file, row})) {
            this.pieces[piece.getLogicalRow()][piece.getLogicalFile()] = null;
            piece.setRow(row);
            piece.setFile(file);
            this.pieces[piece.getLogicalRow()][piece.getLogicalFile()] = piece;
        }
        else {
            System.out.println("Invalid move");
        }
    }

    public void killPiece(int file, int row) {
        this.pieces[row][file] = null;
    }
}
