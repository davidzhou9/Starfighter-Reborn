package com.salamientertainment.onscreenjoystickexample;

import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S600253 on 9/23/2015.
 */

public class GameOne implements GameLevel{

    private AlienHorde a;
    public Bitmap b;
    private ArrayList<Ammo> ammo;

    public GameOne(Bitmap b){
        this.b = b;
        createAliens();
    }

    public void createAliens(){
        a = new AlienHorde();
        a.add(new Alien(100,50,150,150,3));
        a.add(new Alien(100,250,150,150,3));
        a.add(new Alien(500,50,150,150,3));
        a.add(new Alien(500,250,150,150,3));
        a.add(new Alien(900,50,150,150,3));
        a.add(new Alien(900,250,150,150,3));
        a.add(new Alien(1400,50,150,150,3));
        a.add(new Alien(1400, 250, 150, 150, 3));
    }

    @Override
    public void doDraw(final Canvas pCanvas) {
        a.moveEmAll();
        a.removeDeadOnes(ammo);
        Log.d("WINDOW_ONE", "CALLED FOR ALIEN PAINT");
        //pCanvas.drawBitmap(b, 10, 10, null);
        a.drawEmAll(pCanvas, b);
    }

    public void setBullets(ArrayList<Ammo> ammo){
        this.ammo = ammo;
    }
}