package database_test.database.operations;

import database_test.database.connection.DataBaseConexion;
import database_test.util.Colors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class DataBaseDelete {
    private final Connection conn;
    private int idUser;

    public DataBaseDelete() {
        conn = DataBaseConexion.getConnection();
    }

    /*
     * Method to delete data
     */
    public void deleteInformation(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_BLUE + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE + "    DATA DELETION" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE + "═══════════════════════════════" + Colors.RESET + "\n");

        System.out.println(Colors.YELLOW + "📋 INFORMATION AVAIBLE:" + Colors.RESET + "\n");
        DataBasePrint.printData();

        System.out.print("\n" + Colors.CYAN + "➤ Enter the ID you want to delete: " + Colors.RESET);
        try {
            idUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "Error: Invalid ID." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        try {
            /* PreparedStatement: Only when there are paremeters */
            PreparedStatement pst = conn.prepareStatement(
                    "DELETE FROM persona WHERE id = ?"
            );
            pst.setInt(1, idUser);
            int affectedLines = pst.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("\n" + Colors.BRIGHT_GREEN + "Information successfully deleted." + Colors.RESET + "\n");
                DataBasePrint.printData();
            } else {
                System.err.println("\n" + Colors.YELLOW + "The ID entered was not found." + Colors.RESET + "\n");
            }
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "Error deleting the record with the entered ID." + Colors.RESET + "\n");
        }
    }
}