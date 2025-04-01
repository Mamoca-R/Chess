package Chess2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chess {
    public static void main(String[] args) throws IOException {

        // Initialize board
        Board board = new Board();

        // Set up frame
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);

        // Set up panel
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

        // Set up mouse listener
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int file = e.getX() / 64;
                int row = e.getY() / 64;
                // Check if the clicked piece is the same as the selected piece
                // If so, deselect it
                if (board.getSelectedPiece() != null && board.getSelectedPiece().getLogicalFile() == file &&
                        board.getSelectedPiece().getLogicalRow() == row) {
                    board.setSelectedPiece(null);
                    frame.repaint();
                }
                // Else, select the clicked piece
                else {
                    board.setSelectedPiece(board.getPieces()[row][file]);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int logicalFile = e.getX() / 64;
                int logicalRow = e.getY() / 64;
                board.setSelectedPiece(board.getPieces()[logicalRow][logicalFile]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int destFile = (e.getX() / 64) + 1;
                int destRow = 8 - (e.getY() / 64);
                if (board.getSelectedPiece() != null) {
                    board.movePiece(board.getSelectedPiece(), destFile, destRow);
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
        
        // Set up mouse motion listener
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
        
        // Close frame
        frame.setDefaultCloseOperation(3); // Windows close button closes game
        frame.setVisible(true);
    }
}
