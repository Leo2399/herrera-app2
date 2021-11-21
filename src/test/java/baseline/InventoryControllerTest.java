package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {

    private final Items expected = new Items("A-1C2-5GG-1K5", "PS5", "$599.99");
    private final ObservableList<Items> list = FXCollections.observableArrayList();


    @Test
    void addItem() {
        String number = "A-1C2-5GG-1K5";
        String name = "PS5";
        String value = "$599.99";

        assertEquals(number, expected.getSerialNum());
        assertEquals(name, expected.getName());
        assertEquals(value, expected.getValue());

        System.out.println(number+" = "+expected.getSerialNum());
        System.out.println(name+" = "+expected.getName());
        System.out.println(value+" = "+expected.getValue());
    }

    @Test
    void deleteItem(){

        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        list.remove(0);

        assertEquals(2, list.size());

        System.out.println("An item was removed, items left: "+list.size());
    }

    @Test
    void testMinItems(){
        int minItems = 1024;

        for(int i=0; i<minItems; i++){
            list.add(expected);
        }

        assertEquals(minItems, list.size());

        System.out.println("Total items: " +list.size());
    }

    @Test
    void testSaveToFile(){
        System.out.println(expected);
        assertEquals("A-1C2-5GG-1K5\tPS5\t$599.99", expected.toString());
    }

    @Test
    void testLoadFile() throws IOException {
        FileManagement testLoad = new FileManagement();

        testLoad.load(list, new File("C:\\temp/example.txt"));

        assertEquals(2, list.size());

        System.out.println("Size of loaded list: " +list.size());
    }
}