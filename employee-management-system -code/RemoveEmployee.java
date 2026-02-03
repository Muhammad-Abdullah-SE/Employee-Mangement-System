package employee;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    JComboBox<String> cbEmpId;
    JButton delete, back;
    JLabel lblname, lblPhone, lblEmail;

    RemoveEmployee() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Remove Employee");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


        int xOffset = (screenWidth / 2) - 500;
        int yOffset = (screenHeight / 2) - 200;

        JLabel labelempId = new JLabel("Employee ID");
        labelempId.setBounds(xOffset + 50, yOffset + 50, 100, 30);
        add(labelempId);

        cbEmpId = new JComboBox<>();
        cbEmpId.setBounds(xOffset + 200, yOffset + 50, 150, 30);
        add(cbEmpId);

        try {
            Database db = new Database();
            ResultSet rs = db.s.executeQuery("select empId from employee");
            while (rs.next()) {
                cbEmpId.addItem(rs.getString("empId"));
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(xOffset + 50, yOffset + 100, 100, 30);
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(xOffset + 200, yOffset + 100, 200, 30);
        add(lblname);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(xOffset + 50, yOffset + 150, 100, 30);
        add(labelPhone);

        lblPhone = new JLabel();
        lblPhone.setBounds(xOffset + 200, yOffset + 150, 200, 30);
        add(lblPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(xOffset + 50, yOffset + 200, 100, 30);
        add(labelEmail);

        lblEmail = new JLabel();
        lblEmail.setBounds(xOffset + 200, yOffset + 200, 200, 30);
        add(lblEmail);

        refreshLabels();

        cbEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                refreshLabels();
            }
        });


        delete = new JButton("Delete");
        delete.setBounds(xOffset + 50, yOffset + 300, 120, 35);
        delete.addActionListener(this);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setFocusable(false);
        add(delete);

        back = new JButton("Back");
        back.setBounds(xOffset + 200, yOffset + 300, 120, 35);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusable(false);
        add(back);


        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/delete.png"));
            Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel imglbl = new JLabel(i3);
            imglbl.setBounds(xOffset + 400, yOffset, 600, 400);
            add(imglbl);
        } catch (Exception e) {
            System.out.println("Delete image not found.");
        }

        setVisible(true);
    }

    private void refreshLabels() {
        String selectedId = (String) cbEmpId.getSelectedItem();
        if (selectedId == null) return;

        try {
            Database db = new Database();
            String query = "select name, phone, email from employee where empId = ?";
            PreparedStatement pstmt = db.c.prepareStatement(query);
            pstmt.setString(1, selectedId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblPhone.setText(rs.getString("phone"));
                lblEmail.setText(rs.getString("email"));
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                try {
                    Database db = new Database();
                    String query = "delete from employee where empId = ?";
                    PreparedStatement pstmt = db.c.prepareStatement(query);
                    pstmt.setString(1, (String) cbEmpId.getSelectedItem());
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
                    db.closeConnection();
                    this.dispose();
                    new Home();
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
        } else {
            this.dispose();
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}