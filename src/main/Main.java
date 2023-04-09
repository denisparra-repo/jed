package main;

import main.controller.MainController;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.init();
        controller.run();
    }
}
