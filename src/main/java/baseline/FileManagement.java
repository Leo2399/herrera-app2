package baseline;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class FileManagement {

    public void save(ObservableList<Items> items, File file) {
        try(BufferedWriter output = new BufferedWriter(new FileWriter(file))){
            for(Items i : items){
                output.write(i.toString());
                output.newLine();
            }
        }catch (IOException ex){
            ex.getStackTrace();
        }
    }

    public void load(ObservableList<Items> list, File file) throws IOException {

        try (BufferedReader loadFile = new BufferedReader(new FileReader(file))) {
            Scanner readFile = new Scanner(loadFile);
            list.clear();

            while (readFile.hasNext()) {
                String[] info = readFile.nextLine().split("\t");
                list.add(new Items(info[0], info[1], info[2]));
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
