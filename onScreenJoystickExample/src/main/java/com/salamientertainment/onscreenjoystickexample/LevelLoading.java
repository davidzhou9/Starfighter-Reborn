package com.salamientertainment.onscreenjoystickexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.*;
import java.util.logging.Level;

/**
 * Created by S600253 on 9/22/2015.
 */
public class LevelLoading extends Activity {

    int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelloading);
        Intent intent = getIntent();
        final int level = intent.getIntExtra("num", 0);
        TextView text = (TextView) findViewById(R.id.loading);
        Typeface type = Typeface.createFromAsset(getAssets(), "OnkyIta.ttf");
        text.setTypeface(type);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LevelLoading.this,GameActivity.class);
                mainIntent.putExtra("num", level);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                LevelLoading.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}
