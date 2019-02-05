package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        /* Context menu for ToDo */
        MenuItem deleteMenu = new MenuItem("Delete");
        deleteMenu.setOnAction((ActionEvent event) -> handleDeleteTask());

        MenuItem editMenu = new MenuItem("Edit");
        editMenu.setOnAction((ActionEvent event) -> handleEditTask());

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(deleteMenu);
        menu.getItems().add(editMenu);
        taskTable.setContextMenu(menu);

        //Context menu for in progress
        MenuItem ipDeleteMenu = new MenuItem("Delete");
        ipDeleteMenu.setOnAction((ActionEvent event) -> handleIpDeleteTask());

        MenuItem ipEditMenu = new MenuItem("Edit");
        ipEditMenu.setOnAction((ActionEvent event) -> handleIpEditTask());

        ContextMenu ipMenu = new ContextMenu();
        ipMenu.getItems().add(ipDeleteMenu);
        ipMenu.getItems().add(ipEditMenu);
        inProgressTable.setContextMenu(ipMenu);

        //Context menu for done
        MenuItem dDeleteMenu = new MenuItem("Delete");
        dDeleteMenu.setOnAction((ActionEvent event) -> handleDDeleteTask());

        MenuItem dEditMenu = new MenuItem("Edit");
        dEditMenu.setOnAction((ActionEvent event) -> handleDEditTask());

        ContextMenu dMenu = new ContextMenu();
        dMenu.getItems().add(dDeleteMenu);
        dMenu.getItems().add(dEditMenu);
        doneTable.setContextMenu(dMenu);
    }

    @FXML
    private void handleMoveToInProgress() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        main.getInProgressTaskData().addAll(taskTable.getSelectionModel().getSelectedItems());
        main.getTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToInProgressFromDone() {
        int selectedIndex = doneTable.getSelectionModel().getSelectedIndex();
        main.getInProgressTaskData().addAll(doneTable.getSelectionModel().getSelectedItems());
        main.getDoneTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToDone() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
        main.getDoneTaskData().addAll(inProgressTable.getSelectionModel().getSelectedItems());
        main.getInProgressTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleMoveToToDo() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
        main.getTaskData().addAll(inProgressTable.getSelectionModel().getSelectedItems());
        main.getInProgressTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        main.getTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleIpDeleteTask() {
        int selectedIndex = inProgressTable.getSelectionModel().getSelectedIndex();
       main.getInProgressTaskData().remove(selectedIndex);
    }

    @FXML
    private void handleDDeleteTask() {
        int selectedIndex = doneTable.getSelectionModel().getSelectedIndex();
        main.getDoneTaskData().remove(selectedIndex);
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

    @FXML
    private void handleSave() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".ser")) {
                file = new File(file.getPath() + ".ser");
            }
            Map<String, List> map = new HashMap<String, List>();
            map.put("tasks", new ArrayList<Task>(main.getTaskData()));
            map.put("tasksInProgress", new ArrayList<Task>(main.getInProgressTaskData()));
            map.put("tasksDone", new ArrayList<Task>(main.getDoneTaskData()));
            write(map, file.toPath());
        }

    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Serialized files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            Map<String, List> map = read(file.toPath());
            main.getTaskData().setAll(map.get("tasks"));
            main.getInProgressTaskData().setAll(map.get("tasksInProgress"));
            main.getDoneTaskData().setAll(map.get("tasksDone"));
        }

    }

    @FXML
    private void handleSaveToCSV() throws IOException{
        BufferedWriter output = null;
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".csv")) {
                file = new File(file.getPath() + ".csv");
            }
            saveToOutput(file);
        }

    }

    @FXML
    private void handleOpenFromCSV() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            String CsvFile = file.getPath();
            String FieldDelimiter = ";";

            clearLists();
            importFromOutput(CsvFile, FieldDelimiter);
        }

    }

    private void saveToOutput(File file) throws IOException{
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i<main.getTaskData().size(); i++)
            {
                Task element = main.getTaskData().get(i);
                output.write("ToDo;"+element.getTaskName()+";"+element.getTaskDescription()+";"+
                        element.getPriority()+";"+element.getExpDate()+";"+"\n");
            }
            for (int i = 0; i<main.getInProgressTaskData().size(); i++)
            {
                Task element = main.getInProgressTaskData().get(i);
                output.write("InProgress;"+element.getTaskName()+";"+element.getTaskDescription()+";"+
                        element.getPriority()+";"+element.getExpDate()+";"+"\n");
            }
            for (int i = 0; i<main.getDoneTaskData().size(); i++)
            {
                Task element = main.getDoneTaskData().get(i);
                output.write("Done;"+element.getTaskName()+";"+element.getTaskDescription()+";"+
                        element.getPriority()+";"+element.getExpDate()+";"+"\n");
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }

    private void importFromOutput(String CsvFile, String FieldDelimiter) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);

                Task task = new Task(fields[1], fields[2], fields[3],
                        fields[4]);
                if (fields[0].equals("ToDo")) main.getTaskData().add(task);
                else if (fields[0].equals("InProgress")) main.getInProgressTaskData().add(task);
                else if (fields[0].equals("Done")) main.getDoneTaskData().add(task);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearLists(){
        main.getTaskData().clear();
        main.getInProgressTaskData().clear();
        main.getDoneTaskData().clear();
    }

    private static void write(Map<String, List> tasks, Path file) {
        try {
            OutputStream fos = Files.newOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, List> read(Path file) {
        try {
            InputStream in = Files.newInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(in);
            Map<String, List> map = (HashMap<String, List>) ois.readObject();

            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, List> map2 = new HashMap<String, List>();
        return map2;
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
