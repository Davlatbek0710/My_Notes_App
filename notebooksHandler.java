import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;


public class notebooksHandler {
    public static BorderPane createMenu(){
        BorderPane border = new BorderPane();

        // Create one label and place it on the top of Border Layout
        Label label = new Label("Notebooks");
        label.setStyle("-fx-font-weight: bold;");
        label.setScaleX(1.3);
        label.setScaleY(1.3);
        label.setPrefHeight(65);
        border.setTop(label);

        //Placing label to the top bottom side of Grid
        BorderPane.setAlignment(label, Pos.BOTTOM_CENTER);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Create buttons
        Button notebook1 = new Button("notebook 1");
        notebook1.setOnAction(e->{
            Main.replaceSceneContent(NotebookViewer.Open(notebook1.getText()));
        });
        Button notebook2 = new Button("notebook 2");
        Button add_btn = new Button("+Add");

        grid.add(notebook1, 0,0);
        grid.add(notebook2,0,1);
        grid.add(add_btn,0,2);
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.TOP_CENTER);

        //Create column constraints
        ColumnConstraints cC = new ColumnConstraints();
        grid.getColumnConstraints().add(cC);

        cC.setPrefWidth(180);
        notebook1.setMaxWidth(180);
        notebook2.setMaxWidth(180);
        add_btn.setMaxWidth(180);

        // Set up the center of the BorderPane
        border.setCenter(grid);



//        add_btn.setOnAction(e->{
//            int rows = grid.getRowCount()-1;
//            int num_of_buttons = grid.getRowCount();
//
//            String name_of_notebook = JOptionPane.showInputDialog("Enter the name of the notebook: ");
//            Button n = new Button(name_of_notebook);
//            n.setMaxWidth(180);
//
//
////            grid.add(add_btn, 0, rows+1);
//            grid.add(n, 0, rows - 1);
//        });

        return border;
    }
}
