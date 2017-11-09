package com.salamientertainment.onscreenjoystickexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Objects;
import java.util.Timer;

public class TestSurface extends SurfaceView implements
SurfaceHolder.Callback{

	private SurfaceHolder mHolder;
	private float mXCoordinate, mYCoordinate;
	private Bitmap mBitmap;
	private Matrix mMatrix;
	private MyThread mThread;
	private float mVelocityY;
	private float mVelocityX;
	private float mAngle;
	private int level;
	private Ship starfighter;
	private GameLevel gameLevel;
	private Bullets shots;

	public TestSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ship1);
		mThread = new MyThread();
		mHolder = getHolder();
		mHolder.addCallback(this);
		mXCoordinate = 900;
		mYCoordinate = 800;
		mMatrix = new Matrix();
		mMatrix.setTranslate(mXCoordinate, mYCoordinate);
		setZOrderOnTop(true);
		mHolder.setFormat(PixelFormat.TRANSPARENT);
		starfighter = new Ship(20,20);
		gameLevel = new GameOne(BitmapFactory.decodeResource(getResources(), R.drawable.alien1));
		//bulletShots = BitmapFactory.decodeResource(getResources(), R.drawable. )
		shots = new Bullets();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		//mThread.start();
		if(!mThread.isAlive()){
			mThread = new MyThread();
		}
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		mThread.setRunning(false);

		while (retry) {
			try {
				// code to kill Thread
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
		//mThread.pauseThread();
	}
	
	private void moveBy(final float pX, final float pY){
		if(pX != 0 || pY != 0 ){
		mXCoordinate += pX;
		mYCoordinate += pY;
		this.mMatrix.reset();
		mMatrix.setTranslate(mXCoordinate, mYCoordinate);
		this.mMatrix.postRotate(mAngle, mXCoordinate + mBitmap.getWidth(), mYCoordinate+mBitmap.getHeight());
		}
	}
	
	public void move(final float pX, final float pY){
		mVelocityX = pX;
		mVelocityY = pY;
	}
	
	public void rotate(final float pAngle){
		mAngle = pAngle;
	}

	public void setLevel(int level){
		this.level = level;
		if (level==1){
			gameLevel = new GameOne(BitmapFactory.decodeResource(getResources(), R.drawable.alien1));
		}
	}

	public void doDraw(final Canvas pCanvas) {
		pCanvas.drawBitmap(mBitmap, mMatrix, null);
		//starfighter.draw(pCanvas);
		//pCanvas.drawBitmap(b, 10,10,null);

		shots.moveEmAll();
		shots.drawEmAll(pCanvas);
		gameLevel.setBullets(shots.getList());
		gameLevel.doDraw(pCanvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		final double x = event.getX();
		final double y = event.getY();
		Log.d("SCREEN TOUCHED","SCREEN TOUCHED");
		if (x>1200 && y>700){
			shots.add(new Ammo((int) (mXCoordinate), (int) (mYCoordinate),10));

		}
		return true; //processed
	}


	private class MyThread extends Thread {

		private boolean running = false;

		@Override
		public synchronized void start() {
			running = true;
			super.start();
		}

		public void setRunning(final boolean pRunning) {
			running = pRunning;
		}

		@Override
		public void run() {
			while (running) {
					moveBy(mVelocityX, mVelocityY);
					// draw everything to the canvas
					Canvas canvas = null;
					try {
						canvas = mHolder.lockCanvas(null);
						synchronized (mHolder) {
							// reset canvas
							canvas.drawColor(Color.BLACK);
							doDraw(canvas);
						}
					} catch (Exception e) {
					} finally {
						if (canvas != null) {
							mHolder.unlockCanvasAndPost(canvas);
						}
					}
			}
		}
	}
}