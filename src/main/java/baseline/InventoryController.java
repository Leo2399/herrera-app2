package baseline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



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
    private TableColumn<Items, Double> valueCol;

    @FXML
    void addItem(ActionEvent event) {
        // Add an item to the inventory list
        // Add the serial number
        // Add the name
        // Add the value
        // Make sure that each serial number is unique
        // Make sure a valid name is entered
        // Value must be in US dollars
    }

    @FXML
    void clearItem(ActionEvent event) {
        // Clears all existing items in the list
    }

    @FXML
    void deleteItem(ActionEvent event) {
        // Deletes a single item from the list
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
