package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;

public class LoadingOverlay extends Overlay
{
    private String[] messages = {""};

    public LoadingOverlay(int width, int height, String message) {
        super(width, height);

        fill(0, 0, width, height, 0x020202);

        this.messages[0] = ("Entering " + message);

        for (int y = 0; y < messages.length; y++) {
            draw(messages[y], (width - messages[y].length() * 6) / 2, (height - messages.length * 8) / 2 + y * 8 + 1, 0x707070);
        }
    }

    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        game.setActiveOverlay(null);
    }
}
