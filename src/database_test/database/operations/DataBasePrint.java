package database_test.database.operations;

import database_test.database.connection.DataBaseConexion;
import database_test.util.Colors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBasePrint {
    private static final Connection conn;

    static {
        conn = DataBaseConexion.getConnection();
    }

    /*
     * Impresion de datos en la tabla
     */
    public static void printData() {
        try {
            Statement pst = conn.createStatement();
            ResultSet rs = pst.executeQuery(
                    "SELECT * FROM persona"
            );

            System.out.println(Colors.BOLD + Colors.MAGENTA + "╔═════════════════════════════════════════════════╗" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.MAGENTA + "║" + Colors.RESET +
                    Colors.BOLD + "  ID  " + Colors.RESET + Colors.MAGENTA + "│" + Colors.RESET +
                    Colors.BOLD + "      NOMBRE      " + Colors.RESET + Colors.MAGENTA + "│" + Colors.RESET +
                    Colors.BOLD + "  EDAD  " + Colors.RESET +
                    Colors.BOLD + Colors.MAGENTA + "║" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.MAGENTA + "╠═════════════════════════════════════════════════╣" + Colors.RESET);

            boolean hasData = false;

            /* Mientras aun haya otra fila */
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                System.out.println(Colors.MAGENTA + "║" + Colors.RESET +
                        Colors.BRIGHT_CYAN + String.format("  %-4d", id) + Colors.RESET +
                        Colors.MAGENTA + "│" + Colors.RESET +
                        Colors.BRIGHT_WHITE + String.format(" %-16s", nombre) + Colors.RESET +
                        Colors.MAGENTA + "│" + Colors.RESET +
                        Colors.BRIGHT_YELLOW + String.format("  %-5d", edad) + Colors.RESET +
                        Colors.MAGENTA + "║" + Colors.RESET);
            }

            System.out.println(Colors.BOLD + Colors.MAGENTA + "╚═════════════════════════════════════════════════╝" + Colors.RESET);

            if (!hasData) {
                System.out.println(Colors.YELLOW + "\n⚠ No hay registros disponibles en la tabla." + Colors.RESET + "\n");
            } else {
                System.out.println(); // Salto de línea al final
            }

        } catch (SQLException e) {
            System.err.println("\n" + Colors.RED + "❌ Error al obtener los datos de la base de datos." + Colors.RESET + "\n");
        }
    }
}