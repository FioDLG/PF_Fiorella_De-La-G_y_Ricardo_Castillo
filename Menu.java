import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu extends JFrame {
    public static class DatabaseConnector {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "myrf0424";

        public static Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
            return conn;
        }
    }

    public Menu() {
        setTitle("Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Botón "Hacer Reservación"
        JButton btnReservar = new JButton("Hacer Reservación");
        btnReservar.setBackground(new Color(243, 212, 142));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        backgroundPanel.add(btnReservar, gbc);

        btnReservar.addActionListener(e -> {
            setVisible(false);
            Reservacion hacerReservacion = new Reservacion();
            hacerReservacion.setVisible(true);
        });

        // Botón "Agregar Info de Vuelo"
        JButton btnAgregarVuelo = new JButton("Agregar Info de Vuelo");
        btnAgregarVuelo.setBackground(new Color(243, 212, 142));
        btnAgregarVuelo.setForeground(Color.WHITE);
        btnAgregarVuelo.setFocusPainted(false);
        gbc.gridy = 2;
        backgroundPanel.add(btnAgregarVuelo, gbc);

        btnAgregarVuelo.addActionListener(e -> {
            setVisible(false);
            InfoVuelo agregarVuelo = new InfoVuelo();
            agregarVuelo.setVisible(true);
        });

        // Botón "Ver Reservaciones"
        JButton btnVerReservaciones = new JButton("Ver Reservaciones");
        btnVerReservaciones.setBackground(new Color(243, 212, 142));
        btnVerReservaciones.setForeground(Color.WHITE);
        btnVerReservaciones.setFocusPainted(false);
        gbc.gridy = 3;
        backgroundPanel.add(btnVerReservaciones, gbc);

        btnVerReservaciones.addActionListener(e -> {
            setVisible(false);
            MostrarReservacion verReservaciones = new MostrarReservacion();
            verReservaciones.setVisible(true);
        });

        // Botón "Eliminar una Reservación"
        JButton btnEliminarReservacion = new JButton("Eliminar una Reservación");
        btnEliminarReservacion.setBackground(new Color(243, 212, 142));
        btnEliminarReservacion.setForeground(Color.WHITE);
        btnEliminarReservacion.setFocusPainted(false);
        gbc.gridy = 4;
        backgroundPanel.add(btnEliminarReservacion, gbc);

        btnEliminarReservacion.addActionListener(e -> {
            setVisible(false);
            Eliminar eliminarReservacion = new Eliminar();
            eliminarReservacion.setVisible(true);
        });

        // Botón "Agregar un Huésped"
        JButton btnAgregarHuesped = new JButton("Agregar un Huésped");
        btnAgregarHuesped.setBackground(new Color(243, 212, 142));
        btnAgregarHuesped.setForeground(Color.WHITE);
        btnAgregarHuesped.setFocusPainted(false);
        gbc.gridy = 5;
        backgroundPanel.add(btnAgregarHuesped, gbc);

        btnAgregarHuesped.addActionListener(e -> {
            setVisible(false);
            new huesped();
        });

        // Crear el botón "Mostrar Huésped"
        JButton btnMostrarHuesped = new JButton("Mostrar Huésped");
        btnMostrarHuesped.setBackground(new Color(243, 212, 142));
        btnMostrarHuesped.setForeground(Color.WHITE);
        btnMostrarHuesped.setFocusPainted(false);
        gbc.gridy = 6;
        backgroundPanel.add(btnMostrarHuesped, gbc);

        btnMostrarHuesped.addActionListener(e -> {
            setVisible(false);
            MostrarHuesped MostrarHuesped = new MostrarHuesped();
            MostrarHuesped.setVisible(true);
        });

        add(backgroundPanel);
        setVisible(true);
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon("imagenes/menu.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu();
        });
    }
}
