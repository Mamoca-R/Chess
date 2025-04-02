package Chess2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class Board {
    private Piece[][] pieces; // 2D array of pieces
    private boolean turn; // true if white's turn, false if black's turn
    private Piece selectedPiece; // currently selected piece

    /*
    Board constructor
     */

    public Board() throws IOException {
        initializeBoard();
        this.turn = true;
    }

    /*
    Turn getter
     */
    public boolean getTurn() {
        return this.turn;
    }
    public Piece[][] getPieces() {
        return this.pieces;
    }
    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
    }

    public void initializeBoard() throws IOException {
        Piece[][] pieces = new Piece[8][8];
        Image[] images = extractImages();

        // Create black pieces
        Piece blackRook1 = new Rook(false, 1, 8, images[10]);
        Piece blackKnight1 = new Knight(false, 2, 8, images[9]);
        Piece blackBishop1 = new Bishop(false, 3, 8, images[8]);
        Piece blackQueen = new Queen(false, 4, 8, images[7]);
        Piece blackKing = new King(false, 5, 8, images[6]);
        Piece blackBishop2 = new Bishop(false, 6, 8, images[8]);
        Piece blackKnight2 = new Knight(false, 7, 8, images[9]);
        Piece blackRook2 = new Rook(false, 8, 8, images[10]);
        Piece blackPawn1 = new Pawn(false, 1, 7, images[11]);
        Piece blackPawn2 = new Pawn(false, 2, 7, images[11]);
        Piece blackPawn3 = new Pawn(false, 3, 7, images[11]);
        Piece blackPawn4 = new Pawn(false, 4, 7, images[11]);
        Piece blackPawn5 = new Pawn(false, 5, 7, images[11]);
        Piece blackPawn6 = new Pawn(false, 6, 7, images[11]);
        Piece blackPawn7 = new Pawn(false, 7, 7, images[11]);
        Piece blackPawn8 = new Pawn(false, 8, 7, images[11]);

        // Create white pieces
        Piece whiteRook1 = new Rook(true, 1, 1, images[4]);
        Piece whiteKnight1 = new Knight(true, 2, 1, images[3]);
        Piece whiteBishop1 = new Bishop(true, 3, 1, images[2]);
        Piece whiteQueen = new Queen(true, 4, 1, images[1]);
        Piece whiteKing = new King(true, 5, 1, images[0]);
        Piece whiteBishop2 = new Bishop(true, 6, 1, images[2]);
        Piece whiteKnight2 = new Knight(true, 7, 1, images[3]);
        Piece whiteRook2 = new Rook(true, 8, 1, images[4]);
        Piece whitePawn1 = new Pawn(true, 1, 2, images[5]);
        Piece whitePawn2 = new Pawn(true, 2, 2, images[5]);
        Piece whitePawn3 = new Pawn(true, 3, 2, images[5]);
        Piece whitePawn4 = new Pawn(true, 4, 2, images[5]);
        Piece whitePawn5 = new Pawn(true, 5, 2, images[5]);
        Piece whitePawn6 = new Pawn(true, 6, 2, images[5]);
        Piece whitePawn7 = new Pawn(true, 7, 2, images[5]);
        Piece whitePawn8 = new Pawn(true, 8, 2, images[5]);

        pieces[0][0] = blackRook1;
        pieces[0][1] = blackKnight1;
        pieces[0][2] = blackBishop1;
        pieces[0][3] = blackQueen;
        pieces[0][4] = blackKing;
        pieces[0][5] = blackBishop2;
        pieces[0][6] = blackKnight2;
        pieces[0][7] = blackRook2;
        pieces[1][0] = blackPawn1;
        pieces[1][1] = blackPawn2;
        pieces[1][2] = blackPawn3;
        pieces[1][3] = blackPawn4;
        pieces[1][4] = blackPawn5;
        pieces[1][5] = blackPawn6;
        pieces[1][6] = blackPawn7;
        pieces[1][7] = blackPawn8;

        pieces[7][0] = whiteRook1;
        pieces[7][1] = whiteKnight1;
        pieces[7][2] = whiteBishop1;
        pieces[7][3] = whiteQueen;
        pieces[7][4] = whiteKing;
        pieces[7][5] = whiteBishop2;
        pieces[7][6] = whiteKnight2;
        pieces[7][7] = whiteRook2;
        pieces[6][0] = whitePawn1;
        pieces[6][1] = whitePawn2;
        pieces[6][2] = whitePawn3;
        pieces[6][3] = whitePawn4;
        pieces[6][4] = whitePawn5;
        pieces[6][5] = whitePawn6;
        pieces[6][6] = whitePawn7;
        pieces[6][7] = whitePawn8;

        this.pieces = pieces;
    }

    public void resetBoard() {
        this.pieces = new Piece[8][8];
    }

    /*
    Move piece from original position to new position
     */
    public void movePiece(Piece selectedPiece, int destFile, int destRow) {
        // Store original position in case we need to revert
        int originalFile = selectedPiece.getLogicalFile();
        int originalRow = selectedPiece.getLogicalRow();
        System.out.println("Moving to " + destFile + " " + destRow);
        System.out.print("Available moves: ");
        for (int[] move : selectedPiece.getLegalMoves(this.pieces)) {
            System.out.print(move[0] + " " + move[1] + ", ");
        }
        
        // Check if the move is valid
        if (selectedPiece.getLegalMoves(this.pieces).contains(new int[]{destFile, destRow})) {
            // Move is valid, perform the move
            selectedPiece.setRow(destRow);
            selectedPiece.setFile(destFile);
            selectedPiece.setX((destFile-1)*64);
            selectedPiece.setY((8-destRow)*64);
            // Convert to logical coordinates for array indexing
            this.pieces[8-destRow][destFile-1] = selectedPiece;
            this.pieces[originalRow][originalFile] = null;
        }
        else {
            System.out.println("Invalid move");
        }
        // If invalid, do nothing
    }

    public void killPiece(int file, int row) {
        this.pieces[row][file] = null;
    }

    /*
    Extract images from chess.png
     */
    public static Image[] extractImages() throws IOException {
        BufferedImage all = ImageIO.read(new File("C:\\Users\\joshu\\IdeaProjects\\Chess\\.github\\src\\Chess2\\GUI\\assets\\chess.png"));
        Image[] images = new Image[12];
        int index = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                images[index] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                index++;
            }
        }
        return images;
    }
}
