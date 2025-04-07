package user_page;

import database.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX_Hello extends Application {

    CRUD crud = new CRUD();
    PassGen pg = new PassGen();
    PasswordObject passwordObject = new PasswordObject();
    private final ObservableList<PasswordObject> passwordData = FXCollections.observableArrayList();

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
            passwordData.setAll(crud.getAllPasswords());
            right.setItems(passwordData);

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
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        t1.setPromptText("Enter Name");
        t2.setPromptText("Enter Password");
        vbox.getChildren().addAll(l, t1, t2, b1, b2);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                t2.setText(pg.GeneratePassword());
            }
        });
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                String owner = t1.getText();
                String password = t2.getText();
                if (!owner.isEmpty() && !password.isEmpty()) {
                    crud.insertPassword(owner, password);
                    t1.clear();
                    t2.clear();
                    passwordData.setAll(crud.getAllPasswords());
                }
            }
        });
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
