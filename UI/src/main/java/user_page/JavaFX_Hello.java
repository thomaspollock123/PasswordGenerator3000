package user_page;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFX_Hello extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("This is a Test VBox");

            // Create VBox for each corner
            VBox Top = createVBox(10, "TOP_LEFT", "Password Input", "Press me :D");
            VBox Bottom = createVBox(10, "BOTTOM_LEFT", "Password Generator", "Enter");
            TableView right = new TableView();

            // Create a GridPane and place VBoxes in four corners
            GridPane grid = new GridPane();
            grid.setHgap(10); // Horizontal gap between VBoxes
            grid.setVgap(10); // Vertical gap between VBoxes
            grid.setPadding(new Insets(10));

            // Place VBoxes in four corners of the grid
            grid.add(Top, 0, 0);
            grid.add(Bottom, 0, 1);
            grid.add(right, 1, 0, 1,2);
            grid.setAlignment(Pos.BASELINE_LEFT);

            Scene scene = new Scene(grid, 350, 300);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to create a VBox
    private VBox createVBox(int size, String position, String lab, String but) {
        VBox vbox = new VBox(size);
        Label l = new Label(lab);
        Button b = new Button(but);
        TextField t = new TextField();
        vbox.getChildren().addAll(l, t, b);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
