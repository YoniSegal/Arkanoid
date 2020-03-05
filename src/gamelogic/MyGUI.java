package gamelogic;

import biuoop.GUI;

public class MyGUI extends GUI {
    private static GUI gui_instance = null;

    private MyGUI(String title, int width, int height) {
        super(title, width, height);
    }

    public static GUI getInstance(String title, int width, int height) {
        if (gui_instance == null) {
            gui_instance = new MyGUI(title, width, height);
        }
        return gui_instance;
    }
}
