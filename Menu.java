import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(245, 245, 220));
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(titleLabel, gbc);

        // Botón "Hacer Reservación"
        JButton btnReservar = new JButton("Hacer Reservación");
        btnReservar.setBackground(new Color(243, 212, 142));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(btnReservar, gbc);

        btnReservar.addActionListener(e -> {
            Reservacion hacerReservacion = new Reservacion();
            hacerReservacion.setVisible(true);
        });

        // Botón "Agregar Info de Vuelo"
        JButton btnAgregarVuelo = new JButton("Agregar Info de Vuelo");
        btnAgregarVuelo.setBackground(new Color(243, 212, 142));
        btnAgregarVuelo.setForeground(Color.WHITE);
        btnAgregarVuelo.setFocusPainted(false);
        gbc.gridy = 2;
        menuPanel.add(btnAgregarVuelo, gbc);

        btnAgregarVuelo.addActionListener(e -> {
            InfoVuelo agregarVuelo = new InfoVuelo();
            agregarVuelo.setVisible(true);
        });

        // Botón "Ver Reservaciones"
        JButton btnVerReservaciones = new JButton("Ver Reservaciones");
        btnVerReservaciones.setBackground(new Color(243, 212, 142));
        btnVerReservaciones.setForeground(Color.WHITE);
        btnVerReservaciones.setFocusPainted(false);
        gbc.gridy = 3;
        menuPanel.add(btnVerReservaciones, gbc);

        btnVerReservaciones.addActionListener(e -> {
            MostrarReservacion verReservaciones = new MostrarReservacion();
            verReservaciones.setVisible(true);
        });

        // Botón "Eliminar una Reservación"
        JButton btnEliminarReservacion = new JButton("Eliminar una Reservación");
        btnEliminarReservacion.setBackground(new Color(243, 212, 142));
        btnEliminarReservacion.setForeground(Color.WHITE);
        btnEliminarReservacion.setFocusPainted(false);
        gbc.gridy = 4;
        menuPanel.add(btnEliminarReservacion, gbc);

        btnEliminarReservacion.addActionListener(e -> {
            Eliminar eliminarReservacion = new Eliminar();
            eliminarReservacion.setVisible(true);
        });

        add(menuPanel);
    }
}
