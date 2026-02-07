package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Splash extends JFrame implements ActionListener {

    // Constants
    private static final int BLINK_INTERVAL_MS = 500;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private static final int BUTTON_BOTTOM_MARGIN = 100;
    private static final int IMAGE_BOTTOM_MARGIN = 250;

    // UI Components
    private JLabel heading;
    private JButton continueButton;

    public Splash() {
        initializeFrame();
        initializeComponents();
        startHeadingAnimation();
    }

    private void initializeFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
    }

    private void initializeComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        createHeading(screenWidth);
        createContinueButton(screenWidth, screenHeight);
        createBackgroundImage(screenWidth, screenHeight);
    }


    private void createHeading(int screenWidth) {
        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(0, 50, screenWidth, 80);
        heading.setFont(new Font("serif", Font.BOLD, 60));
        heading.setForeground(new Color(30, 144, 255));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);
    }


    private void createContinueButton(int screenWidth, int screenHeight) {
        continueButton = new JButton("CLICK HERE TO CONTINUE");

        int buttonX = (screenWidth / 2) - (BUTTON_WIDTH / 2);
        int buttonY = screenHeight - BUTTON_BOTTOM_MARGIN;

        continueButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));
        continueButton.setFocusable(false);
        continueButton.addActionListener(this);
        add(continueButton);
    }


    private void createBackgroundImage(int screenWidth, int screenHeight) {
        try {
            ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("front.jpg"));
            int imageHeight = screenHeight - IMAGE_BOTTOM_MARGIN;

            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    screenWidth, imageHeight, Image.SCALE_SMOOTH
            );

            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(0, 130, screenWidth, imageHeight);
            add(imageLabel);
        } catch (Exception e) {
            System.err.println("Warning: Could not load splash image 'front.jpg'");
        }
    }

    /**
     * Starts the blinking animation for the heading
     */
    private void startHeadingAnimation() {
        new Thread(() -> {
            try {
                while (true) {
                    heading.setVisible(false);
                    Thread.sleep(BLINK_INTERVAL_MS);
                    heading.setVisible(true);
                    Thread.sleep(BLINK_INTERVAL_MS);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == continueButton) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Splash());
    }
}