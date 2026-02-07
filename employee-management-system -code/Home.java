package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Home extends JFrame implements ActionListener {

    // Constants
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_SPACING = 80;

    // UI Components
    private JButton addButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton removeButton;


    public Home() {
        initializeFrame();
        initializeComponents();
    }


    private void initializeFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Home Page - Employee Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }


    private void initializeComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        createBackgroundImage(screenWidth, screenHeight);
    }


    private void createBackgroundImage(int screenWidth, int screenHeight) {
        try {
            ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("home.jpg"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    screenWidth, screenHeight, Image.SCALE_SMOOTH
            );
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel backgroundLabel = new JLabel(scaledIcon);
            backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
            add(backgroundLabel);

            addComponentsToBackground(backgroundLabel, screenWidth);

        } catch (Exception e) {
            System.err.println("Warning: Could not load home background image 'home.jpg'");
        }
    }


    private void addComponentsToBackground(JLabel background, int screenWidth) {
        int rightSideX = screenWidth - 700;

        createHeading(background, rightSideX);
        createNavigationButtons(background, rightSideX);
    }


    private void createHeading(JLabel background, int x) {
        JLabel heading = new JLabel("Employee Management System");
        heading.setFont(new Font("Raleway", Font.BOLD, 40));
        heading.setBounds(x, 50, 650, 60);
        heading.setForeground(Color.BLACK);
        background.add(heading);
    }


    private void createNavigationButtons(JLabel background, int x) {
        // Add Employee button
        addButton = createStyledButton("Add Employee", x, 150);
        background.add(addButton);

        // View Employees button
        viewButton = createStyledButton("View Employees", x + 250, 150);
        background.add(viewButton);

        // Update Employee button
        updateButton = createStyledButton("Update Employee", x, 230);
        background.add(updateButton);

        // Remove Employee button
        removeButton = createStyledButton("Remove Employee", x + 250, 230);
        background.add(removeButton);
    }


    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();

        if (e.getSource() == addButton) {
            openAddEmployee();
        } else if (e.getSource() == viewButton) {
            openViewEmployee();
        } else if (e.getSource() == updateButton) {
            openUpdateEmployee();
        } else if (e.getSource() == removeButton) {
            openRemoveEmployee();
        }
    }


    private void openAddEmployee() {
        new AddEmployee();
    }


    private void openViewEmployee() {
        new ViewEmployee();
    }

    private void openUpdateEmployee() {
        new ViewEmployee();
    }


    private void openRemoveEmployee() {
        new RemoveEmployee();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home());
    }
}