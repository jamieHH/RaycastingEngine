package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.overlays.Overlay;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Overlay {

    public int pauseTime = 0;
    public int textMarginX = 12;
    public int selectedTextMarginX = 6;

    public List<String> options = new ArrayList<String>();
    public int optionIndex = 0;

    public Menu() {
        options.clear();
        options.add("Option 1");
        options.add("Option 2");
    }

    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
        }
    }
}
