import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InfoVuelo extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    private JTextField pasaporteField, nVueloField, aerolineaField, horaLlegadaField, lugarLlegadaField,
            horaSalidaField, lugarSalidaField;

    public InfoVuelo() {
        setTitle("Información del Vuelo");
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel pasaporteLabel = new JLabel("Pasaporte:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(pasaporteLabel, gbc);

        pasaporteField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(pasaporteField, gbc);

        JLabel nVueloLabel = new JLabel("Número de Vuelo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nVueloLabel, gbc);

        nVueloField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nVueloField, gbc);

        JLabel aerolineaLabel = new JLabel("Aerolínea:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(aerolineaLabel, gbc);

        aerolineaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(aerolineaField, gbc);

        JLabel horaLlegadaLabel = new JLabel("Hora Llegada:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(horaLlegadaLabel, gbc);

        horaLlegadaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(horaLlegadaField, gbc);

        JLabel lugarLlegadaLabel = new JLabel("Lugar Llegada:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lugarLlegadaLabel, gbc);

        lugarLlegadaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lugarLlegadaField, gbc);

        JLabel horaSalidaLabel = new JLabel("Hora Salida:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(horaSalidaLabel, gbc);

        horaSalidaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(horaSalidaField, gbc);

        JLabel lugarSalidaLabel = new JLabel("Lugar Salida:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lugarSalidaLabel, gbc);

        lugarSalidaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lugarSalidaField, gbc);

        JButton submitButton = new JButton("Guardar Información del Vuelo");
        submitButton.setBackground(new Color(243, 212, 142));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pasaporte = pasaporteField.getText();
                int nVuelo = Integer.parseInt(nVueloField.getText());
                String aerolinea = aerolineaField.getText();
                String horaLlegada = horaLlegadaField.getText();
                String lugarLlegada = lugarLlegadaField.getText();
                String horaSalida = horaSalidaField.getText();
                String lugarSalida = lugarSalidaField.getText();

                // Se llama al método para guardar la información del vuelo
                if (guardarInfoVuelo(pasaporte, nVuelo, aerolinea, horaLlegada, lugarLlegada, horaSalida,
                        lugarSalida)) {
                    JOptionPane.showMessageDialog(null, "Información del vuelo guardada con éxito");
                    cerrarVentana();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar la información del vuelo");
                }
            }
        });

        add(panel);
    }

    private boolean guardarInfoVuelo(String pasaporte, int nVuelo, String aerolinea, String horaLlegada,
            String lugarLlegada, String horaSalida, String lugarSalida) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Se llama al procedimiento almacenado "New_infoVuelo"
            CallableStatement stmt = conn.prepareCall("{CALL New_infoVuelo(?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, pasaporte); // Pasaporte
            stmt.setInt(2, nVuelo); // Número de vuelo
            stmt.setString(3, aerolinea); // Aerolínea
            stmt.setString(4, horaLlegada); // Hora de llegada
            stmt.setString(5, lugarLlegada); // Lugar de llegada
            stmt.setString(6, horaSalida); // Hora de salida
            stmt.setString(7, lugarSalida); // Lugar de salida

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InfoVuelo infoVuelo = new InfoVuelo();
            infoVuelo.setVisible(true);
        });
    }

    public void cerrarVentana() {
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
