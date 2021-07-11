package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Tasks are saved as...
 *  Name::Description::DueDate::Status
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class ListController extends List {
    public TableColumn<ItemObject, String> ItemName;
    public TableColumn<ItemObject, String> ItemDescription;
    public TableColumn<ItemObject, String> ItemDueDate;
    public TableColumn<ItemObject, String> ItemStatus;
    public TableView<ItemObject> itemTable;
    public TextField addItemName;
    public Text pathToFile;
    public TextField addItemDescription;
    public Button loadListButton;
    public DatePicker addItemDueDate;
    public DatePicker updateDate;
    public TextField updateDescription;


    public void initialize ()    {
        File file = new File("ToDoList.txt");
        if(file.exists())   {
            pathToFile.setText(file.getAbsolutePath());
            loadHelper();
        }
    }

    @FXML
    public void addNewItemClick() throws IOException {
        //grab info from text boxes
        //if no file is loaded, create a new file
        //Call addItem in editItem class passing the current file path and the properties of the object
        //reload the table

        EditItem edit = new EditItem();

        String[] properties = new String[3];
        properties[0] = addItemName.getText();
        properties[1] = addItemDescription.getText();
        properties[2] = addItemDueDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if(pathToFile.getText().equalsIgnoreCase(""))   {
            File newFile = new File("ToDoList.txt");
            newFile.createNewFile();
            pathToFile.setText(newFile.getAbsolutePath());
        }

        edit.addItem(pathToFile.getText(), properties);
        loadHelper();

    }
    @FXML
    public void removeItemClick() throws IOException {
        //get info of selected item in list
        //Call removeItem in editItem class passing the itemObject
        //if no item is selected, throw up a message
        //reload the table

        EditItem edit = new EditItem();

        if(itemTable.getSelectionModel().getSelectedItem()!=null)    {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();
            edit.removeItem(pathToFile.getText(), selectedObject);
        }
        else   {
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("Unable to remove task!");
            noSelection.setHeaderText("No task selected!");
            noSelection.setContentText("Please select a task!");
            noSelection.show();
        }
        loadHelper();
    }

    @FXML
    public void editDescriptionClick() throws IOException {
        //call editDescription  in EditItem class passing Path, selected itemObject and the new description
        //throw up message if no item is selected
        //reload the table

        EditItem edit = new EditItem();

        if(itemTable.getSelectionModel().getSelectedItem()!=null)    {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();
            edit.editDescription(pathToFile.getText(), selectedObject, updateDescription.getText());
        }
        else   {
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("Unable to remove task!");
            noSelection.setHeaderText("No task selected!");
            noSelection.setContentText("Please select a task!");
            noSelection.show();
        }
        loadHelper();
    }

    @FXML
    public void editDueDateClick() throws IOException {
        //get new date
        //call editDueDate from EditItem class passing Path, the selected object, and the new date
        //throw up a message if no task is selected
        //reload the table

        EditItem edit = new EditItem();

        String date = updateDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(itemTable.getSelectionModel().getSelectedItem()!=null)    {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();

            edit.editDueDate(pathToFile.getText(), selectedObject, date);
        }
        else   {
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("Unable to update due date!");
            noSelection.setHeaderText("No task selected!");
            noSelection.setContentText("Please select a task!");
            noSelection.show();
        }
        loadHelper();

    }
    @FXML
    public void markCompleteClick() throws IOException {
        //call markComplete from EditItem class passing Path and the selected task from the table
        //throw up message if no task is selected
        //reload table

        EditItem edit = new EditItem();

        if(itemTable.getSelectionModel().getSelectedItem()!=null)    {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();
            edit.markComplete(pathToFile.getText(), selectedObject);
        }
        else   {
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("Unable to mark as complete!!");
            noSelection.setHeaderText("No task selected!");
            noSelection.setContentText("Please select a task!");
            noSelection.show();
        }
        loadHelper();

    }
    @FXML
    public void markIncompleteCLick() throws IOException {
        //call markIncomplete from EditItem class passing Path and selected task from table
        //throw up message if no task is selected
        //reload table

        EditItem edit = new EditItem();

        if (itemTable.getSelectionModel().getSelectedItem() != null) {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();
            edit.markIncomplete(pathToFile.getText(), selectedObject);
        } else {
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("Unable to mark as complete!!");
            noSelection.setHeaderText("No task selected!");
            noSelection.setContentText("Please select a task!");
            noSelection.show();
        }
        loadHelper();
    }

    @FXML
    public void showCompleteClick() {
        //call load getCompleteInfo from LoadList class passing the path stored in a text field
        //convert resulting arraylist into an observable arraylist
        //push data from the list to the table
        //reload table

        EditList load = new EditList();
        File file = load.loadList(pathToFile.getText());
        ArrayList<ItemObject> items = load.getCompletedInfo(file);
        ObservableList<ItemObject> observableItems = FXCollections.observableArrayList(items);

        ItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ItemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        ItemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemTable.setItems(observableItems);
    }
    @FXML
    public void showIncompleteClick() {
        //call load getIncompleteInfo from LoadList class passing the path stored in a text field
        //convert resulting arraylist into an observable arraylist
        //push data from the list to the table
        //reload table

        EditList load = new EditList();
        File file = load.loadList(pathToFile.getText());
        ArrayList<ItemObject> items = load.getIncompleteInfo(file);
        ObservableList<ItemObject> observableItems = FXCollections.observableArrayList(items);

        ItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ItemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        ItemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemTable.setItems(observableItems);
    }
    @FXML
    public void showAllClick() {
        //simply grab and show everything in txt file
        loadHelper();
    }


    @FXML
    public void loadListClick() {
        //set the in use file path to nothing to force program to change path
        pathToFile.setText("");
        loadHelper();
    }

    public void loadHelper() {
        //get current file path
        //call load from the LoadList class passing the path
        //convert resulting arraylist into observable array list
        //clear text fields
        //push data to table

        EditList load = new EditList();
        File file = load.loadList(pathToFile.getText());
        pathToFile.setText(file.getPath());
        ArrayList<ItemObject> items = load.getInfo(file);
        ObservableList<ItemObject> observableItems = FXCollections.observableArrayList(items);
        addItemName.setText("");
        addItemDueDate.setValue(null);
        addItemDescription.setText("");
        updateDate.setValue(null);
        updateDescription.setText("");
        ItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ItemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        ItemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemTable.setItems(observableItems);

    }

    public void removeAllCLick() throws IOException {
        //overwrite the file currently in use with ""
        //reload the table
        EditList remove = new EditList();
        remove.removeAll(pathToFile.getText());
        loadHelper();
    }

    public void helpButtonClick() {
        //open a simple window with text
        //included the tribute to Rey
        try   {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpPage.fxml")));
            Stage help = new Stage();
            Scene scene = new Scene(root);

            help.setScene(scene);
            help.setTitle("This help screen is brought to you by Rey");
            help.showAndWait();
        }
        catch (IOException e)   {
            e.printStackTrace();
        }
    }

    public void saveListAsClick() throws IOException {
        //have user select new place to save
        //copy current in use file to the placed they decided to save
        EditList save = new EditList();
        save.saveList(pathToFile.getText(), "");
        loadHelper();

    }
}
