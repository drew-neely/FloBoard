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

public class AccelerationSensor implements SensorEventListener {

    private Sensor accelerometer;
    private SensorManager SM;

    private float accX, accY, accZ;

    public AccelerationSensor(SensorManager SM) {
        this.SM = SM;
        accelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public float getAccX() {
        return accX;
    }

    public float getAccY() {
        return accY;
    }

    public float getAccZ() {
        return accZ;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accX = event.values[0];
        accZ = event.values[2];
        accY = event.values[1];
        System.out.printf("%.2f %.2f %.2f%n", accX, accY, accZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

}
