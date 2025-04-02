package Chess2.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Panel extends JPanel {
    private static final int TILE_SIZE = 70;
    private static final int BOARD_SIZE = 8;
    private Map<String, Image> pieceImages;
    private Timer whiteTimer, blackTimer;
    private int whiteTime = 300, blackTime = 300; // 5 minutes each (300 seconds)
    private boolean whiteTurn = true;
    private JButton whiteClock, blackClock;

    public Panel() {
        setPreferredSize(new Dimension(1280, 720));
        setupTimers();
        loadPieceImages();
        setupClockButtons();
    }

    // Load piece images from files
    private void loadPieceImages() {
        pieceImages = new HashMap<>();
        String[] pieces = {"pawn", "rook", "knight", "bishop", "queen", "king"};
        String[] colors = {"white", "black"};

        for (String color : colors) {
            for (String piece : pieces) {
                String filename = ".github\\src\\Chess2\\GUI\\assets\\" + color + "_" + piece + ".png";
                try {
                    BufferedImage image = ImageIO.read(new File(filename));
                    pieceImages.put(piece + "_" + color, image);
                } catch (IOException | NullPointerException e) {
                    System.err.println("Failed to load image: " + filename);
                }
            }
        }
    }

    private void setupTimers() {
        whiteTimer = new Timer(1000, e -> updateTimer(true));
        blackTimer = new Timer(1000, e -> updateTimer(false));
    }

    private void setupClockButtons() {
        whiteClock = new JButton(formatTime(whiteTime));
        blackClock = new JButton(formatTime(blackTime));

        whiteClock.setFont(new Font("Garamond", Font.BOLD, 24));
        whiteClock.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        blackClock.setFont(new Font("Garamond", Font.BOLD, 24));
        blackClock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        whiteClock.setBackground(Color.WHITE);
        whiteClock.setForeground(Color.BLACK);
        blackClock.setBackground(Color.BLACK);
        blackClock.setForeground(Color.WHITE);

        whiteClock.addActionListener(e -> switchTimer(true));
        blackClock.addActionListener(e -> switchTimer(false));

        setLayout(null);
        add(whiteClock);
        add(blackClock);
    }

    private void switchTimer(boolean isWhite) {
        if (isWhite) {
            blackTimer.stop();
            if (!whiteTimer.isRunning()) whiteTimer.start();
        } else {
            whiteTimer.stop();
            if (!blackTimer.isRunning()) blackTimer.start();
        }
    }

    private void updateTimer(boolean isWhite) {
        if (isWhite) {
            whiteTime--;
            whiteClock.setText(formatTime(whiteTime));
        } else {
            blackTime--;
            blackClock.setText(formatTime(blackTime));
        }
        repaint();
    }

    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawBoard(g);
        drawPieces(g);
        positionClocks();
    }

    private void drawBoard(Graphics g) {
        int boardSize = Math.min(getWidth(), getHeight()) * 3 / 4;
        int tileSize = boardSize / BOARD_SIZE;
        int xOffset = (getWidth() - boardSize) / 2;
        int yOffset = (getHeight() - boardSize) / 2;

        // Draw the chessboard
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.GRAY.darker());
                }
                g.fillRect(xOffset + col * tileSize, yOffset + row * tileSize, tileSize, tileSize);
            }
        }
    }

    private void drawPieces(Graphics g) {
        int boardSize = Math.min(getWidth(), getHeight()) * 3 / 4;
        int tileSize = boardSize / BOARD_SIZE;
        int xOffset = (getWidth() - boardSize) / 2;
        int yOffset = (getHeight() - boardSize) / 2;

        String[][] initialBoard = {
                {"rook_black", "knight_black", "bishop_black", "queen_black", "king_black", "bishop_black", "knight_black", "rook_black"},
                {"pawn_black", "pawn_black", "pawn_black", "pawn_black", "pawn_black", "pawn_black", "pawn_black", "pawn_black"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"pawn_white", "pawn_white", "pawn_white", "pawn_white", "pawn_white", "pawn_white", "pawn_white", "pawn_white"},
                {"rook_white", "knight_white", "bishop_white", "queen_white", "king_white", "bishop_white", "knight_white", "rook_white"}
        };

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                String piece = initialBoard[row][col];
                if (piece != null) {
                    g.drawImage(pieceImages.get(piece), xOffset + col * tileSize, yOffset + row * tileSize, tileSize, tileSize, this);
                }
            }
        }
    }

    private void positionClocks() {
        int boardSize = Math.min(getWidth(), getHeight()) * 3 / 4;
        int tileSize = boardSize / BOARD_SIZE;
        int xOffset = (getWidth() - boardSize) / 2;
        int yOffset = (getHeight() - boardSize) / 2;
        /*
         * Position the clocks relative to the board
         * Adjust the positions based on the board size and tile size
         */
        int buttonWidth = 150, buttonHeight = 50;
        blackClock.setBounds(xOffset + boardSize - buttonWidth - 10, yOffset - buttonHeight - 20, buttonWidth, buttonHeight); // Right of the board
        whiteClock.setBounds(xOffset + boardSize - buttonWidth - 10, yOffset + boardSize + 20, buttonWidth, buttonHeight); // Below the board
    }
}
