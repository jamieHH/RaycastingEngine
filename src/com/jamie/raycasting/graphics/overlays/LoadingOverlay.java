package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;

public class LoadingOverlay extends Overlay
{
    private int showTime = 30;

    public LoadingOverlay(int width, int height, String message) {
        super(width, height);

        fill(0, 0, width, height, 0x020202);

        message = "Entering " + message;
        draw(message, (width - (message.length() * 6)) / 2, ((height - 8) / 2), 0x707070);
    }

    public void tick(Game game) {
        if (showTime > 0) {
            showTime--;
            return;
        }

        game.setActiveOverlay(null);
    }
}
