package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Task;
import sample.DateUtil;

public class TaskEditDialogController {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskExpDateField;
    @FXML
    private TextField taskDescriptionField;

    @FXML
    private ComboBox<Priority> priorityComboBox;



    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        priorityComboBox.getItems().addAll(Priority.Low, Priority.Medium, Priority.Important);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setTask(Task task) {
        this.task = task;

        taskNameField.setText(task.getTaskName());
        priorityComboBox.setValue(task.getPriority());
        taskDescriptionField.setText(task.getTaskDescription());
        taskExpDateField.setText(DateUtil.format(task.getExpDate()));
        taskExpDateField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            task.setTaskName(taskNameField.getText());
            task.setPriority(priorityComboBox.getValue());
            task.setTaskDescription(taskDescriptionField.getText());
            task.setExpDate(DateUtil.parse(taskExpDateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (taskNameField.getText() == null || taskNameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        else {
            String previous = taskNameField.getText();
            if (priorityComboBox.getValue().equals(Priority.Medium)) taskNameField.setText(" ! "+previous);
            else if (priorityComboBox.getValue().equals(Priority.Important)) taskNameField.setText(" ! ! "+previous);
        }

        if (priorityComboBox.getValue() == null ) {
            errorMessage += "No valid priority! Priority should be: 'Low' / 'Medium' / 'Important'!\n";
        }
        else if (!priorityComboBox.getValue().equals(Priority.Low) && !priorityComboBox.getValue().equals(Priority.Medium)
                && !priorityComboBox.getValue().equals(Priority.Important)) {
            errorMessage += "No valid priority! Priority should be: 'Low' / 'Medium' / 'Important'!\n";
        }
        if (taskDescriptionField.getText() == null || taskDescriptionField.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }

        if (taskExpDateField.getText() == null || taskExpDateField.getText().length() == 0) {
            errorMessage += "No valid expiration date!\n";
        } else {
            if (!DateUtil.validDate(taskExpDateField.getText())) {
                errorMessage += "No valid expiration date. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
