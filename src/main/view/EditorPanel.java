package main.view;

import main.model.ChangeEditorTextListener;

import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel implements ChangeEditorTextListener {
     private JTextArea panelText;
    public EditorPanel() {
        setLayout(new BorderLayout());
        panelText = new JTextArea();
        JScrollPane sp = new JScrollPane(panelText);
        add(sp, BorderLayout.CENTER);
    }

    public void appendText(String text) {
        System.out.println("2");
        SwingUtilities.invokeLater(() -> {
            panelText.append(String.format("%s", text));
        });
    }

    public JTextArea getTextContent() {
        return panelText;
    }
}
