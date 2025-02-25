package Chess2;

import java.awt.*;
import java.util.List;

public class Knight extends Piece {


    public Knight(boolean isWhite, int file, int row, Image img) {
        super(isWhite, file, row, img);
    }

    @Override
    public List<int[]> getLegalMoves(Piece[][] board) {
        return null;
    }

    /*
            Move function for knight
             */
    public static void move() {}

    /*
    Print legal moves for knight
     */
    public static int[][] legalMoves() {
        return null;
    }
}
