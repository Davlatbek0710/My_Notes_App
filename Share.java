import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Share {
    public static BorderPane SetUpUI(){
        //Create new BorderPane object
        BorderPane border = new BorderPane();

        // Create UI components
        Button back_btn = new Button("Back");
        Label label = new Label("Share");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 30");


        //Create a StackPane and add all to buttons and labels
        StackPane Top = new StackPane();
        Top.setPrefHeight(100);
        Top.setPadding(new Insets(0,30,0,30));

        //Add components
        Top.getChildren().add(back_btn);
        StackPane.setAlignment(back_btn, Pos.CENTER_LEFT);
        Top.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);

        // Add StackPane layout to the top of BorderPane
        border.setTop(Top);

        // Create GridPane and other buttons for Task Notes;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setGridLinesVisible(false);

        //Buttons
        Button share = new Button("User 1");
        Button share1 = new Button("User 2");
        Button add_btn = new Button("+Search");

        // Add buttons to GridPane;
        grid.add(share, 0,0);
        grid.add(share1, 0,1);
        grid.add(add_btn, 0,2);

        //Create ColumnConstraints
        ColumnConstraints cc = new ColumnConstraints();
        grid.getColumnConstraints().add(cc);
        cc.setPrefWidth(180);

        // Set all buttons to their horizontal max size
        share.setMaxWidth(180);
        share1.setMaxWidth(180);
        add_btn.setMaxWidth(180);


        // Place the grid to the center to of the border pane
        BorderPane.setAlignment(grid, Pos.TOP_CENTER);
        border.setCenter(grid);

        back_btn.setOnAction(e->{
            Main.replaceSceneContent(ReadHandler.Open(ReadHandler.index, "My Notes", DatabaseInteractionManager.date_of_creation[ReadHandler.index]));
        });


        return border;

    }
}
