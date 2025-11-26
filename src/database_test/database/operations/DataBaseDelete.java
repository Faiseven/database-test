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
     * Informacion para eliminar datos
     */
    public void deleteInformation(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_BLUE + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE + "    ELIMINACIÓN DE DATOS" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE + "═══════════════════════════════" + Colors.RESET + "\n");

        System.out.println(Colors.YELLOW + "📋 INFORMACIÓN DISPONIBLE:" + Colors.RESET + "\n");
        DataBasePrint.printData();

        System.out.print("\n" + Colors.CYAN + "➤ Ingresa el ID que desea eliminar: " + Colors.RESET);
        try {
            idUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: Ingresa un ID valido." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        try {
            /* PreparedStatement: Solo cuando hay parametros */
            PreparedStatement pst = conn.prepareStatement(
                    "DELETE FROM persona WHERE id = ?"
            );
            pst.setInt(1, idUser);
            int affectedLines = pst.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("\n" + Colors.BRIGHT_GREEN + "✓ Informacion eliminada correctamente." + Colors.RESET + "\n");
                DataBasePrint.printData();
            } else {
                System.err.println("\n" + Colors.YELLOW + "⚠ No se encontro el ID ingresado." + Colors.RESET + "\n");
            }
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error al eliminar el registro con el ID ingresado." + Colors.RESET + "\n");
        }
    }
}