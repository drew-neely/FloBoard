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

/**
 * Created by Drew on 11/25/2016.
 * Edited by Tyler and Shreyash on 11/25/2016.
 */

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener, SensorEventListener {


    private TextView xText, yText, zText;
    private EditText firstArray, secondArray, thirdArray, fourthArray;
    private Sensor mySensor;
    private SensorManager SM;
    private double xAcc, yAcc, zAcc;
    public double xVel, yVel, zVel;
    public double xPos, yPos, zPos;
    private double lastTime = System.currentTimeMillis();
    boolean left, right, up, down;
    String sentence = "";
    int xCount, yCount;


    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
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
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                    ic.deleteSurroundingText(1,0);
                    break;
            case Keyboard.KEYCODE_SHIFT:
                    caps = !caps;
                    keyboard.setShifted(caps);
                    kv.invalidateAllKeys();
                    break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode;
                if(Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code), 1);
        }
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X " + event.values[0]);
        xAcc =  event.values[0];
        yText.setText("Y " + event.values[1]);
        yAcc = event.values[1];
        zText.setText("Z " + event.values[2]);
        zAcc = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }
}
