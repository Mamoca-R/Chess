package Chess2;

import java.awt.*;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        return null;
    }

    /*
            Move function for bishop
             */
    public static void move() {}

    /*
    Print legal moves for bishop
     */
    public static int[][] legalMoves() {
        return null;
    }
}
