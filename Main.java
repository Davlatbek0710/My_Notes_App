import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WIDTH = 450;
    public static final int HEIGHT = 650;
    public static Image icon;
    public static Stage PrimaryStage;
    public static Scene scene;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Main.PrimaryStage = stage;
        BorderPane border = RegistrationManager.createRegistrationPage();
        PrimaryStage.setTitle("My Notes");
        icon = new Image(getClass().getResourceAsStream("app_icon.png"));
        Main.PrimaryStage.getIcons().add(icon);
        Main.scene = new Scene(border, 450,650);
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }
    public static void replaceSceneContent(BorderPane newLayout){
        Main.scene = null;
        Main.scene = new Scene(newLayout, WIDTH, HEIGHT);
        Main.PrimaryStage.setScene(Main.scene);
    }
}
