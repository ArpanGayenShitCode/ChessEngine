package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon("res\\images\\icno.png");
        frame.setIconImage(img.getImage());
        frame.setTitle("Scaccomatto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

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

        TitleScreen titleScreen = new TitleScreen(frame, cardLayout, cardPanel);
        CreditScreen creditScreen = new CreditScreen(cardLayout, cardPanel);
        MultiplayerScreen multiplayerScreen = new MultiplayerScreen(cardLayout, cardPanel);

        cardPanel.add(titleScreen, "Title");
        cardPanel.add(multiplayerScreen, "Multiplayer");;
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(creditScreen, "Credits");

        frame.setContentPane(cardPanel);
        cardLayout.show(cardPanel, "Title");
        frame.setVisible(true);
    }
}