package base_prueba.database.connection;

import base_prueba.util.Colors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConexion {
    /* Vairables | Objetos para el funcionamiento de la base de datos */
    private static Connection conn;
    private static final String DATA_BASE_TABLE = """
                CREATE TABLE IF NOT EXISTS persona(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre VARCHAR(200) NOT NULL,
                        edad INT NOT NULL
                );
            """;

    /*
     * Conexion | Creacion a la base de datos
     */
    public static void connectDataBase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:Informacion_Personas.db");
            System.out.println(Colors.BRIGHT_GREEN + "✓ Conexión a la base de datos establecida correctamente." + Colors.RESET);
        } catch (SQLException e) {
            System.err.println("\n" + Colors.RED + "❌ Error al conectar con la base de datos." + Colors.RESET);
            System.err.println(Colors.RED + "   Detalles: " + e.getMessage() + Colors.RESET + "\n");
        }
    }

    /*
     * Creacion de la tabla
     */
    public static void createDataBase() {
        try {
            /* Statement + createStatement: Solo cuando no hay parametros */
            Statement st = conn.createStatement();
            st.executeUpdate(DATA_BASE_TABLE);
            System.out.println(Colors.BRIGHT_GREEN + "✓ Tabla 'persona' verificada/creada correctamente." + Colors.RESET + "\n");
        } catch (SQLException e) {
            System.err.println("\n" + Colors.RED + "❌ Error al crear la tabla en la base de datos." + Colors.RESET);
            System.err.println(Colors.RED + "   Detalles: " + e.getMessage() + Colors.RESET + "\n");
        }
    }

    /*
     * Retorno de la conexion a la base
     */
    public static Connection getConnection() {
        if (conn == null) {
            System.out.println("\n" + Colors.CYAN + "🔄 Inicializando conexión a la base de datos..." + Colors.RESET + "\n");
            connectDataBase();
            createDataBase();
        }
        return conn;
    }
}