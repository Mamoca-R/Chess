package Chess2;

import java.awt.Image;
import java.util.List;

public abstract class Piece {
    protected boolean isWhite;
    protected int row;
    protected int file;
    protected int x;
    protected int y;
    protected Image img;

    /*
    Piece constructor
     */
    public Piece(boolean isWhite, int file, int row, Image img) {
        this.isWhite = isWhite;
        this.row = row;
        this.file = file;
        this.x = (file-1)*64;
        this.y = (8-row)*64;
        this.img = img;
    }

    public boolean getIsWhite() {
        return isWhite;
    }
    public int getRow() {
        return row;
    }
    public int getFile() {
        return file;
    }
    public Image getImage() {
        return img;
    }
    public int getLogicalRow() {
        return 8 - row;
    }
    public int getLogicalFile() {
        return file - 1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setRow(int row) {
        this.row = row;
        this.y = (8-row)*64;
    }
    public void setFile(int file) {
        this.file = file;
        this.x = (file-1)*64;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }




    /*
    Move function
     */



    /*
        Print legal moves for pawn
         */
    public abstract List<int[]> getLegalMoves(Piece[][] board);
}
