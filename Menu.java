import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

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

        // Crear botones para las opciones del menú
        JButton reservarButton = new JButton("Hacer Reservación");
        JButton agregarVueloButton = new JButton("Agregar Info de Vuelo");
        JButton verReservacionesButton = new JButton("Ver Reservaciones");
        JButton eliminarReservacionButton = new JButton("Eliminar una Reservación");

        // Personalizar los botones (opcional)
        reservarButton.setBackground(new Color(243, 212, 142));
        reservarButton.setForeground(Color.BLACK);
        agregarVueloButton.setBackground(new Color(243, 212, 142));
        agregarVueloButton.setForeground(Color.BLACK);
        verReservacionesButton.setBackground(new Color(243, 212, 142));
        verReservacionesButton.setForeground(Color.BLACK);
        eliminarReservacionButton.setBackground(new Color(243, 212, 142));
        eliminarReservacionButton.setForeground(Color.BLACK);

        // Agregar los botones al panel de fondo
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(reservarButton, gbc);

        gbc.gridy = 1;
<<<<<<< HEAD
        gbc.gridwidth = 2;
        menuPanel.add(btnReservar, gbc);

        btnReservar.addActionListener(e -> {
            Reservacion hacerReservacion = new Reservacion();
            hacerReservacion.setVisible(true);
        });
=======
        backgroundPanel.add(agregarVueloButton, gbc);
>>>>>>> b3006d07dd536d0298df1ee985a8532566e32316

        gbc.gridy = 2;
<<<<<<< HEAD
        menuPanel.add(btnAgregarVuelo, gbc);

        btnAgregarVuelo.addActionListener(e -> {
            InfoVuelo agregarVuelo = new InfoVuelo();
            agregarVuelo.setVisible(true);
        });
=======
        backgroundPanel.add(verReservacionesButton, gbc);
>>>>>>> b3006d07dd536d0298df1ee985a8532566e32316

        gbc.gridy = 3;
        backgroundPanel.add(eliminarReservacionButton, gbc);

        add(backgroundPanel);

        // Acciones de ejemplo para cada botón (puedes agregar la funcionalidad que necesites)
        reservarButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción: Hacer Reservación"));
        agregarVueloButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción: Agregar Info de Vuelo"));
        verReservacionesButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción: Ver Reservaciones"));
        eliminarReservacionButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opción: Eliminar una Reservación"));
    }

    // Clase interna para el panel de fondo personalizado
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Cargar la imagen desde el archivo
            backgroundImage = new ImageIcon("imagenes/menu.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen escalada al tamaño del panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });
<<<<<<< HEAD

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
=======
>>>>>>> b3006d07dd536d0298df1ee985a8532566e32316
    }
}
