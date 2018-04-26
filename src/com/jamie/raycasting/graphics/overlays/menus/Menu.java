package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.graphics.overlays.Overlay;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu extends Overlay
{
    public List<String> options = new ArrayList<String>();
    public int optionIndex = 0;

    public Menu(int width, int height) {
        super(width, height);
    }
}
