package com.example.drew.simplekeyboard4;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Drew on 11/25/2016.
 * Edited by Tyler and Shreyash on 11/25/2016.
 */

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {


    private double lastTime = System.currentTimeMillis();


    private KeyboardView kv;
    private Keyboard keyboard;
    AccelerationSensor accelerationSensor = new AccelerationSensor();

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        accelerationSensor.onResume();
        return kv;
    }

    @Override
    public void onFinishInput() {
        accelerationSensor.onPause();
    }

    private void playClick(int key) {
        //ignoring sound
    }


    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        // !!! Evaluate Character
        System.out.println("Clicked");
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
