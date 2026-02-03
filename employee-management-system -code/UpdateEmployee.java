package employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField nameField, dobField, aadharField, empIdField;
    JTextField educatioField, fnameField, salaryField, addressField, phoneField, emailField, designationField;
    JButton update, backbtn;
    String empId;

    UpdateEmployee(String empId) {
        this.empId = empId;

        // 1. Full Screen Logic
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Employee Management System - Update Employee");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // 2. Center Calculation Logic
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Offset based on original design width (~950) and height (~700)
        int xOffset = (screenWidth / 2) - 475;
        int yOffset = (screenHeight / 2) - 350;

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(xOffset + 305, yOffset + 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 35));
        add(heading);

        // --- Row 1: Name & Father's Name ---
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(xOffset + 60, yOffset + 150, 150, 30);
        nameLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(xOffset + 190, yOffset + 150, 150, 30);
        nameField.setEditable(false);
        nameField.setBackground(new Color(245, 245, 245));
        add(nameField);

        JLabel fnameLabel = new JLabel("Father's Name");
        fnameLabel.setBounds(xOffset + 470, yOffset + 150, 150, 30);
        fnameLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(fnameLabel);

        fnameField = new JTextField();
        fnameField.setBounds(xOffset + 660, yOffset + 150, 150, 30);
        add(fnameField);

        // --- Row 2: DOB & Salary ---
        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(xOffset + 60, yOffset + 210, 150, 30);
        dobLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(xOffset + 190, yOffset + 210, 150, 30);
        dobField.setEditable(false);
        dobField.setBackground(new Color(245, 245, 245));
        add(dobField);

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setBounds(xOffset + 470, yOffset + 210, 150, 30);
        salaryLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(xOffset + 660, yOffset + 210, 150, 30);
        add(salaryField);

        // --- Row 3: Address & Phone ---
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(xOffset + 60, yOffset + 280, 150, 30);
        addressLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
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

        // --- Row 4: Email & Education ---
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

        educatioField = new JTextField();
        educatioField.setBounds(xOffset + 660, yOffset + 350, 150, 30);
        add(educatioField);

        // --- Row 5: Designation & Aadhar ---
        JLabel designationLabel = new JLabel("Designation");
        designationLabel.setBounds(xOffset + 60, yOffset + 420, 150, 30);
        designationLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(xOffset + 190, yOffset + 420, 150, 30);
        add(designationField);

        JLabel aadharLabel = new JLabel("Aadhar Number");
        aadharLabel.setBounds(xOffset + 470, yOffset + 420, 150, 30);
        aadharLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(xOffset + 660, yOffset + 420, 150, 30);
        aadharField.setEditable(false);
        aadharField.setBackground(new Color(245, 245, 245));
        add(aadharField);

        // --- Row 6: Employee ID (Locked) ---
        JLabel empIdLabel = new JLabel("Employee ID");
        empIdLabel.setBounds(xOffset + 60, yOffset + 490, 150, 30);
        empIdLabel.setFont(new Font("SERIF", Font.PLAIN, 20));
        add(empIdLabel);

        empIdField = new JTextField(empId);
        empIdField.setBounds(xOffset + 190, yOffset + 490, 150, 30);
        empIdField.setEditable(false);
        empIdField.setBackground(new Color(245, 245, 245));
        empIdField.setFont(new Font("SERIF", Font.BOLD, 20));
        add(empIdField);

        // --- BUTTONS ---
        update = new JButton("Update Details");
        update.setBounds(xOffset + 250, yOffset + 570, 150, 40);
        update.addActionListener(this);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setFocusable(false);
        add(update);

        backbtn = new JButton("Back");
        backbtn.setBounds(xOffset + 470, yOffset + 570, 150, 40);
        backbtn.addActionListener(this);
        backbtn.setBackground(Color.BLACK);
        backbtn.setForeground(Color.WHITE);
        backbtn.setFocusable(false);
        add(backbtn);

        // Data Fetching Logic
        try {
            Database db = new Database();
            String query = "SELECT * FROM employee WHERE empId = ?";
            PreparedStatement pstmt = db.c.prepareStatement(query);
            pstmt.setString(1, empId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                nameField.setText(rs.getString("name"));
                fnameField.setText(rs.getString("fname"));
                dobField.setText(rs.getString("dob"));
                salaryField.setText(rs.getString("salary"));
                addressField.setText(rs.getString("address"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
                educatioField.setText(rs.getString("education"));
                designationField.setText(rs.getString("designation"));
                aadharField.setText(rs.getString("aadhar"));
            }
            db.closeConnection();
        } catch (Exception e) { e.printStackTrace(); }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            try {
                Database db = new Database();
                String query = "UPDATE employee SET fname=?, salary=?, address=?, phone=?, email=?, education=?, designation=? WHERE empId=?";
                PreparedStatement pstmt = db.c.prepareStatement(query);

                pstmt.setString(1, fnameField.getText());
                pstmt.setString(2, salaryField.getText());
                pstmt.setString(3, addressField.getText());
                pstmt.setString(4, phoneField.getText());
                pstmt.setString(5, emailField.getText());
                pstmt.setString(6, educatioField.getText());
                pstmt.setString(7, designationField.getText());
                pstmt.setString(8, empId);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Details updated successfully!");
                db.closeConnection();
                this.dispose();
                new Home();
            } catch (Exception ex) { ex.printStackTrace(); }
        } else {
            this.dispose();
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}