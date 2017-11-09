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

import java.util.ArrayList;
import java.util.List;

public class Bullets
{
	private ArrayList<Ammo> ammo;

	public Bullets()
	{
		ammo = new ArrayList<Ammo>();
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	//post - draw each Ammo
	public void drawEmAll(final Canvas window)
	{
		Log.d("AMOUNT OF BULLETS","BULLET COUNT = "+ammo.size());
		for (Ammo x : ammo)
		{
			x.draw(window);
		}
	}

	public void moveEmAll()
	{
		for (Ammo x: ammo)
		{
			x.move("UP");
		}
	}

	public void cleanEmUp()
	{
		for (Ammo x : ammo)
		{
			ammo.remove(x);
		}
	}

	public ArrayList<Ammo> getList()
	{
		return ammo;
	}

	public String toString()
	{
		return "";
	}
}