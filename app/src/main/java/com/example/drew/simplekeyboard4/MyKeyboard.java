package com.example.drew.simplekeyboard4;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Drew on 11/25/2016.
 * Edited by Tyler and Shreyash on 11/25/2016.
 */

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {


    private KeyboardView kv;
    private Keyboard keyboard;
    AccelerationSensor accelerationSensor;

    private boolean timerSet = false;

    private double timePressed;

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
        if(!timerSet) {
            Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    checkAddSpace();
                }

            } , 0 , 15);
        }
        return kv;
    }

    public void checkAddSpace() {
        if(accelerationSensor.doShake()) {
            InputConnection ic = getCurrentInputConnection();
            ic.commitText(String.valueOf(" "), 1);
        }
    }


    @Override
    public void onFinishInput() {

    }

    private void playClick(int key) {
        //ignoring sound
    }



    @Override
    public void onPress(int primaryCode) {
        timePressed= Calendar.MILLISECOND;
        System.out.println("Time Pressed:"+timePressed);
        Keyboard currentKeyboard = kv.getKeyboard();
        List<Keyboard.Key> keys = currentKeyboard.getKeys();
        kv.invalidateKey(primaryCode);//tells phone to repaint key

        for(int i = 0; i < keys.size()-1; i++ )
        {
            Keyboard.Key currentKey = keys.get(i);

            //?????codes is the unicode for the key
            if(currentKey.codes[0] == primaryCode)
            {
                currentKey.label = null;
//                currentKey.icon = getResources().getDrawable( R.mipmap.capitalkeysthree, null);//!!!!!!!!!  NEEDS FIXING ASAP  looks for Drawable to put as new keyIcon
                break; // leave the loop once you find your match
            }
        }

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
