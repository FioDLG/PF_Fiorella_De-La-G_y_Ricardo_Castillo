import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Eliminar extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    public Eliminar() {
        setTitle("Eliminar Reservación");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("Número de la Reservación:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(new Color(243, 212, 142));
        eliminarButton.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(eliminarButton, gbc);

        eliminarButton.addActionListener(e -> {
            try {
                String inputText = idField.getText();
                if (inputText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número para la reservación.");
                    return;
                }

                int id = Integer.parseInt(inputText);
                if (!verificarExistenciaReservacion(id)) {
                    JOptionPane.showMessageDialog(this, "No se encontró la reservación con ID: " + id);
                    return;
                }

                eliminarReservacion(id);

                // Después de eliminar, cerrar la ventana y abrir el menú
                dispose();
                new Menu().setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la reservación.");
            }
        });

        add(panel);
    }

    private void eliminarReservacion(int id) {
        String query = "{ CALL Borrar_reserva(?) }";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                CallableStatement statement = conn.prepareCall(query)) {

            statement.setInt(1, id);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la reservación: " + e.getMessage());
        }
    }

    private boolean verificarExistenciaReservacion(int id) {
        String query = "SELECT COUNT(*) FROM reserva WHERE Nreserva = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al verificar la reservación: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Eliminar eliminar = new Eliminar();
            eliminar.setVisible(true);
        });
    }
}
