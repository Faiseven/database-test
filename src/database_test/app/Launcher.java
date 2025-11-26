package database_test.app;

import database_test.database.menu.DataBaseMenu;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataBaseMenu::new);
    }
}
