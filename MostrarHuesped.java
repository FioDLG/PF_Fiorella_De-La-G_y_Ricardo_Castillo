
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
        setSize(800, 400);
        setLocationRelativeTo(null);

        // Panel de tabla
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        mostrarHuespedes(table);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Menu();
            }
        });
    }

    private void mostrarHuespedes(JTable table) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT passport, Nombre1, Nombre2, Apellido1, Apellido2 FROM huesped";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            table.setModel(buildTableModel(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los huéspedes");
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>(columnCount);
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>(columnCount);
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MostrarHuesped mostrarHuesped = new MostrarHuesped();
            mostrarHuesped.setVisible(true);
        });
    }
}
