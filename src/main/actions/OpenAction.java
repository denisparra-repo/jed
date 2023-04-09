package main.actions;

import main.model.ChangeEditorTextListener;
import main.model.FileChangeListener;
import main.model.FileProcessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenAction extends AbstractAction {
    private JFrame parent;

    private ArrayList<ChangeEditorTextListener> listeners = new ArrayList<>();

    public OpenAction(JFrame parent) {
        super("File");
        this.parent = parent;
    }

    public void addListener(ChangeEditorTextListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(String text) {
        for (ChangeEditorTextListener listener: listeners) {
            listener.appendText(text);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            new FileProcessor(file, listeners).start();
        }
    }
}
