package com.jamie.jamapp;

public abstract class Display extends Render
{
    public Display(int width, int height) {
        super(width, height);
    }

    public abstract void render();
}
