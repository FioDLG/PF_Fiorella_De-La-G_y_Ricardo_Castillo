
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Usuario extends JFrame {
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

    // Credenciales de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservas_villa_mon_coeur"; // base de datos
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "myrf0424";

    public Usuario() {
        setTitle("Login de Usuario");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(245, 245, 220));
        backgroundPanel.setLayout(new CardLayout());
        add(backgroundPanel);

        cardLayout = new CardLayout();
        backgroundPanel.setLayout(cardLayout);

        createLoginPanel(backgroundPanel);
        createRegisterPanel(backgroundPanel);

        cardLayout.show(backgroundPanel, "Login");
    }

    private void createLoginPanel(JPanel parentPanel) {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(245, 245, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel userLabel = new JLabel("Usuario:", SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Contraseña:", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBackground(new Color(243, 212, 142));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                    dispose(); // Cierra la ventana de inicio de sesión
                    Menu menu = new Menu();
                    menu.setVisible(true); // Muestra el menú principal
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        JButton registerSwitchButton = new JButton("Registrarse");
        registerSwitchButton.setBackground(new Color(243, 212, 142));
        registerSwitchButton.setForeground(Color.WHITE);
        registerSwitchButton.setFocusPainted(false);
        gbc.gridy = 4;
        loginPanel.add(registerSwitchButton, gbc);

        registerSwitchButton.addActionListener(e -> cardLayout.show(parentPanel, "Register"));

        parentPanel.add(loginPanel, "Login");
    }

    private void createRegisterPanel(JPanel parentPanel) {
        registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(new Color(245, 245, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registerPanel.add(titleLabel, gbc);

        JLabel userLabel = new JLabel("Nuevo Usuario:", SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        registerPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        registerPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Nueva Contraseña:", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(passLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBackground(new Color(243, 212, 142));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = userField.getText();
                String newPassword = new String(passwordField.getPassword());

                if (registerUser(newUsername, newPassword)) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    cardLayout.show(parentPanel, "Login");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: el usuario ya existe");
                }
            }
        });

        JButton loginSwitchButton = new JButton("Volver a Login");
        loginSwitchButton.setBackground(new Color(243, 212, 142));
        loginSwitchButton.setForeground(Color.WHITE);
        loginSwitchButton.setFocusPainted(false);
        gbc.gridy = 4;
        registerPanel.add(loginSwitchButton, gbc);

        loginSwitchButton.addActionListener(e -> cardLayout.show(parentPanel, "Login"));

        parentPanel.add(registerPanel, "Register");
    }

    private boolean checkCredentials(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM usuario WHERE User_Name = ? AND Password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean registerUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO usuario (User_Name, Password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario loginWindow = new Usuario();
            loginWindow.setVisible(true);
        });
    }
}
