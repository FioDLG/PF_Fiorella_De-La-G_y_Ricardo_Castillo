import javax.swing.*;
import java.awt.*;

public class Reservacion extends JFrame {
    public Reservacion() {
        setTitle("Hacer Reservación");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titleLabel = new JLabel("Hacer Reservación", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Campo para Nombre
        JLabel nameLabel = new JLabel("Nombre:");
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Campo para Fecha de Vuelo
        JLabel fechaLabel = new JLabel("Fecha de Vuelo:");
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(fechaLabel, gbc);

        JTextField fechaField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(fechaField, gbc);

        // Botón para confirmar reservación
        JButton reservarButton = new JButton("Confirmar Reservación");
        reservarButton.setBackground(new Color(243, 212, 142));
        reservarButton.setForeground(Color.WHITE);
        reservarButton.setFocusPainted(false);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(reservarButton, gbc);

        // Evento del botón (aquí podrías agregar lógica para guardar la reservación)
        reservarButton.addActionListener(e -> {
            String nombre = nameField.getText();
            String fecha = fechaField.getText();
            JOptionPane.showMessageDialog(this, "Reservación hecha para " + nombre + " el " + fecha);
            // Aquí puedes agregar código para guardar la reservación en la base de datos
        });

        add(panel);
    }
}
