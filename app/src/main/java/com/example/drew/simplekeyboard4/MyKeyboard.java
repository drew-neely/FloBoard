package com.example.drew.simplekeyboard4;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.view.View;


/**
 * Created by Drew on 11/25/2016.
 * Edited by Tyler and Shreyash on 11/25/2016.
 */

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {


    private KeyboardView kv;
    private Keyboard keyboard;
    AccelerationSensor accelerationSensor;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        accelerationSensor = new AccelerationSensor();
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
