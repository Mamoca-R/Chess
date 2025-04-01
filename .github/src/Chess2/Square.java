package Chess2;

public class Square {
    private String name; // e.g. "a1"
    private int file; // 1-8
    private int row; // 1-8
    private Piece piece; // piece on square
    private boolean isLightSquare; // true if light square

    // Constructor
    public Square(String name, int file, int row, Piece piece, boolean isLightSquare) {
        this.name = name;
        this.file = file;
        this.row = row;
        this.piece = piece;
        this.isLightSquare = isLightSquare;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getFile() {
        return file;
    }
    public int getRow() {
        return row;
    }
    public Piece getPiece() {
        return piece;
    }
    public boolean isLightSquare() {
        return isLightSquare;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setFile(int file) {
        this.file = file;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setLightSquare(boolean isLightSquare) {
        this.isLightSquare = isLightSquare;
    }
}
