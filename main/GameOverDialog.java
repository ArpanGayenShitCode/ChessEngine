package main;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog {
    private final JDialog dialog;

    public GameOverDialog(Component parent, boolean isCheckmate, boolean isRacistMove) {
        // Create dialog
        dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Game Over", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(new Color(106, 130, 149)); // #6A8295

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(106, 130, 149));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        // OK button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 18));
        okButton.setBackground(new Color(106, 130, 149));
        okButton.setForeground(Color.WHITE);
        okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(Box.createVerticalStrut(20));
        panel.add(okButton);

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