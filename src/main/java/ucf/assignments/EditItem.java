package ucf.assignments;

import javafx.fxml.FXML;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Files saved as
 * path
 * description
 * Name::description::duedate::0/1
 */
public class EditItem {

    public String addItem(String path, String[] properties)   {
        //clears req 9
        //try to open file given path from tableview
        //get data from text fields name, description, and due date
        //save data to a string
        //add string to the end of the txt file
        //close txt file
        //call showAll from loadItems class passing path to update table view
        System.out.println("Entered EditItem.addItem");

        Path filePath = Path.of(path);
        try   {
            Files.write((filePath), (properties[0] + "::" + properties[1] + "::" + properties[2] + "::Incomplete\n").getBytes(), StandardOpenOption.APPEND);
        }
        catch(Exception ignored)   {

        }



        return "complete";
    }

    public String removeItem(String path, ItemObject item) throws IOException {
        //clears req 10
        //try to open file given path from tableview
        //skip to the line of the index
        //remove line
        //close txt file
        //call showAll from loadItems class passing path to update table view
        System.out.printf("Entered EditItem.removeItem\n");
        FileIO change = new FileIO();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        String newPath;
        selectedProperties[0] = item.getName();
        selectedProperties[1] = item.getDescription();
        selectedProperties[2] = item.getDueDate();
        selectedProperties[3] = item.getStatus();
        System.out.printf("Name: %s\nDescription: %s\nDue Date: %s\nStatus: %s\n", selectedProperties[0],selectedProperties[1],selectedProperties[2],selectedProperties[3] );
        ArrayList<String> newFileData = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(!matches(item, tempItem))   {
                    newFileData.add(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored) {}
        change.writeTempFile(newFileData);
        newPath = change.createTempFile();
        change.removeOldFile(path);
        change.renameFile(path, newPath);

        return "complete";
    }



    public boolean matches(ItemObject o1, ItemObject o2)   {
        return o1.getName().equals(o2.getName()) && o1.getDescription().equals(o2.getDescription()) && o1.getDueDate().equals(o2.getDueDate()) && o1.getStatus().equals(o2.getStatus());
    }


    public String editDescription(String path, ItemObject item, String newDescription) throws IOException {
        //clears req 11
        //prompt using dialog box for new description
        //open list txt file from selected tableview
        //store current line in a temp string
        //create new string based on temp string replacing the old description with the new one
        //close txt file
        //call showAll from LoadItems class to update tableview
        System.out.printf("Entered EditItem.editDescription\n");
        FileIO change = new FileIO();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        String newPath;
        selectedProperties[0] = item.getName();
        selectedProperties[1] = item.getDescription();
        selectedProperties[2] = item.getDueDate();
        selectedProperties[3] = item.getStatus();
        ArrayList<String> newFileData = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(!matches(item, tempItem))   {
                    newFileData.add(currentLine);
                }
                else   {
                    currentLine = properties[0]+"::"+newDescription+"::"+properties[2]+"::"+properties[3];
                    newFileData.add(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored) {}
        change.writeTempFile(newFileData);
        newPath = change.createTempFile();
        change.removeOldFile(path);
        change.renameFile(path, newPath);

        return "complete";
    }

    public String editDueDate(String path, ItemObject item, String newDate) throws IOException {
        //clears req 12
        //prompt using dialog box for new due date
        //open list txt file from selected tableview
        //store current line in a temp string
        //create new string based on temp string replacing the old date with the new one
        //close txt file
        //call showAll from LoadItems class to update tableview
        System.out.printf("Entered EditItem.editDescription\n");
        FileIO change = new FileIO();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        String newPath;
        selectedProperties[0] = item.getName();
        selectedProperties[1] = item.getDescription();
        selectedProperties[2] = item.getDueDate();
        selectedProperties[3] = item.getStatus();
        ArrayList<String> newFileData = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(!matches(item, tempItem))   {
                    newFileData.add(currentLine);
                }
                else   {
                    currentLine = properties[0]+"::"+properties[1]+"::"+newDate+"::"+properties[3];
                    newFileData.add(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored) {}
        change.writeTempFile(newFileData);
        newPath = change.createTempFile();
        change.removeOldFile(path);
        change.renameFile(path, newPath);


        return "complete";
    }

    public String saveList(String path)   {
        //clears req 17
        //open a file chooser for user to save as txt file
        return "complete";
    }

    public String saveAll()   {
        //clears req 18
        //open file chooser for user the save as txt file
        //use file stream to combine all txt files in dir into one txt file
        return "complete";
    }

    public String markIncomeplete(String path, ItemObject item) throws IOException {
        System.out.printf("Entered EditItem.markComplete\n");
        FileIO change = new FileIO();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        String newPath;
        selectedProperties[0] = item.getName();
        selectedProperties[1] = item.getDescription();
        selectedProperties[2] = item.getDueDate();
        selectedProperties[3] = item.getStatus();
        ArrayList<String> newFileData = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(!matches(item, tempItem))   {
                    newFileData.add(currentLine);
                }
                else   {
                    currentLine = properties[0]+"::"+properties[1]+"::"+properties[2]+"::"+"Incomplete";
                    newFileData.add(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored) {}
        change.writeTempFile(newFileData);
        newPath = change.createTempFile();
        change.removeOldFile(path);
        change.renameFile(path, newPath);

        return "complete";
    }

    public String markComplete(String path, ItemObject item) throws IOException {
        System.out.printf("Entered EditItem.markComplete\n");
        FileIO change = new FileIO();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        String newPath;
        selectedProperties[0] = item.getName();
        selectedProperties[1] = item.getDescription();
        selectedProperties[2] = item.getDueDate();
        selectedProperties[3] = item.getStatus();
        ArrayList<String> newFileData = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                ItemObject tempItem = new ItemObject(properties[0],properties[1],properties[2],properties[3]);

                if(!matches(item, tempItem))   {
                    newFileData.add(currentLine);
                }
                else   {
                    currentLine = properties[0]+"::"+properties[1]+"::"+properties[2]+"::"+"Complete";
                    newFileData.add(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignored) {}
        change.writeTempFile(newFileData);
        newPath = change.createTempFile();
        change.removeOldFile(path);
        change.renameFile(path, newPath);

        return "complete";
    }




}
