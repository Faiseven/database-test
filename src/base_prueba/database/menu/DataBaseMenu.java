package base_prueba.database.menu;

import base_prueba.database.connection.DataBaseConexion;
import base_prueba.database.operations.DataBaseDelete;
import base_prueba.database.operations.DataBaseInsertion;
import base_prueba.database.operations.DataBaseUpdate;
import base_prueba.database.operations.DataBaseVerification;
import base_prueba.util.Colors;

import java.util.Scanner;

public class DataBaseMenu {
    /* Variables | Objetos de referencia a las clases */
    DataBaseInsertion dbi = new DataBaseInsertion();
    DataBaseDelete dbd = new DataBaseDelete();
    DataBaseVerification dbv = new DataBaseVerification();
    DataBaseUpdate dbu = new DataBaseUpdate();

    /* Variable | Objeto para la entrada de informacion */
    private final Scanner scn = new Scanner(System.in);

    /*
     * Inicia la conexion cuanod inicie el programa
     */
    static {
        DataBaseConexion.connectDataBase();
        DataBaseConexion.createDataBase();
    }

    public DataBaseMenu() {
        menu();
    }

    /*
     * Menu de opciones
     */
    private void menu() {
        while (true) {
            System.out.println("\n" + Colors.BOLD + Colors.CYAN + "╔═══════════════════════════╗" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.CYAN + "║" + Colors.RESET + "    " + Colors.BOLD + Colors.BRIGHT_WHITE + "MENU PRINCIPAL" + Colors.RESET + "         " + Colors.BOLD + Colors.CYAN + "║" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.CYAN + "╚═══════════════════════════╝" + Colors.RESET);

            System.out.println(Colors.BRIGHT_GREEN + "(1)" + Colors.RESET + " - Insercion datos");
            System.out.println(Colors.BRIGHT_BLUE + "(2)" + Colors.RESET + " - Eliminacion datos");
            System.out.println(Colors.BRIGHT_YELLOW + "(3)" + Colors.RESET + " - Verificacion datos");
            System.out.println(Colors.BRIGHT_MAGENTA + "(4)" + Colors.RESET + " - Actualizacion datos");
            System.out.println(Colors.BRIGHT_RED + "(5)" + Colors.RESET + " - Quit programm");

            System.out.print("\n" + Colors.BOLD + "Ingresa una opcion: " + Colors.RESET);
            int userSelect = scn.nextInt();
            scn.nextLine();

            if (userSelect == 5) {
                System.out.println("\n" + Colors.BRIGHT_MAGENTA + "¡Hasta luego!" + Colors.RESET + "\n");
                scn.close();
                break;
            }

            optionsSelect(userSelect);
        }
    }

    /*
     * Menu de selecciones
     */
    private void optionsSelect(int userSelect) {
        switch (userSelect) {
            case 1:
                dbi.insertData(scn);
                break;
            case 2:
                dbd.deleteInformation(scn);
                break;
            case 3:
                dbv.verifcationData(scn);
                break;
            case 4:
                dbu.updateInformation(scn);
            default:
                System.err.println("\n" + Colors.RED + "❌ Opcion invalida. Ingrese otra opcion." + Colors.RESET + "\n");
                break;
        }
    }
}