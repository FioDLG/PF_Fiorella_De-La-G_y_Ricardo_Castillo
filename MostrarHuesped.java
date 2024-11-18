
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MostrarHuesped extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    public MostrarHuesped() {
        setTitle("Ver Huéspedes");
        setSize(800, 400); // Ajusté el tamaño para más comodidad
        setLocationRelativeTo(null); // Centra la ventana

        // Crear el panel y la tabla
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);

        // Mostrar los huéspedes en la tabla
        mostrarHuespedes(table);

        // Agregar el listener para cerrar la ventana y abrir el menú
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Menu(); // Crear una nueva instancia de Menu
            }
        });
    }

    private void mostrarHuespedes(JTable table) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Llamar al procedimiento almacenado o realizar una consulta simple para
            // obtener los huéspedes
            String query = "SELECT passport, Nombre1, Nombre2, Apellido1, Apellido2 FROM huesped";
            Statement stmt = conn.createStatement();

            // Ejecutar la consulta
            ResultSet resultSet = stmt.executeQuery(query);

            // Establecer el modelo de la tabla con los datos obtenidos
            table.setModel(buildTableModel(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los huéspedes");
        }
    }

    // Método para construir el modelo de la tabla a partir de un ResultSet
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Obtener los nombres de las columnas
        Vector<String> columnNames = new Vector<>(columnCount);
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Obtener los datos de las filas
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>(columnCount);
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        // Crear y retornar el modelo de la tabla
        return new DefaultTableModel(data, columnNames);
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MostrarHuesped mostrarHuesped = new MostrarHuesped(); // Usar el nombre correcto de la clase
            mostrarHuesped.setVisible(true); // Mostrar la ventana
        });
    }
}
