package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;

public class LoadingOverlay extends Overlay
{
    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        game.setActiveOverlay(null);
    }

    public void render(Screen screen, String levelName) {
        super.render(screen);

        String[] messages = { "Entering " + levelName };
        for (int y = 0; y < messages.length; y++) {
            screen.draw(messages[y], (screen.width - messages[y].length() * 6) / 2, (screen.height - messages.length * 8) / 2 + y * 8 + 1, 0x707070);
        }
    }
}
