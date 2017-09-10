package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Screen;
import com.jamie.raycasting.graphics.overlays.menus.Menu;

public class LoadingOverlay extends Menu {

    public void tick(Game game) {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        game.menu = null;
    }

    public void render(Screen screen, String levelname) {
        super.render(screen);

        String[] messages = { "Entering " + levelname };
        for (int y = 0; y < messages.length; y++) {
            screen.draw(messages[y], (screen.width - messages[y].length() * 6) / 2, (screen.height - messages.length * 8) / 2 + y * 8 + 1, 0x707070);
        }
    }
}
