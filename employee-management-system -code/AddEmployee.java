package employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener {

    Random rdm = new Random();
    int number = rdm.nextInt(999999);

    JTextField nameField, fnameField, salaryField, addressField, phoneField;
    JTextField emailField, designationField, aadharField, empIdField;
    JDateChooser dcdob;
    JComboBox<String> educationcb;
    JButton add, backbtn;

    AddEmployee() {
        // 1. Full Screen Logic
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Employee Management System - Add Employee");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // 2. Center Calculation Logic
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Form anchor points based on original layout width (~950)
        int xOffset = (screenWidth / 2) - 475;
        int yOffset = (screenHeight / 2) - 345;

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(xOffset + 305, yOffset + 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 35));
        add(heading);

        // --- Row 1 ---
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(xOffset + 60, yOffset + 150, 150, 30);
        nameLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(xOffset + 190, yOffset + 150, 150, 30);
        add(nameField);

        JLabel fnameLabel = new JLabel("Father's Name");
        fnameLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        fnameLabel.setBounds(xOffset + 470, yOffset + 150, 150, 30);
        add(fnameLabel);

        fnameField = new JTextField();
        fnameField.setBounds(xOffset + 660, yOffset + 150, 150, 30);
        add(fnameField);

        // --- Row 2 ---
        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        dobLabel.setBounds(xOffset + 60, yOffset + 210, 150, 30);
        add(dobLabel);

        dcdob = new JDateChooser();
        dcdob.setBounds(xOffset + 190, yOffset + 210, 150, 30);
        dcdob.setDateFormatString("yyyy-MM-dd");
        add(dcdob);

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        salaryLabel.setBounds(xOffset + 470, yOffset + 210, 150, 30);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(xOffset + 660, yOffset + 210, 150, 30);
        add(salaryField);

        // --- Row 3 ---
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        addressLabel.setBounds(xOffset + 60, yOffset + 280, 150, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(xOffset + 190, yOffset + 280, 150, 30);
        add(addressField);

        JLabel phoneLabel = new JLabel("Phone No");
        phoneLabel.setBounds(xOffset + 470, yOffset + 280, 150, 30);
        phoneLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(xOffset + 660, yOffset + 280, 150, 30);
        add(phoneField);

        // --- Row 4 ---
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(xOffset + 60, yOffset + 350, 150, 30);
        emailLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(xOffset + 190, yOffset + 350, 150, 30);
        add(emailField);

        JLabel educationLabel = new JLabel("Highest Education");
        educationLabel.setBounds(xOffset + 470, yOffset + 350, 150, 30);
        educationLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(educationLabel);

        String[] courses = {"BBA", "BS", "BTECH", "MA", "MS", "PHD", "MBA", "MSC"};
        educationcb = new JComboBox<>(courses);
        educationcb.setBackground(Color.WHITE);
        educationcb.setBounds(xOffset + 660, yOffset + 350, 150, 30);
        add(educationcb);

        // --- Row 5 ---
        JLabel designationLabel = new JLabel("Designation");
        designationLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        designationLabel.setBounds(xOffset + 60, yOffset + 420, 150, 30);
        add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(xOffset + 190, yOffset + 420, 150, 30);
        add(designationField);

        JLabel aadharLabel = new JLabel("Aadhar Number");
        aadharLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        aadharLabel.setBounds(xOffset + 470, yOffset + 420, 150, 30);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(xOffset + 660, yOffset + 420, 150, 30);
        add(aadharField);

        // --- Row 6: Auto-Assigned ID Field ---
        JLabel empIdLabel = new JLabel("Employee ID");
        empIdLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        empIdLabel.setBounds(xOffset + 60, yOffset + 490, 150, 30);
        add(empIdLabel);

        empIdField = new JTextField("" + number);
        empIdField.setEditable(false); // Locked for automatic assignment
        empIdField.setBackground(new Color(245, 245, 245));
        empIdField.setFont(new Font("SERIF", Font.BOLD, 20));
        empIdField.setBounds(xOffset + 190, yOffset + 490, 150, 30);
        add(empIdField);

        // --- Buttons ---
        add = new JButton("Add Details");
        add.addActionListener(this);
        add.setFocusable(false);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(xOffset + 250, yOffset + 570, 150, 40);
        add(add);

        backbtn = new JButton("Back");
        backbtn.addActionListener(this);
        backbtn.setFocusable(false);
        backbtn.setForeground(Color.WHITE);
        backbtn.setBackground(Color.BLACK);
        backbtn.setBounds(xOffset + 470, yOffset + 570, 150, 40);
        add(backbtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = nameField.getText();
            String fname = fnameField.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String salary = salaryField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String education = (String) educationcb.getSelectedItem();
            String designation = designationField.getText();
            String aadhar = aadharField.getText();
            String empId = empIdField.getText();

            if (name.isEmpty() || dob.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all mandatory fields (Name, DOB, Phone)");
                return;
            }

            try {
                Database db = new Database();
                String query = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = db.c.prepareStatement(query);

                pstmt.setString(1, name);
                pstmt.setString(2, fname);
                pstmt.setString(3, dob);
                pstmt.setString(4, salary);
                pstmt.setString(5, address);
                pstmt.setString(6, phone);
                pstmt.setString(7, email);
                pstmt.setString(8, education);
                pstmt.setString(9, designation);
                pstmt.setString(10, aadhar);
                pstmt.setString(11, empId);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
                db.closeConnection();
                this.dispose();
                new Home();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: Could not add employee.");
            }
        } else {
            this.dispose();
            new Home();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}