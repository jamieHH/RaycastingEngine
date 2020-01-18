package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.items.Item;

public class HotkeyOverlay extends Overlay
{
    private String id;

    public HotkeyOverlay(String id) {
        super(16, 16);
        this.id = id;
        fill(0x404040);
    }

    public void update(Item item) {
        if (item != null) {
            draw(item.icon, 0, 0);
        } else {
            draw(id, halfWidth() - (fontWidth() / 2), halfHeight() - (fontHeight() / 2), 0x404040);
        }

        box(0, 0, width, height, 0x606060);
    }
}
