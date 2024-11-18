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

        // Panel para el formulario de reserva
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos del formulario
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
                // Obtener los valores ingresados por el usuario
                String pasaporte = pasaporteField.getText();
                int cantidadDias = Integer.parseInt(cantidadDiasField.getText());
                int cantidadPersonas = Integer.parseInt(cantidadPersonasField.getText());

                // Llamar al método para guardar la reserva
                if (guardarReserva(pasaporte, cantidadDias, cantidadPersonas)) {
                    JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
                    cerrarVentana(); // Cerrar la ventana de reserva y abrir el menú
                } else {
                    JOptionPane.showMessageDialog(null, "Error al realizar la reserva");
                }
            }
        });

        // Agregar el panel al JFrame
        add(panel);
    }

    private boolean guardarReserva(String pasaporte, int cantidadDias, int cantidadPersonas) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Llamar al procedimiento almacenado 'New_reserva'
            CallableStatement stmt = conn.prepareCall("{CALL New_reserva(?, ?, ?)}");
            stmt.setString(1, pasaporte); // Pasaporte
            stmt.setInt(2, cantidadDias); // Cantidad de días
            stmt.setInt(3, cantidadPersonas); // Cantidad de personas

            // Ejecutar el procedimiento
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para cerrar la ventana y abrir el menú
    private void cerrarVentana() {
        this.setVisible(false); // Ocultar la ventana de reserva
        Menu menu = new Menu(); // Crear una nueva instancia del menú
        menu.setVisible(true); // Mostrar el menú
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reservacion reserva = new Reservacion();
            reserva.setVisible(true);
        });
    }
}
