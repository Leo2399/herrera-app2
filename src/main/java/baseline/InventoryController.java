package baseline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;


public class InventoryController {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private MenuBar fileMenu;

    @FXML
    private TableView<Items> inventory;

    @FXML
    private MenuItem loadFile;

    @FXML
    private TableColumn<Items, String> nameCol;

    @FXML
    private MenuItem saveFile;

    @FXML
    private TableColumn<Items, String> serialNumCol;

    @FXML
    private ComboBox<String> sortBox;

    @FXML
    private TableColumn<Items, BigDecimal> valueCol;

    @FXML
    private TextField serialTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField valueTextField;

    private final ObservableList<Items> list = FXCollections.observableArrayList();
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();

    @FXML
    void addItem(ActionEvent event) {
        // Add an item to the inventory list

        BigDecimal value = new BigDecimal(valueTextField.getText());
        currency.format(value);

        list.add(new Items(serialTextField.getText(), nameTextField.getText(), value));
        inventory.setItems(list);

        // Add the serial number
        serialNumCol.setCellValueFactory(param -> param.getValue().serialNumProperty());

        // Add the name
        nameCol.setCellValueFactory(param -> param.getValue().nameProperty());

        // Add the value
        valueCol.setCellValueFactory(param -> param.getValue().valueProperty());
        // Make sure that each serial number is unique
        // Make sure a valid name is entered
        // Value must be in US dollars
    }

    @FXML
    void clearItem(ActionEvent event) {
        // Clears all existing items in the list
        inventory.getItems().clear();
    }

    @FXML
    void deleteItem(ActionEvent event) {
        // Deletes a single item from the list
        inventory.getItems().removeAll(inventory.getSelectionModel().getSelectedItem());
    }

    @FXML
    void loadToList(ActionEvent event) {
        // Load a previous list into the current list
    }

    @FXML
    void saveToFile(ActionEvent event) {
        // Save the list into a file
    }

    @FXML
    void sortItems(ActionEvent event) {
        // Sort items by serial number, name, or value
    }

}
