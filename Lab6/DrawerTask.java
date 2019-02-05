package sample;

import javafx.concurrent.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawerTask extends Task {

    private List<NewPointListener> listeners = new ArrayList<>();
    private Random random = new Random();
    private Equation equation = new Equation();
    private int n;
    private int k = 0;

    DrawerTask(int quantity) {
        this.n=quantity;
    }

    @Override
    protected Object call() throws Exception {
        final double RANGE_MIN = -8;
        final double RANGE_MAX = 8;
        double area;

        for(int i = 0; i < n; ++i) {

            updateProgress(i, n);

            double x = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * random.nextDouble();
            double y = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * random.nextDouble();

            if(!equation.calc(x,y)) {
                for (NewPointListener hl : listeners)
                    hl.onPointCalculated(new NewPointEvent(this, x, y, true));

            }
            else {
                for (NewPointListener hl : listeners)
                    hl.onPointCalculated(new NewPointEvent(this, x, y, false));
                k++;
            }
            if(isCancelled()) break;
        }
        area = 16.0*16.0*(double)k/(double)n;

        return area;
    }

    public void addListener(NewPointListener toAdd) {
        listeners.add(toAdd);
    }
}

