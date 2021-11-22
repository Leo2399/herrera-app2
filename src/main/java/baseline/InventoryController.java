/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Leonardo Herrera
 */

package baseline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {


    @FXML
    private MenuBar fileMenu;

    @FXML
    private TableView<Items> inventory;

    @FXML
    private TableColumn<Items, String> nameCol;

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

    private  ObservableList<Items> list = FXCollections.observableArrayList();
    private final ValidateItems validateItems = new ValidateItems();
    private final NumberFormat getCurrency = NumberFormat.getCurrencyInstance();
    private final FileChooser fileChooser = new FileChooser();


    @FXML
    void addItem(ActionEvent event) {
        // Add an item to the inventory list

        String serialNumber = serialTextField.getText();
        String name = nameTextField.getText();
        String value = valueTextField.getText();

        // Will check is the data is valid, if not valid appropriate error messages will pop up
        // And will not add anything to the table
        if(validateItems.validate(serialNumber, name, value) && validateItems.uniqueNumber(list, serialNumber)){

            // Converts value from text field to a decimal
            BigDecimal price = new BigDecimal(value);

            // Converts data to US dollars
            String dollars = getCurrency.format(price);

            // If valid, add the data to the table
            list.add(new Items(serialNumber, name, dollars));
            inventory.setItems(list);

            // Clears text fields to add new information
            serialTextField.clear();
            nameTextField.clear();
            valueTextField.clear();
        }

        searchList();
    }

    @FXML
    void clearItem(ActionEvent event) {
        // Clears all existing items in the list
        list.clear();
    }

    @FXML
    void deleteItem(ActionEvent event) {
        // Deletes a single item from the list
        int selected = inventory.getSelectionModel().getSelectedIndex();
        list.remove(selected);
    }

    @FXML
    void loadToList(ActionEvent event) throws IOException {
        // Load a previous list into the current list

        FileManagement loadFile = new FileManagement();

        fileChooser.setTitle("Load list");
        File file = fileChooser.showOpenDialog(new Stage());

        loadFile.load(list, file);
        inventory.setItems(list);
        searchList();
    }

    @FXML
    void saveToFile(ActionEvent event) throws IOException {
        // Save the list into a file
        FileManagement saveFile = new FileManagement();

        Stage newStage = new Stage();
        fileChooser.setTitle("Save list");

        File file = fileChooser.showSaveDialog(newStage);

        if(file.getName().contains(".txt")) {
            saveFile.save(inventory.getItems(), file);
        }else if(file.getName().contains(".json")){
            saveFile.saveJson(list, file);
        }
    }

    private void searchList(){
        // Method created to be able to search the list

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

        // Find item based on search
        inventory.setItems(sortedList);
    }

    private void editSerialNumber(){
        // Helper method when editing an item's serial number

        serialNumCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Edit serial number on double-click
        serialNumCol.setOnEditCommit(event -> {
            String newNum = event.getNewValue();
            String oldNum = event.getOldValue();

            // Validates new serial number, will display appropriate error message
            if(validateItems.validNumber(newNum) && validateItems.uniqueNumber(list, newNum)){
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setSerialNum(newNum);
            }else{
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setSerialNum(oldNum);
            }
            serialNumCol.setVisible(false);
            serialNumCol.setVisible(true);
        });
    }

    private void editName(){
        // Method to edit item's name in list

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Edit name on double-click
        nameCol.setOnEditCommit(event -> {
            String newName = event.getNewValue();
            String oldName = event.getOldValue();

            // Validates new name, if invalid an error message will display
            if(validateItems.validName(newName)){
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(newName);
            }else{
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(oldName);
            }
            nameCol.setVisible(false);
            nameCol.setVisible(true);
        });
    }

    private void editValue(){
        // Method to edit item's value in the list

        valueCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Edit value on double-click
        valueCol.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            String oldValue = event.getOldValue();

            // Validates new value, if invalid an error message will display
            if(validateItems.validValue(newValue)){
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setValue(newValue);
            }else{
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setValue(oldValue);
            }
            valueCol.setVisible(false);
            valueCol.setVisible(true);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        // Add serial number
        serialNumCol.setCellValueFactory(param -> param.getValue().serialNumProperty());

        // Add name
        nameCol.setCellValueFactory(param -> param.getValue().nameProperty());

        // Add value
        valueCol.setCellValueFactory(param -> param.getValue().valueProperty());

        // Allow editing
        inventory.setEditable(true);
        editSerialNumber();
        editName();
        editValue();

        // Sort by serial number
        inventory.getSortOrder().add(serialNumCol);

        // Sort by name
        inventory.getSortOrder().add(nameCol);

        //Sort by value
        list.sort(Comparator.comparing(Items::getValue));

        // Set initial directory and extensions
        fileChooser.setInitialDirectory(new File("C:\\temp"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TSV (tab-separated values)","*.txt"),
                new FileChooser.ExtensionFilter("HTML file","*.html"),
                new FileChooser.ExtensionFilter("JSON file","*.json"));
    }
}
