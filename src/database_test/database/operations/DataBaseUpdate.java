package database_test.database.operations;

import database_test.database.connection.DataBaseConexion;
import database_test.util.Colors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class DataBaseUpdate {
    private final Connection conn;
    private int idUser;
    private String newName;
    private int newAge;

    public DataBaseUpdate() {
        conn = DataBaseConexion.getConnection();
    }

    /*
     * Method to update the information
     */
    public void updateInformation(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_MAGENTA + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_MAGENTA + "    DATA UPDATE" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_MAGENTA + "═══════════════════════════════" + Colors.RESET + "\n");

        System.out.println(Colors.YELLOW + "📋 INFORMATION AVAIBLE:" + Colors.RESET + "\n");
        DataBasePrint.printData();

        System.out.print("\n" + Colors.CYAN + "➤ Enter the ID you want to update: " + Colors.RESET);
        try {
            idUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "Error: Invalid ID." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        System.out.print(Colors.CYAN + "➤ Enter the new name: " + Colors.RESET);
        newName = scn.nextLine();

        System.out.print(Colors.CYAN + "➤ Enter the new age: " + Colors.RESET);
        try {
            newAge = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "Error: Invalid age." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        try {
            PreparedStatement pst = conn.prepareStatement(
                    "UPDATE persona SET nombre = ?, edad = ? WHERE id = ?"
            );
            pst.setString(1, newName);
            pst.setInt(2, newAge);
            pst.setInt(3, idUser);

            int affectedLines = pst.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("\n" + Colors.BRIGHT_GREEN + "Information correctly updated." + Colors.RESET + "\n");
                DataBasePrint.printData();
            } else {
                System.err.println("\n" + Colors.YELLOW + "The ID was not found." + Colors.RESET + "\n");
            }
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "Error updating the registry." + Colors.RESET + "\n");
        }
    }
}