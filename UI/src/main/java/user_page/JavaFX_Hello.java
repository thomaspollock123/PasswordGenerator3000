package user_page;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFX_Hello extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("This is a Test VBox");

            // Create VBox for each corner
            VBox topLeft = createVBox("Top Left");
            VBox topRight = createVBox("Top Right");
            VBox bottomLeft = createVBox("Bottom Left");
            VBox bottomRight = createVBox("Bottom Right");

            // Create a GridPane and place VBoxes in four corners
            GridPane grid = new GridPane();
            grid.setHgap(20); // Horizontal gap between VBoxes
            grid.setVgap(20); // Vertical gap between VBoxes

            // Place VBoxes in four corners of the grid
            grid.add(topLeft, 0, 0);
            grid.add(topRight, 1, 0);
            grid.add(bottomLeft, 0, 1);
            grid.add(bottomRight, 1, 1);
            grid.setAlignment(Pos.CENTER);

            Scene scene = new Scene(grid, 200, 200);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to create a VBox
    private VBox createVBox(String labelText) {
        VBox vbox = new VBox(10);
        Label l = new Label("This is " + labelText);
        Button b = new Button("Press me :D");
        vbox.getChildren().addAll(l, b);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
