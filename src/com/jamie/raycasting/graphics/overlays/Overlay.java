package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;

public abstract class Overlay extends Bitmap
{
    protected int bp = 2;
    public int opacity = 95;


    public Overlay(int width, int height) {
        super(width, height);
    }

    public void resizeOverlay() {
        setSize(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    }

    public void tick() {

    }

    public void update() {

    }
}
