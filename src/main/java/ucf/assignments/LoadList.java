package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Tasks are saved as...
 *  Name::Description::DueDate::Status
 */

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LoadList {
    public File loadList(String path) {
        System.out.println("Entered LoadList.loadList with path: " +path);
        //clears req 19 and 20
        //create new file chooser
        //allow user to select one or more files
        //for each file, read the first three lines to get path, name, and description
        //store the path, name, and description into observable arraylist
        //show observable array list in tableview
        File file;
        if(path.equalsIgnoreCase("")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select list to load");
            file = fileChooser.showOpenDialog(new Stage());
        }
        else   {
            file = new File(path);
        }

        return file;
    }

    public ArrayList<ItemObject> getInfo(File file) {
        System.out.println("Entered LoadList.getInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();

        String currentLine;
        String[] properties;

        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                System.out.printf("%s\n",currentLine);
                properties = currentLine.split("::");
                System.out.printf("Name: %s\nDescription: %s\nDue Date: %s\nStatus: %s\n", properties[0],properties[1],properties[2],properties[3] );
                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);
                itemsInList.add(tempItem);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored)   {

        }


        System.out.print("Here");
        return itemsInList;
    }

    public ArrayList<ItemObject> getCompletedInfo(File file) {
        System.out.println("Entered LoadList.getCompletedInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();

        String currentLine;
        String[] properties;

        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                System.out.printf("%s\n",currentLine);
                properties = currentLine.split("::");
                System.out.printf("Name: %s\nDescription: %s\nDue Date: %s\nStatus: %s\n", properties[0],properties[1],properties[2],properties[3] );
                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(properties[3].contains("Complete")) {
                    itemsInList.add(tempItem);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored)   {

        }

        return itemsInList;
    }

    public ArrayList<ItemObject> getIncompleteInfo(File file) {
        System.out.println("Entered LoadList.getIncompleteInfo");
        ArrayList<ItemObject> itemsInList = new ArrayList<>();

        String currentLine;
        String[] properties;

        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                System.out.printf("%s\n",currentLine);
                properties = currentLine.split("::");
                System.out.printf("Name: %s\nDescription: %s\nDue Date: %s\nStatus: %s\n", properties[0],properties[1],properties[2],properties[3] );
                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(properties[3].contains("Incomplete")) {
                    itemsInList.add(tempItem);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored)   {

        }

        return itemsInList;
    }

}
