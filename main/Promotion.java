package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Promotion {
    private final JDialog dialog;
    private String selectedPiece = "Queen";

    public Promotion(Component parent, boolean isRacist, int tileSize) {


        // Create dialog
        dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Time To Decide", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(new Color(23, 21, 19));

        // Load piece sprites
        BufferedImage sheet;
        int sheetScale;
        Image queenSprite, rookSprite, knightSprite, bishopSprite;
        Image queenSpriteLarge, rookSpriteLarge, knightSpriteLarge, bishopSpriteLarge;
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("res\\images\\icno.png"));
            sheetScale = sheet.getWidth() / 6;
            int yOffset = isRacist ? 0 : sheetScale;
            // Normal size (tileSize / 2)
            queenSprite = sheet.getSubimage(1 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            rookSprite = sheet.getSubimage(4 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            knightSprite = sheet.getSubimage(3 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            bishopSprite = sheet.getSubimage(2 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize / 2, tileSize / 2, BufferedImage.SCALE_SMOOTH);
            // Large size (tileSize * 3/4)
            queenSpriteLarge = sheet.getSubimage(1 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize * 3 / 4, tileSize * 3 / 4, BufferedImage.SCALE_SMOOTH);
            rookSpriteLarge = sheet.getSubimage(4 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize * 3 / 4, tileSize * 3 / 4, BufferedImage.SCALE_SMOOTH);
            knightSpriteLarge = sheet.getSubimage(3 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize * 3 / 4, tileSize * 3 / 4, BufferedImage.SCALE_SMOOTH);
            bishopSpriteLarge = sheet.getSubimage(2 * sheetScale, yOffset, sheetScale, sheetScale)
                .getScaledInstance(tileSize * 3 / 4, tileSize * 3 / 4, BufferedImage.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(23, 21, 19));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.fill = GridBagConstraints.NONE;

        // Button sizes
        Dimension normalSize = new Dimension(tileSize / 2 + 10, tileSize / 2 + 10);
        Dimension largeSize = new Dimension(tileSize * 3 / 4 + 10, tileSize * 3 / 4 + 10);

        //Hover effect wowoowow
        JButton queenButton = new JButton(new ImageIcon(queenSprite));
        queenButton.setBackground(new Color(67, 90, 106));
        queenButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        queenButton.setPreferredSize(normalSize);
        queenButton.addActionListener(e -> {
            selectedPiece = "Queen";
            dialog.dispose();
        });
        queenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                queenButton.setIcon(new ImageIcon(queenSpriteLarge));
                queenButton.setPreferredSize(largeSize);
                panel.revalidate();
                dialog.pack();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                queenButton.setIcon(new ImageIcon(queenSprite));
                queenButton.setPreferredSize(normalSize);
                panel.revalidate();
                dialog.pack();
            }
        });
        gbc.gridx = 0;
        panel.add(queenButton, gbc);

        JButton rookButton = new JButton(new ImageIcon(rookSprite));
        rookButton.setBackground(new Color(145, 170, 192));
        rookButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rookButton.setPreferredSize(normalSize);
        rookButton.addActionListener(e -> {
            selectedPiece = "Rook";
            dialog.dispose();
        });
        rookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rookButton.setIcon(new ImageIcon(rookSpriteLarge));
                rookButton.setPreferredSize(largeSize);
                panel.revalidate();
                dialog.pack();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rookButton.setIcon(new ImageIcon(rookSprite));
                rookButton.setPreferredSize(normalSize);
                panel.revalidate();
                dialog.pack();
            }
        });
        gbc.gridx = 1;
        panel.add(rookButton, gbc);

        JButton knightButton = new JButton(new ImageIcon(knightSprite));
        knightButton.setBackground(new Color(67, 90, 106));
        knightButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        knightButton.setPreferredSize(normalSize);
        knightButton.addActionListener(e -> {
            selectedPiece = "Knight";
            dialog.dispose();
        });
        knightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                knightButton.setIcon(new ImageIcon(knightSpriteLarge));
                knightButton.setPreferredSize(largeSize);
                panel.revalidate();
                dialog.pack();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                knightButton.setIcon(new ImageIcon(knightSprite));
                knightButton.setPreferredSize(normalSize);
                panel.revalidate();
                dialog.pack();
            }
        });
        gbc.gridx = 2;
        panel.add(knightButton, gbc);

        JButton bishopButton = new JButton(new ImageIcon(bishopSprite));
        bishopButton.setBackground(new Color(145, 170, 192));
        bishopButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        bishopButton.setPreferredSize(normalSize);
        bishopButton.addActionListener(e -> {
            selectedPiece = "Bishop";
            dialog.dispose();
        });
        bishopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bishopButton.setIcon(new ImageIcon(bishopSpriteLarge));
                bishopButton.setPreferredSize(largeSize);
                panel.revalidate();
                dialog.pack();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bishopButton.setIcon(new ImageIcon(bishopSprite));
                bishopButton.setPreferredSize(normalSize);
                panel.revalidate();
                dialog.pack();
            }
        });
        gbc.gridx = 3;
        panel.add(bishopButton, gbc);

        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        try {
            ImageIcon icon = new ImageIcon("res\\images\\icno.png");
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