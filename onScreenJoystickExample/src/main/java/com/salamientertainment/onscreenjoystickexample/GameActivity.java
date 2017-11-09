package com.salamientertainment.onscreenjoystickexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.salamientertainment.view.onscreenjoystick.OnScreenJoystick;
import com.salamientertainment.view.onscreenjoystick.OnScreenJoystickListener;

/**
 * Created by S600253 on 9/10/2015.
 */
public class GameActivity extends Activity implements OnScreenJoystickListener{

    private TestSurface mTestSurface;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        level = getIntent().getIntExtra("num",0);
        initUI();
    }

    private void initUI() {
        ((OnScreenJoystick) findViewById(R.id.directionJoystick))
                .setJoystickListener(this);

        mTestSurface = (TestSurface) findViewById(R.id.surface);
        //mTestSurface.setLevel(level);
    }

    @Override
    public void onTouch(OnScreenJoystick pJoystick, float pX, float pY) {
        mTestSurface.move(pX * 3, -pY * 3);
        Log.d("joy", "x: " + pX + " y: " + pY);

    }
}
