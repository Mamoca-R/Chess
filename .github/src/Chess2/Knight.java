package Chess2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {


    public Knight(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    /*
    Get legal moves for knight
     */
    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        List<int[]> legalMoves = new ArrayList<>();
        
        // Define all possible directions: horizontal, vertical, and diagonal
        int[][] directions = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
        };
        
        // Check each direction
        for (int[] direction : directions) {
            int currentFile = file + direction[0];
            int currentRow = row + direction[1];
            
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
                if (targetPiece.getIsWhite() != this.isWhite) {
                    legalMoves.add(new int[]{currentFile, currentRow});
                }
            }
        }
        return legalMoves;
    }
}
