package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Promotion {
    private final JDialog dialog;
    private String selectedPiece = "Queen"; // Default to Queen

    public Promotion(Component parent, boolean isRacist, int tileSize) {

        // Create dialog
        dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Pawn Promotion", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(new Color(23, 21, 19));

        // Load piece sprites
        BufferedImage sheet;
        int sheetScale;
        Image queenSprite, rookSprite, knightSprite, bishopSprite;
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("res\\pieces.png"));
            sheetScale = sheet.getWidth() / 6;
            int yOffset = isRacist ? 0 : sheetScale;
            queenSprite = sheet.getSubimage(1 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            rookSprite = sheet.getSubimage(4 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            knightSprite = sheet.getSubimage(3 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            bishopSprite = sheet.getSubimage(2 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            // Use default Queen if sprite loading fails
            return;
        }

        // Create panel
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
        panel.setBackground(new Color(23, 21, 19));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        JButton queenButton = new JButton(new ImageIcon(queenSprite));
        queenButton.setBackground(new Color(23, 21, 19));
        queenButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        queenButton.addActionListener(e -> {
            selectedPiece = "Queen";
            dialog.dispose();
        });
        panel.add(queenButton);

        JButton rookButton = new JButton(new ImageIcon(rookSprite));
        rookButton.setBackground(new Color(23, 21, 19));
        rookButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rookButton.addActionListener(e -> {
            selectedPiece = "Rook";
            dialog.dispose();
        });
        panel.add(rookButton);

        JButton knightButton = new JButton(new ImageIcon(knightSprite));
        knightButton.setBackground(new Color(23, 21, 19));
        knightButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        knightButton.addActionListener(e -> {
            selectedPiece = "Knight";
            dialog.dispose();
        });
        panel.add(knightButton);

        JButton bishopButton = new JButton(new ImageIcon(bishopSprite));
        bishopButton.setBackground(new Color(23, 21, 19));
        bishopButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        bishopButton.addActionListener(e -> {
            selectedPiece = "Bishop";
            dialog.dispose();
        });
        panel.add(bishopButton);

        // Set up dialog
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        try {
            ImageIcon icon = new ImageIcon("res\\icno.png");
            dialog.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showDialog() {
        dialog.setVisible(true);
        return selectedPiece;
    }
}