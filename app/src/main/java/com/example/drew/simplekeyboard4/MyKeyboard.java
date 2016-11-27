package com.example.drew.simplekeyboard4;

import android.hardware.SensorManager;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.inputmethod.InputConnection;


/**
 * Created by Drew on 11/25/2016.
 * Edited by Tyler and Shreyash on 11/25/2016.
 */

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {


    private KeyboardView kv;
    private Keyboard keyboard;
    AccelerationSensor accelerationSensor;

    static char[][] alphabets = {{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
        {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}};

    @Override
    public View onCreateInputView() {
        System.out.println("onCreateInputViewCalled");
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        if(accelerationSensor == null) {
            accelerationSensor = new AccelerationSensor((SensorManager)getSystemService(SENSOR_SERVICE));
        }
        kv.setRotation(90);
        return kv;
    }

    @Override
    public void onFinishInput() {

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
        ic.commitText(String.valueOf(accelerationSensor.determineCharacter(alphabets[0])), 1);
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
