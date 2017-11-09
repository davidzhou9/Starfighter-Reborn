package com.salamientertainment.onscreenjoystickexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by S600253 on 9/23/2015.
 */
public interface GameLevel {
    public void doDraw(final Canvas bitmap);
    public void setBullets(ArrayList<Ammo> ammo);
}
