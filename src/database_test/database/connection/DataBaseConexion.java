package database_test.database.connection;

import database_test.util.Colors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConexion {
    /* Variabl | Objets used for the database */
    private static Connection conn;
    private static final String DATA_BASE_TABLE = """
                CREATE TABLE IF NOT EXISTS persona(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre VARCHAR(200) NOT NULL,
                        edad INT NOT NULL
                );
            """;

    /*
     * Connection | Creation of the database
     */
    public static void connectDataBase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            System.out.println(Colors.BRIGHT_GREEN + "Connection to the database established successfully." + Colors.RESET);
        } catch (SQLException e) {
            System.err.println("\n" + Colors.RED + "Error connecting to the database." + Colors.RESET);
            System.err.println(Colors.RED + "   Detalles: " + e.getMessage() + Colors.RESET + "\n");
        }
    }

    /*
     * Table creation
     */
    public static void createDataBase() {
        try {
            /* Statement + createStatement: Only when there are parameters */
            Statement st = conn.createStatement();
            st.executeUpdate(DATA_BASE_TABLE);
            System.out.println(Colors.BRIGHT_GREEN + "Table ‘person’ successfully verified/created." + Colors.RESET + "\n");
        } catch (SQLException e) {
            System.err.println("\n" + Colors.RED + "Error creating the table in the database." + Colors.RESET);
            System.err.println(Colors.RED + "   Details: " + e.getMessage() + Colors.RESET + "\n");
        }
    }

    /*
     * Connection return to base
     */
    public static Connection getConnection() {
        if (conn == null) {
            System.out.println("\n" + Colors.CYAN + "Initializing connection to the database..." + Colors.RESET + "\n");
            connectDataBase();
            createDataBase();
        }
        return conn;
    }
}