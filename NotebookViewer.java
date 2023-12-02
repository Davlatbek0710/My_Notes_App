import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javax.swing.*;

public class NotebookViewer {

    // Array to store titles and buttons
    public static String[] titles = new String[20];
    public static Button[] buttons = new Button[20];
    public static String TITLEOFNOTEBOOK;

    // Open method to create and display the UI for viewing notebooks
    public static BorderPane Open(String app_title) {
        TITLEOFNOTEBOOK = app_title;

        // Create new BorderPane object
        BorderPane border = new BorderPane();




        // Create UI components
        Label label = new Label();
        if (app_title != null) {
            label.setText(app_title);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 30");
        }
//        label.setStyle("-fx-font-fill: white;");

        // Create a StackPane and add buttons and labels
        StackPane Top = new StackPane();
        Top.setPrefHeight(100);
        Top.setPadding(new Insets(0, 30, 0, 30));

        // Add components to the StackPane
        Top.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);

        // Add StackPane layout to the top of BorderPane
        border.setTop(Top);

        // Create GridPane and other buttons for Task Notes;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        // Load the titles
        DatabaseInteractionManager.Load_Data();
        int number_of_buttons = 0;
        for (int i = 0; i < DatabaseInteractionManager.titles.length; i++) {
            if (DatabaseInteractionManager.titles[i] != null) {
                number_of_buttons++;

                // Initialize the button and set its properties
                buttons[i] = new Button();
                buttons[i].setText(DatabaseInteractionManager.titles[i]);

                // Add buttons to GridPane;
                buttons[i].setMaxWidth(180);
                grid.add(buttons[i], 0, i);
                StylesManager.SetButtonsStyle(buttons[i]);

                // Set action listener for each button to open the corresponding note
                int finalI = i;
                buttons[i].setOnAction(e -> {
                    Main.replaceSceneContent(ReadHandler.Open(finalI, buttons[finalI].getText(),
                            DatabaseInteractionManager.date_of_creation[finalI]));
                });
            } else {
                break;
            }
        }

        // Adding delete button on the top right side of the border pane
//        if (number_of_buttons > 0) {
//            Button delete_btn = new Button("Delete");
//            Top.getChildren().add(delete_btn);
//            StackPane.setAlignment(delete_btn, Pos.CENTER_RIGHT);
//        }

        // Create "Create New" button
        Button add_btn = new Button("+Create New");

        // Add "Create New" button to the GridPane
        grid.add(add_btn, 0, number_of_buttons);

        // Create ColumnConstraints
        ColumnConstraints cc = new ColumnConstraints();
        grid.getColumnConstraints().add(cc);
        cc.setPrefWidth(180);

        // Set all buttons to their horizontal max size
        add_btn.setMaxWidth(180);

        // Place the grid to the center of the border pane
        BorderPane.setAlignment(grid, Pos.TOP_CENTER);
        border.setCenter(grid);

        // Create a log-out button at the bottom of the border pane
        Button log_out = new Button("Log out");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(log_out);
        StackPane.setAlignment(log_out, Pos.CENTER_LEFT);
        StackPane.setMargin(log_out, new Insets(25, 25, 25, 25));

        // Add log-out button to the bottom of the border pane
        border.setBottom(stackPane);

        // Set action listener for log-out button
        log_out.setOnAction(e -> {
            DatabaseInteractionManager.EmptySetOfTitles();
            int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response !=1 && response!=-1){
                Main.replaceSceneContent(RegistrationManager.createRegistrationPage());
            }
        });

        // Set action listener for "Create New" button
        add_btn.setOnAction(e -> {
            if (DatabaseInteractionManager.Create_Note()) {
                Main.replaceSceneContent(NotebookViewer.Open("My Notes"));
            }
        });

        // Set styles of all components
        StylesManager.SetSceneBackground(border);
        StylesManager.SetButtonsStyle(add_btn);
        StylesManager.SetButtonsStyle(log_out);

        // Return the constructed BorderPane
        return border;
    }
}
