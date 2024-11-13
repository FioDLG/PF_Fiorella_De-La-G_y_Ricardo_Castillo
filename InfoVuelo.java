import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InfoVuelo extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur"; // base de datos
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    public InfoVuelo() {
        setTitle("Agregar Información de Vuelo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos de entrada
        JLabel passportLabel = new JLabel("Número de Pasaporte:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(passportLabel, gbc);

        JTextField passportField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(passportField, gbc);

        JLabel vueloLabel = new JLabel("Número de Vuelo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(vueloLabel, gbc);

        JTextField vueloField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(vueloField, gbc);

        JLabel aerolineaLabel = new JLabel("Aerolinea:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(aerolineaLabel, gbc);

        JTextField aerolineaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(aerolineaField, gbc);

        JLabel horaLlegadaLabel = new JLabel("Hora de Llegada:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(horaLlegadaLabel, gbc);

        JTextField horaLlegadaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(horaLlegadaField, gbc);

        JLabel lugarLlegadaLabel = new JLabel("Lugar de Llegada:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lugarLlegadaLabel, gbc);

        JTextField lugarLlegadaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lugarLlegadaField, gbc);

        JLabel horaSalidaLabel = new JLabel("Hora de Salida:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(horaSalidaLabel, gbc);

        JTextField horaSalidaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(horaSalidaField, gbc);

        JLabel lugarSalidaLabel = new JLabel("Lugar de Salida:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lugarSalidaLabel, gbc);

        JTextField lugarSalidaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lugarSalidaField, gbc);

        JButton agregarButton = new JButton("Agregar Vuelo");
        agregarButton.setBackground(new Color(243, 212, 142));
        agregarButton.setForeground(Color.WHITE);
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(agregarButton, gbc);

        agregarButton.addActionListener(e -> {
            String passport = passportField.getText();
            int vuelo = Integer.parseInt(vueloField.getText());
            String aerolinea = aerolineaField.getText();
            String horaLlegada = horaLlegadaField.getText();
            String lugarLlegada = lugarLlegadaField.getText();
            String horaSalida = horaSalidaField.getText();
            String lugarSalida = lugarSalidaField.getText();
            agregarInfoVuelo(passport, vuelo, aerolinea, horaLlegada, lugarLlegada, horaSalida, lugarSalida);
        });

        add(panel);
    }

    private void agregarInfoVuelo(String passport, int vuelo, String aerolinea, String horaLlegada, String lugarLlegada,
            String horaSalida, String lugarSalida) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "{ CALL New_infoVuelo(?, ?, ?, ?, ?, ?, ?) }";
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, passport);
            statement.setInt(2, vuelo);
            statement.setString(3, aerolinea);
            statement.setString(4, horaLlegada);
            statement.setString(5, lugarLlegada);
            statement.setString(6, horaSalida);
            statement.setString(7, lugarSalida);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Información de vuelo agregada exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar la información de vuelo");
        }
    }

}
