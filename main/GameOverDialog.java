package main;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog {
    private final JDialog dialog;
    private final Board board;

    public GameOverDialog(Component parent, boolean isCheckmate, boolean isRacistMove) {
        this.board = (Board) parent;
        // Create dialog
        dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Game Over", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        // CHANGED: Changed dialog background to RGB(41, 93, 136)
        dialog.getContentPane().setBackground(new Color(41, 93, 136));

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // CHANGED: Changed panel background to RGB(41, 93, 136)
        panel.setBackground(new Color(41, 93, 136));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Status label (CHECKMATE or STALEMATE)
        JLabel statusLabel = new JLabel(isCheckmate ? "CHECKMATE" : "STALEMATE");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 36));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(statusLabel);

        // Outcome label
        String outcomeText;
        if (isCheckmate) {
            outcomeText = (isRacistMove ? "Black" : "White") + " emerged Victorious";
        } else {
            outcomeText = "The game is a draw";
        }
        JLabel outcomeLabel = new JLabel(outcomeText);
        outcomeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        outcomeLabel.setForeground(Color.WHITE);
        outcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10)); // Spacing
        panel.add(outcomeLabel);

        // Button panel for OK, New Game, Quit
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        // CHANGED: Changed button panel background to RGB(41, 93, 136)
        buttonPanel.setBackground(new Color(41, 93, 136));

        // New Game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        // CHANGED: Changed button background to RGB(41, 93, 136)
        newGameButton.setBackground(new Color(41, 93, 136));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        newGameButton.addActionListener(e -> {
            board.resetBoard();
            dialog.dispose();
        });
        buttonPanel.add(newGameButton);

        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        // CHANGED: Changed button background to RGB(41, 93, 136)
        quitButton.setBackground(new Color(41, 93, 136));
        quitButton.setForeground(Color.WHITE);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        quitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(quitButton);

        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);

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

    public void showDialog() {
        dialog.setVisible(true);
    }
}