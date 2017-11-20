package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Screen;

public abstract class Overlay
{
    public int pauseTime = 0;

    public void render(Screen screen) {
        for (int i = 0; i < (screen.width * screen.height); i++ ) {
            screen.pixels[i] = 0;
        }
    }

    public void tick(Mob mob) {
        if (pauseTime > 0) {
            pauseTime--;
        }
    }

    public void tick(Game game) { // make redundant!
        if (pauseTime > 0) {
            pauseTime--;
        }
    }
}
