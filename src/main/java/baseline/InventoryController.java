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
    private TableView<?> inventory;

    @FXML
    private MenuItem loadFile;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private MenuItem saveFile;

    @FXML
    private TableColumn<?, ?> serialNumCol;

    @FXML
    private ComboBox<?> sortBox;

    @FXML
    private TableColumn<?, ?> valueCol;

    @FXML
    void addItem(ActionEvent event) {

    }

    @FXML
    void clearItem(ActionEvent event) {

    }

    @FXML
    void deleteItem(ActionEvent event) {

    }

    @FXML
    void loadToList(ActionEvent event) {

    }

    @FXML
    void saveToFile(ActionEvent event) {

    }

    @FXML
    void sortItems(ActionEvent event) {

    }

}
