package employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.util.Random;
import java.sql.*;


public class AddEmployee extends JFrame implements ActionListener {

    // Constants
    private static final int FORM_WIDTH = 950;
    private static final int FORM_HEIGHT = 690;
    private static final String[] EDUCATION_OPTIONS = {
            "BBA", "BS", "BTECH", "MA", "MS", "PHD", "MBA", "MSC"
    };

    // Employee ID generation
    private final int employeeId;

    private JTextField nameField;
    private JTextField fatherNameField;
    private JTextField salaryField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField designationField;
    private JTextField aadharField;
    private JTextField empIdField;


    private JDateChooser dobDateChooser;
    private JComboBox<String> educationComboBox;
    private JButton addButton;
    private JButton backButton;


    public AddEmployee() {
        this.employeeId = generateEmployeeId();
        initializeFrame();
        initializeComponents();
    }

    /**
     * Generates a random employee ID
     */
    private int generateEmployeeId() {
        Random random = new Random();
        return random.nextInt(999999);
    }


    private void initializeFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Employee Management System - Add Employee");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }


    private void initializeComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xOffset = (screenSize.width / 2) - (FORM_WIDTH / 2);
        int yOffset = (screenSize.height / 2) - (FORM_HEIGHT / 2);

        createHeading(xOffset, yOffset);
        createFormFields(xOffset, yOffset);
        createButtons(xOffset, yOffset);
    }

    private void createHeading(int x, int y) {
        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(x + 305, y + 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 35));
        add(heading);
    }


    private void createFormFields(int x, int y) {
        createNameFields(x, y);
        createDobAndSalaryFields(x, y);
        createAddressAndPhoneFields(x, y);
        createEmailAndEducationFields(x, y);
        createDesignationAndAadharFields(x, y);
        createEmployeeIdField(x, y);
    }


    private void createNameFields(int x, int y) {
        // Name
        addLabel("Name", x + 60, y + 150);
        nameField = addTextField(x + 190, y + 150);

        // Father's Name
        addLabel("Father's Name", x + 470, y + 150);
        fatherNameField = addTextField(x + 660, y + 150);
    }


    private void createDobAndSalaryFields(int x, int y) {
        // Date of Birth
        addLabel("Date of Birth", x + 60, y + 210);
        dobDateChooser = new JDateChooser();
        dobDateChooser.setBounds(x + 190, y + 210, 150, 30);
        dobDateChooser.setDateFormatString("yyyy-MM-dd");
        add(dobDateChooser);

        // Salary
        addLabel("Salary", x + 470, y + 210);
        salaryField = addTextField(x + 660, y + 210);
    }


    private void createAddressAndPhoneFields(int x, int y) {
        // Address
        addLabel("Address", x + 60, y + 280);
        addressField = addTextField(x + 190, y + 280);

        // Phone
        addLabel("Phone No", x + 470, y + 280);
        phoneField = addTextField(x + 660, y + 280);
    }


    private void createEmailAndEducationFields(int x, int y) {
        // Email
        addLabel("Email", x + 60, y + 350);
        emailField = addTextField(x + 190, y + 350);

        // Education
        addLabel("Highest Education", x + 470, y + 350);
        educationComboBox = new JComboBox<>(EDUCATION_OPTIONS);
        educationComboBox.setBackground(Color.WHITE);
        educationComboBox.setBounds(x + 660, y + 350, 150, 30);
        add(educationComboBox);
    }


    private void createDesignationAndAadharFields(int x, int y) {
        // Designation
        addLabel("Designation", x + 60, y + 420);
        designationField = addTextField(x + 190, y + 420);

        // Aadhar Number
        addLabel("Aadhar Number", x + 470, y + 420);
        aadharField = addTextField(x + 660, y + 420);
    }


    private void createEmployeeIdField(int x, int y) {
        addLabel("Employee ID", x + 60, y + 490);

        empIdField = new JTextField(String.valueOf(employeeId));
        empIdField.setBounds(x + 190, y + 490, 150, 30);
        empIdField.setEditable(false);
        empIdField.setBackground(new Color(245, 245, 245));
        empIdField.setFont(new Font("SERIF", Font.BOLD, 20));
        add(empIdField);
    }


    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(label);
    }


    private JTextField addTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 150, 30);
        add(field);
        return field;
    }


    private void createButtons(int x, int y) {
        // Add Details button
        addButton = new JButton("Add Details");
        addButton.setBounds(x + 250, y + 570, 150, 40);
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        add(addButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(x + 470, y + 570, 150, 40);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        add(backButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            handleAddEmployee();
        } else if (e.getSource() == backButton) {
            handleBack();
        }
    }


    private void handleAddEmployee() {
        if (!validateRequiredFields()) {
            return;
        }

        EmployeeData employeeData = collectEmployeeData();

        if (saveEmployeeToDatabase(employeeData)) {
            JOptionPane.showMessageDialog(this,
                    "Employee Added Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new Home();
        }
    }


    private boolean validateRequiredFields() {
        String name = nameField.getText().trim();
        String dob = ((JTextField) dobDateChooser.getDateEditor().getUiComponent()).getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || dob.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all mandatory fields (Name, DOB, Phone)",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }


    private EmployeeData collectEmployeeData() {
        EmployeeData data = new EmployeeData();
        data.name = nameField.getText().trim();
        data.fatherName = fatherNameField.getText().trim();
        data.dob = ((JTextField) dobDateChooser.getDateEditor().getUiComponent()).getText().trim();
        data.salary = salaryField.getText().trim();
        data.address = addressField.getText().trim();
        data.phone = phoneField.getText().trim();
        data.email = emailField.getText().trim();
        data.education = (String) educationComboBox.getSelectedItem();
        data.designation = designationField.getText().trim();
        data.aadhar = aadharField.getText().trim();
        data.empId = empIdField.getText().trim();
        return data;
    }


    private boolean saveEmployeeToDatabase(EmployeeData data) {
        Database db = null;
        PreparedStatement pstmt = null;

        try {
            db = new Database();
            String query = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = db.c.prepareStatement(query);

            pstmt.setString(1, data.name);
            pstmt.setString(2, data.fatherName);
            pstmt.setString(3, data.dob);
            pstmt.setString(4, data.salary);
            pstmt.setString(5, data.address);
            pstmt.setString(6, data.phone);
            pstmt.setString(7, data.email);
            pstmt.setString(8, data.education);
            pstmt.setString(9, data.designation);
            pstmt.setString(10, data.aadhar);
            pstmt.setString(11, data.empId);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Database error while adding employee");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Database Error: Could not add employee.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            closeResources(db, pstmt);
        }
    }


    private void closeResources(Database db, PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
            if (db != null) db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void handleBack() {
        this.dispose();
        new Home();
    }


    private static class EmployeeData {
        String name;
        String fatherName;
        String dob;
        String salary;
        String address;
        String phone;
        String email;
        String education;
        String designation;
        String aadhar;
        String empId;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEmployee());
    }
}