package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class Menu extends Overlay {

    public int pauseTime = 0;
    public int textMarginX = 12;
    public int selectedTextMarginX = 6;

    private String[] options = {
            "Option 1",
            "Option 2",
    };

    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }
    }
}
