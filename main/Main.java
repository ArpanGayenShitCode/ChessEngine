package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon("res\\icno.png");
        frame.setIconImage(img.getImage());
        frame.setTitle("Scaccomatto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 1000);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Game screen components
        MoveHistoryPanel moveHistoryPanel = new MoveHistoryPanel();
        Board board = new Board();
        board.setMoveHistoryPanel(moveHistoryPanel);
        board.setPreferredSize(new Dimension(800, 800));

        JPanel boardWrapper = new JPanel(new GridBagLayout());
        boardWrapper.setBackground(new Color(23, 21, 19));
        boardWrapper.add(board);

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(new Color(23, 21, 19));
        gamePanel.add(boardWrapper, BorderLayout.CENTER);
        gamePanel.add(moveHistoryPanel, BorderLayout.EAST);

        // Title and credit screens
        TitleScreen titleScreen = new TitleScreen(frame, cardLayout, cardPanel);
        CreditScreen creditScreen = new CreditScreen(cardLayout, cardPanel);

        cardPanel.add(titleScreen, "Title");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(creditScreen, "Credits");

        frame.setContentPane(cardPanel);
        cardLayout.show(cardPanel, "Title");
        frame.setVisible(true);
    }
}
class CreditScreen extends JPanel {
    public CreditScreen(CardLayout cardLayout, JPanel cardPanel) {
        setBackground(new Color(23, 21, 19));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Credits");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(title);

        JLabel name = new JLabel("Made by Arpan Gayen XI A");
        name.setFont(new Font("Arial", Font.PLAIN, 24));
        name.setForeground(Color.WHITE);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(name);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Title"));
        add(Box.createVerticalStrut(40));
        add(backButton);
    }
}
