package com.jamie.jamapp;

public abstract class Display extends Bitmap
{
    public Display(int width, int height) {
        super(width, height);
    }

    public abstract void tick();
}
