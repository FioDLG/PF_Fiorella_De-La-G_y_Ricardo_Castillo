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
                eliminarReservacion(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la reservación.");
            }
        });

        add(panel);
    }

    private void eliminarReservacion(int id) {
        String query = "{ CALL Borrar_reserva(?) }"; // Llamada al procedimiento almacenado

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                CallableStatement statement = conn.prepareCall(query)) {

            // Establecer el valor del parámetro del procedimiento almacenado
            statement.setInt(1, id);

            // Ejecutar la actualización
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la reservación con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la reservación: " + e.getMessage());
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Eliminar eliminar = new Eliminar();
            eliminar.setVisible(true);
        });
    }
}
