package database_test.database.operations;

import database_test.database.connection.DataBaseConexion;
import database_test.util.Colors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class DataBaseVerification {
    private final Connection conn;
    private int idUser;

    public DataBaseVerification() {
        conn = DataBaseConexion.getConnection();
    }

    public void verifcationData(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_YELLOW + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_YELLOW + "    VERIFICACIÓN DE DATOS" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_YELLOW + "═══════════════════════════════" + Colors.RESET + "\n");

        System.out.print(Colors.CYAN + "➤ Ingresa el ID que desea verificar: " + Colors.RESET);
        try {
            idUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: Ingresa un ID valido." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        try {
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT * FROM persona WHERE id = ?"
            );
            pst.setInt(1, idUser);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("\n" + Colors.BRIGHT_GREEN + "✓ Registro encontrado:" + Colors.RESET);
                System.out.println(Colors.BOLD + Colors.CYAN + "┌─────────────────────────────────────┐" + Colors.RESET);
                System.out.println(Colors.CYAN + "│" + Colors.RESET + " " + Colors.BOLD + "ID:" + Colors.RESET + " " +
                        Colors.BRIGHT_WHITE + rs.getInt("id") + Colors.RESET + " " +
                        Colors.CYAN + "│" + Colors.RESET + " " + Colors.BOLD + "Nombre:" + Colors.RESET + " " +
                        Colors.BRIGHT_WHITE + rs.getString("nombre") + Colors.RESET + " " +
                        Colors.CYAN + "│" + Colors.RESET + " " + Colors.BOLD + "Edad:" + Colors.RESET + " " +
                        Colors.BRIGHT_WHITE + rs.getInt("edad") + Colors.RESET + " " +
                        Colors.CYAN + "│" + Colors.RESET);
                System.out.println(Colors.BOLD + Colors.CYAN + "└─────────────────────────────────────┘" + Colors.RESET + "\n");
            } else {
                System.out.println("\n" + Colors.YELLOW + "⚠ No existe ningun registro con ese ID." + Colors.RESET + "\n");
            }

        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: No se pudo verificar la existencia del ID." + Colors.RESET + "\n");
        }
    }
}