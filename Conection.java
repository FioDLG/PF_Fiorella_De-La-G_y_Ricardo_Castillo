import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Conection {
    // Credenciales de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/villamoncouer?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Chimuelo1514.";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establecer conexión
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");


        } catch (SQLException e) {
            System.err.println("Error durante la conexión: " + e.getMessage());
        } finally {
            // Cerrar la conexión y el escáner
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
}  