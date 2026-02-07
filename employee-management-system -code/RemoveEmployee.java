package employee;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


public class RemoveEmployee extends JFrame implements ActionListener {

    // Constants
    private static final int FORM_WIDTH = 1000;
    private static final int FORM_HEIGHT = 400;

    // UI Components
    private JComboBox<String> employeeIdComboBox;
    private JButton deleteButton;
    private JButton backButton;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;


    public RemoveEmployee() {
        initializeFrame();
        initializeComponents();
        loadEmployeeIds();
        displayEmployeeInfo();
    }

    private void initializeFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Remove Employee");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }


    private void initializeComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xOffset = (screenSize.width / 2) - (FORM_WIDTH / 2);
        int yOffset = (screenSize.height / 2) - (FORM_HEIGHT / 2);

        createEmployeeSelectionSection(xOffset, yOffset);
        createEmployeeInfoSection(xOffset, yOffset);
        createActionButtons(xOffset, yOffset);
        createDecorativeImage(xOffset, yOffset);
    }

    private void createEmployeeSelectionSection(int x, int y) {
        JLabel labelEmpId = new JLabel("Employee ID");
        labelEmpId.setBounds(x + 50, y + 50, 100, 30);
        add(labelEmpId);

        employeeIdComboBox = new JComboBox<>();
        employeeIdComboBox.setBounds(x + 200, y + 50, 150, 30);
        employeeIdComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    displayEmployeeInfo();
                }
            }
        });
        add(employeeIdComboBox);
    }

    private void createEmployeeInfoSection(int x, int y) {
        // Name
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(x + 50, y + 100, 100, 30);
        add(labelName);

        nameLabel = new JLabel();
        nameLabel.setBounds(x + 200, y + 100, 200, 30);
        add(nameLabel);

        // Phone
        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(x + 50, y + 150, 100, 30);
        add(labelPhone);

        phoneLabel = new JLabel();
        phoneLabel.setBounds(x + 200, y + 150, 200, 30);
        add(phoneLabel);

        // Email
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(x + 50, y + 200, 100, 30);
        add(labelEmail);

        emailLabel = new JLabel();
        emailLabel.setBounds(x + 200, y + 200, 200, 30);
        add(emailLabel);
    }


    private void createActionButtons(int x, int y) {
        // Delete button
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(x + 50, y + 300, 120, 35);
        deleteButton.addActionListener(this);
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusable(false);
        add(deleteButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(x + 200, y + 300, 120, 35);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        add(backButton);
    }


    private void createDecorativeImage(int x, int y) {
        try {
            ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(x + 400, y, 600, 400);
            add(imageLabel);
        } catch (Exception e) {
            System.err.println("Warning: Could not load delete image 'delete.png'");
        }
    }

    /**
     * Loads all employee IDs from database into the dropdown
     */
    private void loadEmployeeIds() {
        Database db = null;
        ResultSet rs = null;

        try {
            db = new Database();
            rs = db.s.executeQuery("SELECT empId FROM employee");

            while (rs.next()) {
                employeeIdComboBox.addItem(rs.getString("empId"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading employee IDs");
            e.printStackTrace();
        } finally {
            closeResources(db, rs);
        }
    }

    /**
     * Displays employee information based on selected ID
     */
    private void displayEmployeeInfo() {
        String selectedId = (String) employeeIdComboBox.getSelectedItem();

        if (selectedId == null || selectedId.isEmpty()) {
            clearEmployeeInfo();
            return;
        }

        loadEmployeeDetails(selectedId);
    }

    /**
     * Loads employee details from database and displays them
     */
    private void loadEmployeeDetails(String empId) {
        Database db = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            db = new Database();
            String query = "SELECT name, phone, email FROM employee WHERE empId = ?";
            pstmt = db.c.prepareStatement(query);
            pstmt.setString(1, empId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
                phoneLabel.setText(rs.getString("phone"));
                emailLabel.setText(rs.getString("email"));
            } else {
                clearEmployeeInfo();
            }
        } catch (SQLException e) {
            System.err.println("Error loading employee details");
            e.printStackTrace();
            clearEmployeeInfo();
        } finally {
            closePreparedStatementResources(db, pstmt, rs);
        }
    }

    /**
     * Clears all employee information labels
     */
    private void clearEmployeeInfo() {
        nameLabel.setText("");
        phoneLabel.setText("");
        emailLabel.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            handleDelete();
        } else if (e.getSource() == backButton) {
            handleBack();
        }
    }


    private void handleDelete() {
        String selectedId = (String) employeeIdComboBox.getSelectedItem();

        if (selectedId == null || selectedId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an employee to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this employee record?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            deleteEmployee(selectedId);
        }
    }


    private void deleteEmployee(String empId) {
        Database db = null;
        PreparedStatement pstmt = null;

        try {
            db = new Database();
            String query = "DELETE FROM employee WHERE empId = ?";
            pstmt = db.c.prepareStatement(query);
            pstmt.setString(1, empId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this,
                        "Employee Deleted Successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Home();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Employee not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Database Error: Could not delete employee",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            closePreparedStatementResources(db, pstmt, null);
        }
    }


    private void handleBack() {
        this.dispose();
        new Home();
    }


    private void closeResources(Database db, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (db != null) db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void closePreparedStatementResources(Database db, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (db != null) db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoveEmployee());
    }
}