package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private ObservableList<Task> taskData = FXCollections.observableArrayList();
    private ObservableList<Task> inProgressTaskData = FXCollections.observableArrayList();
    private ObservableList<Task> doneTaskData = FXCollections.observableArrayList();

    public Main() {
        taskData.add(new Task("Rest", "opis1"));
        taskData.add(new Task("Sleep", "opis1 32"));
        taskData.add(new Task("Drink", "opis1 sefs"));
        taskData.add(new Task("Repeat", "opis1 sfdsf dsf"));
        taskData.add(new Task("Something different", "opis1 sfs sdf sfdsf sdf"));
        inProgressTaskData.add(new Task("Learn Java", "opis1"));
        inProgressTaskData.add(new Task("Show assignment", "opis2 f"));
        inProgressTaskData.add(new Task("Live", "opis3 fsd"));
        doneTaskData.add(new Task("Learn Polish", "opis23 fsd"));
        doneTaskData.add(new Task("Learn walking", "opis23 fsd"));

    }

    public ObservableList<Task> getTaskData() {
        return taskData;
    }

    public ObservableList<Task> getInProgressTaskData() {
        return inProgressTaskData;
    }

    public ObservableList<Task> getDoneTaskData() {
        return doneTaskData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        primaryStage.setTitle("Kanban Table");
        primaryStage.setScene(new Scene((Pane) loader.load(), 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setMain(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showTaskEditDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("taskEditDialog.fxml"));

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene((Pane) loader.load(), 346, 294));
            dialogStage.setResizable(false);

            TaskEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

