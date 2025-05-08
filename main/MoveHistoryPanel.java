package main;

import javax.swing.*;
import java.awt.*;

public class MoveHistoryPanel extends JPanel {
    private final JPanel moveListPanel;
    private int moveCounter = 1;
    private JLabel currentMoveLabel;
    private String pendingWhiteMove = "";

    public MoveHistoryPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 1000));
        setBackground(new Color(23, 21, 19));

        moveListPanel = new JPanel();
        moveListPanel.setLayout(new BoxLayout(moveListPanel, BoxLayout.Y_AXIS));
        moveListPanel.setBackground(new Color(23, 21, 19));

        JScrollPane scrollPane = new JScrollPane(moveListPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMove(String notation, boolean isWhiteMove) {
        if (isWhiteMove) {
            pendingWhiteMove = moveCounter + ". " + notation;
            currentMoveLabel = createMoveLabel(pendingWhiteMove);
            moveListPanel.add(Box.createVerticalStrut(5));
            moveListPanel.add(currentMoveLabel);
        } else {
            String fullMove = pendingWhiteMove + " " + notation;
            currentMoveLabel.setText(fullMove);
            moveCounter++;
            pendingWhiteMove = "";
        }
        revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    private JLabel createMoveLabel(String text) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(new Color(45, 45, 45));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        label.setHorizontalAlignment(SwingConstants.RIGHT); 
        return label;
    }

    public void reset() {
        moveCounter = 1;
        pendingWhiteMove = "";
        currentMoveLabel = null;
        moveListPanel.removeAll();
        revalidate();
        repaint();
    }
}