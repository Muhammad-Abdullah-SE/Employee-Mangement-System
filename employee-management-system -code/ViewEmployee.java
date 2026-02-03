package employee;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice employeeIdChoice;
    JButton search, print, update, back;

    ViewEmployee() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("View Employee Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


        int xOffset = (screenWidth / 2) - 450; // Centering based on the control group width

        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(xOffset + 20, 20, 180, 20);
        searchlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(searchlbl);

        employeeIdChoice = new Choice();
        employeeIdChoice.setBounds(xOffset + 210, 20, 150, 20);
        add(employeeIdChoice);

        try {
            Database db = new Database();
            ResultSet rs = db.s.executeQuery("select empId from employee");
            while(rs.next()) {
                employeeIdChoice.add(rs.getString("empId"));
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        fetchData();


        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 120, screenWidth, screenHeight - 200);
        add(jsp);


        search = new JButton("Search");
        search.setBounds(xOffset + 20, 70, 100, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(xOffset + 140, 70, 100, 30);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(xOffset + 260, 70, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(xOffset + 380, 70, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void fetchData() {
        try {
            Database db = new Database();
            ResultSet rs = db.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from employee where empId = ?";
            try {
                Database db = new Database();
                PreparedStatement pstmt = db.c.prepareStatement(query);
                pstmt.setString(1, employeeIdChoice.getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                // Note: Keep connection open until model is set if necessary
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            this.dispose();

            new UpdateEmployee(employeeIdChoice.getSelectedItem());
        } else {
            this.dispose();
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}