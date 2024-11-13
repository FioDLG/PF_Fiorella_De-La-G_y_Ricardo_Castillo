import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class huesped {

    // Componentes de la interfaz gráfica
    private JFrame frame;
    private JTextField passportField, nombre1Field, nombre2Field, apellido1Field, apellido2Field;
    private JButton agregarButton;

    // Detalles de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
    private static final String USER = "root";
    private static final String PASSWORD = "myrf0424";

    public huesped() {
        // Crear la ventana
        frame = new JFrame("Agregar Huésped");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2));

        // Crear los campos de texto
        passportField = new JTextField();
        nombre1Field = new JTextField();
        nombre2Field = new JTextField();
        apellido1Field = new JTextField();
        apellido2Field = new JTextField();

        // Crear el botón para agregar el huésped
        agregarButton = new JButton("Agregar Huésped");

        // Agregar los componentes al frame
        frame.add(new JLabel("Passport:"));
        frame.add(passportField);
        frame.add(new JLabel("Nombre 1:"));
        frame.add(nombre1Field);
        frame.add(new JLabel("Nombre 2:"));
        frame.add(nombre2Field);
        frame.add(new JLabel("Apellido 1:"));
        frame.add(apellido1Field);
        frame.add(new JLabel("Apellido 2:"));
        frame.add(apellido2Field);
        frame.add(new JLabel()); // Fila vacía
        frame.add(agregarButton);

        // Agregar el manejador de eventos para el botón
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarHuesped();
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void agregarHuesped() {
        // Obtener los valores ingresados
        String passport = passportField.getText();
        String nombre1 = nombre1Field.getText();
        String nombre2 = nombre2Field.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();

        // Validar los campos
        if (passport.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty()
                || apellido2.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Hay campos que deben ser llenados.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al procedimiento almacenado para agregar el huésped
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Preparar la llamada al procedimiento almacenado
            CallableStatement stmt = conn.prepareCall("{CALL New_huesped(?, ?, ?, ?, ?)}");

            // Establecer los parámetros del procedimiento
            stmt.setString(1, passport);
            stmt.setString(2, nombre1);
            stmt.setString(3, nombre2);
            stmt.setString(4, apellido1);
            stmt.setString(5, apellido2);

            // Ejecutar la llamada
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Huésped agregado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después de agregar el huésped
            passportField.setText("");
            nombre1Field.setText("");
            nombre2Field.setText("");
            apellido1Field.setText("");
            apellido2Field.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al agregar huésped: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Iniciar la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new huesped();
            }
        });
    }
}
