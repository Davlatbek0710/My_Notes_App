import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class AuthenticationManager {
    public static String username;
    public static String password;
    public static boolean signUp(String username, String password) {
        AuthenticationManager.username = username;
        AuthenticationManager.password = password;
        try{
            Connection conn = DatabaseConnector.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select pass from users where name='"+username+"'");
            return rs.next() && rs.getString("pass").equals(password);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "INFO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}
