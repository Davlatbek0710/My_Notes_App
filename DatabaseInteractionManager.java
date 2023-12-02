import javax.swing.JOptionPane;
import java.sql.*;

public class DatabaseInteractionManager {
    // Variables to store data
    public static ResultSet rs;
    public static String[] texts = new String[20];
    public static String[] titles = new String[20];
    public static String[] date_of_creation = new String[20];

    // Get the current date
    public static String date = DateTimeUtil.getTime();

    // Database connection and statement
    public static Connection conn;
    public static Statement st;

    // Load data from the database
    public static void Load_Data() {
        try {
            // Establish a database connection
            conn = DatabaseConnector.getConnection();
            st = conn.createStatement();

            // Execute a query to get data from the database
            rs = st.executeQuery("select title, texts, date_of_creation from textnotes where name='" + AuthenticationManager.username + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Process the results
        int i = 0;
        try {
            while (rs.next()) {
                titles[i] = rs.getString(1);
                texts[i] = rs.getString(2);
                date_of_creation[i] = rs.getString(3);
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Print titles to the console (for testing)
//        for (String title : titles) {
//            if (!(title == null)) {
//                System.out.println(title);
//            } else {
//                break;
//            }
//        }
    }

    // Update data in the database
    public static void Update_Data(String text) {
            try {
                if (text.equals(DatabaseInteractionManager.texts[ReadHandler.index])){
                // Execute an update query to modify data in the database
                    st.executeUpdate("update textnotes set texts='" + text + "'"+
                            "where title='" + DatabaseInteractionManager.titles[ReadHandler.index] + "' and name='" + AuthenticationManager.username + "'"+
                            "and date_of_creation='"+DatabaseInteractionManager.texts[ReadHandler.index]+"'");
                }
                else{
                    st.executeUpdate("update textnotes set texts='" + text + "'"+", date_of_creation='"+date+"'"+
                            "where title='" + DatabaseInteractionManager.titles[ReadHandler.index] + "' and name='" + AuthenticationManager.username + "'");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    // Create a new note in the database
    public static boolean Create_Note() {
        try {
            String title;
            while (true) {
                // Prompt the user to enter a title
                title = JOptionPane.showInputDialog("Enter title:");

                // Check if the user clicked "Cancel" or entered an empty string
                if (title == null) {
                    return false; // User clicked "Cancel"
                }
                if (title.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Specify a valid title!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // User entered a valid title, proceed to update the database

                    // Execute an insert query to add new data to the database
                    PreparedStatement preparedStatement = conn.prepareStatement("insert into textnotes values(?,?,?,?)");
                    preparedStatement.setString(1, AuthenticationManager.username);
                    preparedStatement.setString(2, title);
                    preparedStatement.setString(3, "");
                    preparedStatement.setString(4, date);
                    preparedStatement.executeUpdate();

                    return true; // Successfully created a new note
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "INFO", JOptionPane.ERROR_MESSAGE);
            return false; // Failed to create a new note
        }
    }
    public static void Delete_Note(String title, String text){
        EmptySetOfTitles();
        try{
            Statement delete_statement = conn.createStatement();
            delete_statement.executeUpdate("delete from textnotes where name='" +
                    AuthenticationManager.username+"' and title='"+title+"'" +
                    " and texts='"+text+"'");
            System.out.println("Successfully deleted");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void EmptySetOfTitles(){
        // Before deleting first set all array data strings to null;
        for (int i = 0; i <= 2 && i < titles.length; i++) {
            if (titles[i]!=null){
                titles[i] = null;
                texts[i] = null;
                date_of_creation[i] = null;
            }
            else
                break;
        }
    }
}
