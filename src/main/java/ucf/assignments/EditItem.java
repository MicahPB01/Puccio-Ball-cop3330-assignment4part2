package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Tasks are saved as...
 *  Name::Description::DueDate::Status
 */

import javafx.fxml.FXML;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class EditItem {

    public String addItem(String path, String[] properties)   {
        //shorten description to 256 characters
        //add a line of text to txt file using item properties
        System.out.println("Entered EditItem.addItem");
        int length = 256;

        if(properties[1].length() > 256)
        {
            properties[1] = properties[1].substring(0, length);
        }
        Path filePath = Path.of(path);
        try   {
            Files.write((filePath), (properties[0] + "::" + properties[1] + "::" + properties[2] + "::Incomplete\n").getBytes(), StandardOpenOption.APPEND);
            return "Complete";
        }
        catch(Exception ignored)   {
            return "Error";
        }


    }

    public String removeItem(String path, ItemObject item) throws IOException {
        //find an entry in the txt file the matches the object the user selected
        //when the line is found call to the FileIO class

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
        //check to see if current object matches the selected object
        return o1.getName().equals(o2.getName()) && o1.getDescription().equals(o2.getDescription()) && o1.getDueDate().equals(o2.getDueDate()) && o1.getStatus().equals(o2.getStatus());
    }


    public String editDescription(String path, ItemObject item, String newDescription) throws IOException {
        //shorten new description to 256 characters
        //Find the line in the txt file which matches the selected object
        //once the correct task is found, remove the line, replacing it with the same thing except with the new description
        //call to FileIO class
        System.out.printf("Entered EditItem.editDescription\n");
        int length = 256;

        if(newDescription.length() > 256)
        {
            newDescription = newDescription.substring(0, length);
        }
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
        //Find the line in the txt file which matches the selected object
        //once the correct task is found, remove the line, replacing it with the same thing except with the new due date
        //call to FileIO class
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

    public String markIncomeplete(String path, ItemObject item) throws IOException {
        //Find the line in the txt file which matches the selected object
        //once the correct task is found, remove the line, replacing it with the same thing except with "Incomplete" and the end of the line
        //call to FileIO class
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
        //Find the line in the txt file which matches the selected object
        //once the correct task is found, remove the line, replacing it with the same thing except with "Complete" and the end of the line
        //call to FileIO class
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
