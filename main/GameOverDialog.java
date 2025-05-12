package main;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog {
    private final JDialog dialog;
    private final Board board;

    public GameOverDialog(Component parent, boolean isCheckmate, boolean isRacistMove) {
        this.board = (Board) parent;
        dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Game Over", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(new Color(0, 180, 160));

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 180, 160));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel statusLabel = new JLabel(isCheckmate ? "CHECKMATE" : "STALEMATE");
        statusLabel.setFont(Fonts.load("BlackOpsOne", Font.BOLD, 45));
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
        outcomeLabel.setFont(new Font("Monda", Font.BOLD, 24));
        outcomeLabel.setForeground(Color.WHITE);
        outcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(outcomeLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(0, 180, 160));

        // New Game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(Fonts.load("Monda", Font.PLAIN, 18));
        newGameButton.setBackground(new Color(0, 180, 160));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        newGameButton.addActionListener(e -> {
            board.resetBoard();
            dialog.dispose();
        });
        buttonPanel.add(newGameButton);

        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(Fonts.load("Monda", Font.PLAIN, 18));
        quitButton.setBackground(new Color(0, 180, 160));
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
            ImageIcon icon = new ImageIcon("res\\images\\icno.png");
            dialog.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog() {
        dialog.setVisible(true);
    }
}