package user_page;

import database.CRUD;
import database.PasswordObject;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class JavaFX_Hello extends Application {

    PassGen pg = new PassGen();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("This is a Test VBox");

            // Create VBox for each corner
            VBox Top = createVBox(10, "TOP_LEFT", "Password Input", "Press me :D", "Enter");
            VBox Bottom = createVBox(10, "BOTTOM_LEFT", "Password Generator", "Generate", "Enter");

            TableView<PasswordObject> right = new TableView<PasswordObject>();
            TableColumn<PasswordObject, String> column1 = new TableColumn<PasswordObject, String>("owner");
            column1.setCellValueFactory(new PropertyValueFactory<PasswordObject, String>("owner"));
            TableColumn<PasswordObject, String> column2 = new TableColumn<PasswordObject, String>("password");
            column2.setCellValueFactory(new PropertyValueFactory<PasswordObject, String>("password"));

            right.getColumns().add(column1);
            right.getColumns().add(column2);

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
    private VBox createVBox(int size, String position, String lab, String but1, String but2) {
        VBox vbox = new VBox(size);
        Label l = new Label(lab);
        Button b1 = new Button(but1);
        Button b2 = new Button(but2);
        TextField t = new TextField();
        vbox.getChildren().addAll(l, t, b1, b2);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                t.setText(pg.GeneratePassword());
            }
        });
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                t.setText(pg.GeneratePassword());
            }
        });
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
