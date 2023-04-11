package main.actions;

import main.model.CSVToXMLGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveConvertXMLAction extends AbstractAction {

    private JTextArea parent;
    private JFileChooser chooser;
    private JTextField delimiter;
    private JTextField rootTag;
    private JTextField rootRecord;

    public SaveConvertXMLAction(JTextArea parent, JFileChooser chooser, JTextField delimiter, JTextField rootRecord, JTextField rootTag) {
        this.parent = parent;
        this.chooser = chooser;
        this.delimiter = delimiter;
        this.rootTag = rootTag;
        this.rootRecord = rootRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      new CSVToXMLGenerator(chooser.getSelectedFile().getPath(), delimiter.getText(), rootTag.getText(), rootRecord.getText(), parent).start();
    }
}
