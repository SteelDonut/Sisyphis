package com.example.matthew.sisyphis;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float x,y,z;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private int max;
    private boolean interval;
    private int xp;
    private int xpMax = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        max = 1000;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
        //    return;
        //}
            /*
             * record the accelerometer data, the event's timestamp as well as
             * the current time. The latter is needed so we can calculate the
             * "present" time during rendering. In this application, we need to
             * take into account how the screen is rotated with respect to the
             * sensors (which always return data in a coordinate space aligned
             * to with the screen in its native orientation).
             */

//        switch (mDisplay.getRotation()) {
//            case Surface.ROTATION_0:
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];
/*

                )                break;
            case Surface.ROTATION_90:
                x = -event.values[1];
                y = event.values[0];
                z = event.values[2];

                break;
            case Surface.ROTATION_180:
                x = -event.values[1];
                y = -event.values[0];
                z = event.values[2];

                break;
            case Surface.ROTATION_270:
                x = event.values[1];
                y = -event.values[0];
                z = event.values[2];
                break;
        }
*/
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        int t = (int)  (((z + 9.0) / 18) * 1000) ;

        TextView v = (TextView) findViewById(R.id.xyz);
        v.setText("xyz: " + x + "," + y + "," + z);

        if (interval = true) {
            if (z >= 9) {
                xp += 1;
                interval = false;
            }
        }
        else if (interval = false) {
            xp += 0;
            if (z <= -8) {
                interval = true;
            }
        }
        if (xp >= 100) {
            xp = 0;
            xpMax += 100;
        }

        TextView p = (TextView) findViewById(R.id.xp);
        p.setText("XP: " + xp + "/" + xpMax);

        bar.setProgress(t);
    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
