package com.jamie.raycasting.graphics.overlays;

public class HudBarOverlay extends Overlay
{
    public HudBarOverlay(int width, int height) {
        super(width, height);

        fill(0, 0, width, 1, 0x404040);
        fill(0, 1, width, height, 0x606060);
    }

    public void update(String itemName) {
        fill(0, 1, width, height, 0x606060);
        draw(itemName, width - (itemName.length() * 6) - 2, 1, 0x909090);
    }
}
