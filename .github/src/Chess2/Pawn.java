package Chess2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {


    public Pawn(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    /*
            Move function for pawn
             */

    /*
    Print legal moves for pawn
     */
    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        List<int[]> moves = new ArrayList<>();

        //region White Pawn moves
        if (this.isWhite) {
            // Forward move by 1
            if (isWithinBounds(this.file, this.row + 1) && board[file][row + 1] == null) {
                moves.add(new int[]{file, row + 1});

                // Forward move by 2 from starting row
                if (row == 2 && board[file][row + 2] == null) {
                    moves.add(new int[]{file, row + 2});
                }
            }

            // Capture moves
            if (isWithinBounds(this.file - 1, this.row + 1) && board[file - 1][row + 1] != null &&
                    !board[file - 1][row + 1].isWhite) {
                moves.add(new int[]{file - 1, row + 1});
            }
            if (isWithinBounds(this.file + 1, this.row + 1) && board[file + 1][row + 1] != null &&
                    !board[file + 1][row + 1].isWhite) {
                moves.add(new int[]{file + 1, row + 1});
            }
        }
        //endregion
        //region Black Pawn moves
        if (!this.isWhite) {
            // Forward move by 1
            if (isWithinBounds(this.file, this.row - 1) && board[file][row - 1] == null) {
                moves.add(new int[]{file, row - 1});

                // Forward move by 2 from starting row
                if (row == 7 && board[file][row - 2] == null) {
                    moves.add(new int[]{file, row - 2});
                }
            }

            // Capture moves
            if (isWithinBounds(this.file - 1, this.row - 1) && board[file - 1][row - 1] != null &&
                    board[file - 1][row - 1].isWhite) {
                moves.add(new int[]{file - 1, row - 1});
            }
            if (isWithinBounds(this.file + 1, this.row - 1) && board[file + 1][row - 1] != null &&
                    board[file + 1][row - 1].isWhite) {
                moves.add(new int[]{file + 1, row - 1});
            }
        }
        //endregion

        // for (int[] move : moves) {
        //     System.out.println(move[0] + " " + move[1]);
        // }

        return moves;
    }


}
