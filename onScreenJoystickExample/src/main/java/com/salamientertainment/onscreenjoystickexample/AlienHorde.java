package com.salamientertainment.onscreenjoystickexample;//� A+ Computer Science  -  www.apluscompsci.com

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
	private List<Alien> aliens;
	private int a = 0;

	public AlienHorde()
	{
		aliens = new ArrayList<Alien>();
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll(final Canvas window , Bitmap b)
	{
		for (Alien x : aliens)
		{
			x.draw(window, b);
		}
	}

	public void moveEmAll()
	{
		
		for (Alien x : aliens)
		{
			if (a < 301)
			{
				x.move("RIGHT");
			}
			if (a>301 && a<601)
			{
				x.move("LEFT");
			}
			if (a == 601)
			{
				a = 0;
			}
		a++;
		}
		
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
			for(int x = 0; x<aliens.size(); x++)
		{
			for(int xl=0; xl<shots.size(); xl++)
			{
				if(shots.get(xl).getX()>=aliens.get(x).getX() && shots.get(xl).getX()<=aliens.get(x).getX()+40 && shots.get(xl).getY()>=aliens.get(x).getY() && shots.get(xl).getY()<=aliens.get(x).getY()+40 )
				{
					aliens.remove(x);
					shots.remove(xl); 
				}
			}
		}
	}
			
	

	public String toString()
	{
		return "";
	}
}
