package user_page;

import database.*;
import utility.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import utility.PassGen;

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
            column1.setText("Name");
            TableColumn<PasswordObject, String> column2 = new TableColumn<PasswordObject, String>("password");
            column2.setCellValueFactory(new PropertyValueFactory<PasswordObject, String>("password"));
            column2.setText("Password");

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
        HBox hboxLabel = new HBox();
        HBox hboxCheckBox1 = new HBox();
        HBox hboxCheckBox2 = new HBox();

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);

        String position = "TOP_LEFT";
        Slider slider = new Slider(4, 30, 1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        Label sliderLabel = new Label();

        CheckBox letters = new CheckBox("Letters");
        letters.setSelected(true);
        CheckBox numbers = new CheckBox("Numbers");
        numbers.setSelected(true);
        CheckBox capitals = new CheckBox("Capitals");
        capitals.setSelected(true);
        CheckBox special = new CheckBox("Special");
        special.setSelected(false);
        special.setStyle("-fx-padding: 0 12 0 0;");

        sliderLabel.textProperty().bind(Bindings.format("%.0f", slider.valueProperty()));
        Button b1 = new Button("Generate Password");
        Label l = new Label("Password Generator");
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                t1.setText(pg.GeneratePassword(letters.isSelected(), capitals.isSelected(), numbers.isSelected(), special.isSelected(), (int) slider.getValue()));
            }
        });
        hboxLabel.getChildren().addAll(l, spacer1, sliderLabel);
        hboxCheckBox1.getChildren().addAll(letters, spacer2, numbers);
        hboxCheckBox2.getChildren().addAll(capitals, spacer3, special);
        vbox.getChildren().addAll(hboxLabel, slider, hboxCheckBox1, hboxCheckBox2, b1);
        vbox.setAlignment(Pos.valueOf(position.toUpperCase()));
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
