package main.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        setLayout(new BorderLayout());
        SwingUtilities.invokeLater(() -> {
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        });
    }

    public void addMainPanel(JPanel panel) {
        SwingUtilities.invokeLater(() -> {
            add(panel, BorderLayout.CENTER);
            repaint();
        });
    }

    public void addMainMenu(JMenuBar menuBar) {
        SwingUtilities.invokeLater(() -> {
            setJMenuBar(menuBar);
            repaint();
        });
    }
}
