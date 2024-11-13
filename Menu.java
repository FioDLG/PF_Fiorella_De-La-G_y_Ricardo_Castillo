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
        reservarButton.setForeground(Color.WHITE);
        agregarVueloButton.setBackground(new Color(243, 212, 142));
        agregarVueloButton.setForeground(Color.WHITE);
        verReservacionesButton.setBackground(new Color(243, 212, 142));
        verReservacionesButton.setForeground(Color.WHITE);
        eliminarReservacionButton.setBackground(new Color(243, 212, 142));
        eliminarReservacionButton.setForeground(Color.WHITE);

        // Agregar los botones al panel de fondo
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(reservarButton, gbc);

        gbc.gridy = 1;
        backgroundPanel.add(agregarVueloButton, gbc);

        gbc.gridy = 2;
        backgroundPanel.add(verReservacionesButton, gbc);

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
    }
}
