package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {

    private final Items expected = new Items("A-1C2-5GG-1K5", "PS5", "$599.99");
    private final ObservableList<Items> list = FXCollections.observableArrayList();

    @Test
    void addItem() {

        String number = expected.getSerialNum();
        String name = expected.getName();
        String value = expected.getValue();

        list.add(expected);

        assertEquals(number, list.get(0).getSerialNum());
        assertEquals(name, list.get(0).getName());
        assertEquals(value, list.get(0).getValue());

        System.out.println(number+" = "+expected.getSerialNum());
        System.out.println(name+" = "+expected.getName());
        System.out.println(value+" = "+expected.getValue());
    }

    @Test
    void deleteItem(){

        int listSize = 3;

        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        System.out.println("Number of items: "+list.size());

        list.remove(0);
        assertEquals(listSize-1, list.size());

        System.out.println("An item was removed, items left: "+list.size());
    }

    @Test
    void clearItems(){
        int listSize = 3;

        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        System.out.println("Number of items: "+list.size());
        assertEquals(listSize, list.size());

        list.clear();

        assertEquals(listSize-3, list.size());
        System.out.println("Cleared list of all the items");
    }

    @Test
    void testMinItems(){

        int minItems = 1024;

        System.out.println("Total items (before adding): "+list.size());

        for(int i=0; i<minItems; i++){
            list.add(expected);
        }

        assertEquals(minItems, list.size());

        System.out.println("Total items: " +list.size());
    }

    @Test
    void testSearchBySerialNumber(){
        ObservableList<Items> search = FXCollections.observableArrayList();
        int expectedSize = 1;

        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        for(Items i : list){
            String lowerCaseFilter = i.getSerialNum().toLowerCase();
            if(lowerCaseFilter.contains("a")){
                search.add(i);
            }
        }

        assertEquals(expectedSize, search.size());
        System.out.println("Number of items matching search bar: "+search.size());
    }

    @Test
    void testSearchByName(){
        ObservableList<Items> search = FXCollections.observableArrayList();
        int expectedSize = 2;

        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        for(Items i : list){
            String lowerCaseFilter = i.getName().toLowerCase();
            if(lowerCaseFilter.contains("p")){
                search.add(i);
            }
        }

        assertEquals(expectedSize, search.size());
        System.out.println("Number of items matching search bar: "+search.size());
    }

    @Test
    void testSaveToFile() throws IOException {
        FileManagement testSave = new FileManagement();
        list.add(new Items("A-1C2-5GG-1K5", "PS5", "$599.99"));
        list.add(new Items("B-123-456-747", "Something", "$69.00"));
        list.add(new Items("C-D84-GGG-LOL", "Please give me a C", "$1.00"));

        testSave.save(list, new File("C:\\temp/testCase.txt"));

        int expectedSize = 3;
        System.out.println("File created");

        testSave.load(list, new File("C:\\temp/testCase.txt"));
        assertEquals(expectedSize, list.size());
        System.out.println("Size of new file: "+list.size());
    }

    @Test
    void testLoadFile() {
        FileManagement testLoad = new FileManagement();

        int expected = 15;

        testLoad.load(list, new File("C:\\temp/LoadForDemo.txt"));

        assertEquals(expected, list.size());

        System.out.println("Size of loaded list: " +list.size());
    }
}