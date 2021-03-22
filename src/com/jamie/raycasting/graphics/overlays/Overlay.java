package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;

public abstract class Overlay extends Bitmap
{
    protected final int bp = 2;
    public final int opacity = 90;


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
