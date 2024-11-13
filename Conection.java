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
        Scanner scanner = new Scanner(System.in);
        

        try {
            // Establecer conexión
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Insertar registro");
                System.out.println("2. Mostrar registros");
                System.out.println("3. Eliminar registro");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        insertarRegistro(connection, scanner);
                        break;
                    case 2:
                        mostrarRegistros(connection);
                        break;
                    case 3:
                        eliminarRegistro(connection, scanner);
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error durante la conexión: " + e.getMessage());
        } finally {
            // Cerrar la conexión y el escáner
            try {
                if (connection != null) {
                    connection.close();
                }
                scanner.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }

    private static void insertarRegistro(Connection connection, Scanner scanner) {
        String sql = "INSERT INTO estudiantes (cedula, nombre1, nombre2, apellido1, apellido2) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Ingresar datos
            System.out.print("Ingrese la cédula: ");
            String cedula = scanner.nextLine();
            preparedStatement.setString(1, cedula);

            System.out.print("Ingrese el primer nombre: ");
            String nombre1 = scanner.nextLine();
            preparedStatement.setString(2, nombre1);

            System.out.print("Ingrese el segundo nombre: ");
            String nombre2 = scanner.nextLine();
            preparedStatement.setString(3, nombre2);

            System.out.print("Ingrese el primer apellido: ");
            String apellido1 = scanner.nextLine();
            preparedStatement.setString(4, apellido1);

            System.out.print("Ingrese el segundo apellido: ");
            String apellido2 = scanner.nextLine();
            preparedStatement.setString(5, apellido2);

            // Ejecutar la inserción
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " registro(s) insertado(s) correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar registro: " + e.getMessage());
        }
    }

    private static void mostrarRegistros(Connection connection) {
        String sql = "SELECT * FROM estudiantes";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nRegistros en la tabla 'estudiantes':");
            System.out.println("Cédula: " + "\t" + " Nombre 1: " + "\t" + " Nombre 2: " + "\t" + " Apellido 1: "
                    + "\t\t" + " Apellido 2: ");
            while (resultSet.next()) {
                String cedula = resultSet.getString("cedula");
                String nombre1 = resultSet.getString("nombre1");
                String nombre2 = resultSet.getString("nombre2");
                String apellido1 = resultSet.getString("apellido1");
                String apellido2 = resultSet.getString("apellido2");
                System.out.println(
                        cedula + " \t " + nombre1 + " \t\t " + nombre2 + " \t " + apellido1 + " \t\t " + apellido2);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar registros: " + e.getMessage());
        }
    }

    private static void eliminarRegistro(Connection connection, Scanner scanner) {
        String sql = "DELETE FROM estudiantes WHERE cedula = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.print("Ingrese la cédula del registro a eliminar: ");
            String cedula = scanner.nextLine();
            preparedStatement.setString(1, cedula);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún registro con esa cédula.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar registro: " + e.getMessage());
        }
    }
}