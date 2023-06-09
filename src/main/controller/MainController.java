package main.controller;

import main.actions.ConvertToXmlAction;
import main.actions.OpenAction;
import main.view.EditorPanel;
import main.view.MainView;

import javax.swing.*;

public class MainController {
    private EditorPanel panel;
    private MainView mainView;

    private OpenAction openAction;
    public MainController() {
       panel = new EditorPanel();
       mainView = new MainView();
       openAction = new OpenAction(mainView);
    }

    public void init() {
       openAction.addListener(panel);
       mainView.addMainPanel(panel);
       JMenuBar menu = new JMenuBar();
       JMenu file = new JMenu("File");
       JMenuItem menuItem = new JMenuItem(openAction);
       file.add(menuItem);
       JMenu actions = new JMenu("Actions");
       JMenuItem actionItem = new JMenuItem(new ConvertToXmlAction(mainView, panel.getTextContent()));
       actions.add(actionItem);
       menu.add(file);
       menu.add(actions);
       mainView.addMainMenu(menu);
    }

    public void run() {
        mainView.setVisible(true);
    }
}
