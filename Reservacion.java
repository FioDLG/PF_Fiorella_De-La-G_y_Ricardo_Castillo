import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Reservacion extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/villamoncouer";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Chimuelo1514.";

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField vueloField, fechaField;
    private JTextField pasaporteField, nombreField, nombre2Field, apellido1Field, apellido2Field;

    public Reservacion() {
        setTitle("Hacer Reservación");
        setSize(600, 500);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear los dos formularios (vuelo y huésped)
        crearFormularioVuelo();
        crearFormularioHuesped();

        add(cardPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void crearFormularioVuelo() {
        JPanel vueloPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos de información del vuelo
        JLabel vueloLabel = new JLabel("Vuelo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        vueloPanel.add(vueloLabel, gbc);

        vueloField = new JTextField(20);
        gbc.gridx = 1;
        vueloPanel.add(vueloField, gbc);

        JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        vueloPanel.add(fechaLabel, gbc);

        fechaField = new JTextField(20);
        gbc.gridx = 1;
        vueloPanel.add(fechaField, gbc);

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.setBackground(new Color(243, 212, 142));
        siguienteButton.setForeground(Color.WHITE);
        siguienteButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        vueloPanel.add(siguienteButton, gbc);

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Huésped");
            }
        });

        // Agregar el formulario de vuelo al CardLayout
        cardPanel.add(vueloPanel, "Vuelo");
    }

    private void crearFormularioHuesped() {
        JPanel huespedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos de información del huésped
        JLabel pasaporteLabel = new JLabel("Pasaporte:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        huespedPanel.add(pasaporteLabel, gbc);

        pasaporteField = new JTextField(20);
        gbc.gridx = 1;
        huespedPanel.add(pasaporteField, gbc);

        JLabel nombreLabel = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        huespedPanel.add(nombreLabel, gbc);

        nombreField = new JTextField(20);
        gbc.gridx = 1;
        huespedPanel.add(nombreField, gbc);

        JLabel nombre2Label = new JLabel("Nombre 2:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        huespedPanel.add(nombre2Label, gbc);

        nombre2Field = new JTextField(20);
        gbc.gridx = 1;
        huespedPanel.add(nombre2Field, gbc);

        JLabel apellido1Label = new JLabel("Apellido 1:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        huespedPanel.add(apellido1Label, gbc);

        apellido1Field = new JTextField(20);
        gbc.gridx = 1;
        huespedPanel.add(apellido1Field, gbc);

        JLabel apellido2Label = new JLabel("Apellido 2:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        huespedPanel.add(apellido2Label, gbc);

        apellido2Field = new JTextField(20);
        gbc.gridx = 1;
        huespedPanel.add(apellido2Field, gbc);

        JButton submitButton = new JButton("Hacer Reservación");
        submitButton.setBackground(new Color(243, 212, 142));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        huespedPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recoger los datos de los formularios
                String vuelo = vueloField.getText();
                String fecha = fechaField.getText();
                String pasaporte = pasaporteField.getText();
                String nombre = nombreField.getText();
                String nombre2 = nombre2Field.getText();
                String apellido1 = apellido1Field.getText();
                String apellido2 = apellido2Field.getText();

                if (guardarReservacion(vuelo, fecha, pasaporte, nombre, nombre2, apellido1, apellido2)) {
                    JOptionPane.showMessageDialog(null, "Reservación realizada con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al realizar la reservación");
                }
            }
        });

        // Agregar el formulario de huésped al CardLayout
        cardPanel.add(huespedPanel, "Huésped");
    }

    // Método para guardar la reservación en la base de datos
    private boolean guardarReservacion(String vuelo, String fecha, String pasaporte, String nombre, String nombre2, String apellido1, String apellido2) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO reservaciones (vuelo, fecha, pasaporte, nombre, nombre2, apellido1, apellido2) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, vuelo);
            statement.setString(2, fecha);
            statement.setString(3, pasaporte);
            statement.setString(4, nombre);
            statement.setString(5, nombre2);
            statement.setString(6, apellido1);
            statement.setString(7, apellido2);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reservacion hacerReservacion = new Reservacion();
            hacerReservacion.setVisible(true);
        });
    }
}
