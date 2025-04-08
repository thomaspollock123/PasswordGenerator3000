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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;

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
            Pair<VBox, TextField> topPair = createPasswordInput(10, "TOP_LEFT", "Password Input", "Search", "Insert", "Delete", "Update");
            VBox Top = topPair.getKey();
            TextField t1 = topPair.getValue();
            //VBox Top = createPasswordInput(10, "TOP_LEFT", "Password Input", "Search", "Insert", "Delete", "Update");
            VBox Bottom = createPasswordGenerator(t1);

            TableView<PasswordObject> right = new TableView<PasswordObject>();
            TableColumn<PasswordObject, String> column1 = new TableColumn<PasswordObject, String>("owner");
            column1.setCellValueFactory(new PropertyValueFactory<PasswordObject, String>("owner"));
            TableColumn<PasswordObject, String> column2 = new TableColumn<PasswordObject, String>("password");
            column2.setCellValueFactory(new PropertyValueFactory<PasswordObject, String>("password"));

            right.getColumns().add(column1);
            right.getColumns().add(column2);
            passwordData.setAll(crud.getAllPasswords());
            right.setItems(passwordData);
            right.setPrefWidth(199);

            // Create a GridPane and place VBoxes in four corners
            GridPane grid = new GridPane();
            grid.setHgap(10); // Horizontal gap between VBoxes
            grid.setVgap(10); // Vertical gap between VBoxes
            grid.setPadding(new Insets(10));

            // Place VBoxes in four corners of the grid
            grid.add(Top, 0, 0);
            grid.add(Bottom, 0, 1);
            grid.add(right, 1, 0, 1, 2);
            //grid.add(right, 0, 1, 1,2);
            grid.setAlignment(Pos.BASELINE_LEFT);

            Scene scene = new Scene(grid, 455, 300);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to create a VBox
    private Pair<VBox, TextField> createPasswordInput(int size, String position, String lab, String but1, String but2, String but3, String but4) {
        VBox vbox = new VBox(size);
        Label l = new Label(lab);
        HBox hbox = new HBox(2);
        Button b1 = new Button(but1);
        Button b2 = new Button(but2);
        Button b3 = new Button(but3);
        Button b4 = new Button(but4);
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        t1.setPromptText("Enter Name");
        t2.setPromptText("Enter Password");
        hbox.getChildren().addAll(b1, b2, b3, b4);
        vbox.getChildren().addAll(l, t1, t2, hbox);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                String owner = t1.getText();
                t1.clear();
                t2.clear();
                if (!owner.isEmpty()) {
                    passwordData.setAll(crud.SearchPassword(owner));
                } else {
                    passwordData.setAll(crud.getAllPasswords());
                }
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
        b3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                String owner = t1.getText();
                t1.clear();
                t2.clear();
                if (!owner.isEmpty()) {
                    crud.DeletePassword(owner);
                    passwordData.setAll(crud.getAllPasswords());
                };
            }
        });
        b4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                String owner = t1.getText();
                String password = t2.getText();
                if (!owner.isEmpty() && !password.isEmpty()) {
                    crud.UpdatePassword(owner, password);
                    t1.clear();
                    t2.clear();
                    passwordData.setAll(crud.getAllPasswords());
                }
            }
        });
        return new Pair<>(vbox, t2);
    }

    private VBox createPasswordGenerator(TextField t1) {
        VBox vbox = new VBox(10);
        String position = "TOP_LEFT";
        Button b1 = new Button("Generate Password");
        Label l = new Label("Password Generator");
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                t1.setText(pg.GeneratePassword());
            }
        });
        vbox.getChildren().addAll(l, b1);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
