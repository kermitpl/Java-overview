package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;


public class Task {
    private StringProperty taskName;
    private StringProperty taskDescription;
    private Priority priority;
    private ObjectProperty<LocalDate> expDate;

    public Task() {
        this(null, null);
    }

    public Task(String taskName, String taskDescription) {
        this.taskName = new SimpleStringProperty(taskName);
        this.taskDescription = new SimpleStringProperty(taskDescription);
        //Default
        this.priority = Priority.Low;
        this.expDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(2019, 2, 21));

    }

    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }

    public StringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription.set(taskDescription);
    }

    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        this.priority= priority;
    }

    public LocalDate getExpDate() {
        return expDate.get();
    }

    public ObjectProperty<LocalDate> expDateProperty() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate.set(expDate);
    }
}
