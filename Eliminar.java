import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Eliminar extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/villamoncouer";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Chimuelo1514.";

    public Eliminar() {
        setTitle("Eliminar Reservación");
        setSize(500, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("Numero de la Reservación:");
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
            int id = Integer.parseInt(idField.getText());
            eliminarReservacion(id);
        });

        add(panel);
    }

    private void eliminarReservacion(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM reserva WHERE Nreserva = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la reservación con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la reservación");
        }
    }
}

