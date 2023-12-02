import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/info";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "INFO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
