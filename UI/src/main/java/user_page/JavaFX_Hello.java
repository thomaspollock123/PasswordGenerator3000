package user_page;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
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
            VBox Top = createVBox("Top");
            VBox Bottom = createVBox("Bottom");

            // Create a GridPane and place VBoxes in four corners
            GridPane grid = new GridPane();
            grid.setHgap(10); // Horizontal gap between VBoxes
            grid.setVgap(10); // Vertical gap between VBoxes
            grid.setPadding(new Insets(0, 0, 0, 10));

            // Place VBoxes in four corners of the grid
            grid.add(Top, 0, 0);
            grid.add(Bottom, 0, 1);
            grid.setAlignment(Pos.BASELINE_LEFT);

            Scene scene = new Scene(grid, 400, 200);
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
        TextField t = new TextField();
        vbox.getChildren().addAll(l, t, b);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
