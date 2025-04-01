package Chess2;

public class Move {
    private Piece selectedPiece;
    private Square startSquare;
    private Square targetSquare;

    // Constructor
    public Move(Piece selectedPiece, Square startSquare, Square targetSquare) {
        this.selectedPiece = selectedPiece;
        this.startSquare = startSquare;
        this.targetSquare = targetSquare;
    }

    // Getters
    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }
    public Square getStartSquare() {
        return this.startSquare;
    }
    public Square getTargetSquare() {
        return this.targetSquare;
    }

    // Setters
    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }
    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
    }
    public void setTargetSquare(Square targetSquare) {
        this.targetSquare = targetSquare;
    }

    public String toString() {
        //return selectedPiece.getName() + targetSquare.getName();
        return null;
    }


}
