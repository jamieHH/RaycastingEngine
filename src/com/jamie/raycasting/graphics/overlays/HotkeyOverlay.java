package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.items.Item;

public class HotkeyOverlay extends Overlay
{
    public HotkeyOverlay() {
        super(18, 18);

        fill(0x606060);
    }

    public void update(Item item) {
        fill(1, 1, width - 1, height - 1, 0x404040);
        if (item != null) {
            draw(item.icon, 1, 1);
        }
    }
}
