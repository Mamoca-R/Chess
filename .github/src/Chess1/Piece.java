package Chess1;

import java.util.*;
public class Piece implements Pieces {
    int x_coord;
    int y_coord;
    int x;
    int y;
    boolean isWhite;
    boolean hasMoved = false;
    LinkedList<Piece> pieces;
    String name;
    public Piece(int x_coord, int y_coord, boolean isWhite, String name, LinkedList<Piece> pieces) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.x = x_coord * 64;
        this.y = y_coord * 64;
        this.isWhite = isWhite;
        this.pieces = pieces;
        this.name = name;
        pieces.add(this);
    }

    /*
    This piece moves from its current position to the place position
     */
    public void move(int x_coord, int y_coord) {
        // Get piece, if any, on the destination square
        Piece destinationPiece = Chess.getPiece(x_coord*64, y_coord*64);

        // If there is a non-self piece, check that it is not the same color
        if (destinationPiece != null && this != destinationPiece) {
            // If selectedPiece and destinationPiece are the same color, put selectedPiece back to its initial square
            if (destinationPiece.isWhite == this.isWhite) {
                this.x = this.x_coord * 64;
                this.y = this.y_coord * 64;
                return;
            }
            // Else, check if legal move
            Chess.getPiece(x_coord*64, y_coord*64).kill();
        }
        // Space is open, okay to move
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.x = x_coord * 64;
        this.y = y_coord * 64;
        this.hasMoved = true;
    }

    /*
    Checks if (x,y) is a legal move for selected piece
     */
    public static boolean isLegalMove(Piece piece, int x_coord, int y_coord) {
        Piece destinationPiece = Chess.getPiece(x_coord * 64, y_coord * 64);
        if (piece.name.equalsIgnoreCase("pawn")) {
            // Case 1: Pawn has not moved --> can move either 1 space or 2 spaces, or take diagonally one space
            // White pawns
            if (piece.isWhite && !piece.hasMoved) {
                if (x_coord == piece.x_coord && y_coord == piece.y_coord-1) { // Tries to move 1 space
                    if (destinationPiece != null) return false; // There is a piece in the way
                    else return true;
                }
                if (x_coord == piece.x_coord && y_coord == piece.y_coord-2) { // Tries to move 2 spaces
                    if (Chess.getPiece(x_coord*64, (y_coord-1)*64) != null) return false; // There is a piece in the way on the first square
                    if (destinationPiece != null) return false; // There is a piece in the way on the second square
                    else return true;
                }
                if (x_coord == piece.x_coord-1 && y_coord == piece.y_coord-1) { // Tries to take left

                }
                else return false;
            }
            if (piece.isWhite) {
                if (x_coord == piece.x_coord && y_coord == piece.y_coord-1) { // Tries to move 1 space
                    if (destinationPiece != null) return false; // There is a piece in the way
                    else return true;
                }
            }

            // Black pawns
            if (!piece.isWhite && !piece.hasMoved) {
                if (x_coord == piece.x_coord && y_coord == piece.y_coord-1) { // Tries to move 1 space
                    if (destinationPiece != null) return false; // There is a piece in the way
                    else return true;
                }
                if (x_coord == piece.x_coord && y_coord == piece.y_coord-2) { // Tries to move 2 spaces
                    if (Chess.getPiece(x_coord*64, (y_coord-1)*64) != null) return false; // There is a piece in the way on the first square
                    if (destinationPiece != null) return false; // There is a piece in the way on the second square
                    else return true;
                }
            }

            // Case 2: Pawn has moved --> can only move 1 space or take diagonally one space
            // Case 3: En passant --> can take diagonally if pawn to left or right has just moved two spaces
        }
        if (piece.name.equalsIgnoreCase("bishop")) {

        }
        if (piece.name.equalsIgnoreCase("knight")) {

        }
        if (piece.name.equalsIgnoreCase("rook")) {

        }
        if (piece.name.equalsIgnoreCase("queen")) {

        }
        if (piece.name.equalsIgnoreCase("king")) {

        }
        return false;
    }

    /*
    This piece has been captured
     */
    public void kill() {
        pieces.remove(this);
    }
}
