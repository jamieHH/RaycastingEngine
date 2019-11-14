package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.Bitmap;

public abstract class Overlay extends Bitmap
{
    protected int bp = 2;
    public int opacity = 95;


    public Overlay(int width, int height) {
        super(width, height);
    }

    public void tick() {

    }

    public void update() {

    }
}
