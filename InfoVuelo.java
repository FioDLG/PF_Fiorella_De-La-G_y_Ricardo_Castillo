import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InfoVuelo extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/villamoncouer";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Chimuelo1514.";

    public InfoVuelo() {
        setTitle("Agregar Información de Vuelo");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos de entrada
        JLabel vueloLabel = new JLabel("Número de Vuelo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(vueloLabel, gbc);

        JTextField vueloField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(vueloField, gbc);

        JLabel destinoLabel = new JLabel("Destino:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(destinoLabel, gbc);

        JTextField destinoField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(destinoField, gbc);

        JLabel fechaLabel = new JLabel("Fecha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(fechaLabel, gbc);

        JTextField fechaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(fechaField, gbc);

        JButton agregarButton = new JButton("Agregar Vuelo");
        agregarButton.setBackground(new Color(243, 212, 142));
        agregarButton.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(agregarButton, gbc);

        agregarButton.addActionListener(e -> {
            String vuelo = vueloField.getText();
            String destino = destinoField.getText();
            String fecha = fechaField.getText();
            agregarVuelo(vuelo, destino, fecha);
        });

        add(panel);
    }

    private void agregarVuelo(String vuelo, String destino, String fecha) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO vuelos (num_vuelo, destino, fecha) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, vuelo);
            statement.setString(2, destino);
            statement.setString(3, fecha);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Vuelo agregado exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el vuelo");
        }
    }
}
