package baseline;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;

public class Items {
    private final SimpleStringProperty serialNum;
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<BigDecimal> value;

    public Items(String serialNum, String name, BigDecimal value){
        this.serialNum = new SimpleStringProperty(serialNum);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleObjectProperty<>(value);
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

    public BigDecimal getValue() {
        return value.get();
    }

    public SimpleObjectProperty<BigDecimal> valueProperty() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value.set(value);
    }

    @Override
    public String toString(){
        return this.getSerialNum()+" "+this.getName()+" "+this.getValue();
    }
}
