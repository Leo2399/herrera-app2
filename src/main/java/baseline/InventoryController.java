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
    private TableColumn<Items, String> valueCol;

    @FXML
    private TextField serialTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField valueTextField;

    private final ObservableList<Items> list = FXCollections.observableArrayList();
    private final ValidateItems validate = new ValidateItems();
    private final NumberFormat getCurrency = NumberFormat.getCurrencyInstance();

    @FXML
    void addItem(ActionEvent event) {
        // Add an item to the inventory list

        // Will check is the data is valid, if not valid appropriate error messages will pop up
        // And will not add anything to the table
        if(validate.validate(serialTextField.getText(), nameTextField.getText(), valueTextField.getText())){

            BigDecimal value = new BigDecimal(valueTextField.getText());

            // If valid, add the data to the table
            list.add(new Items(serialTextField.getText(), nameTextField.getText(), getCurrency.format(value)));
            inventory.setItems(list);

            // Add serial number
            serialNumCol.setCellValueFactory(param -> param.getValue().serialNumProperty());

            // Add name
            nameCol.setCellValueFactory(param -> param.getValue().nameProperty());

            // Add value
            valueCol.setCellValueFactory(param -> param.getValue().valueProperty());

            // Clears text fields to add new information
            serialTextField.clear();
            nameTextField.clear();
            valueTextField.clear();
        }
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
