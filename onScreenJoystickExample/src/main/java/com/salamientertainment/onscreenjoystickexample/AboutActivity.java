package com.salamientertainment.onscreenjoystickexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by S600253 on 9/16/2015.
 */
public class AboutActivity extends Activity{

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        back = (Button) findViewById(R.id.backbutton);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"OnkyIta.ttf");
        back.setTypeface(typeFace);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
