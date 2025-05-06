package main;
import javax.swing.*;

import java.awt.*;

class Main{
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon("res\\icno.png");

        frame.setIconImage(img.getImage());
        frame.setTitle("Scaccomatto");;
        frame.getContentPane().setBackground(new Color(23,21,19));
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        
        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);
    }
}