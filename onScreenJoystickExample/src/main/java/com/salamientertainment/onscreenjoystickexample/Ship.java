package com.salamientertainment.onscreenjoystickexample;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Ship extends MovingThing
{
	private int speed;
	private Bitmap image;

	public Ship()
	{
		super(10,10,10,10);
		speed = 10;
		
	}

	public Ship(int x, int y)
	{
	   super(x,y,10,10);
	   speed = 10;
	   
	}

	public Ship(int x, int y, int s)
	{
	   //add code here
	   super(x,y,10,10);
	   speed = s;
	   
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			image = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ship1);
		}
		catch(Exception e)
		{
			System.out.println("swag");
		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	   
	}
	public void move(String direction)
	{
	   if (direction.equals("LEFT"))
	   {
	   	setX(getX()-getSpeed());
	   }
	   if (direction.equals("RIGHT"))
	   {
	   	setX(getX()+getSpeed());
	   }
	   if (direction.equals("UP"))
	   {
	   	setY(getY() - getSpeed());
	   }
	   if (direction.equals("DOWN"))
	   {
	   	setY(getY() + getSpeed());
	   }
	}

	public void draw( Canvas window , Bitmap b)
	{
   	//window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
		window.drawBitmap(image, getX(), getY(), null);
	}

	@Override
	public void draw(Canvas window) {

	}

	public String toString()
	{
		return super.toString() + " " +" " + getSpeed();
	}
}
