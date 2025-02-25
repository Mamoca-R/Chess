package Chess2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.LinkedList;

public class Chess {
    public static void main(String[] args) throws IOException {

        Piece[][] pieces = new Piece[8][8];
        Image[] images = extractImages();

        // Create black pieces
        Piece blackRook1 = new Rook(false, 1, 8, images[10]);
        Piece blackKnight1 = new Knight(false, 2, 8, images[9]);
        Piece blackBishop1 = new Bishop(false, 3, 8, images[8]);
        Piece blackQueen = new Rook(false, 4, 8, images[7]);
        Piece blackKing = new King(false, 5, 8, images[6]);
        Piece blackBishop2 = new Knight(false, 6, 8, images[8]);
        Piece blackKnight2 = new Bishop(false, 7, 8, images[9]);
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
        Piece whiteQueen = new Rook(true, 4, 1, images[1]);
        Piece whiteKing = new King(true, 5, 1, images[0]);
        Piece whiteBishop2 = new Knight(true, 6, 1, images[2]);
        Piece whiteKnight2 = new Bishop(true, 7, 1, images[3]);
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

        Board board = new Board(pieces, "white");

        // Start drawing board
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (white) g.setColor(Color.white);
                        else g.setColor(Color.gray);
                        g.fillRect(i * 64, j * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Piece[] row : board.getPieces()) {
                    for (Piece p : row) {
                        if (p != null) {
                            g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                        }
                    }
                }
                if (board.getSelectedPiece() != null) {
                    for (int[] legalMove : board.getSelectedPiece().getLegalMoves(board.getPieces())) {
                        g.setColor(Color.GREEN);
                        g.fillOval((legalMove[0]-1) * 64 + 24, (8-legalMove[1]) * 64 + 24, 16, 16);
                    }
                }
            }
        };
        frame.add(pn);
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int file = e.getX() / 64;
                int row = e.getY() / 64;
                board.setSelectedPiece(board.getPieces()[row][file]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int file = (e.getX() / 64) + 1;
                int row = 8 - (e.getY() / 64);
                if (board.getSelectedPiece() != null) {
                    board.movePiece(board.getSelectedPiece(), file, row);
                    board.setSelectedPiece(null);
                }
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (board.getSelectedPiece() != null) {
                    board.getSelectedPiece().setX(e.getX() - 32);
                    board.getSelectedPiece().setY(e.getY() - 32);
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.setDefaultCloseOperation(3); // Windows close button closes game
        frame.setVisible(true);
    }

    /*
    Extract images from chess.png
     */
    public static Image[] extractImages() throws IOException {
        BufferedImage all = ImageIO.read(new File("C:\\Users\\joshu\\IdeaProjects\\Chess\\.github\\src\\Chess2\\chess.png"));
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
