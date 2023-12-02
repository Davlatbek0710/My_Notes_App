import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AccountManager {
    private static String username;
    private static String password;
    public static boolean checkAccount(String username, String password) {
        AccountManager.username = username;
        AccountManager.password = password;
        try{
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement st = conn.prepareStatement("insert into users values(?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "This account already registered", "INFO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}
