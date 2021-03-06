/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Leonardo Herrera
 */

package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class ValidateItems {

    private final Alert errorMessage = new Alert(Alert.AlertType.ERROR);

    public boolean validNumber(String number){
        if(number.matches("[A-Z][-][A-Z0-9]{3}[-][A-Z0-9]{3}[-][A-Z0-9]{3}")){
            return true;
        }else {
            errorMessage.setHeaderText("Serial number error");
            errorMessage.setContentText("Serial number must be in A-XXX-XXX-XXX format\n" +
                    "A must be a letter and X can be either a letter or a digit");
            errorMessage.showAndWait();

            return false;
        }
    }

    public boolean uniqueNumber(ObservableList<Items> list, String number){
        for(Items items : list){
            if(items.getSerialNum().equals(number)){
                errorMessage.setHeaderText("Serial number error");
                errorMessage.setContentText("Serial number already exists\n" +
                        "Must be unique");
                errorMessage.showAndWait();

                return false;
            }
        }

        return true;
    }

    public boolean validName(String name){
        if(name.length() > 256 || name.length() < 2){
            errorMessage.setHeaderText("Name error");
            errorMessage.setContentText("Name must be between 2 to 256 characters in length");
            errorMessage.showAndWait();
            return false;
        }else{
            return true;
        }
    }

    public boolean validValue(String value){
        if(value.matches("[A-Za-z]") || value.isEmpty()){
            errorMessage.setHeaderText("Value error");
            errorMessage.setContentText("Enter a valid price value");
            errorMessage.showAndWait();

            return false;
        }else{
            return true;
        }
    }

    public boolean validate(String serialNum, String itemName, String itemValue){
        boolean serialNumber = validNumber(serialNum);
        boolean name = validName(itemName);
        boolean value = validValue(itemValue);

        return serialNumber && name && value;
    }
}
