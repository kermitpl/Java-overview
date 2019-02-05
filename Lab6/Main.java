package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        primaryStage.setTitle("Integral Draw & Calc");
        primaryStage.setScene(new Scene(loader.load(),  500, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

        Controller controller = loader.getController();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
