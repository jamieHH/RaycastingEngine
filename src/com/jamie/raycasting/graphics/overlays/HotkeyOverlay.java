package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.items.Item;

public class HotkeyOverlay extends Overlay
{
    public HotkeyOverlay() {
        super(16, 16);

        fill(0x404040);
    }

    public void update(Item item) {
        if (item != null) {
            draw(item.icon, 0, 0);
        }
        fill(0, 0, width, 1, 0x606060);
        fill(0, 0, 1, height, 0x606060);
        fill(width -1, 1, width, height, 0x606060);
        fill(1, height-1, width, height, 0x606060);
    }
}
