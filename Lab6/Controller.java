package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import java.awt.image.BufferedImage;


public class Controller {

    private NewPointListener newPointListener;
    private BufferedImage bi= new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    private final double RANGE_MIN = -8;
    private final double RANGE_MAX = 8;
    private int counting = 0;

    private DrawerTask task;

    @FXML
    private Canvas canvas;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button runButton;

    @FXML
    private Label resultLabel;

    @FXML
    public TextField quantityTextField;

    @FXML
    private void handleRunBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int pointsQuantity;

        try {
            int i = Integer.parseInt(quantityTextField.getText());
            pointsQuantity = i;
        }
        catch (NumberFormatException x) {
            pointsQuantity = 1000000;
            quantityTextField.setText("1000000");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Provided text was not an integer.");
            alert.setContentText("Using 100000 points.");

            alert.showAndWait();
        }

        task = new DrawerTask(pointsQuantity);
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                double area = (double) task.getValue();
                resultLabel.setText(""+area);
            }
        });
        runButton.disableProperty().bind(task.runningProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        task.addListener(newPointListener);
        new Thread(task).start();

        newPointListener = new NewPointListener() {
            @Override
            public void onPointCalculated(NewPointEvent event) {
                int a = 1;
                int b = (int)canvas.getHeight() - 1;

                int dotX = (int) ((b - a) * (event.getX() - RANGE_MIN) / (RANGE_MAX - RANGE_MIN) + a);
                int dotY = (int) ((b - a) * (event.getY() - RANGE_MIN) / (RANGE_MAX - RANGE_MIN) + a);

                dotY = (int)canvas.getHeight() - dotY;
                if(event.isInside()) {
                    bi.setRGB(dotX, dotY, 824);
                }
                else {
                    bi.setRGB(dotX, dotY, 234223424);
                }
                counting++;
                if (counting % 1000 ==0) gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
            }
        };

    }

    @FXML
    private void handleStopBtnAction(){
        task.cancel();
    }

    /*
    private void drawShapes(GraphicsContext gc)
    {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                    gc.getCanvas().getLayoutY(),
                    gc.getCanvas().getWidth(),
                    gc.getCanvas().getHeight());
    }
    */
}
