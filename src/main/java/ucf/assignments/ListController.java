package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Files saved as
 * path
 * description
 * Name::description::duedate::0/1
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

    @FXML
    public void addNewItemClick() throws IOException {
        //Call addItem in AddItem class passing Path from selected table view
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
        //Call removeItem in RemoveItem class passing Path from selected table view
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
        //call editDescription  in EditList class passing Path from selected table view
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
        //call editItem from EditItem class passing Path from selected table view
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
        //call completeItem from EditItem class passing Path from selected table view
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
        //call incompleteItem from EditItem class passing Path from selected table view
        EditItem edit = new EditItem();
        if (itemTable.getSelectionModel().getSelectedItem() != null) {
            ItemObject selectedObject = itemTable.getSelectionModel().getSelectedItem();
            edit.markIncomeplete(pathToFile.getText(), selectedObject);
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
        //call showComplete from LoadItems class passing Path and tableView index from selected table view
        LoadList load = new LoadList();
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
        //call showIncomplete from LoadItems class passing Path and tableView index from selected table view
        LoadList load = new LoadList();
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
        loadHelper();
    }


    @FXML
    public void loadListClick() {
        pathToFile.setText("");
        loadHelper();
    }

    public void loadHelper() {
        LoadList load = new LoadList();
        File file = load.loadList(pathToFile.getText());
        pathToFile.setText(file.getPath());
        ArrayList<ItemObject> items = load.getInfo(file);
        ObservableList<ItemObject> observableItems = FXCollections.observableArrayList(items);

        ItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ItemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        ItemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemTable.setItems(observableItems);
    }

    public void removeAllCLick() throws IOException {
        new FileWriter(pathToFile.getText(), false).close();
        loadHelper();
    }

    public void helpButtonClick() {
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select list to load");

        File save = fileChooser.showSaveDialog(new Stage());
        File load = new File(pathToFile.getText());

        Files.copy(load.toPath(), save.toPath());


    }
}
