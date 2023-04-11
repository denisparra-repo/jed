package main.actions;

import main.view.ParseToXmlDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConvertToXmlAction extends AbstractAction {

    private JFrame main;
    private JTextArea textContent;

    public ConvertToXmlAction(JFrame main, JTextArea textContent) {
        super("Parse to XML");
        this.main = main;
        this.textContent = textContent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         JDialog dialog = new ParseToXmlDialog(textContent);
         dialog.setVisible(true);
    }
}
