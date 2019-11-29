package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Client;

public class LoadingOverlay extends Overlay
{
    private int showTime = 30;

    public LoadingOverlay(int width, int height, String message) {
        super(width, height);

        fill(0x020202);

        message = "Entering " + message;
        draw(message, (width - (message.length() * 6)) / 2, ((height - 8) / 2), 0x707070);
    }

    public void tick() {
        if (showTime > 0) {
            showTime--;
            return;
        }

        Client.setGetActiveOverlay(null);
    }
}
