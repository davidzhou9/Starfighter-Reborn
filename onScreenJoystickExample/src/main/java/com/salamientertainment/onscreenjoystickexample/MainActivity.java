

package com.salamientertainment.onscreenjoystickexample;

/*
 * Copyright (c) 2014 Ville Saarinen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.salamientertainment.view.onscreenjoystick.OnScreenJoystick;
import com.salamientertainment.view.onscreenjoystick.OnScreenJoystickListener;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;

public class MainActivity extends Activity implements SurfaceHolder.Callback{

	private Button button;
	private Button store;
	private Button tutorial;
	private Button about;
	private TextView title;
	String path;
	private MediaPlayer mp;
	private SurfaceView surfaceView;
	private SurfaceHolder holder;
	boolean pausing = false;
	public static String filepath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.angry_btn);
		store = (Button) findViewById(R.id.store);
		tutorial = (Button) findViewById(R.id.tutorial);
		about = (Button) findViewById(R.id.about);
		title = (TextView) findViewById(R.id.title);

		Typeface typeFace=Typeface.createFromAsset(getAssets(),"OnkyIta.ttf");
		button.setTypeface(typeFace);
		store.setTypeface(typeFace);
		tutorial.setTypeface(typeFace);
		about.setTypeface(typeFace);
		title.setTypeface(typeFace);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, LevelSelect.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});

		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		init();
	}

	public void init(){
		getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		holder = surfaceView.getHolder();
		holder.addCallback(this);
		holder.setFixedSize(176, 144);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mp = new MediaPlayer();
	}

	@Override
	public void onPause(){
		super.onPause();
		mp.release();
	}

	@Override
	public void onResume(){
		super.onResume();
		init();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
							   int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//mp.setDisplay(holder);
		play();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	public void play(){
		Log.d("HELLO", "REACHED POINT");
		if (mp.isPlaying()){
			mp.reset();
		}
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setDisplay(holder);


		Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
				+ R.raw.outerspace);


		try{
			mp.setDataSource(getApplicationContext(), video);
			mp.prepare();
			mp.setLooping(true);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mp.start();
	}


}
