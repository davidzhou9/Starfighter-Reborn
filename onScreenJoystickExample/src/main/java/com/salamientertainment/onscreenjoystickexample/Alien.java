package com.salamientertainment.onscreenjoystickexample;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date - 
//Class -
//Lab  -

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Alien extends MovingThing
{
	private int speed;

	public Alien()
	{
		super(10,10,10,10);
		speed = 10;
	}

	public Alien(int x, int y)
	{
		//add code here
		super(x,y,10,10);
		speed = 10;
	}

	public Alien(int x, int y, int s)
	{
		//add code here
		super(x,y,10,10);
		speed = s;
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		super(x,y,w,h);
		speed=s;
	}

	public void setSpeed(int s)
	{
	   //add code
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

   public void move(String direction)
	{
	   //add code here
	   if (direction.equals("RIGHT"))
	   {
	   	setX(getX()+speed);
	   }
	   if (direction.equals("LEFT"))
	   {
	   	setX(getX()-speed);
	   }
	   
	}

	public void draw(final Canvas window, Bitmap b )
	{
   	//window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
		window.drawBitmap(b, getX(),getY(),null);
		Log.d("WINDOW_TWO", "CALLED FOR ALIEN PAINT");
	}

	@Override
	public void draw(Canvas window) {
	}

	public String toString()
	{
		return "";
	}
}
