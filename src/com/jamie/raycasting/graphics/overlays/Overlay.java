package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.graphics.Screen;

public class Overlay
{
    public void render(Screen screen) {
        for (int i = 0; i < (screen.width * screen.height); i++ ) {
            screen.pixels[i] = 0;
        }
    }

}
