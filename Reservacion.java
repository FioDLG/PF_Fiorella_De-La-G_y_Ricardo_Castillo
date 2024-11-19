import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Reservacion extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    private JTextField pasaporteField, cantidadDiasField, cantidadPersonasField;

    public Reservacion() {
        setTitle("Reserva de Huesped");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Formulario de reserva
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

        JLabel cantidadDiasLabel = new JLabel("Cantidad de Días:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(cantidadDiasLabel, gbc);

        cantidadDiasField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cantidadDiasField, gbc);

        JLabel cantidadPersonasLabel = new JLabel("Cantidad de Personas:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cantidadPersonasLabel, gbc);

        cantidadPersonasField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cantidadPersonasField, gbc);

        JButton submitButton = new JButton("Realizar Reserva");
        submitButton.setBackground(new Color(243, 212, 142));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pasaporte = pasaporteField.getText();
                int cantidadDias = Integer.parseInt(cantidadDiasField.getText());
                int cantidadPersonas = Integer.parseInt(cantidadPersonasField.getText());
                if (guardarReserva(pasaporte, cantidadDias, cantidadPersonas)) {
                    JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
                    cerrarVentana();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al realizar la reserva");
                }
            }
        });

        add(panel);
    }

    private boolean guardarReserva(String pasaporte, int cantidadDias, int cantidadPersonas) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement stmt = conn.prepareCall("{CALL New_reserva(?, ?, ?)}");
            stmt.setString(1, pasaporte);
            stmt.setInt(2, cantidadDias);
            stmt.setInt(3, cantidadPersonas);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cerrarVentana() {
        this.setVisible(false);
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reservacion reserva = new Reservacion();
            reserva.setVisible(true);
        });
    }
}
