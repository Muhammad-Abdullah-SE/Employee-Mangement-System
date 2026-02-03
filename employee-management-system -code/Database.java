package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    // These are public so your other files can access them: db.c or db.s
    public Connection c;
    public Statement s;

    public Database() {
        // Change these details to match your local MySQL setup
        String url = "jdbc:mysql://localhost:3306/employee";
        String username = "root";
        String password = "Pak@76543210";

        try {
            // 1. Explicitly load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the connection
            c = DriverManager.getConnection(url, username, password);

            // 3. Create the statement object for simple queries
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println("CRITICAL ERROR: Could not connect to the database.");
            System.out.println("Check if MySQL Service is running and the password is correct.");
            e.printStackTrace();
        }
    }


    public void closeConnection() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}