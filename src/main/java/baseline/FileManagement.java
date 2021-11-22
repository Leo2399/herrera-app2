/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Leonardo Herrera
 */

package baseline;


import com.google.gson.Gson;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class FileManagement {

    public void save(ObservableList<Items> list, File file) {
        try(BufferedWriter output = new BufferedWriter(new FileWriter(file))){
            for(Items i : list){
                output.write(i.toString());
                output.newLine();
            }
        }catch (IOException ex){
            ex.getStackTrace();
        }
    }

    public void load(ObservableList<Items> list, File file) {

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

    public void saveJson(ObservableList<Items> list, File file) throws IOException {
        // I wanted to use this method to save a Json file

        Gson gson = new Gson();
        String toString = null;
        for(Items i : list){
            toString = gson.toJson(i);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        assert toString != null;
        bw.write(toString);
        bw.close();
    }
}
