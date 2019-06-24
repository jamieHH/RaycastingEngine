package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.jamapp.Render;

public abstract class Overlay extends Render
{
    protected int bp = 2;


    public Overlay(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {

    }

    public void update() {

    }
}
