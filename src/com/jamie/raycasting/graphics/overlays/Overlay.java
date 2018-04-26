package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Screen;

public abstract class Overlay extends Render
{
    public int pauseTime = 0;
    protected int borderPadding = 2;

    public Overlay(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
        }
    }

    public void update() {

    }
}
