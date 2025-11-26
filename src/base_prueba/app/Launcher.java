package base_prueba.app;

import base_prueba.database.menu.DataBaseMenu;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataBaseMenu::new);
    }
}
