package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleScreen extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(23, 21, 19);
    private static final Color BUTTON_COLOR = new Color(23, 21, 19);
    private static final Color TEXT_COLOR = Color.WHITE;

    public TitleScreen(JFrame frame, CardLayout cardLayout, JPanel cardPanel) {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(BACKGROUND_COLOR);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Title
        JLabel titleLabel = new JLabel("Scaccomatto");
        titleLabel.setFont(Fonts.load("BlackOpsOne", Font.BOLD, 64));
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

        add(content, BorderLayout.WEST);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Fonts.load("Montserrat", Font.PLAIN, 24));
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
                button.setBackground(new Color(27, 199, 162)); //rgb(27, 199, 162)
                button.setFont(Fonts.load("Montserrat", Font.BOLD, 30));
                button.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 2));
                button.setBackground(BUTTON_COLOR);
                button.setFont(Fonts.load("Montserrat", Font.PLAIN, 30));
                button.revalidate();
            }
        });

        return button;
    }
}
class CreditScreen extends JPanel {
    public CreditScreen(CardLayout cardLayout, JPanel cardPanel) {
        setBackground(new Color(23, 21, 19));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Credits");
        title.setFont(Fonts.load("BlackOpsOne", Font.BOLD, 64));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(title);

        JLabel code = new JLabel("Lead Programmer");
        code.setFont(Fonts.load("CrimsonText", Font.PLAIN, 40));
        code.setForeground(Color.WHITE);
        code.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(code);

        JLabel name = new JLabel("Arpan Gayen XI A");
        name.setFont(Fonts.load("DancingScript", Font.PLAIN, 24));
        name.setForeground(Color.WHITE);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(name);

        JButton backButton = new JButton("Back");
        backButton.setFont(Fonts.load("Montserrat", Font.PLAIN, 20));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Title"));
        add(Box.createVerticalStrut(40));
        add(backButton);
    }
}
