package base_prueba.database.operations;

import base_prueba.database.connection.DataBaseConexion;
import base_prueba.util.Colors;

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
     * Insercion de datos a la tabla
     */
    public void insertData(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_GREEN + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "    INSERCIÓN DE DATOS" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "═══════════════════════════════" + Colors.RESET + "\n");

        /* Pedimos nombre y edad */
        System.out.print(Colors.CYAN + "➤ Ingrese el nombre: " + Colors.RESET);
        nameUser = scn.nextLine();

        System.out.print(Colors.CYAN + "➤ Ingrese la edad: " + Colors.RESET);
        try {
            ageUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: Ingresa un numero entero." + Colors.RESET + "\n");
            return;
        }

        /* Insercion de datos a la tabla */
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO persona(nombre, edad) VALUES(?, ?)"
            );

            /* Establecemos valores | parametros y ejecutamos el cambio */
            ps.setString(1, nameUser);
            ps.setInt(2, ageUser);
            ps.executeUpdate();

            System.out.println("\n" + Colors.BRIGHT_GREEN + "✓ Datos insertados correctamente!" + Colors.RESET + "\n");

            DataBasePrint.printData();
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error insertando datos en la base de datos." + Colors.RESET + "\n");
        }
    }
}