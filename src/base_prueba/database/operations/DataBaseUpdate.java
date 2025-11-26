package base_prueba.database.operations;

import base_prueba.database.connection.DataBaseConexion;
import base_prueba.util.Colors;

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

    public void updateInformation(Scanner scn) {
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_MAGENTA + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_MAGENTA + "    ACTUALIZACIÓN DE DATOS" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_MAGENTA + "═══════════════════════════════" + Colors.RESET + "\n");

        System.out.println(Colors.YELLOW + "📋 INFORMACIÓN DISPONIBLE:" + Colors.RESET + "\n");
        DataBasePrint.printData();

        System.out.print("\n" + Colors.CYAN + "➤ Ingresa el ID que desea actualizar: " + Colors.RESET);
        try {
            idUser = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: Ingresa un ID valido." + Colors.RESET + "\n");
            scn.nextLine();
            return;
        }

        System.out.print(Colors.CYAN + "➤ Ingrese el nuevo nombre: " + Colors.RESET);
        newName = scn.nextLine();

        System.out.print(Colors.CYAN + "➤ Ingrese la nueva edad: " + Colors.RESET);
        try {
            newAge = scn.nextInt();
            scn.nextLine();
        } catch (Exception ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error: Ingresa un numero entero." + Colors.RESET + "\n");
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
                System.out.println("\n" + Colors.BRIGHT_GREEN + "✓ Informacion actualizada correctamente." + Colors.RESET + "\n");
                DataBasePrint.printData();
            } else {
                System.err.println("\n" + Colors.YELLOW + "⚠ No se encontro el ID ingresado." + Colors.RESET + "\n");
            }
        } catch (SQLException ignored) {
            System.err.println("\n" + Colors.RED + "❌ Error al actualizar el registro." + Colors.RESET + "\n");
        }
    }
}