package database_test.database.operations;

import database_test.database.connection.DataBaseConexion;
import database_test.util.Colors;

import java.sql.*;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class DataBaseInsertion {
    private String nameUser;
    private int ageUser;
    private final Connection conn;

    public DataBaseInsertion() {
        conn = DataBaseConexion.getConnection();
    }

    /*
     * Inserting data into the table
     */
    public void insertData(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_GREEN + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "    DATA ENTRY" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "═══════════════════════════════" + Colors.RESET + "\n");

        /* Ask for name and age */
        System.out.print(Colors.CYAN + "➤ Enter the name: " + Colors.RESET);
        nameUser = scn.nextLine();

        System.out.print(Colors.CYAN + "➤ Enter your age: " + Colors.RESET);
        try {
            ageUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "Error: Enter an integer." + Colors.RESET + "\n");
            return;
        }

        /* Inserting data into the table */
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO persona(nombre, edad) VALUES(?, ?)"
            );

            /* Set values | parameters and execute the update */
            ps.setString(1, nameUser);
            ps.setInt(2, ageUser);
            ps.executeUpdate();

            System.out.println("\n" + Colors.BRIGHT_GREEN + "Data successfully entered!" + Colors.RESET + "\n");

            DataBasePrint.printData();
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "Error inserting data into the database." + Colors.RESET + "\n");
        }
    }
}