package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.graphics.overlays.Overlay;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Overlay
{
    public final int textMarginX = 12;
    public final int selectedTextMarginX = 6;

    public List<String> options = new ArrayList<String>();
    public int optionIndex = 0;

    public Menu() {
        options.clear();
        options.add("Option 1");
        options.add("Option 2");
    }
}
