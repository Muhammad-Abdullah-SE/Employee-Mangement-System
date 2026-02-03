package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {

    JLabel heading;

    Splash() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // 1. Heading - Centered at the top
        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(0, 50, screenWidth, 80);
        heading.setFont(new Font("serif", Font.BOLD, 60));
        heading.setForeground(new Color(30, 144, 255));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // 2. Click Here To Continue Button - Moved "very little more down"
        JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
        // Moved from -120 to -100 to bring it lower on the screen
        clickhere.setBounds((screenWidth / 2) - 150, screenHeight - 100, 300, 60);
        clickhere.setBackground(Color.BLACK);
        clickhere.setForeground(Color.WHITE);
        clickhere.setFont(new Font("Arial", Font.BOLD, 16));
        clickhere.setFocusable(false);
        clickhere.addActionListener(this);
        add(clickhere);

        // 3. Background Image - Scaled to stop right before the button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/front.jpg"));
        // Image height adjusted to ensure the button has its own clear space
        Image i2 = i1.getImage().getScaledInstance(screenWidth, screenHeight - 250, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 130, screenWidth, screenHeight - 250);
        add(image);

        setVisible(true);

        // Blinking Thread logic
        new Thread(() -> {
            try {
                while(true) {
                    heading.setVisible(false);
                    Thread.sleep(500);
                    heading.setVisible(true);
                    Thread.sleep(500);
                }
            } catch (Exception e) {}
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Splash();
    }
}