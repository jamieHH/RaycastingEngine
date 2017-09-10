package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

public class Overlay {
//    public int pauseTime = 0;

//    public void tick(Game game) {
//        if (pauseTime > 0) {
//            pauseTime--;
//            return;
//        }
//    }

    public void render(Screen screen) {
        for (int i = 0; i < (screen.width * screen.height); i++ ) {
            screen.pixels[i] = 0;
        }
    }

}
