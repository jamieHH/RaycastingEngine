package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.graphics.overlays.Overlay;

public abstract class Menu extends Overlay
{
    public int optionIndex = 0;
    public abstract String[] getOptions();
    public String getOption(int i) {
        return getOptions()[i];
    }

    public Menu(int width, int height) {
        super(width, height);
    }
}
