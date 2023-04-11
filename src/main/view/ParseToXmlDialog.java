package main.view;

import main.actions.ConvertToXmlAction;
import main.actions.SaveConvertXMLAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParseToXmlDialog extends JDialog {

    public JButton save;

    public JTextArea parent;
    public ParseToXmlDialog(JTextArea parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0,2, 5,10));
        JLabel rootTag = new JLabel("Root Tag: ");
        rootTag.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField fieldRootTag = new JTextField();
        JLabel rootRecord = new JLabel("Root Record: ");
        rootRecord.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField fieldRootRecord = new JTextField();
        JLabel delimiter = new JLabel("Delimiter: ");
        delimiter.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField delimiterField = new JTextField();
        JLabel outputPath = new JLabel("Output Path: ");
        outputPath.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField selectedFile = new JTextField();
        selectedFile.setEditable(false);
        JPanel chooserPanel = new JPanel();
        chooserPanel.setLayout(new BoxLayout(chooserPanel, BoxLayout.LINE_AXIS));
        JButton selectOutput = new JButton("...");
        JFileChooser chooser = new JFileChooser();
        chooserPanel.add(selectedFile);
        chooserPanel.add(selectOutput);
        selectOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFile.setText(chooser.getSelectedFile().getPath());
                }
            }
        });
        save = new JButton("Save");
        save.addActionListener(new SaveConvertXMLAction(parent, chooser, delimiterField, fieldRootRecord, fieldRootTag));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        form.add(rootTag);
        form.add(fieldRootTag);
        form.add(rootRecord);
        form.add(fieldRootRecord);
        form.add(delimiter);
        form.add(delimiterField);
        form.add(outputPath);
        form.add(chooserPanel);
        form.add(save);
        form.add(cancel);
        add(form, BorderLayout.CENTER);
        setSize(450, 200);
        setLocationRelativeTo(null);
    }

}
