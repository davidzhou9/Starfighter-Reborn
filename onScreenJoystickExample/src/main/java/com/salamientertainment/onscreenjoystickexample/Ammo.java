package com.salamientertainment.onscreenjoystickexample;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Ammo extends MovingThing
{
	private int speed;

	public Ammo()
	{
		super(0,0,10,10);
	}

	public Ammo(int x, int y)
	{
		//add code
		super(x,y,10,10);
	}

	public Ammo(int x, int y, int s)
	{
		//add code
		super(x,y,10,10);
		speed = s;
	}

	public void setSpeed(int s)
	{
		speed =s;
	   //add code
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Canvas window, Bitmap b )
	{
		//window.setColor(Color.CYAN);
		//window.fillRect(getX(),getY(),10,10);
		//add code to draw the ammo
	}

	@Override
	public void draw(final Canvas window) {
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setStrokeWidth(100);
		window.drawRect(getX()+70, getY(), getX() + 80, getY()+10, paint);
		Log.d("SCREEN TOUCHED", "COLOR CREATED");
		//window.drawRect();
	}


	public void move(String direction)
	{
			setY(getY()-speed);

		//add code to draw the ammo
	}

	public String toString()
	{
		return "";
	}
}
