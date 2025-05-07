package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleScreen extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(23, 21, 19);
    private static final Color BUTTON_COLOR = new Color(41, 93, 136);
    private static final Color TEXT_COLOR = Color.WHITE;

    public TitleScreen(JFrame frame, CardLayout cardLayout, JPanel cardPanel) {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        // Content container with vertical layout, left-aligned
        JPanel content = new JPanel();
        content.setBackground(BACKGROUND_COLOR);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // padding

        // Title
        JLabel titleLabel = new JLabel("Scaccomatto");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(40));

        // Buttons
        JButton startButton = createStyledButton("Start Game");
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Game"));

        JButton creditsButton = createStyledButton("Credits");
        creditsButton.addActionListener(e -> cardLayout.show(cardPanel, "Credits"));

        JButton quitButton = createStyledButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        content.add(startButton);
        content.add(Box.createVerticalStrut(10));
        content.add(creditsButton);
        content.add(Box.createVerticalStrut(10));
        content.add(quitButton);

        add(content, BorderLayout.WEST); // anchor left
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 4));
                button.setBackground(new Color(61, 113, 156));
                button.setFont(new Font("Arial", Font.BOLD, 26));
                button.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 2));
                button.setBackground(BUTTON_COLOR);
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.revalidate();
            }
        });

        return button;
    }
}
