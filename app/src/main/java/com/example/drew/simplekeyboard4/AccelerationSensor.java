package com.example.drew.simplekeyboard4;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Drew on 11/26/2016.
 */

public class AccelerationSensor implements SensorEventListener {

    private Sensor accelerometer;
    private SensorManager SM;

    private long updatePeriod = 15;
    private double speed = .005 * updatePeriod;
    private double minMoveAcc = 2;
    private double theta = 0;

    private float accX, accY, accZ;

    public AccelerationSensor(SensorManager SM) {
        this.SM = SM;
        accelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                updateTheta();
            }

        } , 0 , updatePeriod);

    }

    public void updateTheta() {
        if(Math.abs(accX) > minMoveAcc) {
            theta -= speed * (Math.abs(accX) - minMoveAcc) * accX / Math.abs(accX); 
        }
        if(theta < 0) {
            theta += 360;
        } else if(theta >= 360) {
            theta -= 360;
        }
    }

    public char determineCharacter(char[] alphabet) {

        int index = (int)((theta / 360) * alphabet.length);
        return alphabet[index];


//        float accX = (float) getAccX();
//        float maxTilt = 6;
//        float pos = maxTilt - accX;
//
//        System.out.println("pos = " + pos);
//
//        int index = (int) (pos / (maxTilt * 2) * alphabet.length);
//
//        if(index < 0) {
//            index = 0;
//        } else if(index > alphabet.length - 1) {
//            index = alphabet.length - 1;
//        }
//
//        System.out.println(index);
//
//        return alphabet[index];
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
        accY = event.values[1];
        accZ = event.values[2];
        System.out.println("Letter = " + determineCharacter(MyKeyboard.alphabets[0]) + " ::: theta = " + theta + " ::: accX = " + accX);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

}
