import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javax.swing.JOptionPane;

public class RegistrationManager {
    public static BorderPane createSign_Up_In_Page(String up_or_in){
        BorderPane border = new BorderPane();

        // Create the GridPane for the layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create UI components
        TextField usernameField = new TextField();
        usernameField.setScaleX(1.075);
        usernameField.setPromptText("username");
//        usernameField.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 1.5px; -fx-border-radius: 10px; -fx-text-fill: white;"+
//                "-fx-font-size: 14px; -fx-prompt-text-fill: white;");


        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        passwordField.setScaleX(1.075);
//        passwordField.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 1.5px; -fx-border-radius: 10px; -fx-text-fill: white;"+
//                "-fx-font-size: 14px;" +
//                "-fx-prompt-text-fill: white;");


        Button registerButton = new Button(up_or_in);
        registerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");


        grid.add(usernameField, 0, 0);
        grid.add(passwordField, 0, 1);
        grid.add(registerButton, 0, 2);

        // Create Column constrains
        ColumnConstraints cc = new ColumnConstraints();
        grid.getColumnConstraints().add(cc);

        cc.setPrefWidth(180);

        usernameField.setMaxWidth(180);
        passwordField.setMaxWidth(180);
        registerButton.setMaxWidth(90);

        // Place the grid to the center of the BorderPane object
        border.setCenter(grid);
        BorderPane.setAlignment(grid, Pos.CENTER);

        // verification of username and password
//        AuthenticationManager.signUp(usernameField.getText(), passwordField.getText());

        if (up_or_in.equals("SignIn")){
        registerButton.setOnAction(e->{
            if(AuthenticationManager.signUp(usernameField.getText(), passwordField.getText())){
                Main.replaceSceneContent(NotebookViewer.Open("My Notes"));
            }
            else{
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect\n" +
                        "Try Again", "INFO", JOptionPane.ERROR_MESSAGE);
            }
        });
        }
        else{
            registerButton.setOnAction(e->{
                if (AccountManager.checkAccount(usernameField.getText(), passwordField.getText())){
                    JOptionPane.showMessageDialog(null, "Successfully created an Account", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    Main.replaceSceneContent(RegistrationManager.createSign_Up_In_Page("SignIn"));
                }
                else{
                    Main.replaceSceneContent(RegistrationManager.createRegistrationPage());
                }
            });
        }
        // Set styles of all components
        StylesManager.SetSceneBackground(border);
        StylesManager.SetTextFieldStyle(usernameField);
        StylesManager.SetTextFieldStyle(passwordField);
        StylesManager.SetButtonsStyle(registerButton);
        return border;
    }
    public static BorderPane createRegistrationPage(){
        BorderPane border = new BorderPane();

        // Create GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);

        // SignIn SignUp Buttons
        Button SignUp = new Button("SignUp");
        Button SignIn = new Button("SignIn");

        // Add buttons to the grid
        grid.add(SignUp, 0,0);
        grid.add(SignIn, 0,1);

        // Set the grid to the center of the border pane
        border.setCenter(grid);
        BorderPane.setAlignment(grid, Pos.TOP_CENTER);

        //Create column constraints
        ColumnConstraints cc = new ColumnConstraints();
        grid.getColumnConstraints().add(cc);
        cc.setPrefWidth(180);
        cc.setFillWidth(true);
        SignUp.setMaxWidth(180);
        SignIn.setMaxWidth(180);

        //create a label for showing the name of the app in bold letters
        StackPane layout = new StackPane();
        Label label = new Label("My Notes");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 25px");
        label.setTextAlignment(TextAlignment.CENTER);
        layout.setPrefHeight(100);
        layout.getChildren().add(label);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);

        //Add StackPane to the top side of BorderPane object
        border.setTop(layout);

        //Set up Action listeners
        SignUp.setOnAction(e->{
            Main.replaceSceneContent(RegistrationManager.createSign_Up_In_Page(SignUp.getText()));
        });
        SignIn.setOnAction(e->{
            Main.replaceSceneContent(RegistrationManager.createSign_Up_In_Page(SignIn.getText()));
        });

        // Set styles of all components
        StylesManager.SetSceneBackground(border);
        StylesManager.SetButtonsStyle(SignIn);
        StylesManager.SetButtonsStyle(SignUp);

        return border;
    }
}
