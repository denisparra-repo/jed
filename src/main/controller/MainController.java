package main.controller;

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
       menu.add(file);
       mainView.addMainMenu(menu);
    }

    public void run() {
        mainView.setVisible(true);
    }
}
