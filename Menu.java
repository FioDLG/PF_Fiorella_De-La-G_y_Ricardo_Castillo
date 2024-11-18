import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu extends JFrame {

    // Clase interna para la conexión a la base de datos
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

        // Crear el panel de fondo con la imagen
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
            // Cerrar el menú y abrir la ventana de reservación
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
            // Cerrar el menú y abrir la ventana de agregar vuelo
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
            // Cerrar el menú y abrir la ventana de ver reservaciones
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
            // Cerrar el menú y abrir la ventana de eliminar reservación
            setVisible(false);
            Eliminar eliminarReservacion = new Eliminar();
            eliminarReservacion.setVisible(true);
        });

        // Crear el botón "Agregar un Huésped"
        JButton btnAgregarHuesped = new JButton("Agregar un Huésped");
        btnAgregarHuesped.setBackground(new Color(243, 212, 142));
        btnAgregarHuesped.setForeground(Color.WHITE);
        btnAgregarHuesped.setFocusPainted(false);
        gbc.gridy = 5; // Establecer la fila para el botón
        backgroundPanel.add(btnAgregarHuesped, gbc);

        // Acción del botón "Agregar un Huésped"
        btnAgregarHuesped.addActionListener(e -> {
            // Cerrar el menú y abrir la ventana de agregar huésped
            setVisible(false);
            new huesped();
        });

        // Crear el botón "Mostrar Huésped"
        JButton btnMostrarHuesped = new JButton("Mostrar Huésped");
        btnMostrarHuesped.setBackground(new Color(243, 212, 142));
        btnMostrarHuesped.setForeground(Color.WHITE);
        btnMostrarHuesped.setFocusPainted(false);
        gbc.gridy = 6; // Establecer la fila para el botón
        backgroundPanel.add(btnMostrarHuesped, gbc);

        // Acción del botón "Mostrar Huésped"
        btnMostrarHuesped.addActionListener(e -> {
            // Cerrar el menú y abrir la ventana para mostrar los huéspedes
            setVisible(false);
            MostrarHuesped MostrarHuesped = new MostrarHuesped();
            MostrarHuesped.setVisible(true);
        });

        // Agregar el panel de fondo a la ventana principal
        add(backgroundPanel);

        // Mostrar la ventana principal
        setVisible(true);
    }

    // Clase interna para el panel de fondo personalizado
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Cargar la imagen desde el archivo
            backgroundImage = new ImageIcon("imagenes/menu.jpg").getImage(); // Asegúrate de que la imagen esté en la
                                                                             // ruta correcta
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen escalada al tamaño del panel
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu(); // Iniciar el menú
        });
    }
}
