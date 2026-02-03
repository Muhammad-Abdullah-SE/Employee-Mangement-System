package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton login, back; // Defined at class level for easier access

    Login() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Login - Employee Management System");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // 1. Logic to calculate the Center of the Screen
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        int x = (screenWidth / 2) - 300;
        int y = (screenHeight / 2) - 150;

        // 2. Username Section
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(x + 50, y + 35, 100, 35);
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Slightly larger font
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(x + 160, y + 35, 180, 35); // Wider and taller field
        add(userField);

        // 3. Password Section
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(x + 50, y + 85, 100, 35);
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(x + 160, y + 85, 180, 35);
        add(passField);

        // 4. LOGIN Button - Adjusted size and width
        login = new JButton("LOGIN");
        // Increased width to 140 and height to 45 for better visibility
        login.setBounds(x + 50, y + 160, 140, 45);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.addActionListener(this);
        login.setFocusable(false);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);
        add(login);

        // 5. BACK Button - For better navigation
        back = new JButton("BACK");
        back.setBounds(x + 210, y + 160, 140, 45);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.addActionListener(this);
        back.setFocusable(false);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        add(back);

        // 6. Sidebar Image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
            Image i2 = i1.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(x + 450, y + 5, 200, 200);
            add(image);
        } catch (Exception e) {
            System.out.println("Login image 'second.jpg' not found.");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Username and Password");
                return;
            }

            try {
                Database db = new Database();
                String query = "SELECT * FROM information WHERE username = ? AND password = ?";
                PreparedStatement pstmt = db.c.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    db.closeConnection();
                    this.dispose();
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password");
                    db.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            this.dispose();
            new Splash();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}