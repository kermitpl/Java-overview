package sample;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.Main;
import sample.Task;

public class Controller {

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskNameColumn;

    @FXML
    private TableView<Task> inProgressTable;
    @FXML
    private TableColumn<Task, String> taskNameinProgressColumn;

    @FXML
    private TableView<Task> doneTable;
    @FXML
    private TableColumn<Task, String> taskNameDoneColumn;


    private Main main;

    public Controller() {
    }

    @FXML
    private void initialize() {
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskNameinProgressColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskNameDoneColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());

        //Context menu for ToDo
        MenuItem deleteMenu = new MenuItem("Delete");
        deleteMenu.setOnAction((ActionEvent event) -> {
            handleDeleteTask();
        });

        MenuItem editMenu = new MenuItem("Edit");
        editMenu.setOnAction((ActionEvent event) -> {
            handleEditTask();
        });

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(deleteMenu);
        menu.getItems().add(editMenu);
        taskTable.setContextMenu(menu);

        //Context menu for in progress
        MenuItem ipDeleteMenu = new MenuItem("Delete");
        ipDeleteMenu.setOnAction((ActionEvent event) -> {
            handleIpDeleteTask();
        });

        MenuItem ipEditMenu = new MenuItem("Edit");
        ipEditMenu.setOnAction((ActionEvent event) -> {
            handleIpEditTask();
        });

        ContextMenu ipMenu = new ContextMenu();
        ipMenu.getItems().add(ipDeleteMenu);
        ipMenu.getItems().add(ipEditMenu);
        inProgressTable.setContextMenu(ipMenu);

        //Context menu for done
        MenuItem dDeleteMenu = new MenuItem("Delete");
        dDeleteMenu.setOnAction((ActionEvent event) -> {
            handleDDeleteTask();
        });

        MenuItem dEditMenu = new MenuItem("Edit");
        dEditMenu.setOnAction((ActionEvent event) -> {
            handleDEditTask();
        });

        ContextMenu dMenu = new ContextMenu();
        dMenu.getItems().add(dDeleteMenu);
        dMenu.getItems().add(dEditMenu);
        doneTable.setContextMenu(dMenu);
    }

    @FXML
    private void handleMoveToInProgress() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        main.getInProgressTaskData().addAll(taskTable.getSelectionModel().getSelectedItems());
        taskTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToInProgressFromDone() {
        int selectedIndex = doneTable.getSelectionModel().getSelectedIndex();
        main.getInProgressTaskData().addAll(doneTable.getSelectionModel().getSelectedItems());
        doneTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToDone() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
        main.getDoneTaskData().addAll(inProgressTable.getSelectionModel().getSelectedItems());
        inProgressTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToToDo() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
        main.getTaskData().addAll(inProgressTable.getSelectionModel().getSelectedItems());
        inProgressTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        taskTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleIpDeleteTask() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
        inProgressTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleDDeleteTask() {
        int selectedIndex = doneTable.getSelectionModel().getSelectedIndex();
        doneTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleAboutClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("About author");
        alert.setHeaderText("Author: ");
        alert.setContentText("Adrian Mazurek");

        alert.showAndWait();
    }

    @FXML
    private void handleNewTask() {
        Task tempTask = new Task();
        boolean okClicked = main.showTaskEditDialog(tempTask);
        if (okClicked) {
            main.getTaskData().add(tempTask);
        }
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            //boolean okClicked = main.showTaskEditDialog(selectedTask);
            main.showTaskEditDialog(selectedTask);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleIpEditTask() {
        Task selectedTask = inProgressTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            //boolean okClicked = main.showTaskEditDialog(selectedTask);
            main.showTaskEditDialog(selectedTask);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDEditTask() {
        Task selectedTask = doneTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            //boolean okClicked = main.showTaskEditDialog(selectedTask);
            main.showTaskEditDialog(selectedTask);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main = main;

        taskTable.setItems(main.getTaskData());
        inProgressTable.setItems(main.getInProgressTaskData());
        doneTable.setItems(main.getDoneTaskData());

        taskTable.getColumns().forEach(this::addTooltipToColumnCells);
    }

    private <T> void addTooltipToColumnCells(TableColumn<Task,T> column) {

        Callback<TableColumn<Task, T>, TableCell<Task,T>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<Task, T> cell = existingCellFactory.call(c);
            Tooltip tooltip = new Tooltip();
            tooltip.textProperty().bind(cell.itemProperty().asString());
            cell.setTooltip(tooltip);
            return cell ;
        });
    }

}
