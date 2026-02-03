package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton add, view, update, remove;

    Home() {
        // 1. Full Screen Logic
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Home Page - Employee Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Get Dynamic Screen Dimensions
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // 2. Background Image - Scaled to Full Screen
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, screenWidth, screenHeight);
        add(image);

        // 3. Heading - Repositioned for Full Screen
        JLabel heading = new JLabel("Employee Management System");
        heading.setFont(new Font("Raleway", Font.BOLD, 40)); // Increased font size
        heading.setBounds(screenWidth - 700, 50, 650, 60);
        heading.setForeground(Color.BLACK);
        image.add(heading); // Adding to image label to show over background

        // 4. Buttons - Adjusted Width, Height, and Placement
        // Grouping buttons on the right side as per original design
        int buttonX = screenWidth - 700;

        add = new JButton("Add Employee");
        add.addActionListener(this);
        add.setFocusable(false);
        add.setFont(new Font("Arial", Font.BOLD, 16));
        add.setBounds(buttonX, 150, 200, 50); // Increased width and height
        image.add(add);

        view = new JButton("View Employees");
        view.addActionListener(this);
        view.setFocusable(false);
        view.setFont(new Font("Arial", Font.BOLD, 16));
        view.setBounds(buttonX + 250, 150, 200, 50);
        image.add(view);

        update = new JButton("Update Employee");
        update.addActionListener(this);
        update.setFocusable(false);
        update.setFont(new Font("Arial", Font.BOLD, 16));
        update.setBounds(buttonX, 230, 200, 50);
        image.add(update);

        remove = new JButton("Remove Employee");
        remove.addActionListener(this);
        remove.setFocusable(false);
        remove.setFont(new Font("Arial", Font.BOLD, 16));
        remove.setBounds(buttonX + 250, 230, 200, 50);
        image.add(remove);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose(); // Logic: Close home screen before opening next
        if (e.getSource() == add) {
            new AddEmployee();
        } else if (e.getSource() == view) {
            new ViewEmployee();
        } else if (e.getSource() == update) {
            new ViewEmployee(); // Selection usually happens here
        } else if (e.getSource() == remove) {
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}