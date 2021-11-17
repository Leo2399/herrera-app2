package baseline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {

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
    private TableColumn<Items, String> valueCol;

    @FXML
    private TextField serialTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField serialSearch;

    @FXML
    private TextField nameSearch;

    private final ObservableList<Items> list = FXCollections.observableArrayList();
    private final ValidateItems validate = new ValidateItems();
    private final NumberFormat getCurrency = NumberFormat.getCurrencyInstance();
    private final FileChooser fileChooser = new FileChooser();

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

        // Created a filter list
        FilteredList<Items> filteredList = new FilteredList<>(list, p->true);

        // Allows user to search for an item by serial number
        serialSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(item -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            return item.getSerialNum().toLowerCase().contains(lowerCaseFilter);
        }));

        // Allow user to search for an item by name
        nameSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(item -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            return item.getName().toLowerCase().contains(lowerCaseFilter);
        }));

        SortedList<Items> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(inventory.comparatorProperty());

        inventory.setItems(sortedList);
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
    void saveToFile(ActionEvent event) throws IOException {
        // Save the list into a file
        Stage newStage = new Stage();
        fileChooser.setTitle("Save list");

        if(list.isEmpty()){
            newStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert empty = new Alert(Alert.AlertType.ERROR, "Empty table", ButtonType.OK);
            empty.setContentText("Table is empty");
        }else{
            File file = fileChooser.showSaveDialog(newStage);

            if(file!=null){
                save(inventory.getItems(), file);
            }
        }
    }

    private void save(ObservableList<Items> items, File file) throws IOException {
        try(BufferedWriter output = new BufferedWriter(new FileWriter(file))){
            for(Items i : items){
                output.write(i.toString());
                output.newLine();
            }
        }catch (IOException ex){
            Alert io = new Alert(Alert.AlertType.ERROR, "No File", ButtonType.OK);
            io.setContentText("File could not be created");
            io.showAndWait();
            if(io.getResult()==ButtonType.OK){
                io.close();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        fileChooser.setInitialDirectory(new File("C:\\temp"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML file","*.html"),
                new FileChooser.ExtensionFilter("JSON file","*.json"));

        // Sort by serial number
        inventory.getSortOrder().add(serialNumCol);

        // Sort by name
        inventory.getSortOrder().add(nameCol);

        //Sort by value
        inventory.getSortOrder().add(valueCol);
    }

}
