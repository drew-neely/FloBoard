package com.example.drew.simplekeyboard4;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Arrays;

/**
 * Created by Drew on 11/26/2016.
 */

public class AccelerationSensor extends Activity implements SensorEventListener {

    private Sensor accelerometer;
    private SensorManager SM;

    public AccelerationSensor() {
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println(Arrays.toString(event.values));

    }

    @Override
    protected void onResume() {
        super.onResume();
        SM.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

}
