import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class StylesManager {
    public static void SetButtonsStyle(Button button){
        button.setStyle("-fx-background-color: #FFCE30; -fx-text-fill: black; -fx-font-size: 14px; ");
    }
    public static void SetSceneBackground(BorderPane borderPane){
//        borderPane.setStyle("-");
        borderPane.setBackground(Background.fill(Color.AZURE));
    }
    public static void SetTextAreaStyle(TextArea textArea){
        textArea.setStyle("-fx-border-width: 2px;"
                + "-fx-border-color: #FFCE30;"
                + "-fx-background-color: Azure;"
                + "-fx-font-size: 15px");
//                + "-fx-font-weight: bold;");
    }
    public static void SetTextFieldStyle(TextField textField) {
        textField.setStyle("-fx-border-width: 1.5px;"
                + "-fx-border-color: #FFCE30;"
                + "-fx-background-color: Azure;"
                + "-fx-font-weight: bold;");
    }
}
