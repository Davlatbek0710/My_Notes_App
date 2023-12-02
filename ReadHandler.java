import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;

public class ReadHandler {
    public static int index;
    public static String text_content;

    // Open method to create and display the UI for reading a note
    public static BorderPane Open(int i, String name_of_notebook, String last_update) {
        index = i;
        BorderPane border = new BorderPane();

        // Create UI components
        Button back_btn = new Button("Back");
        Label label = new Label(name_of_notebook);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        Button delete_btn = new Button("Delete");

        // Create a StackPane and add buttons and labels
        StackPane Top = new StackPane();
        Top.setPrefHeight(100);
        Top.setPadding(new Insets(0, 30, 0, 30));

        // Add components to the StackPane
        Top.getChildren().add(back_btn);
        StackPane.setAlignment(back_btn, Pos.CENTER_LEFT);
        Top.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
        Top.getChildren().add(delete_btn);
        StackPane.setAlignment(delete_btn, Pos.CENTER_RIGHT);

        // Add StackPane layout to the top of BorderPane
        border.setTop(Top);

        // Create TextArea and place it in the center of the BorderPane
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-font-size: 15px;");

        textArea.setText(DatabaseInteractionManager.texts[index]);

        // Create a StackPane for setting margins and place TextArea on it
        StackPane stack = new StackPane();
        stack.getChildren().add(textArea);
        StackPane.setMargin(textArea, new Insets(25, 25, 25, 25));

        // Add to the center of the BorderPane
        border.setCenter(stack);

        // Share button to be placed in the bottom left side of the BorderPane
        StackPane bottom_label = new StackPane();
        BorderPane.setMargin(bottom_label, new Insets(25, 25, 25, 25));
        Button share = new Button("Share");
        bottom_label.getChildren().add(share);
        Label label1 = new Label();
        label1.setText("Last update: " + last_update);
        bottom_label.getChildren().add(label1);
        StackPane.setAlignment(label1, Pos.CENTER_RIGHT);
        label1.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 15px");
        StackPane.setAlignment(share, Pos.CENTER_LEFT);
        border.setBottom(bottom_label);

        // Event handler for the Share button
        share.setOnAction(e -> {
            Main.replaceSceneContent(Share.SetUpUI());
        });

        // Load old record into the TextArea
//        System.out.println(DatabaseInteractionManager.texts[index]);


        // Event handler for the Back button
        back_btn.setOnAction(e -> {
            String texts = textArea.getText();
            DatabaseInteractionManager.Update_Data(texts);
            Main.replaceSceneContent(NotebookViewer.Open("My notes"));
        });
        // Event handler for the Delete button
        delete_btn.setOnAction(e->{
            int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response !=1 && response!=-1){
                DatabaseInteractionManager.Delete_Note(label.getText(), textArea.getText());
                Main.replaceSceneContent(NotebookViewer.Open("My Notes"));
            }
        });


        //setting styles for all components
        StylesManager.SetButtonsStyle(back_btn);
        StylesManager.SetButtonsStyle(delete_btn);
        StylesManager.SetButtonsStyle(share);
        StylesManager.SetSceneBackground(border);
        StylesManager.SetTextAreaStyle(textArea);


        // Return the constructed BorderPane
        return border;
    }
}
