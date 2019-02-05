package com.example.adrian.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ImageView mImageView;
    private CountDownTimer countDownTimer;
    private Random rand= new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageView);
        mContext = this.getApplicationContext();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        countDownTimer = new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mSensorManager.unregisterListener(sensorListener);
                Toast.makeText(mContext, "Timeout", Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void buttonOnClickStart(View view) {
        Toast.makeText(mContext, "START", Toast.LENGTH_LONG).show();
        mSensorManager.registerListener(sensorListener, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        countDownTimer.start();
    }

    public void graphicChange() {
        int rand1 = rand.nextInt(6);
        rand1++;
        String name = "k"+rand1;
        int id = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        mImageView.setImageResource(id);
    }

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType()==Sensor.TYPE_LINEAR_ACCELERATION){
                double[] linear_acceleration = new double [3];

                linear_acceleration[0] = event.values[0];
                linear_acceleration[1] = event.values[1];
                linear_acceleration[2] = event.values[2];

                double sum = linear_acceleration[0]+linear_acceleration[1]+linear_acceleration[2];
                if (sum > 1) {
                    graphicChange();
                    if (sum > 20) graphicChange();
                    if (sum > 40) graphicChange();
                    if (sum > 60) graphicChange();
                    countDownTimer.cancel();
                    countDownTimer.start();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}
