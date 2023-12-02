import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String getTime(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a format for displaying the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

        return currentDateTime.format(formatter);
    }
}
