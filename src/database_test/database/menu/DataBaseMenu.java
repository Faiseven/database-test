package database_test.database.menu;

import database_test.database.connection.DataBaseConexion;
import database_test.database.operations.DataBaseDelete;
import database_test.database.operations.DataBaseInsertion;
import database_test.database.operations.DataBaseUpdate;
import database_test.database.operations.DataBaseVerification;
import database_test.util.Colors;

import java.util.Scanner;

public class DataBaseMenu {
    /* Variables | Objects referencing classes */
    DataBaseInsertion dbi = new DataBaseInsertion();
    DataBaseDelete dbd = new DataBaseDelete();
    DataBaseVerification dbv = new DataBaseVerification();
    DataBaseUpdate dbu = new DataBaseUpdate();

    /* Variable | Objeto para la entrada de informacion */
    private final Scanner scn = new Scanner(System.in);

    /*
     * Starts the connection once the program starts
     */
    static {
        DataBaseConexion.connectDataBase();
        DataBaseConexion.createDataBase();
    }

    public DataBaseMenu() {
        menu();
    }

    /*
     * Options menu
     */
    private void menu() {
        while (true) {
            System.out.println("\n" + Colors.BOLD + Colors.CYAN + "╔═══════════════════════════╗" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.CYAN + "║" + Colors.RESET + "    " + Colors.BOLD + Colors.BRIGHT_WHITE + "MENU PRINCIPAL" + Colors.RESET + "         " + Colors.BOLD + Colors.CYAN + "║" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.CYAN + "╚═══════════════════════════╝" + Colors.RESET);

            System.out.println(Colors.BRIGHT_GREEN + "(1)" + Colors.RESET + " - Data entry");
            System.out.println(Colors.BRIGHT_BLUE + "(2)" + Colors.RESET + " - Data deletion");
            System.out.println(Colors.BRIGHT_YELLOW + "(3)" + Colors.RESET + " - Data verification");
            System.out.println(Colors.BRIGHT_MAGENTA + "(4)" + Colors.RESET + " - Data update");
            System.out.println(Colors.BRIGHT_RED + "(5)" + Colors.RESET + " - Quit programm");

            System.out.print("\n" + Colors.BOLD + "Select an option: " + Colors.RESET);
            int userSelect = scn.nextInt();
            scn.nextLine();

            if (userSelect == 5) {
                System.out.println("\n" + Colors.BRIGHT_MAGENTA + "See you later!" + Colors.RESET + "\n");
                scn.close();
                break;
            }

            optionsSelect(userSelect);
        }
    }

    /*
     * Selection menu
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
                System.err.println("\n" + Colors.RED + "Invalid option. Select another option." + Colors.RESET + "\n");
                break;
        }
    }
}