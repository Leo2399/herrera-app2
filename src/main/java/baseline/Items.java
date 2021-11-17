package baseline;

import javafx.beans.property.SimpleStringProperty;

public class Items {
    private final SimpleStringProperty serialNum;
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;

    public Items(String serialNum, String name, String value){
        this.serialNum = new SimpleStringProperty(serialNum);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
    }

    public String getSerialNum() {
        return serialNum.get();
    }

    public SimpleStringProperty serialNumProperty() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum.set(serialNum);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Override
    public String toString(){
        return this.getSerialNum()+" "+this.getName()+" "+this.getValue();
    }
}
