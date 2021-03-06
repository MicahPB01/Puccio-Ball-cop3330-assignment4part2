package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Micah Puccio-Ball
 *  Tasks are saved as...
 *  Name::Description::DueDate::Status
 */
import javafx.beans.property.SimpleStringProperty;

public class ItemObject {

    private final SimpleStringProperty description;
    private final SimpleStringProperty dueDate;
    private final SimpleStringProperty status;
    private final SimpleStringProperty name;

    public String getDescription() {
        return description.get();
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getName() {
        return name.get();
    }

    public ItemObject(String name, String description, String dueDate, String status)   {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.status = new SimpleStringProperty(status);
    }


}
