package ucf.assignments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class EditItemTest {

    @Test
    void checkAddedItem() throws IOException {
        //create example to do list
        //create a txt which the test should match
        //call the addItem function passing ItemObject properties
        //once function is done, compare the text file properties to the properties passed into the function
        //test will pass if the txt file properties match the pre determined ones

        EditItem edit = new EditItem();
        File file = new File("TestFile.txt");
        file.createNewFile();
        String[] properties = new String[4];
        String[] selectedProperties = new String[4];
        String currentLine;
        selectedProperties[0] = "Test task";
        selectedProperties[1] = "Test Description";
        selectedProperties[2] = "2021/7/10";
        selectedProperties[3] = "Incomplete";

        edit.addItem("TestFile.txt", selectedProperties);
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);

            currentLine = bufferedReader.readLine();
            while(currentLine != null)   {
                properties = currentLine.split("::");

                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception ignore)   {

        }

        Assertions.assertArrayEquals(properties, selectedProperties);


    }
    @Test
    void checkRemoveItem()   {
        //create example to do list
        //create a txt which the test should match
        //call the removeItem function passing a test txt
        //once function is done, compare the newly changed txt file to a pre existing one
        //test will pass if the txt files match
    }

    @Test
    void checkEditDescription()   {
        //create example to do list
        //create a txt which the test should match
        //call the editDescription function passing a test txt
        //once function is done, compare the newly changed txt file to a pre existing one
        //test will pass if the txt files match
    }

    @Test
    void checkEditDueDate()   {
        //create example to do list
        //create a txt which the test should match
        //call the editDueDate function passing a test txt
        //once function is done, compare the newly changed txt file to a pre existing one
        //test will pass if the txt files match
    }





}