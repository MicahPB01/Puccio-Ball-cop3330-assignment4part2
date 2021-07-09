package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Files saved as
 * path
 * description
 * Name::description::duedate::0/1
 */
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.List;

public class LoadList {
    @FXML private TextField listName;
    @FXML private TableView listTable;
    public File loadList(String path) throws IOException {
        System.out.println("Entered LoadList.loadList with path: " +path);
        //clears req 19 and 20
        //create new file chooser
        //allow user to select one or more files
        //for each file, read the first three lines to get path, name, and description
        //store the path, name, and description into observable arraylist
        //show observable array list in tableview




        return null;
    }

    public ArrayList<ItemObject> getInfo(File file) throws IOException {
        System.out.println("Entered LoadList.getInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();

        return itemsInList;
    }

    public ArrayList<ItemObject> getCompletedInfo(File file) throws IOException {
        System.out.println("Entered LoadList.getCompletedInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();


        return itemsInList;
    }

    public ArrayList<ItemObject> getIncompleteInfo(File file) throws IOException {
        System.out.println("Entered LoadList.getIncompleteInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();


        return itemsInList;
    }

}
