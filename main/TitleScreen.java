package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleScreen extends JPanel {
    private static final Image BACKGROUND_IMAGE = new ImageIcon("res\\images\\bg.jpg").getImage();
    private static final Color BUTTON_COLOR = new Color(235, 236, 238);
    private static final Color TEXT_COLOR = Color.BLACK;

    public TitleScreen(JFrame frame, CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
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

        JButton multiplayerButton = createStyledButton("Multiplayer");
        multiplayerButton.addActionListener(e -> cardLayout.show(cardPanel, "Multiplayer"));

        JButton creditsButton = createStyledButton("Credits");
        creditsButton.addActionListener(e -> cardLayout.show(cardPanel, "Credits"));

        JButton quitButton = createStyledButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        content.add(multiplayerButton);
        content.add(Box.createVerticalStrut(10));
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
        button.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(Fonts.load("Monda", Font.BOLD, 30));
                button.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(Fonts.load("Montserrat", Font.PLAIN, 30));
                button.revalidate();
            }
        });

        return button;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);
    }
}
class CreditScreen extends JPanel {
    private static final Image BACKGROUND_IMAGE = new ImageIcon("res\\images\\bg.jpg").getImage();
    private static final Color BUTTON_COLOR = new Color(235, 236, 238);
    private static final Color TEXT_COLOR = Color.BLACK;
    public CreditScreen(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel title = new JLabel("Credits");
        title.setFont(Fonts.load("BlackOpsOne", Font.BOLD, 64));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(50));
        content.add(title);
        

        JLabel code = new JLabel("Lead Programmer");
        code.setFont(Fonts.load("CrimsonText", Font.BOLD, 40));
        code.setForeground(Color.BLACK);
        code.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));
        content.add(code);

        JLabel prog_lead = new JLabel("Arpan Gayen XI A");
        prog_lead.setFont(Fonts.load("DancingScript", Font.PLAIN, 32));
        prog_lead.setForeground(Color.BLACK);
        prog_lead.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));
        content.add(prog_lead);

        JLabel graphics = new JLabel("Lead Graphics Designer");
        graphics.setFont(Fonts.load("CrimsonText", Font.BOLD, 40));
        graphics.setForeground(Color.BLACK);
        graphics.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));
        content.add(graphics);

        JLabel graph_lead = new JLabel("Aniruddha Mallick XI C");
        graph_lead.setFont(Fonts.load("DancingScript", Font.PLAIN, 32));
        graph_lead.setForeground(Color.BLACK);
        graph_lead.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));
        content.add(graph_lead);

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Title"));
        content.add(Box.createVerticalStrut(40));
        content.add(backButton);

        add(content, BorderLayout.WEST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Fonts.load("Montserrat", Font.PLAIN, 24));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(Fonts.load("Monda", Font.BOLD, 30));
                button.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(Fonts.load("Montserrat", Font.PLAIN, 30));
                button.revalidate();
            }
        });

        return button;
    }
}
class MultiplayerScreen extends JPanel {
    private static final Image BACKGROUND_IMAGE = new ImageIcon("res\\images\\bg.jpg").getImage();
    private static final Color BUTTON_COLOR = new Color(235, 236, 238);
    private static final Color TEXT_COLOR = Color.BLACK;
    public MultiplayerScreen(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel title = new JLabel("Multiplayer");
        title.setFont(Fonts.load("BlackOpsOne", Font.BOLD, 64));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(50));
        content.add(title);
        

        
        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Title"));
        content.add(Box.createVerticalStrut(40));
        content.add(backButton);

        add(content, BorderLayout.WEST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Fonts.load("Montserrat", Font.PLAIN, 24));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(Fonts.load("Monda", Font.BOLD, 30));
                button.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(Fonts.load("Montserrat", Font.PLAIN, 30));
                button.revalidate();
            }
        });

        return button;
    }
}