package Chess2;

import java.awt.*;
import java.util.List;

public class King extends Piece {


    public King(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        return null;
    }

    /*
            Move function for king
             */
    public static void move() {}

    /*
    Print legal moves for king
     */
    public static int[][] legalMoves() {
        return null;
    }
}
