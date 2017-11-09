package com.salamientertainment.onscreenjoystickexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by S600253 on 9/16/2015.
 */
public class LevelSelect extends Activity{

    private Button playButton;
    private TextView title;
    private ViewFlipper viewFlipper;
    private MediaPlayer music;
    private float lastX;
    private TextView levelonetext;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelselect);
        levelonetext = (TextView) findViewById(R.id.levelonetext);
        title =(TextView) findViewById(R.id.titlelevelselect);
        playButton = (Button) findViewById(R.id.playlevel);
        level = 1;
        Typeface typeFace=Typeface.createFromAsset(getAssets(), "OnkyIta.ttf");
        //Typeface spaceFont = Typeface.createFromAsset(getAssets(), "SpaceAge.ttf");
        //levelonetext.setTypeface(typeFace);
        title.setTypeface(typeFace);
        playButton.setTypeface(typeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelSelect.this, LevelLoading.class);
                i.putExtra("num", level);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        viewFlipper = (ViewFlipper) findViewById(R.id.levelflip);



        play();
    }

    @Override
    public void onPause(){
        super.onPause();
        music.pause();
    }

    @Override
    public void onResume(){
        super.onResume();
        music.start();
    }

    public void play(){
        Log.d("HELLO", "REACHED POINT");
        music = new MediaPlayer();
        if (music.isPlaying()){
            music.reset();
        }
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.hellmarch);
        music.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try{
            music.setDataSource(LevelSelect.this, video);
            music.prepare();
            music.setLooping(true);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        music.start();
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {

                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_from_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                    level--;
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_from_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                    level++;
                }
                break;
            }
        }
        return false;
    }

}
