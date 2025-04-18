package Chess2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {


    public Queen(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        List<int[]> legalMoves = new ArrayList<>();
        
        // Define all possible directions: horizontal, vertical, and diagonal
        int[][] directions = {
            {-1, 0},  // left
            {1, 0},   // right
            {0, -1},  // up
            {0, 1},   // down
            {-1, -1}, // up-left
            {1, -1},  // up-right
            {-1, 1},  // down-left
            {1, 1}    // down-right
        };
        
        // Check each direction
        for (int[] direction : directions) {
            int currentFile = file;
            int currentRow = row;
            
            // Keep moving in the current direction until we hit a boundary or piece
            while (true) {
                currentFile += direction[0];
                currentRow += direction[1];
                
                // Check if we're still within the board bounds
                if (!isWithinBounds(currentFile, currentRow)) {
                    break;
                }
                
                // Get the piece at the current position
                Piece targetPiece = board[8 -currentRow][currentFile - 1];
                
                // If the square is empty, it's a legal move
                if (targetPiece == null) {
                    legalMoves.add(new int[]{currentFile, currentRow});
                } else {
                    // If there's a piece, we can only capture if it's the opposite color
                    if (targetPiece.getIsWhite() != isWhite) {
                        legalMoves.add(new int[]{currentFile, currentRow});
                    }
                    break; // Stop moving in this direction after encountering any piece
                }
            }
        }
        
        return legalMoves;
    }

    /*
            Move function for queen
             */
    public static void move() {}

    /*
    Print legal moves for queen
     */
    public static int[][] legalMoves() {
        return null;
    }
}
